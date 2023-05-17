package com.example.grantme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

// Activity untuk membuat username bagi penyedia
public class BuatUsername2 extends AppCompatActivity {

    Button btnDaftar;
    ImageButton btnKembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_username2);
        // tombol kembali ke halaman *RegistrasiPenerima
        btnKembali = findViewById(R.id.backbuatusername);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityRegistrasiPenyedia.class);
                startActivity(i);
            }
        });
        // tombol untuk melakukan pendaftaran
        btnDaftar = findViewById(R.id.daftarPenyedia);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), penerima_home.class);
                startActivity(i);
            }
        });
    }
}