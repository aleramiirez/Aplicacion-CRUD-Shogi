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

public interface CRUD {

    @GET("products")
    Call<List<Product>> getAll();

    @POST("products")
    Call<Product>create(@Body ProductDto dto);

    @PUT("products/{productName}")
    Call<Product> update(@Body ProductDto productDTO, @Path("productName") String productName);

    @DELETE("products/{productName}")
    Call<Void>delete(@Path("productName") String productName);

}
