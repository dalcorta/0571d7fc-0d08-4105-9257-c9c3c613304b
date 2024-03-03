package com.prueba.entrevista.c9c3c613304b.services;

import java.time.LocalDateTime;
import java.util.Collection;

import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;

public interface PriceService {

    Collection<PriceDTO> findAll();

    PriceDTO findOne(Long id);

    Collection<PriceDTO> findBy(Long productID, LocalDateTime start, LocalDateTime end, Integer priority);

    void save(PriceDTO dto);

    void update(Long id, PriceDTO dto);

    void deleteOne(Long id);

}