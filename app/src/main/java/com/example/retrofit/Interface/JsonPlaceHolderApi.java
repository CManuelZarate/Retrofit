package com.example.retrofit.Interface;

import com.example.retrofit.Model.Producto;
import com.example.retrofit.Model.Productos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("producto")
    Call<Productos> getProductos();

}
