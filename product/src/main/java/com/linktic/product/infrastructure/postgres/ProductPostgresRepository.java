package com.linktic.product.infrastructure.postgres;

import com.linktic.product.domain.ProductRepository;
import com.linktic.product.infrastructure.postgres.dto.Product;
import com.linktic.product.infrastructure.postgres.dto.ProductDto;
import com.linktic.product.infrastructure.postgres.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductPostgresRepository implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public ProductDto save(String name, BigDecimal price) {
        var product = productJpaRepository.save(Product.build(name, price));
        return convertToDTO(product);
    }

    @Override
    public ProductDto findByID(Integer id) {
        var product = productJpaRepository.findById(id);
        if (product.isPresent()) {
            return convertToDTO(product.get());
        } else {
            throw new ProductNotFoundException(String.format("NOT FOUND Product %s", id));
        }
    }

    @Override
    public ProductDto update(Integer id, ProductDto productDto) {
        var productSaved = productJpaRepository.save(Product.builder()
                .withId(id)
                .withName(productDto.getName())
                .withPrice(productDto.getPrice())
                .build());
        return convertToDTO(productSaved);
    }

    @Override
    public void delete(Integer id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public Page<ProductDto> list(Pageable pageable) {
        Page<Product> productPage = productJpaRepository.findAll(pageable);
        return productPage.map(this::convertToDTO);
    }

    private ProductDto convertToDTO(Product product) {
        return ProductDto.builder()
                .withId(product.getId())
                .withName(product.getName())
                .withPrice(product.getPrice())
                .build();
    }
}
