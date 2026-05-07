package es.iesclaradelrey.da2d1a.api.controllers;

import es.iesclaradelrey.da2d1a.api.dto.AddToCartDTO;
import es.iesclaradelrey.da2d1a.api.dto.CartDTO;
import es.iesclaradelrey.da2d1a.api.dto.CartItemDTO;
import es.iesclaradelrey.da2d1a.api.exceptions.*;
import es.iesclaradelrey.da2d1a.api.mappers.CartMapper;
import es.iesclaradelrey.da2d1a.common.entities.ItemCarrito;
import es.iesclaradelrey.da2d1a.common.entities.Producto;
import es.iesclaradelrey.da2d1a.common.entities.UsuarioRegistrado;
import es.iesclaradelrey.da2d1a.common.services.IItemCarritoService;
import es.iesclaradelrey.da2d1a.common.services.IProductoService;
import es.iesclaradelrey.da2d1a.common.services.IUsuarioRegistradoService;
import es.iesclaradelrey.da2d1a.security.userdetails.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired private IItemCarritoService carritoService;
    @Autowired private IProductoService productoService;
    @Autowired private IUsuarioRegistradoService usuarioService;
    @Autowired private CartMapper cartMapper;

    private Long getUserId(Authentication auth) {
        return ((UsuarioDetails) auth.getPrincipal()).getId();
    }

    private CartDTO buildCartDTO(Long usuarioId) {
        List<ItemCarrito> items = carritoService.findByUsuarioId(usuarioId);
        List<CartItemDTO> itemDTOs = items.stream()
                .map(cartMapper::toDto)
                .toList();

        CartDTO dto = new CartDTO();
        dto.setItems(itemDTOs);
        dto.setNumProductosDistintos(carritoService.countProductosByUsuarioId(usuarioId));
        dto.setNumUnidadesTotales(carritoService.sumUnidadesByUsuarioId(usuarioId));
        dto.setImporteTotal(carritoService.calcularImporteTotalByUsuarioId(usuarioId));
        return dto;
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart(Authentication auth) {
        return ResponseEntity.ok(buildCartDTO(getUserId(auth)));
    }

    @PostMapping
    public ResponseEntity<CartDTO> addToCart(@RequestBody AddToCartDTO dto, Authentication auth) {
        Long usuarioId = getUserId(auth);

        Producto producto = productoService.findById(dto.getProductoId())
                .orElseThrow(() -> new ProductoNotFoundException(dto.getProductoId()));

        if (dto.getUnidades() == null || dto.getUnidades() <= 0) {
            throw new UnidadesInvalidasException();
        }

        if (producto.getStock() < dto.getUnidades()) {
            throw new StockInsuficienteException(dto.getProductoId(), producto.getStock());
        }

        Optional<ItemCarrito> existente =
                carritoService.findByUsuarioIdAndProductoId(usuarioId, dto.getProductoId());

        if (existente.isPresent()) {
            ItemCarrito item = existente.get();
            item.setUnidades(item.getUnidades() + dto.getUnidades());
            item.setFechaActualizacion(LocalDateTime.now());
            carritoService.save(item);
        } else {
            UsuarioRegistrado usuario = usuarioService.findById(usuarioId).orElseThrow();
            ItemCarrito nuevo = new ItemCarrito();
            nuevo.setUsuario(usuario);
            nuevo.setProducto(producto);
            nuevo.setUnidades(dto.getUnidades());
            nuevo.setFechaActualizacion(LocalDateTime.now());
            carritoService.save(nuevo);
        }

        return ResponseEntity.ok(buildCartDTO(usuarioId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<CartDTO> removeFromCart(@PathVariable Long productId, Authentication auth) {
        Long usuarioId = getUserId(auth);

        productoService.findById(productId)
                .orElseThrow(() -> new ProductoNotFoundException(productId));

        carritoService.findByUsuarioIdAndProductoId(usuarioId, productId)
                .orElseThrow(() -> new ItemNoEnCarritoException(productId));

        carritoService.deleteByUsuarioIdAndProductoId(usuarioId, productId);
        return ResponseEntity.ok(buildCartDTO(usuarioId));
    }

    @DeleteMapping
    public ResponseEntity<CartDTO> clearCart(Authentication auth) {
        Long usuarioId = getUserId(auth);
        carritoService.deleteByUsuarioId(usuarioId);
        return ResponseEntity.ok(buildCartDTO(usuarioId));
    }
}
