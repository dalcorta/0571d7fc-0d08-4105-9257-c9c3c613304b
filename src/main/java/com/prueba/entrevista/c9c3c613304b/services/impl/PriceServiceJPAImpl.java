package com.prueba.entrevista.c9c3c613304b.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;
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
                entity.getBrand().getId(), entity.getStart(),
                entity.getEnd(), entity.getProduct().getId(),
                entity.getPriority(), entity.getPrice(), entity.getCurrency().getCurrencyCode()))
                .collect(Collectors.toList());
    }

    @Override
    public PriceDTO findOne(long id) {
        Optional<PriceEntity> existingEntity = repository.findById(id);

        if (existingEntity.isEmpty()) {
            throw new PriceNotFoundException(id);
        }

        return existingEntity.map(entity -> new PriceDTO(entity.getId(),
                entity.getBrand().getId(), entity.getStart(),
                entity.getEnd(), entity.getProduct().getId(),
                entity.getPriority(), entity.getPrice(), entity.getCurrency().getCurrencyCode())).get();
    }

    @Override
    public Collection<PriceDTO> findBy(long productID, LocalDateTime start, LocalDateTime end, Integer priority) {
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
                entity.getBrand().getId(), entity.getStart(),
                entity.getEnd(), entity.getProduct().getId(),
                entity.getPriority(), entity.getPrice(), entity.getCurrency().getCurrencyCode()))
                .collect(Collectors.toList());
    }

    @Override
    public PriceDTO save(PriceDTO dto) {
        PriceEntity entity = new PriceEntity();
        mapper.updatePriceFromDTO(dto, entity);
        entity = repository.save(entity);
        return new PriceDTO(entity.getId(), entity.getBrand().getId(), entity.getStart(), entity.getEnd(),
                entity.getProduct().getId(), entity.getPriority(), entity.getPrice(),
                entity.getCurrency().getCurrencyCode());
    }

    @Override
    @SuppressWarnings("null")
    public PriceDTO update(long id, PriceDTO dto) {
        Optional<PriceEntity> existingEntity = repository.findById(id);

        if (existingEntity.isEmpty()) {
            throw new PriceNotFoundException(id);
        }

        mapper.updatePriceFromDTO(dto, existingEntity.get());
        repository.save(existingEntity.get());
        return new PriceDTO(existingEntity.get().getId(), existingEntity.get().getBrand().getId(),
                existingEntity.get().getStart(),
                existingEntity.get().getEnd(),
                existingEntity.get().getProduct().getId(), existingEntity.get().getPriority(),
                existingEntity.get().getPrice(),
                existingEntity.get().getCurrency().getCurrencyCode());
    }

    @Override
    public void deleteOne(long id) {
        repository.deleteById(id);
    }

}
