package com.prueba.entrevista.c9c3c613304b.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record PriceDTO(BrandDTO brand, LocalDateTime start, LocalDateTime end, ProductDTO product,
        Integer priority, BigDecimal price, Currency currency) {

}
