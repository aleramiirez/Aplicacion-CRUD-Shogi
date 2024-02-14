package com.vedruna.aplication_crud.dto;

import com.vedruna.aplication_crud.model.StockType;

/**
 * Clase que representa un DTO (Data Transfer Object) para un producto.
 */
public class ProductDto {

    private String productName;

    private float quantity;

    private float price;

    private StockType stock;

    private String imageURL;

    /**
     * Constructor vacío de la clase Product.
     */
    public ProductDto() {

    }

    /**
     * Constructor para inicializar un objeto ProductDto con los valores proporcionados.
     *
     * @param productName Nombre del producto.
     * @param quantity Cantidad del producto.
     * @param price Precio del producto.
     * @param stock Tipo de stock del producto.
     * @param imageURL URL de la imagen del producto.
     */
    public ProductDto(String productName, float quantity, float price, StockType stock, String imageURL) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
        this.imageURL = imageURL;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param productName El nombre del producto a establecer.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Obtiene la cantidad del producto.
     *
     * @return La cantidad del producto.
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param quantity La cantidad del producto a establecer.
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price El precio del producto a establecer.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Obtiene el tipo de stock del producto.
     *
     * @return El tipo de stock del producto.
     */
    public StockType getStock() {
        return stock;
    }

    /**
     * Establece el tipo de stock del producto.
     *
     * @param stock El tipo de stock del producto a establecer.
     */
    public void setStock(StockType stock) {
        this.stock = stock;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     *
     * @return La URL de la imagen del producto.
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Establece la URL de la imagen del producto.
     *
     * @param imageURL La URL de la imagen del producto a establecer.
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
