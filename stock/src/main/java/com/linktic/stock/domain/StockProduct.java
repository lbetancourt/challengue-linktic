package com.linktic.stock.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(setterPrefix = "with")
public class StockProduct {
    Integer productId;
    String productName;
    BigDecimal productPrice;
    Integer quantity;
}
