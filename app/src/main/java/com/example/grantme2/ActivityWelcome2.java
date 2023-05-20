package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWelcome2 extends AppCompatActivity {
    Button btnPenyedia, btnPenerima;
    TextView txtMsk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        btnPenerima = findViewById(R.id.btnPenerima);
        btnPenyedia = findViewById(R.id.btnPenyedia);
        txtMsk = findViewById(R.id.txtMasuk);
        btnPenyedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityRegistrasiPenyedia.class);
                startActivity(i);
            }
        });
        btnPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityRegistrasiPenerima.class);
                startActivity(i);
            }
        });
        txtMsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        });
    }
}