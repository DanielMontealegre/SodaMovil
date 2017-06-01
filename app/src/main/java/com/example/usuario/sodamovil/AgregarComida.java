package com.example.usuario.sodamovil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.usuario.sodamovil.BaseDeDatos.DataBase;
import com.example.usuario.sodamovil.Entidades.Comida;
import com.example.usuario.sodamovil.Entidades.Restaurante;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.usuario.sodamovil.AgregarRestauranteActivity.HORARIO_REQUEST;
import static com.example.usuario.sodamovil.AgregarRestauranteActivity.RESULT_LOAD_IMG;

public class AgregarComida extends AppCompatActivity {
    EditText nombreComida;
    EditText precioComida;
    ImageView imagenComida;
    Bitmap imagenComidaFirebase;
    ProgressDialog progressDialog;
    Button btnAgregarComida;
    static int RESULT_LOAD_IMG=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_comida);
        nombreComida= (EditText) findViewById(R.id.nombreComidaId);
        precioComida = (EditText) findViewById(R.id.precioComidaId);
        imagenComida= (ImageView) findViewById(R.id.imageViewComida);
        BitmapDrawable drawable = (BitmapDrawable) imagenComida.getDrawable();
        imagenComidaFirebase =  drawable.getBitmap();
        progressDialog = new ProgressDialog(this);


        btnAgregarComida = (Button) findViewById(R.id.btnAgregarComida);
        btnAgregarComida.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                AgregarComida();
            }

        });
        imagenComida.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                getImageFromGallery();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
/*
        if(requestCode==RESULT_LOAD_IMG && resultCode == RESULT_OK){
            try {
                final Uri imageUri = data.getData();
                //cropImageView.setImageUriAsync(uri);
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imagenComida.setImageBitmap(selectedImage);
                imagenComidaFirebase=selectedImage;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Mensaje("ALGO PASO PERRITO");
            }

        }*/

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imagenComida.setImageURI(resultUri);
                try {
                    final InputStream imageStream = getContentResolver().openInputStream(resultUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imagenComidaFirebase=selectedImage;
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Mensaje("ALGO PASO PERRITO");
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

    public void AgregarComida(){
        progressDialog.setMessage("Agregando comida...");
        progressDialog.show();
        String nombre= nombreComida.getText().toString();
        String precio= precioComida.getText().toString();
        Comida comida= new Comida();
        comida.setNombre(nombre);
        comida.setPrecio(Float.parseFloat(precio));
        Restaurante restauranteActual= VariablesGlobales.getInstance().getRestauranteActual();
        DataBase.getInstance().agregarComida(restauranteActual,comida,imagenComidaFirebase,progressDialog);
        limpiaForm();
        super.onBackPressed();
    }



    public void limpiaForm(){
        nombreComida.setText("");
        precioComida.setText("");
        imagenComida.setImageResource(R.drawable.ic_no_image_available);
        BitmapDrawable drawable = (BitmapDrawable) imagenComida.getDrawable();
        imagenComidaFirebase =  drawable.getBitmap();
    }

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

/*
    public void getImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Escoja una imagen"), RESULT_LOAD_IMG);
    }
*/

    public void getImageFromGallery(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMinCropWindowSize(0,0)
                .setMinCropResultSize(260,260)
                .setMaxCropResultSize(600,410)
                .start(this);
        // .setMinCropWindowSize(0,0)
    }


}
