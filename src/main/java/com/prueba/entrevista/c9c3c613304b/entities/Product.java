package com.prueba.entrevista.c9c3c613304b.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@AllArgsConstructor
@Table(name = "product")
@EqualsAndHashCode(callSuper = false)
public class Product extends AbstractEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "description") 
    private String decription;

    @OneToMany(mappedBy = "product")
    private Set<Price> prices;

}
