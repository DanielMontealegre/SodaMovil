package com.example.usuario.sodamovil.Entidades;

import com.example.usuario.sodamovil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josue on 6/5/2017.
 */

public class Comida {
    private float precio;
    private String nombre;
    private int idDrawable;

    public Comida(float precio, String nombre, int idDrawable) {
        this.precio = precio;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public static final List<Comida> COMIDAS_POPULARES = new ArrayList<Comida>();


    static {
        COMIDAS_POPULARES.add(new Comida(5, "Camarones Tismados", R.drawable.camaron));
        COMIDAS_POPULARES.add(new Comida(3.2f, "Rosca Herbárea", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(12f, "Sushi Extremo", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(9, "Sandwich Deli", R.drawable.sand));
    }

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

}