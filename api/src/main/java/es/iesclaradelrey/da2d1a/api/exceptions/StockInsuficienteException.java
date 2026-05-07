package es.iesclaradelrey.da2d1a.api.exceptions;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(Long productoId, int disponible) {
        super("Stock insuficiente para el producto " + productoId +
              ". Stock disponible: " + disponible);
    }
}
