package com.prueba.entrevista.c9c3c613304b.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@AllArgsConstructor
@Table(name = "price")
@EqualsAndHashCode(callSuper = false)
public class Price extends AbstractEntity{

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "start_date")
    private LocalDateTime start;

    @Column(name = "end_date")
    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "curr")
    private Currency currency;

}
