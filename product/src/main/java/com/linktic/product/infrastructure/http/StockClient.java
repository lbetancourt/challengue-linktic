package com.linktic.product.infrastructure.http;

import com.linktic.product.infrastructure.http.dto.StockRequestDto;
import com.linktic.product.infrastructure.http.dto.StockResponseDto;
import com.linktic.product.shared.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "stockClient", url = "${stock.url}", configuration = FeignClientConfig.class)
public interface StockClient {
    @RequestMapping(method = RequestMethod.POST, value = "/api/stock", produces = "application/json")
    StockResponseDto createStockPerProduct(@RequestBody StockRequestDto stockRequestDto);
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/stock")
    void deleteStock(@PathVariable("id") Integer id);
}
