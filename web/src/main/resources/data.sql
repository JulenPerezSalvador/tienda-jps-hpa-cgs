-- =========================================================
-- 1. INSERTAR MARCAS
-- =========================================================
INSERT INTO marca (nombre) VALUES ('Zara');           -- ID 1
INSERT INTO marca (nombre) VALUES ('Levi''s');        -- ID 2
INSERT INTO marca (nombre) VALUES ('Nike');           -- ID 3
INSERT INTO marca (nombre) VALUES ('North Face');     -- ID 4
INSERT INTO marca (nombre) VALUES ('Massimo Dutti');  -- ID 5
INSERT INTO marca (nombre) VALUES ('Adidas');         -- ID 6
INSERT INTO marca (nombre) VALUES ('Mango');          -- ID 7
INSERT INTO marca (nombre) VALUES ('Marca Sin Productos'); -- ID 8 (Requisito: Marca sin productos)

-- =========================================================
-- 2. INSERTAR CATEGORÍAS
-- =========================================================
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Camisetas', 'Textil basico de algodon', NULL); -- ID 1
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Pantalones', 'Vaqueros y chinos', NULL);       -- ID 2
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Abrigos', 'Prendas de exterior', NULL);          -- ID 3
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Accesorios', 'Complementos varios', NULL);    -- ID 4
INSERT INTO categoria (nombre, descripcion, imagen) VALUES ('Categoria Vacia', 'Sin productos aun', NULL);             -- ID 5 (Requisito: Categoria sin productos)
-- =========================================================
-- 3. INSERTAR PRODUCTOS
-- =========================================================
-- Producto 1
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen)
VALUES ('8412345000010', 'Sudadera Premium Negra', 'Esta es una sudadera de alta gama diseñada para ofrecer la maxima comodidad y durabilidad. Fabricada con materiales sostenibles de algodon organico, su interior cepillado garantiza calidez durante los meses mas frios del año. Cuenta con acabados reforzados en puños y cintura para asegurar que la prenda mantenga su forma original tras muchos lavados. Ideal para un look casual pero sofisticado en cualquier ocasion urbana.', 59.99, 15, 1, NULL);

-- Producto 2
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen)
VALUES ('8412345000027', 'Pantalon Vaquero 501', 'El clasico pantalon vaquero de corte recto que nunca pasa de moda. Fabricado con denim resistente de alta calidad.', 89.90, 0, 2, NULL);

-- Producto 3
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen)
VALUES ('8412345000034', 'Zapatillas Air Running', 'Zapatillas deportivas diseñadas especificamente para corredores de larga distancia que buscan la maxima amortiguacion y respuesta en cada zancada. Incorpora una malla transpirable que permite una ventilacion optima del pie, reduciendo la fatiga termica. La suela de goma de alta resistencia ofrece un agarre superior en superficies tanto secas como mojadas, garantizando la seguridad del atleta en todo momento.', 120.00, 10, 3, NULL);

-- Producto 4
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen)
VALUES ('8412345000041', 'Chaqueta Explorer', 'Chaqueta tecnica preparada para las expediciones mas exigentes. Su tejido impermeable y cortavientos protege contra los elementos mas adversos de la naturaleza, manteniendo el calor corporal gracias a su forro termico avanzado. Dispone de multiples bolsillos sellados para evitar la entrada de humedad y una capucha ajustable compatible con casco. Una inversion segura para los amantes de la montaña.', 199.00, 20, 4, NULL);

-- Producto 5 a 20
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000058', 'Camisa Lino Blanca', 'Camisa ligera de lino natural para verano.', 45.00, 0, 5, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000065', 'Camiseta Logo Sport', 'Camiseta de algodon con logo frontal.', 25.00, 5, 3, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000072', 'Chaqueta Bomber', 'Chaqueta estilo militar con forro naranja.', 65.00, 0, 1, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000089', 'Pantalon Chino Beige', 'Pantalon de vestir casual en algodon.', 40.00, 10, 5, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000096', 'Gorra Running Night', 'Gorra con detalles reflectantes para correr.', 15.00, 0, 3, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000102', 'Jersey Punto Fino', 'Jersey de cuello redondo en lana merina.', 55.00, 0, 5, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000119', 'Abrigo Lana Largo', 'Abrigo elegante para ocasiones formales.', 150.00, 30, 7, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000126', 'Shorts Vaqueros', 'Pantalones cortos de mezclilla.', 30.00, 0, 2, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000133', 'Camiseta Tirantes', 'Ideal para gimnasio y entrenamiento.', 18.00, 0, 6, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000140', 'Cinturon Cuero', 'Accesorio de piel autentica marron.', 35.00, 0, 5, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000157', 'Bufanda Cachemir', 'Suavidad extrema para el invierno.', 45.00, 15, 7, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000164', 'Calcetines Pack 3', 'Calcetines deportivos de alto rendimiento.', 12.00, 0, 3, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000171', 'Parka Impermeable', 'Parka larga con capucha de pelo sintetico.', 110.00, 0, 1, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000188', 'Polo Pique', 'Polo clasico de manga corta.', 38.00, 10, 6, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000195', 'Gafas de Sol Urban', 'Proteccion UV con montura de policarbonato.', 25.00, 0, 1, NULL);
INSERT INTO producto (codigo_ean, nombre, descripcion, precio, descuento, marca_id, imagen) VALUES ('8412345000201', 'Mochila Outdoor', 'Mochila de 20 litros resistente al agua.', 45.00, 0, 4, NULL);

-- Producto 1 en varias categorías (Camisetas y Abrigos)
INSERT INTO productos_categorias (producto_id, categoria_id) VALUES (1, 1);
INSERT INTO productos_categorias (producto_id, categoria_id) VALUES (1, 3);

-- Producto 2 en una sola categoría (Pantalones)
INSERT INTO productos_categorias (producto_id, categoria_id) VALUES (2, 2);

-- Otros productos en categorías individuales
INSERT INTO productos_categorias (producto_id, categoria_id) VALUES (3, 4);
INSERT INTO productos_categorias (producto_id, categoria_id) VALUES (4, 3);
INSERT INTO productos_categorias (producto_id, categoria_id) VALUES (5, 1);
INSERT INTO productos_categorias (producto_id, categoria_id) VALUES (6, 1);
