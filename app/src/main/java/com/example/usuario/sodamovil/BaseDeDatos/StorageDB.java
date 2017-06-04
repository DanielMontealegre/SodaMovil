package com.example.usuario.sodamovil.BaseDeDatos;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.example.usuario.sodamovil.Entidades.Comida;
import com.example.usuario.sodamovil.Entidades.Restaurante;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Danel on 5/21/2017.
 */

public class StorageDB {
    public FirebaseStorage storage;
    public StorageReference storageRef;
    public StorageReference imaginesRestaurante;
    public StorageReference imaginesComidas;


    private static StorageDB instance = null;

    protected StorageDB() {
        initFireStorage();
    }

    public static StorageDB getInstance() {
        if (instance == null) {
            instance = new StorageDB();
        }
        return instance;
    }


    private void initFireStorage() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://soda-movil-fc2c8.appspot.com");
        imaginesRestaurante = storageRef.child("Restaurantes");
        imaginesComidas = storageRef.child("Comidas");
    }


    public void guardarImagenRestauranteBitMap(Bitmap bitmap, String codigoRestaurante){
        StorageReference nuevaImagen = imaginesRestaurante.child(codigoRestaurante);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = nuevaImagen.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
    }


    public void guardarImagenComidaBitMap(Bitmap bitmap, String codigoComida){
        StorageReference nuevaImagen = imaginesComidas.child(codigoComida);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = nuevaImagen.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
    }

    public void setImagenRestauranteEnImageView(Restaurante restaurante, final ImageView view){
        StorageDB.getInstance().imaginesRestaurante.child(restaurante.getCodigo()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                view.setImageURI(uri);
            }
        });
    }

    public void setImagenPlatoEnImageView(Comida comida, final ImageView view){
        StorageDB.getInstance().imaginesComidas.child(comida.getIdFirebase()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                view.setImageURI(uri);
            }
        });
    }


    public void updateImagenRestauranteBitMap(final Bitmap bitmap,final String codigoRestaurante){
        StorageReference storageRef= imaginesRestaurante.child(codigoRestaurante);
        storageRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                guardarImagenRestauranteBitMap(bitmap,codigoRestaurante);
            }
        });

    }

    public void guardarImagenRestauranteBitMap2(final Bitmap bitmap, String codigoRestaurante,final ImageView imageView){
        StorageReference nuevaImagen = imaginesRestaurante.child(codigoRestaurante);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = nuevaImagen.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
// taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
// imageView.setImageBitmap(bitmap);
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                imageView.setImageURI(downloadUrl);
//si da error entonces mejor nada mas
                imageView.setImageBitmap(bitmap);
            }
        });
    }

}
