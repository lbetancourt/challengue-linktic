package com.linktic.product.application;

import com.linktic.product.domain.ProductRepository;
import com.linktic.product.infrastructure.http.StockClient;
import com.linktic.product.infrastructure.http.dto.StockRequestDto;
import com.linktic.product.infrastructure.postgres.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductUseCase implements ProductProcessor {
    private final ProductRepository productRepository;
    private final StockClient stockClient;

    @Override
    public ProductDto save(String name, BigDecimal price) {
        var product = productRepository.save(name, price);
        var stock = StockRequestDto.builder()
                .withProductId(product.getId())
                .withQuantity(0)
                .build();
        stockClient.createStockPerProduct(stock);
        return product;
    }

    @Override
    public ProductDto findByID(Integer id) {
        return productRepository.findByID(id);
    }

    @Override
    public ProductDto update(Integer id, ProductDto productDto) {
        return productRepository.update(id, productDto);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
        stockClient.deleteStock(id);
    }

    @Override
    public Page<ProductDto> list(Pageable pageable) {
        return productRepository.list(pageable);
    }
}
