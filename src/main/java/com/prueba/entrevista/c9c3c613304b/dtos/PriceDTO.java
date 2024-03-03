package com.prueba.entrevista.c9c3c613304b.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDTO(Long id, Long brandID, LocalDateTime start, LocalDateTime end, Long productID,
                Integer priority, BigDecimal price, String currency) {

}
