package com.apishogi.shogi.dto;

import com.apishogi.shogi.persistance.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto {

    private String productName;
    private int quantity;
    private float price;

}
