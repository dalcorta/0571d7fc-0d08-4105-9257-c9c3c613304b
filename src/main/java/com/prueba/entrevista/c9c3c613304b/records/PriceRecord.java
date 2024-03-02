package com.prueba.entrevista.c9c3c613304b.records;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record PriceRecord(BrandRecord brand, LocalDateTime start, LocalDateTime end, ProductRecord product,
        Integer priority, BigDecimal price, Currency currency) {

}
