package com.linktic.stock.infrastructure.http.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(setterPrefix = "with")
public class ProductResponseDto {
    Integer id;
    String name;
    BigDecimal price;
}
