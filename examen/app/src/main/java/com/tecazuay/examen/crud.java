package com.tecazuay.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class crud extends AppCompatActivity {

    Button btncancel;

    Button btncrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        btncancel = findViewById(R.id.btn_cancelar);
        btncrear = findViewById(R.id.btn_crear);

        btncrear.setOnClickListener(l -> Demo());
        btncancel.setOnClickListener(l -> Demo());

    }

    public void Demo() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}