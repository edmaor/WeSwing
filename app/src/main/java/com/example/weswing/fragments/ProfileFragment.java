package com.example.weswing.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.weswing.MainActivity;
import com.example.weswing.R;
import com.example.weswing.objects.User;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {

    private TextView profileName;
    private ImageView profilePic;
    private User user;
    private View view;

    public ProfileFragment() {}

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initUserWithSharedPref();
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ((MainActivity) getActivity()).changeTitle(user.getName());
        initElementsProfile();
        return view;
    }

    private void initUserWithSharedPref() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("userJson", "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Gson gson = new Gson();
        user = gson.fromJson(json, User.class);
    }

    private void initElementsProfile() {
        profileName = view.findViewById(R.id.nom_user);
        profilePic = view.findViewById(R.id.profile_pic);

        profileName.setText(user.getName());
        profilePic.setImageResource(user.getIdProfilePic());

    }
}