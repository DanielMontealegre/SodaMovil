package com.example.usuario.sodamovil;

import com.example.usuario.sodamovil.Entidades.Horario;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by USUARIO on 20/04/2017.
 */
// Se debe crear en el paquete com.example...
// Forma de uso:
// VariablesGlobales vg = VariablesGlobales.getInstance(); vg.setMitexto("Hola");    int i = vg.getMivalor();
public class VariablesGlobales {

    public LatLng posicionAgregarRestaurante;
    private Horario horario;


    private static VariablesGlobales instance = null;

    protected VariablesGlobales() {}
    public static VariablesGlobales getInstance() {
        if(instance == null) {instance = new VariablesGlobales(); }
        return instance;
    }

    public LatLng getPosicionAgregarRestaurante() {
        return posicionAgregarRestaurante;
    }

    public void setPosicionAgregarRestaurante(LatLng posicionAgregarRestaurante) {
        this.posicionAgregarRestaurante = posicionAgregarRestaurante;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}// fin de la clase de variables globales
