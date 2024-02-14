package com.apishogi.shogi.persistance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "T_PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_PRODUCT_ID")
    private int productID;

    @Column(name = "C_PRODUCT_NAME", unique = true)
    private String productName;

    @Column(name = "C_QUANTITY")
    private float quantity;

    @Column(name = "C_PRICE")
    private float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "C_STOCK")
    private StockType stock;

    @Column(name = "T_PRODUCT_IMAGE")
    private String imageURL;

    public Product(String productName, float quantity, float price, StockType stock, String imageURL) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
        this.imageURL = imageURL;
    }

}
