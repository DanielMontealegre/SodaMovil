package com.example.usuario.sodamovil.Entidades;

import android.graphics.Bitmap;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by Danel on 4/12/2017.
 */

public class Restaurante {

    private String codigo;
    private String nombre;
    private String descripcion;
    private Bitmap imagen; //http://stackoverflow.com/questions/9357668/how-to-store-image-in-sqlite-database
    private Double latitudesH;
    private Double latitudesV;
    private Horario horario;
    private String  usuario ;
    private String telefono;


    public Restaurante(String codigo, String nombre, String descripcion, Bitmap imagen, Double latitudesH, Double latitudesV, Horario horario, String usuario,String telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.latitudesH = latitudesH;
        this.latitudesV = latitudesV;
        this.usuario=usuario;
        this.horario=horario;
        this.telefono=telefono;
    }

    public Restaurante() {
        this.codigo = "";
        this.nombre = "";
        this.descripcion = "";
        this.imagen = null;
        this.latitudesH = 0.0;
        this.latitudesV = 0.0;
        this.horario= new Horario();
        this.usuario= "";
        this.telefono="";
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public Double getLatitudesH() {
        return latitudesH;
    }

    public void setLatitudesH(Double latitudesH) {
        this.latitudesH = latitudesH;
    }

    public Double getLatitudesV() {
        return latitudesV;
    }

    public void setLatitudesV(Double latitudesV) {
        this.latitudesV = latitudesV;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codigo",  this.codigo);
        result.put("nombre", this.nombre);
        result.put("descripcion", this.descripcion);
        result.put("latitudesH", this.latitudesH);
        result.put("latitudesV",  this.latitudesV);
        result.put("horario", this.horario);
        result.put("usuario", this.usuario);
        result.put("telefono",  this.telefono);

        return result;
    }

}