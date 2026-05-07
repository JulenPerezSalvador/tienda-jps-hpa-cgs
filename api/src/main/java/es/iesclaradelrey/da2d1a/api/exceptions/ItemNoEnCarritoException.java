package es.iesclaradelrey.da2d1a.api.exceptions;

public class ItemNoEnCarritoException extends RuntimeException {
    public ItemNoEnCarritoException(Long productoId) {
        super("El producto " + productoId + " no está en el carrito");
    }
}
