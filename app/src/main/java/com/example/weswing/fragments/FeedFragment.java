package com.example.weswing.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weswing.MainActivity;
import com.example.weswing.R;
import com.example.weswing.adapters.AdapterNovetats;
import com.example.weswing.listener.SelectListenerNovetats;
import com.example.weswing.objects.Novetats;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment implements SelectListenerNovetats {
    List<Novetats> novetats;
    RecyclerView novetatsRecycler;
    AdapterNovetats novetatsAdapter;

    public FeedFragment() {
        // Required empty public constructor
    }
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed,container,false);
        ((MainActivity) getActivity()).changeTitle("Novetats");
        novetatsRecycler = view.findViewById(R.id.recyclerNovetats);
        initRecyclerReview();
        return view;
    }

    public void initRecyclerReview(){
        novetats = new ArrayList<>();
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Maria Garnacha asistira a I'm beggining to see the light","Fa 6h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Ramon Torres asistira a I'm beggining to see the light","Fa 9h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Aitor Menta asistira a I'm beggining to see the light","Fa 11h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Blanca Paloma asistira a I'm beggining to see the light","Fa 12h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Pere Guitart asistira a I'm beggining to see the light","Fa 23h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Elsa Capuntas asistira a I'm beggining to see the light","Fa 33h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Elsa Badillo asistira a I'm beggining to see the light","Fa 33h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Eric Pastor ha creat l'event: I'm begging to see the light","Fa 36h"));
        novetats.add(new Novetats(R.drawable.we_swing_icon,"Jacces Arnette asistira a I wear sunglasses now","Fa 40h"));

        novetatsAdapter = new AdapterNovetats(novetats,this);
        novetatsRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        novetatsRecycler.setHasFixedSize(true);
        novetatsRecycler.setAdapter(novetatsAdapter);
    }

    @Override
    public void onItemClicked(Novetats novetat) {
        Log.i("Novetat clicked", novetat.getDesc());
    }
}