package com.example.lesamobile.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    private static RetrofitService _serv;
    private static final String base_url = "http://192.168.20.21:8081/";

    public static RetrofitService getServices() {
        if(_serv == null) {
            // Crea una nueva instancia de retrofit con el servicio.
            Retrofit retro = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            _serv = retro.create(RetrofitService.class);
            return _serv;
        }
        // Devuelve el servicio que ya esta intanciado.
        return _serv;
    }

}
