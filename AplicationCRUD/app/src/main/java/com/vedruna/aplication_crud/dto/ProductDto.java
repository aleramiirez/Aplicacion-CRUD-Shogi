package com.vedruna.aplication_crud.dto;

import com.vedruna.aplication_crud.model.StockType;

public class ProductDto {

    private String productName;

    private float quantity;

    private float price;

    private StockType stock;

    public ProductDto() {

    }

    public ProductDto(String productName, float quantity, float price, StockType stock) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
