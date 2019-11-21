package com.danito.p_peliculasencartelera;

import android.graphics.Bitmap;

public class Pelicula {
    private Bitmap imagen;
    private String url;
    private String titulo;
    private String descripcion;

    public Pelicula() {
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pelicula(Bitmap imagen, String url, String titulo, String descripcion) {
        this.imagen = imagen;
        this.url = url;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
}
