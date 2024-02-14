package com.apishogi.shogi.controller;

import com.apishogi.shogi.dto.ProductDto;
import com.apishogi.shogi.persistance.model.Product;
import com.apishogi.shogi.services.ProductServiceI;
import com.apishogi.shogi.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productName}")
    public ProductDto getProductByName(@PathVariable String productName) {
        return productService.getProductByName(productName);
    }

    private Product converDtoToProduct (ProductDto productDto) {
        return new Product(productDto.getProductName(), productDto.getQuantity(), productDto.getPrice(),
                productDto.getStock(), productDto.getImageURL());
    }

    @PostMapping
    public Product addProduct (@RequestBody ProductDto productDto) {
        Product product = converDtoToProduct(productDto);
        return productService.addProduct(product);
    }

    @PutMapping("/{productName}")
    public Product updateProduct(@RequestBody ProductDto productDto, @PathVariable String productName) {
        Product product = converDtoToProduct(productDto);
        return productService.updateProduct(product, productName);
    }

    @DeleteMapping("/{productName}")
    public void deleteProduct(@PathVariable String productName) {
        productService.deleteProduct(productName);
    }

}
