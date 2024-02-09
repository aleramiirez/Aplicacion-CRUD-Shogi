package com.apishogi.shogi.services;

import com.apishogi.shogi.dto.ProductDto;
import com.apishogi.shogi.persistance.model.Product;

import java.util.List;

public interface ProductServiceI {

    List<ProductDto> getProducts();

    ProductDto getProductByName(String productName);

    Product addProduct(Product product);

    Product updateProduct(Product product, String productName);

    void deleteProduct(String productName);

}
