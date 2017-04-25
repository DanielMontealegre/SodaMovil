package com.example.usuario.sodamovil.Entidades;

import android.graphics.Bitmap;

/**
 * Created by Danel on 4/12/2017.
 */

public class Plato {

    private int codigo;
    private String nombre;
    private String descripcion;
    private Bitmap imagen;
    private long precio;
    private Restaurante restaurante;


    public Plato(int codigo, String nombre, String descripcion, Bitmap imagen, long precio, Restaurante restaurante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.restaurante = restaurante;
    }

    public Plato() {
        this.codigo = -1;
        this.nombre = "";
        this.descripcion = "";
        this.imagen = null;
        this.precio = 0;
        this.restaurante = new Restaurante();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
