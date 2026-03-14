package com.product.api.exception;

import org.springframework.http.HttpStatus;
/**
 * Esta exepcion la creamos para manejar errores de la API de forma controlada.
 */
 
public class ApiException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private HttpStatus status;
/**
 * Almacena el código de estado HTTP (400, 404, 500, etc.)
 */
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    /**
     * Constructor principal: recibe el código de error y el mensaje personalizado.
     */
    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
