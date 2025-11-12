package com.linktic.stock.infrastructure.postgres.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventario")
public class Stock {

    @Id
    @Column(name = "producto_id", nullable = false)
    private Integer productId;

    @Column(name = "cantidad", nullable = false)
    private Integer quantity;
}
