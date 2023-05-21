package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class penerima_profil_bantuan extends AppCompatActivity {
    TextView txtNama;
    ImageButton btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
        btnKembali = findViewById(R.id.kembaliBantuan);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtNama = findViewById(R.id.namaPnrama4);
        Intent intent = getIntent();
        String username1 = intent.getStringExtra("username");
        txtNama.setText(username1);
    }
}