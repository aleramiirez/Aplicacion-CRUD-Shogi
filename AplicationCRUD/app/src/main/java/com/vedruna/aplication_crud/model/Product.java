package com.vedruna.aplication_crud.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int productID;


    private String productName;

    private float quantity;

    private float price;

    public Product() {
    }

    public Product(int productID, String productName, float quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String nombre) {
        this.productName = nombre;
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

    public void setPrice(float precio) {
        this.price = precio;
    }


    @Override
    public String toString() {
        return "Id: " + getProductID() + ", Name: " + getProductName() + ", Quantity: " + getQuantity() + ", Price: " + getPrice();
    }

}
