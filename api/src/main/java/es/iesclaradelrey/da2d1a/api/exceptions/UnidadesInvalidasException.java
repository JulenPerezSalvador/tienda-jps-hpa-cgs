package es.iesclaradelrey.da2d1a.api.exceptions;

public class UnidadesInvalidasException extends RuntimeException {
    public UnidadesInvalidasException() {
        super("Las unidades deben ser mayores que cero");
    }
}
