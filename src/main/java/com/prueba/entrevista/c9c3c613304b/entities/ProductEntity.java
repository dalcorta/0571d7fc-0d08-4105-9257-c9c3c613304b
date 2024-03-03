package com.prueba.entrevista.c9c3c613304b.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Entity
@AllArgsConstructor
@Table(name = "product")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductEntity extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String decription;

    @OneToMany(mappedBy = "product")
    private Set<PriceEntity> prices;

}
