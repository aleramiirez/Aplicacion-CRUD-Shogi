package com.vedruna.aplication_crud.interfaz;

import com.vedruna.aplication_crud.dto.ProductDto;
import com.vedruna.aplication_crud.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Interfaz que define las operaciones CRUD (Create, Read, Update, Delete) para los productos.
 */
public interface CRUD {

    /**
     * Obtiene todos los productos.
     *
     * @return Una llamada asíncrona que devolverá una lista de productos.
     */
    @GET("products")
    Call<List<Product>> getAll();

    /**
     * Crea un nuevo producto.
     *
     * @param productDto El objeto ProductDto que contiene la información del producto a crear.
     * @return Una llamada asíncrona que devolverá el producto creado.
     */
    @POST("products")
    Call<Product>create(@Body ProductDto productDto);

    /**
     * Actualiza un producto existente.
     *
     * @param productDto   El objeto ProductDto que contiene la nueva información del producto.
     * @param productName  El nombre del producto que se actualizará.
     * @return Una llamada asíncrona que devolverá el producto actualizado.
     */
    @PUT("products/{productName}")
    Call<Product> update(@Body ProductDto productDto, @Path("productName") String productName);

    /**
     * Elimina un producto existente.
     *
     * @param productName El nombre del producto que se eliminará.
     * @return Una llamada asíncrona que no devuelve ningún resultado.
     */
    @DELETE("products/{productName}")
    Call<Void>delete(@Path("productName") String productName);

}
