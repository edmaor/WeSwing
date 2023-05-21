package com.example.weswing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Inici extends AppCompatActivity {
    Button btnInici;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inici);
        btnInici = findViewById(R.id.iniciarSesionbtn);
        btnRegister = findViewById(R.id.registroBtn);
        btnInici.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),Register.class);
            startActivity(intent);
        });
    }
}