package com.linktic.stock.application;

import com.linktic.stock.domain.StockRepository;
import com.linktic.stock.infrastructure.http.ProductClient;
import com.linktic.stock.infrastructure.http.dto.ProductResponseDto;
import com.linktic.stock.infrastructure.postgres.dto.StockDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class StockUseCaseTests {
    private StockUseCase stockUseCase;
    @MockitoBean
    private StockRepository stockRepository;
    @MockitoBean
    private ProductClient productClient;

    @BeforeEach
    void configure() {
        stockUseCase = new StockUseCase(stockRepository, productClient);
    }

    @Test
    void givenIdProduct_whenFindByID_thenHaveStockProduct() {
        var productResponse = ProductResponseDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        var stock = StockDto.builder()
                .withProductId(1)
                .withQuantity(1)
                .build();
        Mockito.when(productClient.getProductById(Mockito.anyInt())).thenReturn(productResponse);
        Mockito.when(stockRepository.findByProductId(Mockito.anyInt())).thenReturn(stock);

        var stockProduct = stockUseCase.getProductById(1);

        Assertions.assertEquals(stockProduct.getProductId(), productResponse.getId());
        Assertions.assertEquals(stockProduct.getProductName(), productResponse.getName());
        Assertions.assertEquals(stockProduct.getProductPrice(), productResponse.getPrice());
        Assertions.assertEquals(stockProduct.getQuantity(), stock.getQuantity());
    }

    @Test
    void givenIdProductAndQuantity_whenUpdateQuantityStock_thenHaveStockProduct() {
        var stock = StockDto.builder()
                .withProductId(1)
                .withQuantity(1)
                .build();
        var productResponse = ProductResponseDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        Mockito.when(stockRepository.findByProductId(Mockito.anyInt())).thenReturn(stock);
        Mockito.when(stockRepository.updateQuantity(Mockito.anyInt(), Mockito.anyInt())).thenReturn(stock);
        Mockito.when(productClient.getProductById(Mockito.anyInt())).thenReturn(productResponse);

        var stockProduct = stockUseCase.updateQuantityStock(1, 1);

        Assertions.assertEquals(stockProduct.getProductId(), productResponse.getId());
        Assertions.assertEquals(stockProduct.getProductName(), productResponse.getName());
        Assertions.assertEquals(stockProduct.getProductPrice(), productResponse.getPrice());
        Assertions.assertEquals(stockProduct.getQuantity(), stock.getQuantity());
    }

    @Test
    void givenIdProductAndQuantity_whenSaveStock_thenHaveStockProduct() {
        var stock = StockDto.builder()
                .withProductId(1)
                .withQuantity(1)
                .build();
        Mockito.when(stockRepository.save(Mockito.anyInt(),Mockito.anyInt())).thenReturn(stock);

        var stockProduct = stockUseCase.saveStock(1,1);

        Assertions.assertEquals(stockProduct.getProductId(), stock.getProductId());
        Assertions.assertEquals(stockProduct.getQuantity(), stock.getQuantity());
    }

    @Test
    void givenIdProduct_whenDelete_thenDelete() {
        Mockito.doNothing().when(stockRepository).delete(Mockito.anyInt());

        stockUseCase.delete(1);

        verify(stockRepository, times(1)).delete(Mockito.anyInt());
    }
}
