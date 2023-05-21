package com.example.weswing.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weswing.MainActivity;
import com.example.weswing.listener.SelectListenerMogudes;
import com.example.weswing.objects.Moguda;
import com.example.weswing.R;
import com.example.weswing.adapters.AdapterCalendari;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment implements SelectListenerMogudes {
    View vista;
    TextView amics, assistire, tot;
    TextView filtresTv;
    RecyclerView mogudes;

    List<TextView> tvs = new ArrayList<>();

    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_calendar, container, false);
        ((MainActivity) getActivity()).changeTitle("Calendar");
        init();
        initRecyclerTots();

        return vista;
    }

    public void init() {
        amics = vista.findViewById(R.id.amicsBTN);
        amics.setOnClickListener(v -> {
            clickTV(amics);
            initRecyclerAmics();
        });

        assistire = vista.findViewById(R.id.asistireBTN);
        assistire.setOnClickListener(v -> {
            clickTV(assistire);
            initRecyclerAssistire();
        });

        tot = vista.findViewById(R.id.totsBTN);
        tot.setOnClickListener(v -> {
            clickTV(tot);
            initRecyclerTots();
        });
        tvs.add(amics);
        tvs.add(assistire);
        tvs.add(tot);

        filtresTv = vista.findViewById(R.id.filtresTV);
        mogudes = vista.findViewById(R.id.recyclerMogudes);
    }

    public void initRecyclerTots() {
        List<Moguda> mogudaList = new ArrayList<>();

        mogudaList.add(new Moguda("20 de abril de 1990", "I'm beggining to see the light", R.drawable.we_swing_icon, "Organitzat per Edu", "Madrid, Spain", "17/04/2017-18/04/2017 | 2horas", "0 assistents | 0 amics"));
        mogudaList.add(new Moguda("18 de abril de 2017", "I've seen the light", R.drawable.professor3, "Organitzat per Aleix", "Barcelona, Spain", "18/04/2017-18/04/2017 | 5horas", "0 assistents | 0 amics"));
        mogudaList.add(new Moguda("19 de abril de 2017", "I wear sunglasses now", R.drawable.professor1, "Organitzat per Elsa Pato", "Madrid, Spain", "19/04/2017-18/04/2017 | 8horas", "0 assistents | 0 amics"));
        mogudaList.add(new Moguda("1 de mayo de 2017", "The Light is brighter now", R.drawable.we_swing_icon, "Organitzat per Elsa Badillo", "Santander, Spain", "1/05/2017-2/04/2017 | 9horas", "0 assistents | 0 amics"));
        mogudaList.add(new Moguda("5 de mayo de 2017", "I'm beggining to be blind", R.drawable.professor1, "Organitzat per Elsa Pato", "Granada, Spain", "5/05/2017-16/04/2017 | 1horas", "0 assistents | 0 amics"));
        mogudaList.add(new Moguda("7 de mayo de 2017", "I'm officialy blind", R.drawable.professor2, "Organitzat per Aitor Menta", "Madrid, Spain", "7/05/2017-8/04/2017 | 2horas", "0 assistents | 0 amics"));

        AdapterCalendari adapter = new AdapterCalendari(mogudaList, this);
        mogudes.setLayoutManager(new LinearLayoutManager(vista.getContext(), LinearLayoutManager.VERTICAL, false));
        mogudes.setAdapter(adapter);
    }

    public void initRecyclerAmics() {
        List<Moguda> mogudesList = new ArrayList<>();

        mogudesList.add(new Moguda("18 de abril de 2017", "Swing jam de dimarts", R.drawable.hola, "Organitzat per Swing Maniacs", "Barcelona, Spain", "18/04/2017 | 1hora", "1 assistents | 1 amics"));
        mogudesList.add(new Moguda("19 de abril de 2017", "Space swing jam", R.drawable.hola, "Organitzat per Labron James", "Madrid, Spain", "19/04/2017-18/04/2017 | 8horas", "8 assistents | 4 amics"));
        mogudesList.add(new Moguda("1 de mayo de 2017", "Pokemon swingers", R.drawable.hola, "Organitzat per Ash ketchum", "Madrid, Spain", "1/05/2017-2/04/2017 | 9horas", "10 assistents | 9 amics"));

        AdapterCalendari adapter = new AdapterCalendari(mogudesList, this);
        mogudes.setLayoutManager(new LinearLayoutManager(vista.getContext(), LinearLayoutManager.VERTICAL, false));
        mogudes.setAdapter(adapter);
    }

    public void initRecyclerAssistire() {
        List<Moguda> mogudaList = new ArrayList<>();

        mogudaList.add(new Moguda("19 de abril de 2017", "Bal bliss", R.drawable.hola, "Organitzat per big Mama", "Madrid, Spain", "19/04/2017-18/04/2017 | 8horas", "8 assistents | 4 amics"));

        AdapterCalendari adapter = new AdapterCalendari(mogudaList, this);
        mogudes.setLayoutManager(new LinearLayoutManager(vista.getContext(), LinearLayoutManager.VERTICAL, false));
        mogudes.setAdapter(adapter);
    }

    public void clickTV(TextView clicked) {
        clicked.setTextColor(getResources().getColor(R.color.white));
        clicked.setBackgroundColor(getResources().getColor(R.color.tematicRed));

        for (TextView tv :
                tvs) {
            if (tv.equals(clicked)) {
                tv.setTextColor(getResources().getColor(R.color.white));
                tv.setBackgroundColor(getResources().getColor(R.color.tematicRed));
            } else {
                tv.setTextColor(getResources().getColor(R.color.textgrey));
                tv.setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
    }

    @Override
    public void onItemClick(Moguda moguda) {
        shareMogudaFromCalendar(moguda);
        ((MainActivity) getActivity()).replaceFragment(new MoguduesFragment());
    }

    private void shareMogudaFromCalendar(Moguda moguda) {
        Gson gson = new Gson();
        String json = gson.toJson(moguda);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mogudaData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mogudaJson", json);
        editor.apply();
    }
}