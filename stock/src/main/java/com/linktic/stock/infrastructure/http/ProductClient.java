package com.linktic.stock.infrastructure.http;

import com.linktic.stock.infrastructure.http.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "productClient", url = "${product.url}")
public interface ProductClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/product/{id}", produces = "application/json")
    ProductResponseDto getProductById(@PathVariable("id") Integer id);
}
