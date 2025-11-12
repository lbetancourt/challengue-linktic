package com.linktic.stock.infrastructure.controller;

import com.linktic.stock.application.StockProcess;
import com.linktic.stock.domain.StockProduct;
import com.linktic.stock.infrastructure.controller.dto.StockRequestDto;
import com.linktic.stock.infrastructure.controller.dto.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockProcess stockProcess;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StockResponseDto getById(@PathVariable(name = "id") Integer id) {
        return convertToDto(stockProcess.getProductById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StockResponseDto saveStock(@RequestBody StockRequestDto stockRequestDto){
        return convertToDto(stockProcess.saveStock(stockRequestDto.getProductId(), stockRequestDto.getProductId()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StockResponseDto update(@PathVariable(name = "id") Integer id, @RequestBody StockRequestDto stockRequestDto) {
        return convertToDto(stockProcess.updateQuantityStock(id, stockRequestDto.getQuantity()));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        stockProcess.delete(id);
    }

    private StockResponseDto convertToDto(StockProduct stockProduct){
        return StockResponseDto.builder()
                .withProductId(stockProduct.getProductId())
                .withProductName(stockProduct.getProductName())
                .withProductPrice(stockProduct.getProductPrice())
                .withQuantity(stockProduct.getQuantity())
                .build();
    }
}
