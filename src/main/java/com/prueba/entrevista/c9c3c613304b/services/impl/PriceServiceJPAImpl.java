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
import com.prueba.entrevista.c9c3c613304b.repositories.PriceRepository;
import com.prueba.entrevista.c9c3c613304b.services.PriceService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceServiceJPAImpl implements PriceService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final PriceRepository repository;

    @Override
    public Collection<PriceDTO> findAll() {
        return repository.findAll().stream().map(entity -> new PriceDTO(entity.getId(),
                new BrandDTO(entity.getBrand().getId(), entity.getBrand().getDescription()), entity.getStart(),
                entity.getEnd(), new ProductDTO(entity.getProduct().getId(), entity.getProduct().getDecription()),
                entity.getPriority(), entity.getPrice(), entity.getCurrency())).collect(Collectors.toList());
    }

    @Override
    public Optional<PriceDTO> findOne(Long id) {
        return repository.findById(id).map(entity -> new PriceDTO(entity.getId(),
                new BrandDTO(entity.getBrand().getId(), entity.getBrand().getDescription()), entity.getStart(),
                entity.getEnd(), new ProductDTO(entity.getProduct().getId(), entity.getProduct().getDecription()),
                entity.getPriority(), entity.getPrice(), entity.getCurrency()));
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
    public void save(PriceDTO entity) {
        repository.save(new PriceEntity().setId(entity.id()).setBrand(new BrandEntity().setId(entity.brand().id()))
                .setStart(entity.start()).setEnd(entity.end()).setPriority(entity.priority()).setPrice(entity.price())
                .setCurrency(entity.currency()));
    }

    @Override
    public void deleteOne(Long id) {
        repository.deleteById(id);
    }

}
