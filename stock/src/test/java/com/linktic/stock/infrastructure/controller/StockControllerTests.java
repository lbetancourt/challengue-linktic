package com.linktic.stock.infrastructure.controller;

import com.linktic.stock.application.StockProcess;
import com.linktic.stock.domain.StockProduct;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
@ContextConfiguration(classes = StockController.class)
public class StockControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private StockProcess stockProcess;

    @Test
    void givenStock_whenGetById_thenOk() throws Exception {
        var stockProduct = StockProduct.builder()
                .withProductId(1)
                .withProductName("pan")
                .withProductPrice(BigDecimal.valueOf(19.99))
                .withQuantity(1)
                .build();
        Mockito.when(stockProcess.getProductById(Mockito.anyInt())).thenReturn(stockProduct);

        var result = mockMvc.perform(get("/api/stock/1"));

        result.andExpect(status().isOk());
    }

    @Test
    void givenStock_whenSaveStock_thenOk() throws Exception {
        var stockProduct = StockProduct.builder()
                .withProductId(1)
                .withProductName("pan")
                .withProductPrice(BigDecimal.valueOf(19.99))
                .withQuantity(1)
                .build();
        Mockito.when(stockProcess.saveStock(Mockito.anyInt(),Mockito.anyInt())).thenReturn(stockProduct);

        mockMvc.perform(post("/api/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": 1, \"quantity\": 1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenStock_whenUpdate_thenOk() throws Exception {
        var stockProduct = StockProduct.builder()
                .withProductId(1)
                .withProductName("pan")
                .withProductPrice(BigDecimal.valueOf(19.99))
                .withQuantity(1)
                .build();
        Mockito.when(stockProcess.updateQuantityStock(Mockito.anyInt(),Mockito.anyInt())).thenReturn(stockProduct);

        mockMvc.perform(put("/api/stock/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\": 1, \"quantity\": 1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenIdProduct_whenDelete_thenDeleted() throws Exception {
        Mockito.doNothing().when(stockProcess).delete(Mockito.anyInt());

        mockMvc.perform(delete("/api/stock/1"))
                .andExpect(status().isOk());
    }
}
