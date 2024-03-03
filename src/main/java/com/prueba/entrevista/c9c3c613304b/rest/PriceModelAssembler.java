package com.prueba.entrevista.c9c3c613304b.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.prueba.entrevista.c9c3c613304b.dtos.PriceDTO;

public class PriceModelAssembler implements RepresentationModelAssembler<PriceDTO, EntityModel<PriceDTO>> {

    @Override
    public EntityModel<PriceDTO> toModel(PriceDTO entity) {
        return EntityModel.of(entity, linkTo(methodOn(PriceController.class).getOne(entity.id())).withSelfRel(),
                linkTo(methodOn(PriceController.class).getAll()).withRel("prices"),
                linkTo(methodOn(PriceController.class).getByProductID(entity.product().id(), entity.start(),
                        entity.end(), entity.priority()))
                        .withSelfRel());
    }

}
