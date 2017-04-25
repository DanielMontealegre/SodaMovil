package com.example.usuario.sodamovil.Entidades;

/**
 * Created by USUARIO on 24/04/2017.
 */

public class Hora {
    private int hora;
    private int minutos;

    public Hora(int hora,int minutos){
        this.hora=hora;
        this.minutos=minutos;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    @Override
    public String toString(){
        int hour =this.hora%12;
        if(hour==0)
            hour =12;
        String minutes;
        minutes = (this.minutos < 10)? "0"+this.minutos: this.minutos+"";
        String txtHora = (hour)+":"+minutes;
        txtHora+= (this.hora>11)? " PM": " AM";
        return txtHora;
    }
}

