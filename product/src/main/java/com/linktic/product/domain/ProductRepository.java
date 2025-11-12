package com.linktic.product.domain;

import com.linktic.product.infrastructure.postgres.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductRepository {
    ProductDto save(String name, BigDecimal price);

    ProductDto findByID(Integer id);

    ProductDto update(Integer id, ProductDto productDto);

    void delete(Integer id);

    Page<ProductDto> list(Pageable pageable);
}
