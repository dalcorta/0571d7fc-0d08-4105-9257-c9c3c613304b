package com.prueba.entrevista.c9c3c613304b.services;

import java.time.LocalDateTime;
import java.util.Collection;

import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;

public interface PriceService {

    Collection<PriceDTO> findAll();

    PriceDTO findOne(long id);

    Collection<PriceDTO> findBy(long productID, LocalDateTime start, LocalDateTime end, Integer priority);

    PriceDTO save(PriceDTO dto);

    PriceDTO update(long id, PriceDTO dto);

    void deleteOne(long id);

}