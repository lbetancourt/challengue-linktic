package com.linktic.product.infrastructure.postgres;

import com.linktic.product.infrastructure.postgres.dto.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);
}
