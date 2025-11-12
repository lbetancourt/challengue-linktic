package com.linktic.stock.application;

import com.linktic.stock.domain.StockProduct;
import com.linktic.stock.domain.StockRepository;
import com.linktic.stock.infrastructure.http.ProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockUseCase implements StockProcess {

    private final StockRepository stockRepository;
    private final ProductClient productClient;

    @Override
    public StockProduct getProductById(Integer productId) {
        var product = productClient.getProductById(productId);
        var stock = stockRepository.findByProductId(productId);
        return StockProduct.builder()
                .withProductId(product.getId())
                .withProductName(product.getName())
                .withProductPrice(product.getPrice())
                .withQuantity(stock.getQuantity())
                .build();
    }

    @Override
    public StockProduct updateQuantityStock(Integer productId, Integer quantity) {
        var stockNow = stockRepository.findByProductId(productId);
        var stockUpdated = stockRepository.updateQuantity(productId, quantity);
        var product = productClient.getProductById(productId);
        if (stockNow.getQuantity() < quantity) {
            log.info("La cantidad aumento {}", productId);
        } else if (stockNow.getQuantity() > quantity){
            log.info("La cantidad disminuyo {}", productId);
        }else{
            log.info("La cantidad siguio igual {}", productId);
        }
        return StockProduct.builder()
                .withProductId(product.getId())
                .withProductName(product.getName())
                .withProductPrice(product.getPrice())
                .withQuantity(stockUpdated.getQuantity())
                .build();
    }

    @Override
    public StockProduct saveStock(Integer productId, Integer quantity) {
        var stock= stockRepository.save(productId, quantity);
        return StockProduct.builder()
                .withProductId(stock.getProductId())
                .withQuantity(stock.getQuantity())
                .build();
    }

    @Override
    public void delete(Integer productId) {

    }
}
