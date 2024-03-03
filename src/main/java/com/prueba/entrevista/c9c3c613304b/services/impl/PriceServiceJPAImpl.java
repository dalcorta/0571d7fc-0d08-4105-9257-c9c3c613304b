package com.prueba.entrevista.c9c3c613304b.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prueba.entrevista.c9c3c613304b.dtos.BrandDTO;
import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;
import com.prueba.entrevista.c9c3c613304b.dtos.ProductDTO;
import com.prueba.entrevista.c9c3c613304b.entities.BrandEntity;
import com.prueba.entrevista.c9c3c613304b.entities.PriceEntity;
import com.prueba.entrevista.c9c3c613304b.exceptions.PriceNotFoundException;
import com.prueba.entrevista.c9c3c613304b.repositories.PriceRepository;
import com.prueba.entrevista.c9c3c613304b.services.PriceService;
import com.prueba.entrevista.c9c3c613304b.util.PriceMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PriceServiceJPAImpl implements PriceService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final PriceRepository repository;

    private final PriceMapper mapper;

    @Override
    public Collection<PriceDTO> findAll() {
        return repository.findAll().stream().map(entity -> new PriceDTO(entity.getId(),
                new BrandDTO(entity.getBrand().getId(), entity.getBrand().getDescription()), entity.getStart(),
                entity.getEnd(), new ProductDTO(entity.getProduct().getId(), entity.getProduct().getDecription()),
                entity.getPriority(), entity.getPrice(), entity.getCurrency())).collect(Collectors.toList());
    }

    @Override
    public PriceDTO findOne(Long id) {
        Optional<PriceEntity> existingEntity = repository.findById(id);

        if (existingEntity.isEmpty()) {
            throw new PriceNotFoundException(id);
        }
        
        return existingEntity.map(entity -> new PriceDTO(entity.getId(),
                new BrandDTO(entity.getBrand().getId(), entity.getBrand().getDescription()), entity.getStart(),
                entity.getEnd(), new ProductDTO(entity.getProduct().getId(), entity.getProduct().getDecription()),
                entity.getPriority(), entity.getPrice(), entity.getCurrency())).get();
    }

    @Override
    public Collection<PriceDTO> findBy(Long productID, LocalDateTime start, LocalDateTime end, Integer priority) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PriceEntity> query = cb.createQuery(PriceEntity.class);
        Root<PriceEntity> root = query.from(PriceEntity.class);
        query.select(root);

        Predicate predicate = cb.equal(root.get("product_id"), productID);

        if (start != null) {
            Predicate startPredicate = cb.equal(root.get("start_date"), start);
            predicate = cb.and(predicate, startPredicate);
        }

        if (end != null) {
            Predicate endPredicate = cb.equal(root.get("end_date"), end);
            predicate = cb.and(predicate, endPredicate);
        }

        if (priority != null) {
            Predicate priorityPredicate = cb.equal(root.get("priority"), priority);
            predicate = cb.and(predicate, priorityPredicate);
        }

        query.where(predicate);
        return entityManager.createQuery(query).getResultStream().map(entity -> new PriceDTO(entity.getId(),
                new BrandDTO(entity.getBrand().getId(), entity.getBrand().getDescription()), entity.getStart(),
                entity.getEnd(), new ProductDTO(entity.getProduct().getId(), entity.getProduct().getDecription()),
                entity.getPriority(), entity.getPrice(), entity.getCurrency())).collect(Collectors.toList());
    }

    @Override
    public void save(PriceDTO dto) {
        repository.save(new PriceEntity().setId(dto.id()).setBrand(new BrandEntity().setId(dto.brand().id()))
                .setStart(dto.start()).setEnd(dto.end()).setPriority(dto.priority()).setPrice(dto.price())
                .setCurrency(dto.currency()));
    }

    @Override
    public void update(Long id, PriceDTO dto) {
        Optional<PriceEntity> existingEntity = repository.findById(id);

        if (existingEntity.isEmpty()) {
            throw new PriceNotFoundException(id);
        }

        mapper.updatePriceFromDTO(dto, existingEntity.get());
        repository.save(existingEntity.get());
    }

    @Override
    public void deleteOne(Long id) {
        repository.deleteById(id);
    }

}
