package com.prueba.entrevista.c9c3c613304b.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;
import com.prueba.entrevista.c9c3c613304b.dtos.ProductDTO;
import com.prueba.entrevista.c9c3c613304b.repositories.PriceRepository;
import com.prueba.entrevista.c9c3c613304b.services.PriceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceServiceJPAImpl implements PriceService {

    private final PriceRepository repository;

    @Override
    public Collection<PriceDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public PriceDTO findOne(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public Collection<PriceDTO> findBy(Long productID, LocalDateTime start, LocalDateTime end, Integer priority) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public void save(ProductDTO entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteOne(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOne'");
    }

}
