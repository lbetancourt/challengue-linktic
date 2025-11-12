package com.linktic.product.infrastructure.postgres.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal price;

    public static Product build(String name, BigDecimal price) {
        return Product.builder()
                .withName(name)
                .withPrice(price)
                .build();
    }

    public static Product build(Integer id, String name, BigDecimal price) {
        return Product.builder()
                .withId(id)
                .withName(name)
                .withPrice(price)
                .build();
    }
}
