package com.example.grantme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class ActivityRegistrasiPenyedia extends AppCompatActivity {
    ImageButton btnKembali;
    Button btnLanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_penyedia);
        // tombol kembali ke halaman *RegistrasiPenerima
        btnKembali = findViewById(R.id.backregispenyedia);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityWelcome2.class);
                startActivity(i);
            }
        });
        // tombol untuk melakukan pendaftaran
        btnLanjut = findViewById(R.id.lanjutpenyedia);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BuatUsername2.class);
                startActivity(i);
            }
        });
    }
}