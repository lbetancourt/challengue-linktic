package com.linktic.product.infrastructure.controller;

import com.linktic.product.application.ProductProcessor;
import com.linktic.product.infrastructure.postgres.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = ProductController.class)
class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ProductProcessor productProcessor;

    @Test
    void givenProducts_whenList_thenListProduct() throws Exception {
        Pageable pageable = PageRequest.of(0, 1);
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        List<ProductDto> listProducts = List.of(productDto);
        Page<ProductDto> productPageable = new PageImpl<>(listProducts, pageable, 1);
        Mockito.when(productProcessor.list(Mockito.any())).thenReturn(productPageable);

        var result = mockMvc.perform(get("/api/product"));

        result.andExpect(status().isOk());
    }

    @Test
    void givenIdProduct_whenFindByID_thenHaveProduct() throws Exception {
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        Mockito.when(productProcessor.findByID(Mockito.anyInt())).thenReturn(productDto);

        var result = mockMvc.perform(get("/api/product/1"));

        result.andExpect(status().isOk());
    }

    @Test
    void givenNameAndPrice_whenSave_thenHaveProduct() throws Exception {
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        Mockito.when(productProcessor.save(Mockito.anyString(), Mockito.any())).thenReturn(productDto);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"pan\", \"price\": 19.99}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenIdProductAndProduct_whenUpdate_thenHaveProduct() throws Exception {
        var productDto = ProductDto.builder()
                .withId(1)
                .withName("pan")
                .withPrice(BigDecimal.valueOf(19.99))
                .build();
        Mockito.when(productProcessor.update(Mockito.anyInt(), Mockito.any())).thenReturn(productDto);

        mockMvc.perform(put("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\":\"pan\", \"price\": 19.99}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void givenIdProduct_whenDelete_thenDeleted() throws Exception {
        Mockito.doNothing().when(productProcessor).delete(Mockito.anyInt());

        mockMvc.perform(delete("/api/product/1"))
                .andExpect(status().isOk());
    }
}
