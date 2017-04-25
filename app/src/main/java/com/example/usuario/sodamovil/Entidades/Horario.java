package com.example.usuario.sodamovil.Entidades;

import java.util.ArrayList;

/**
 * Created by USUARIO on 24/04/2017.
 */

public class Horario {

    private ArrayList<Dia> dias;

    public Horario(String[] nombreDias){
        dias = new ArrayList<>();
        for(int i=0;i<nombreDias.length;i++)
            dias.add(new Dia(nombreDias[i],null,null));
    }

    public Horario(ArrayList<Dia> dias) {
        this.dias = dias;
    }

    public Horario(){
        this.dias=new ArrayList<>();
    }
    public ArrayList<Dia> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Dia> dias) {
        this.dias = dias;
    }

    public Dia getDia(String nombre){
        for (Dia d: this.dias) {
            if(d.getNombre().equals(nombre))
                return d;
        }
        return null;
    }


    public void agregarHora(int position, Hora horaAbrir,Hora horaCerrar){
        dias.get(position).setHoraAbrir(horaAbrir);
        dias.get(position).setHoraCerrar(horaCerrar);
    }

    public void removeDia(int position){
        dias.remove(position);
    }




}

