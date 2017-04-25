package com.example.usuario.sodamovil.BaseDeDatos;

import com.example.usuario.sodamovil.Entidades.Restaurante;
import com.example.usuario.sodamovil.Entidades.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by USUARIO on 20/04/2017.
 */

public class DataBase {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private static DataBase instance = null;

    protected DataBase() {
        initFirebase();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }


    private void initFirebase() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    public FirebaseDatabase getmFirebaseDatabase() {
        return mFirebaseDatabase;
    }

    public void setmFirebaseDatabase(FirebaseDatabase mFirebaseDatabase) {
        this.mFirebaseDatabase = mFirebaseDatabase;
    }

    public DatabaseReference getmDatabaseReference() {
        return mDatabaseReference;
    }

    public void setmDatabaseReference(DatabaseReference mDatabaseReference) {
        this.mDatabaseReference = mDatabaseReference;
    }

    public void agregarUsuario(Usuario usuario) {
        String key = mDatabaseReference.child("Usuario").push().getKey();
        usuario.setIdFirebase(key);
        mDatabaseReference.child("Usuario").child(key).setValue(usuario.toMap());
    }


    public Restaurante agregarRestaurante(Restaurante restaurante, String email) {
        String key = mDatabaseReference.child("Restaurante").push().getKey();
        restaurante.setCodigo(key);
        restaurante.getUsuarios().add(email);
        mDatabaseReference.child("Restaurante").child(key).setValue(restaurante);
        return restaurante;
    }

    public void actualizarRestaurantesUsuario(Restaurante restaurante,Usuario usuario) {
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> Valores = new HashMap<>();
        usuario.getRestaurantes().add(restaurante.getCodigo());
        childUpdates.put("/Usuario/" + usuario.getIdFirebase(), usuario.toMap());
        mDatabaseReference.updateChildren(childUpdates);
    }
}