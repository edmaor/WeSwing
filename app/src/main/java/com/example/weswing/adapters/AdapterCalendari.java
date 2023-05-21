package com.example.weswing.adapters;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.weswing.listener.SelectListenerMogudes;
import com.example.weswing.objects.Moguda;
import com.example.weswing.R;

public class AdapterCalendari extends RecyclerView.Adapter<AdapterCalendari.MyviewHolder> {

    ViewGroup parent;
    List<Moguda> mogudaList;
    SelectListenerMogudes listenerMogudes;

    public AdapterCalendari(List<Moguda> mos, SelectListenerMogudes listenerMogudes) {
        this.mogudaList = mos;
        this.listenerMogudes = listenerMogudes;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        return new MyviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mogudes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.bindData(mogudaList.get(position));

        holder.cardView.setOnClickListener(v -> listenerMogudes.onItemClick(mogudaList.get(position)));
    }

    @Override
    public int getItemCount() {
        return mogudaList.size();
    }


    public class MyviewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView dataGran, title, assistents,duracio,lloc,organitzador;
        View cardView;

        public MyviewHolder(@NonNull View view){
            super(view);
            iv = view.findViewById(R.id.IVperfil);
            dataGran = view.findViewById(R.id.TVData);
            title = view.findViewById(R.id.TitleTV);
            organitzador = view.findViewById(R.id.OrganitzaTV);
            duracio = view.findViewById(R.id.duracio);
            lloc = view.findViewById(R.id.lloc);
            assistents = view.findViewById(R.id.assistentsTV);
            cardView = itemView.findViewById(R.id.cardMoguda);
        }

        void bindData(final Moguda moguda){
            iv.setImageResource(moguda.getImageID());
            dataGran.setText(moguda.getDataTop());
            title.setText(moguda.getTitle());
            organitzador.setText(moguda.getOrganitzat());
            duracio.setText(moguda.getDuracio());
            lloc.setText(moguda.getLloc());
            assistents.setText(moguda.getAssistents());

        }

    }

}
