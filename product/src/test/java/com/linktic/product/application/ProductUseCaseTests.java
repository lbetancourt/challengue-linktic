package com.linktic.product.application;

import com.linktic.product.domain.ProductRepository;
import com.linktic.product.infrastructure.http.StockClient;
import com.linktic.product.infrastructure.http.dto.StockRequestDto;
import com.linktic.product.infrastructure.http.dto.StockResponseDto;
import com.linktic.product.infrastructure.postgres.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ProductUseCaseTests {
    private ProductUseCase productUseCase;
    @MockitoBean
    private ProductRepository productRepository;
    @MockitoBean
    private StockClient stockClient;

    @BeforeEach
    void configure() {
        productUseCase = new ProductUseCase(productRepository, stockClient);
    }

    @Test
    void givenNameAndPrice_whenSave_thenHaveProduct() {
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        Mockito.when(productRepository.save(Mockito.anyString(), Mockito.any())).thenReturn(productDto);
        var stockResponseDto = StockResponseDto.builder()
                .withProductId(1)
                .withProductName("pan")
                .withProductPrice(BigDecimal.valueOf(19.99))
                .withQuantity(0)
                .build();
        Mockito.when(stockClient.createStockPerProduct(Mockito.any(StockRequestDto.class))).thenReturn(stockResponseDto);

        var productExpected = productUseCase.save("pan", BigDecimal.valueOf(19.99));

        Assertions.assertEquals(productExpected.getId(), productDto.getId());
        Assertions.assertEquals(productExpected.getName(), productDto.getName());
        Assertions.assertEquals(productExpected.getPrice(), productDto.getPrice());
    }

    @Test
    void givenIdProduct_whenFindByID_thenHaveProduct() {
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        Mockito.when(productRepository.findByID(Mockito.anyInt())).thenReturn(productDto);

        var productExpected = productUseCase.findByID(1);

        Assertions.assertEquals(productExpected.getId(), productDto.getId());
        Assertions.assertEquals(productExpected.getName(), productDto.getName());
        Assertions.assertEquals(productExpected.getPrice(), productDto.getPrice());
    }

    @Test
    void givenIdProductAndProduct_whenUpdate_thenHaveProduct() {
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();

        Mockito.when(productRepository.update(Mockito.anyInt(), Mockito.any())).thenReturn(productDto);

        var productToSend = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();

        var productExpected = productUseCase.update(1, productToSend);

        Assertions.assertEquals(productExpected.getId(), productDto.getId());
        Assertions.assertEquals(productExpected.getName(), productDto.getName());
        Assertions.assertEquals(productExpected.getPrice(), productDto.getPrice());
    }

    @Test
    void givenIdProduct_whenDelete_thenDeleted() {
        Mockito.doNothing().when(productRepository).delete(Mockito.anyInt());
        Mockito.doNothing().when(stockClient).deleteStock(Mockito.anyInt());

        productUseCase.delete(1);

        verify(productRepository, times(1)).delete(Mockito.anyInt());
        verify(stockClient, times(1)).deleteStock(Mockito.anyInt());
    }

    @Test
    void givenProducts_whenList_thenListProduct() {
        Pageable pageable = PageRequest.of(0, 1);
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        List<ProductDto> listProducts = List.of(productDto);
        Page<ProductDto> productPageable = new PageImpl<>(listProducts, pageable, 1);
        Mockito.when(productRepository.list(Mockito.any())).thenReturn(productPageable);

        var productsExpected = productUseCase.list(pageable);

        Assertions.assertEquals(productsExpected.getTotalElements(), productPageable.getTotalElements());
    }
}
