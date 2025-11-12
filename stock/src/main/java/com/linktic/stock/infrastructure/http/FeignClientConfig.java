package com.linktic.stock.infrastructure.http;

import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignClientConfig {

    @Value("${product.api-key}")
    private String secretApiKey;

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return new ApiKeyRequestInterceptor(secretApiKey);
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(
                100, // Periodo inicial (tiempo en milisegundos para el primer reintento)
                TimeUnit.SECONDS.toMillis(1), // Periodo máximo (tiempo máximo para esperar entre reintentos)
                3 // Número máximo de reintentos (incluyendo el intento inicial)
        );
    }
}
