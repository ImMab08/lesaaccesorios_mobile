package com.example.lesamobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lesamobile.Models.ProductoDTO;
import com.example.lesamobile.R;

import java.util.List;

public class ProductosMenuAdapter extends RecyclerView.Adapter<ProductosMenuAdapter.ViewHolder> {

    List<ProductoDTO> _listProducto;
    Context _contexto;

    public ProductosMenuAdapter (List<ProductoDTO> listProducto, Context contexto) {
        this._listProducto = listProducto;
        this._contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.menucard, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductoDTO objproducto = _listProducto.get(position);
        holder.tv_nombreproducto.setText(objproducto.getNombre());
        holder.tv_descripcionproducto.setText(objproducto.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return _listProducto.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombreproducto, tv_descripcionproducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombreproducto = itemView.findViewById(R.id.tv_nombreproducto);
            tv_descripcionproducto = itemView.findViewById(R.id.tv_descripcionproducto);
        }

    }


}
