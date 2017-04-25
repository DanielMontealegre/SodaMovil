package com.example.usuario.sodamovil.Fragmentos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.sodamovil.Entidades.Dia;
import com.example.usuario.sodamovil.Entidades.Horario;
import com.example.usuario.sodamovil.R;

import java.util.ArrayList;

/**
 * Created by USUARIO on 24/04/2017.
 */

public class AdaptadorHorario extends RecyclerView.Adapter<AdaptadorHorario.DiaViewHolder> {

    Horario horario;

    public AdaptadorHorario(Horario horario){
        this.horario = horario;
    }


    @Override
    public DiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item,parent,false);
        DiaViewHolder holder = new DiaViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiaViewHolder holder, final int position) {
        ArrayList<Dia> listaDias = this.horario.getDias();

        if(listaDias.get(position)!=null){
            holder.tvDia.setText(listaDias.get(position).getNombre());
            holder.imgCancel.setImageResource(R.drawable.ic_close_black_24dp);

            if(listaDias.get(position).getHoraAbrir()!=null){
                holder.tvHorario.setText(listaDias.get(position).toString());
                holder.imgCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        borraHorarioDia(position);
                    }
                });
            }
            else{
                holder.tvHorario.setText("Cerrado");
            }
        }
    }

    private void borraHorarioDia(int position) {
        this.horario.removeDia(position);
        notifyDataSetChanged();
    }

    /*    public void swap(ArrayList<Dia> lista){
            this.listaDias.clear();
            this.listaDias.addAll(lista);
            notifyDataSetChanged();
        }
    */
    @Override
    public int getItemCount() {
        return this.horario.getDias().size();
    }

    public static class DiaViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvDia;
        private TextView tvHorario;
        private ImageView imgCancel;

        public DiaViewHolder(View itemView) {
            super(itemView);

            tvDia = (TextView) itemView.findViewById(R.id.tvDia);
            tvHorario =(TextView) itemView.findViewById(R.id.tvHorario);
            imgCancel = (ImageView) itemView.findViewById(R.id.imgCancel);
        }
    }
}

