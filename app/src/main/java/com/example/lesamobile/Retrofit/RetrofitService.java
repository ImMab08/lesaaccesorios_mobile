package com.example.lesamobile.Retrofit;

import com.example.lesamobile.Models.Login;
import com.example.lesamobile.Models.LoginDTO;
import com.example.lesamobile.Models.ProductoDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("v01/auth/login")
    Call<LoginDTO> login(@Body Login log);

    @GET("v01/producto/usuario")
    Call<List<ProductoDTO>> traerProductos(@Header("Authorization") String Token);

}
