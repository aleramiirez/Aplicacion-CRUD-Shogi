package com.vedruna.aplication_crud.model;

import java.io.Serializable;

/**
 * Clase que representa un producto.
 * Implementa la interfaz Serializable para permitir la serialización de objetos.
 */
public class Product implements Serializable {

    // Identificador único del producto
    private int productID;

    // Nombre del producto
    private String productName;

    // Cantidad disponible del producto
    private float quantity;

    // Precio del producto
    private float price;

    // Tipo de stock del producto
    private StockType stock;

    // URL de la imagen asociada al producto
    private String imageURL;

    /**
     * Constructor vacío de la clase Product.
     */
    public Product() {
    }

    /**
     * Constructor de la clase Product que inicializa todos los atributos.
     *
     * @param productID   Identificador único del producto
     * @param productName Nombre del producto
     * @param quantity    Cantidad disponible del producto
     * @param price       Precio del producto
     * @param stock       Tipo de stock del producto
     * @param imageURL    URL de la imagen asociada al producto
     */
    public Product(int productID, String productName, float quantity, float price, StockType stock, String imageURL) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.stock = stock;
        this.imageURL = imageURL;
    }

    /**
     * Método getter para obtener el identificador del producto.
     *
     * @return El identificador único del producto
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Método setter para establecer el identificador del producto.
     *
     * @param productID El identificador único del producto
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Método getter para obtener el nombre del producto.
     *
     * @return El nombre del producto
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Método setter para establecer el nombre del producto.
     *
     * @param nombre El nombre del producto
     */
    public void setProductName(String nombre) {
        this.productName = nombre;
    }

    /**
     * Método getter para obtener la cantidad disponible del producto.
     *
     * @return La cantidad disponible del producto
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Método setter para establecer la cantidad disponible del producto.
     *
     * @param quantity La cantidad disponible del producto
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * Método getter para obtener el precio del producto.
     *
     * @return El precio del producto
     */
    public float getPrice() {
        return price;
    }

    /**
     * Método setter para establecer el precio del producto.
     *
     * @param price El precio del producto
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Método getter para obtener el tipo de stock del producto.
     *
     * @return El tipo de stock del producto
     */
    public StockType getStock() {
        return stock;
    }

    /**
     * Método setter para establecer el tipo de stock del producto.
     *
     * @param stock El tipo de stock del producto
     */
    public void setStock(StockType stock) {
        this.stock = stock;
    }

    /**
     * Método getter para obtener la URL de la imagen asociada al producto.
     *
     * @return La URL de la imagen asociada al producto
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Método setter para establecer la URL de la imagen asociada al producto.
     *
     * @param imageURL La URL de la imagen asociada al producto
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Método toString para representar el objeto Product como una cadena de texto.
     *
     * @return Representación en cadena de texto del objeto Product
     */
    @Override
    public String toString() {
        return "Id: " + getProductID() + ", Name: " + getProductName() + ", Quantity: " + getQuantity() + ", Price: " + getPrice() +
                ", Stock: " + getStock() + ", ImageURL: " + getImageURL();
    }

}
