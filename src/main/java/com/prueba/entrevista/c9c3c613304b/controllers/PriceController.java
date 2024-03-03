package com.prueba.entrevista.c9c3c613304b.controllers;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.entrevista.c9c3c613304b.records.PriceRecord;
import com.prueba.entrevista.c9c3c613304b.services.PriceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/api/v1/prices")
public class PriceController {

    private final PriceService service;

    @GetMapping
    public ResponseEntity<Collection<PriceRecord>> getAll() {
        return null;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PriceRecord> getOne(@PathVariable(name = "id") Integer id) {
        return null;
    }

    @GetMapping("/product-id/{product-id}")
    public Collection<PriceRecord> getByProductID(@PathVariable(name = "product-id") Integer productID,
            @RequestParam(name = "start-date") LocalDateTime start, @RequestParam(name = "end-date") LocalDateTime end,
            @RequestParam("priority") Integer priority) {
        return null;
    }

    @PostMapping
    public ResponseEntity<PriceRecord> postRecord(@RequestBody PriceRecord entity) {
        return null;
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<PriceRecord> putMethodName(@PathVariable(name = "id") String id, @RequestBody PriceRecord entity) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<PriceRecord> deleteOne(@PathVariable(name = "id") String id) {
        return null;
    }
    
}