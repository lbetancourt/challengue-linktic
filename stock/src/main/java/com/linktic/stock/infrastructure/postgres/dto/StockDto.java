package com.linktic.stock.infrastructure.postgres.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class StockDto {
    Integer productId;
    Integer quantity;
}
