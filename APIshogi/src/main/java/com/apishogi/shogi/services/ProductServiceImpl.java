package com.apishogi.shogi.services;

import com.apishogi.shogi.dto.ProductDto;
import com.apishogi.shogi.persistance.model.Product;
import com.apishogi.shogi.persistance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductServiceI{

    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productsDto = new ArrayList<>();

        for (Product product: products) {
            ProductDto productDto = new ProductDto(product.getProductName(), product.getQuantity(),
                    product.getPrice(), product.getStock(), product.getImageURL());

            productsDto.add(productDto);
        }

        return productsDto;
    }

    @Override
    public ProductDto getProductByName(String productName) {
        Product product = productRepo.findByProductName(productName);
        ProductDto productDto = new ProductDto(product.getProductName(), product.getQuantity(),
                product.getPrice(), product.getStock(), product.getImageURL());
        return productDto;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product, String productName) {
        Product productUpdate = productRepo.findByProductName(productName);

        productUpdate.setProductName(product.getProductName());
        productUpdate.setQuantity(product.getQuantity());
        productUpdate.setStock(product.getStock());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setImageURL(product.getImageURL());

        return productRepo.save(productUpdate);
    }

    @Override
    public void deleteProduct(String productName) {
        Product product = productRepo.findByProductName(productName);
        productRepo.delete(product);
    }
}
