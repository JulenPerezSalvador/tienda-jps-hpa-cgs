package es.iesclaradelrey.da2d1a.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProductoNotFound(ProductoNotFoundException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Producto no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pd);
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<ProblemDetail> handleStockInsuficiente(StockInsuficienteException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        pd.setTitle("Stock insuficiente");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(pd);
    }

    @ExceptionHandler(UnidadesInvalidasException.class)
    public ResponseEntity<ProblemDetail> handleUnidadesInvalidas(UnidadesInvalidasException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        pd.setTitle("Unidades inválidas");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pd);
    }

    @ExceptionHandler(ItemNoEnCarritoException.class)
    public ResponseEntity<ProblemDetail> handleItemNoEnCarrito(ItemNoEnCarritoException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        pd.setTitle("Producto no está en el carrito");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(pd);
    }
}
