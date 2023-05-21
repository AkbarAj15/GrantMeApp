package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class penerima_profil_dokumenSaya extends AppCompatActivity {
    ImageButton btnKembali;
    TextView namaPengguna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokumen_saya);
        btnKembali = findViewById(R.id.kembaliDokumen);
        namaPengguna = findViewById(R.id.namaPnrama2);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String username1 = intent.getStringExtra("username");
        namaPengguna.setText(username1);
    }
}