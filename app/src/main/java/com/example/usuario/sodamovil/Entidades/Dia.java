package com.example.usuario.sodamovil.Entidades;

/**
 * Created by USUARIO on 24/04/2017.
 */

public class Dia {

    private String nombre;
    private Hora horaAbrir;
    private  Hora horaCerrar;

    public Dia(String nombre, Hora horaAbrir, Hora horaCerrar) {
        this.nombre = nombre;
        this.horaAbrir = horaAbrir;
        this.horaCerrar = horaCerrar;
    }

    public Dia() {
        this.nombre = "";
        this.horaAbrir = new Hora();
        this.horaCerrar =  new Hora();
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Hora getHoraAbrir() {
        return horaAbrir;
    }

    public void setHoraAbrir(Hora horaAbrir) {
        this.horaAbrir = horaAbrir;
    }

    public Hora getHoraCerrar() {
        return horaCerrar;
    }

    public void setHoraCerrar(Hora horaCerrar) {
        this.horaCerrar = horaCerrar;
    }

    public String toString(){
        return this.horaAbrir.toString()+" - "+this.horaCerrar.toString();
    }
}

