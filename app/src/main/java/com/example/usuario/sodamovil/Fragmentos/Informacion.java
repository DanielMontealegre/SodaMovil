package com.example.usuario.sodamovil.Fragmentos;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.usuario.sodamovil.BaseDeDatos.StorageDB;
import com.example.usuario.sodamovil.Entidades.Restaurante;
import com.example.usuario.sodamovil.R;
import com.example.usuario.sodamovil.VariablesGlobales;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.usuario.sodamovil.R.id.container;
import static com.example.usuario.sodamovil.R.id.textView;


public class Informacion extends Fragment {
    String numero="";
    String latitud="";
    String longitud="";
    public Informacion(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragmento_informacion, container, false);
        setRestaurante(inf);
        setHasOptionsMenu(true);
        return inf;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.menucontacto, menu);
        super.onCreateOptionsMenu(menu, inflater);
      //  return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemLlamada:
                MarcarNumero(numero);
                return false;
            case R.id.itemUbicacion:
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:"+latitud+","+longitud));
                startActivity(i);
                return true;
            default:
                break;
        }

        return false;
    }



    public void setRestaurante(View view){
        Restaurante restaurante=VariablesGlobales.getInstance().getRestauranteActual();
        if(restaurante != null){

            TextView NombreTW = (TextView) view.findViewById(R.id.NombreRest);
            TextView DescripcionTW = (TextView) view.findViewById(R.id.DescripRest);
            TextView UbicacionTW = (TextView)view.findViewById(R.id.UbicacionRest);
            TextView HorarioTW = (TextView) view.findViewById(R.id.HorarioRest);
            ImageView imagen= (ImageView) view.findViewById(R.id.FotoRestaurante);



            StorageReference imaginesRestaurante = StorageDB.getInstance().imaginesRestaurante.child(restaurante.getCodigo());
            //-KkdQF54Mr5Xj4cCycUd
            //-KkdPFHZin_6zmo9r7va

            Glide.with(imagen.getContext())
                    .using(new FirebaseImageLoader())
                    .load(imaginesRestaurante)
                    .centerCrop()
                    .into(imagen);


            NombreTW.setText(restaurante.getNombre());
            DescripcionTW.setText(restaurante.getDescripcion());
            HorarioTW.setText(restaurante.getHorario().toString());
            UbicacionTW.setText(restaurante.getUbicacion());
            numero=restaurante.getTelefono();
            latitud=String.valueOf(restaurante.getLatitudesH());
            longitud=String.valueOf(restaurante.getLatitudesV());

        }
    }


    public void MarcarNumero (String numero){
        Intent i = new
                Intent(android.content.Intent.ACTION_DIAL,
                Uri.parse("tel:" + numero));
        startActivity(i);
    }
    ;


}
