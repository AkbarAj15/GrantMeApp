package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class penyedia_profil_bantuan extends AppCompatActivity {
    ImageButton btnKembali;
    TextView txtNama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_profil_bantuan);
        btnKembali = findViewById(R.id.kembaliBantuan2);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        txtNama = findViewById(R.id.usrPenyediaBantuan);
        txtNama.setText(username);

    }
}