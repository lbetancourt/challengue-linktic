package com.linktic.stock.infrastructure.postgres;

import com.linktic.stock.domain.StockRepository;
import com.linktic.stock.infrastructure.postgres.dto.Stock;
import com.linktic.stock.infrastructure.postgres.dto.StockDto;
import com.linktic.stock.infrastructure.postgres.exception.StockNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPostgresRepository implements StockRepository {

    private final StockJpaRepository stockJpaRepository;

    @Override
    public StockDto findByProductId(Integer productId) {
        var stock = stockJpaRepository.findById(productId);
        if (stock.isPresent()) {
            return convertToDTO(stock.get());
        } else {
            throw new StockNotFoundException(String.format("NOT FOUND Stock per product %s", productId));
        }
    }

    @Override
    public StockDto updateQuantity(Integer productId, Integer quantity) {
        var stock = findByProductId(productId);
        var stockNew = Stock.builder()
                .withProductId(stock.getProductId())
                .withQuantity(quantity)
                .build();
        return convertToDTO(stockJpaRepository.save(stockNew));
    }

    @Override
    public StockDto save(Integer productId, Integer quantity) {
        var stock = Stock.builder()
                .withProductId(productId)
                .withQuantity(quantity)
                .build();
        return convertToDTO(stockJpaRepository.save(stock));
    }

    @Override
    public void delete(Integer productId) {
        stockJpaRepository.deleteById(productId);
    }

    private StockDto convertToDTO(Stock stock) {
        return StockDto.builder()
                .withProductId(stock.getProductId())
                .withQuantity(stock.getQuantity())
                .build();
    }
}
