package com.linktic.stock.infrastructure.postgres;

import com.linktic.stock.infrastructure.postgres.dto.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJpaRepository extends JpaRepository<Stock, Integer> {
}
