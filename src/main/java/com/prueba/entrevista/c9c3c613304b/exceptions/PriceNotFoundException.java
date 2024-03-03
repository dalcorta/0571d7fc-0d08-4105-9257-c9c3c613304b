package com.prueba.entrevista.c9c3c613304b.exceptions;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long id) {
        super(String.format("Could not find price %d", id));
    }

}
