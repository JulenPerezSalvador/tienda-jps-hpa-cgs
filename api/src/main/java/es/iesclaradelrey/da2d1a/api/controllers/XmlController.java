package es.iesclaradelrey.da2d1a.api.controllers;

import es.iesclaradelrey.da2d1a.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.common.entities.Marca;
import es.iesclaradelrey.da2d1a.common.entities.Producto;
import es.iesclaradelrey.da2d1a.common.repositories.ICategoriaRepository;
import es.iesclaradelrey.da2d1a.common.repositories.IMarcaRepository;
import es.iesclaradelrey.da2d1a.common.services.IProductoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/xml")
public class XmlController {

    @Autowired private IProductoService productoService;
    @Autowired private ICategoriaRepository categoriaRepository;
    @Autowired private IMarcaRepository marcaRepository;

    @GetMapping
    public void exportarXml(HttpServletResponse response) throws Exception {
        List<Producto> productos = productoService.findAll();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("products");
        doc.appendChild(root);

        for (Producto p : productos) {
            Element prod = doc.createElement("product");
            root.appendChild(prod);

            appendElement(doc, prod, "id",          String.valueOf(p.getId()));
            appendElement(doc, prod, "codigoEan",   p.getCodigoEan());
            appendElement(doc, prod, "nombre",      p.getNombre());
            appendElement(doc, prod, "descripcion", p.getDescripcion());
            appendElement(doc, prod, "precio",      String.valueOf(p.getPrecio()));
            appendElement(doc, prod, "descuento",   String.valueOf(p.getDescuento()));
            appendElement(doc, prod, "stock",       String.valueOf(p.getStock()));

            if (p.getMarca() != null) {
                Element marca = doc.createElement("marca");
                appendElement(doc, marca, "id",     String.valueOf(p.getMarca().getId()));
                appendElement(doc, marca, "nombre", p.getMarca().getNombre());
                prod.appendChild(marca);
            }

            Element cats = doc.createElement("categorias");
            for (Categoria c : p.getCategorias()) {
                Element cat = doc.createElement("categoria");
                appendElement(doc, cat, "id",     String.valueOf(c.getId()));
                appendElement(doc, cat, "nombre", c.getNombre());
                cats.appendChild(cat);
            }
            prod.appendChild(cats);
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm"));
        String filename = "products-export." + timestamp + ".xml";

        response.setContentType("application/xml;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(doc), new StreamResult(response.getOutputStream()));
    }

    private void appendElement(Document doc, Element parent, String tag, String value) {
        Element el = doc.createElement(tag);
        el.setTextContent(value != null ? value : "");
        parent.appendChild(el);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> importarXml(@RequestParam("productsfile") MultipartFile file) {
        try (InputStream is = file.getInputStream()) {

            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxFactory.newSAXParser();

            List<ProductoImportData> datos = new ArrayList<>();
            saxParser.parse(is, new ProductoSaxHandler(datos));

            for (ProductoImportData d : datos) {
                Marca marca = marcaRepository.findById(d.marcaId)
                        .orElseThrow(() -> {
                            ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                                    HttpStatus.NOT_FOUND,
                                    "Marca no encontrada con id: " + d.marcaId);
                            pd.setTitle("Marca no encontrada");
                            return new MarcaNotFoundException(pd);
                        });

                List<Categoria> categorias = new ArrayList<>();
                for (Long catId : d.categoriaIds) {
                    Categoria cat = categoriaRepository.findById(catId)
                            .orElseThrow(() -> {
                                ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                                        HttpStatus.NOT_FOUND,
                                        "Categoría no encontrada con id: " + catId);
                                pd.setTitle("Categoría no encontrada");
                                return new CategoriaNotFoundException(pd);
                            });
                    categorias.add(cat);
                }

                Producto nuevo = new Producto();
                nuevo.setCodigoEan(d.codigoEan);
                nuevo.setNombre(d.nombre);
                nuevo.setDescripcion(d.descripcion);
                nuevo.setPrecio(d.precio);
                nuevo.setDescuento(d.descuento);
                nuevo.setStock(d.stock);
                nuevo.setMarca(marca);
                nuevo.setCategorias(categorias);
                productoService.save(nuevo);
            }

            return ResponseEntity.ok().body("Importados " + datos.size() + " productos correctamente.");

        } catch (MarcaNotFoundException | CategoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar el XML: " + e.getMessage());
        }
    }

    static class ProductoImportData {
        String codigoEan, nombre, descripcion;
        Double precio;
        Integer descuento = 0, stock = 10;
        Long marcaId;
        List<Long> categoriaIds = new ArrayList<>();
    }

    static class ProductoSaxHandler extends DefaultHandler {
        private final List<ProductoImportData> datos;
        private ProductoImportData current;
        private boolean inMarca = false, inCategoria = false;
        private Long currentCatId;
        private StringBuilder text = new StringBuilder();

        ProductoSaxHandler(List<ProductoImportData> datos) { this.datos = datos; }

        @Override
        public void startElement(String uri, String local, String qName, Attributes attrs) {
            text = new StringBuilder();
            switch (qName) {
                case "product"   -> current = new ProductoImportData();
                case "marca"     -> inMarca = true;
                case "categoria" -> { inCategoria = true; currentCatId = null; }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            text.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String local, String qName) {
            String val = text.toString().trim();
            if (current == null) return;

            if (inMarca) {
                if ("id".equals(qName)) current.marcaId = Long.parseLong(val);
                if ("marca".equals(qName)) inMarca = false;
            } else if (inCategoria) {
                if ("id".equals(qName)) currentCatId = Long.parseLong(val);
                if ("categoria".equals(qName)) {
                    if (currentCatId != null) current.categoriaIds.add(currentCatId);
                    inCategoria = false;
                }
            } else {
                switch (qName) {
                    case "codigoEan"   -> current.codigoEan   = val;
                    case "nombre"      -> current.nombre      = val;
                    case "descripcion" -> current.descripcion = val;
                    case "precio"      -> current.precio      = Double.parseDouble(val);
                    case "descuento"   -> current.descuento   = Integer.parseInt(val);
                    case "stock"       -> current.stock       = Integer.parseInt(val);
                    case "product"     -> datos.add(current);
                }
            }
        }
    }

    static class MarcaNotFoundException extends RuntimeException {
        private final ProblemDetail pd;
        MarcaNotFoundException(ProblemDetail pd) { super(pd.getDetail()); this.pd = pd; }
        ProblemDetail getProblemDetail() { return pd; }
    }

    static class CategoriaNotFoundException extends RuntimeException {
        private final ProblemDetail pd;
        CategoriaNotFoundException(ProblemDetail pd) { super(pd.getDetail()); this.pd = pd; }
        ProblemDetail getProblemDetail() { return pd; }
    }
}
