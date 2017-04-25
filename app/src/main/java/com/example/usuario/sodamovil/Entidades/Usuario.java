package com.example.usuario.sodamovil.Entidades;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by JOSE PABLO on 12/04/2017.
 */

public class Usuario {

    private String idFirebase;
    private String correo;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private int habilitado;
    private List<String> restaurantes;


    public Usuario(){
        this.correo="";
        this.contrasena="";
        this.nombre="";
        this.apellidos="";
        this.restaurantes= new LinkedList<>();
        this.habilitado=0;
        this.idFirebase="";
    }

    public Usuario(String us, String contr, String nom, String ape,List<String> restaurantes){
        this.correo=us;
        this.contrasena=contr;
        this.nombre=nom;
        this.apellidos=ape;
        this.restaurantes=restaurantes;
        this.habilitado=0;
        this.idFirebase="";
    }

    public Usuario(String us,String contr){
        this.correo=us;
        this.contrasena=contr;
        this.nombre="";
        this.apellidos="";
        this.restaurantes=new LinkedList<>();
        this.habilitado=0;
        this.idFirebase="";
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String usuario) {
        this.correo = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<String> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<String> usuarios) {
        this.restaurantes = usuarios;
    }

    public String getIdFirebase() {
        return idFirebase;
    }

    public void setIdFirebase(String idFirebase) {
        this.idFirebase = idFirebase;
    }

    @Override
    public String toString(){
        return idFirebase;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("correo",  this.correo);
        result.put("contrasena", "****");
        result.put("nombre", this.nombre);
        result.put("apellidos", this.apellidos);
        result.put("restaurantes",  this.restaurantes);
        result.put("habilitado", this.habilitado);
        result.put("idFirebase", this.idFirebase);
        return result;
    }

}