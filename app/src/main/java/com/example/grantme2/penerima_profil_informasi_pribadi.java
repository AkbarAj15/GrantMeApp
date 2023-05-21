package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class penerima_profil_informasi_pribadi extends AppCompatActivity {
    ImageButton btnKembali;
    ImageView fotoProfil;
    TextView namaProfil, statusProfil;
    EditText namaLengkap, email, ttl,jenkel,noTelp;
    Button ubah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penerima_informasi_pribadi);
        // kembali ke halaman sebelumnya
        btnKembali = findViewById(R.id.backPrflPnrma);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // deklarasi untuk menghubungkan data penerima
        fotoProfil = findViewById(R.id.fotoPnrma);
        namaProfil = findViewById(R.id.namaPnrama);
        statusProfil = findViewById(R.id.statusPnrma);
        namaLengkap = findViewById(R.id.namaLkpPrfl);
        email = findViewById(R.id.emailPrfl);
        ttl = findViewById(R.id.ttlPrfl);
        jenkel = findViewById(R.id.jenkelPrfl);
        noTelp = findViewById(R.id.noTelpPrfl);
        tampilData();
        ubah = findViewById(R.id.simpanPrfl);
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void tampilData(){
        Intent intent = getIntent();
        String namaPenerima = intent.getStringExtra("namaLengkap");
        String email1 = intent.getStringExtra("email");
        String ttl1 = intent.getStringExtra("ttl");
        String jenKel1 = intent.getStringExtra("jenKel");
        String noTelp1 = intent.getStringExtra("noTelp");
        String username1 = intent.getStringExtra("username");

        namaLengkap.setText(namaPenerima);
        email.setText(email1);
        ttl.setText(ttl1);
        jenkel.setText(jenKel1);
        noTelp.setText(noTelp1);
        namaProfil.setText(username1);

    }
}