package com.example.weswing.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.weswing.MainActivity;
import com.example.weswing.R;
import com.example.weswing.objects.Moguda;
import com.example.weswing.objects.User;
import com.google.gson.Gson;

import kotlinx.coroutines.internal.MainDispatcherFactory;

public class MoguduesFragment extends Fragment implements View.OnClickListener {

    Moguda moguda;
    View view, organitzador;
    TextView titol,tipus,data,descripcio;
    Button asistencia;
//    CardView cardView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void initComponents(){
        organitzador = view.findViewById(R.id.mogudaOrganitzador);
//        cardView = view.findViewById(R.id.cardView);
        titol = view.findViewById(R.id.nomMoguda);
        tipus = view.findViewById(R.id.tipusMoguda);
        descripcio = view.findViewById(R.id.descriptionText);
        data = view.findViewById(R.id.dataMoguda);
        asistencia = view.findViewById(R.id.asistentsButton);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initMogudaSharedPref();
        view = inflater.inflate(R.layout.fragment_mogudues, container, false);
        ((MainActivity) getActivity()).changeTitle("Moguda");
        // Inflate the layout for this fragment
        initComponents();
        displayMoguda();
        asistencia.setOnClickListener(this);
        organitzador.setOnClickListener(v -> ((MainActivity) getActivity()).replaceFragment(new EscolaFragment()));
        return view;
    }

    @Override
    public void onClick(View view) {
        ((MainActivity) getActivity()).replaceFragment(new AssistentsFragment());
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }

    private void initMogudaSharedPref() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mogudaData", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("mogudaJson", "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Gson gson = new Gson();
        moguda = gson.fromJson(json, Moguda.class);
    }

    private void displayMoguda() {
        titol.setText(moguda.getTitle());
        data.setText(moguda.getLloc() + " | " + moguda.getDataTop());
    }
}