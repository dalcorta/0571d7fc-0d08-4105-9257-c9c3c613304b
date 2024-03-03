package com.prueba.entrevista.c9c3c613304b.services;

import java.time.LocalDateTime;
import java.util.Collection;

import com.prueba.entrevista.c9c3c613304b.records.PriceRecord;
import com.prueba.entrevista.c9c3c613304b.records.ProductRecord;

public interface PriceService {

    Collection<PriceRecord> findAll();

    PriceRecord findOne(Integer id);

    Collection<PriceRecord> findBy(Integer productID, LocalDateTime start, LocalDateTime end, Integer priority);

    void save(ProductRecord entity);

    void deleteOne(Integer id);

}