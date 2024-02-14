package com.apishogi.shogi.dto;

import com.apishogi.shogi.persistance.model.Product;
import com.apishogi.shogi.persistance.model.StockType;
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
    private float quantity;
    private float price;
    private StockType stock;
    private String imageURL;

}
