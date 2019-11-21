package com.danito.p_peliculasencartelera;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Holder>{
    ArrayList<Pelicula> peliculas;

    public Adaptador(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula, parent, false);
        Holder h = new Holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(peliculas.get(position));
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imagenPelicula;
        TextView titulo, descripcion;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imagenPelicula = itemView.findViewById(R.id.imagenPelicula);
            titulo = itemView.findViewById(R.id.textoTitulo);
            descripcion = itemView.findViewById(R.id.descripcionPelicula);
        }

        public void bind(Pelicula pelicula) {
            new DescargarImagen(imagenPelicula).execute(pelicula.getUrl());
            titulo.setText(pelicula.getTitulo());
            descripcion.setText(pelicula.getDescripcion());
        }
    }
}
