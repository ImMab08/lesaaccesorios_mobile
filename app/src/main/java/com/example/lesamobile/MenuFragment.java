package com.example.lesamobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesamobile.Adapters.ProductosMenuAdapter;
import com.example.lesamobile.Models.ProductoDTO;
import com.example.lesamobile.Retrofit.RetrofitAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    RecyclerView rv_menu;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        rv_menu = root.findViewById(R.id.rv_menu);
        rv_menu.setLayoutManager(new LinearLayoutManager(root.getContext()));

        if (getArguments() != null) {
            token = getArguments().getString("Token");
            Log.i("Respuestafragment", "Token recibido en menufragment: " + token);
        } else {
            Log.i("Respuestafragment", "No se recibio ningun argumento");
        }

        if(token != null && !token.isEmpty()) {
            Call<List<ProductoDTO>> traerProductos = RetrofitAdapter.getServices().traerProductos("Bearer " + token);
            traerProductos.enqueue(new Callback<List<ProductoDTO>>() {
                @Override
                public void onResponse(Call<List<ProductoDTO>> call, Response<List<ProductoDTO>> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        List<ProductoDTO> objproductosList = response.body();
                        ProductosMenuAdapter adaptador = new ProductosMenuAdapter(objproductosList, root.getContext());
                        rv_menu.setAdapter(adaptador);
                    } else {
                        Log.e("Respuesta", "Bad response: " + response.raw() + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<ProductoDTO>> call, Throwable t) {
                    Log.e("Respuesta", "onFailure: " + t.getCause() + t.getMessage());
                }
            });

        }

        return root;
    }

}