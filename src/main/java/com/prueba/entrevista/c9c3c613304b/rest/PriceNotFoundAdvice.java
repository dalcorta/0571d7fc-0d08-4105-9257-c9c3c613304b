package com.prueba.entrevista.c9c3c613304b.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.prueba.entrevista.c9c3c613304b.exceptions.PriceNotFoundException;

@ControllerAdvice
public class PriceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String priceNotFoundHandler(PriceNotFoundException ex) {
        return ex.getMessage();
    }
}
