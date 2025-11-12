package com.linktic.product.infrastructure.postgres.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(setterPrefix = "with")
public class ProductDto {
    Integer id;
    String name;
    BigDecimal price;

    public static ProductDto build (String name, BigDecimal price){
        return ProductDto.builder()
                .withName(name)
                .withPrice(price)
                .build();
    }
}
