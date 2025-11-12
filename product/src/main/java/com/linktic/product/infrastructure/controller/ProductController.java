package com.linktic.product.infrastructure.controller;

import com.linktic.product.application.ProductProcessor;
import com.linktic.product.infrastructure.controller.dto.ProductRequestDto;
import com.linktic.product.infrastructure.controller.dto.ProductResponseDto;
import com.linktic.product.infrastructure.postgres.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductProcessor productProcessor;

    @GetMapping
    public Page<ProductResponseDto> getAll(Pageable pageable) {
        return productProcessor.list(pageable).map(this::convertToDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponseDto getById(@PathVariable(name = "id") Integer id) {
        return convertToDto(productProcessor.findByID(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponseDto save(@RequestBody ProductRequestDto productRequestDto) {
        return convertToDto(productProcessor.save(productRequestDto.getName(), productRequestDto.getPrice()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponseDto update(@PathVariable(name = "id") Integer id, @RequestBody ProductRequestDto productRequestDto) {
        return convertToDto(productProcessor.update(id, ProductDto.build(productRequestDto.getName(), productRequestDto.getPrice())));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        productProcessor.delete(id);
    }

    private ProductResponseDto convertToDto(ProductDto productDto) {
        return ProductResponseDto.builder()
                .withId(productDto.getId())
                .withName(productDto.getName())
                .withPrice(productDto.getPrice())
                .build();
    }
}
