package com.linktic.stock.application;

import com.linktic.stock.domain.StockProduct;

public interface StockProcess {
    StockProduct getProductById(Integer productId);

    StockProduct updateQuantityStock(Integer productId, Integer quantity);

    StockProduct saveStock(Integer productId, Integer quantity);

    void delete(Integer productId);
}
