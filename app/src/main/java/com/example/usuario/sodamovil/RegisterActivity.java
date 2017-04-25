package com.example.usuario.sodamovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.sodamovil.BaseDeDatos.DataBase;
import com.example.usuario.sodamovil.Entidades.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.LinkedList;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    TextView input_password,input_email,input_nombre,input_apellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registro");
        input_password = (TextView) findViewById(R.id.passwordRegistro);
        input_email = (TextView) findViewById(R.id.emailRegistro);
        input_nombre = (TextView) findViewById(R.id.nombreRegistro);
        input_apellidos = (TextView) findViewById(R.id.apellidoRegistro);

        progressDialog = new ProgressDialog(this);

        // alambramos el Button
        Button MiButton = (Button) findViewById(R.id.btnRegistrarse);
        firebaseAuth= FirebaseAuth.getInstance();
        //Programamos el evento onclick

        MiButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0) {
                registerUser();
            }

        });







    } // Fin del Oncreate


    private void registerUser(){
        //getting email and password from edit texts
        String email =  input_email.getText().toString();
        String password  =input_password.getText().toString();
        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            CrearUsuario();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            task.getException();
                            Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }


    //----------------------------------------------------------------------------------------------------//////
    public void CrearUsuario(){
        String email =  input_email.getText().toString();
        String nombre = input_nombre.getText().toString();
        String apellidos = input_apellidos.getText().toString();
        Usuario user = new Usuario(email,"******",nombre,apellidos,new LinkedList<String>());
        updateUsuario(nombre+" "+apellidos);
        DataBase db= DataBase.getInstance();
        db.agregarUsuario(user);
    }

    private void updateUsuario(String displayNombre){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayNombre).build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("ACTIVIDAD", "User profile updated.");
                }
            }
        });
    }



    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

} // [03:07:21 p. m.] Fin de la Clase Register
