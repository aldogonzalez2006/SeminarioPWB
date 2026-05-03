package com.product.exception;

import org.springframework.dao.DataAccessException;

public class DBAccessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // Agrega este constructor para que el Service pueda usarlo sin pasarle nada
    public DBAccessException() {
        super("Error de acceso a la base de datos");
    }

    public DBAccessException(DataAccessException e) {
        super("Error de acceso a la base de datos");
    }

    public DBAccessException(String message) {
        super(message);
    }
}