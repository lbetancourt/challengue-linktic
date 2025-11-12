package com.linktic.stock.domain;

import com.linktic.stock.infrastructure.postgres.dto.StockDto;

public interface StockRepository {
    StockDto findByProductId(Integer productId);
    StockDto updateQuantity(Integer productId, Integer quantity);
    StockDto save(Integer productId, Integer quantity);
    void delete(Integer productId);
}
