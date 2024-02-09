package com.apishogi.shogi.persistance.repository;

import com.apishogi.shogi.dto.ProductDto;
import com.apishogi.shogi.persistance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductName(String productName);

}
