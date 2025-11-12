package com.linktic.stock.infrastructure.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(setterPrefix = "with")
public class StockResponseDto {
    Integer productId;
    String productName;
    BigDecimal productPrice;
    Integer quantity;
}
