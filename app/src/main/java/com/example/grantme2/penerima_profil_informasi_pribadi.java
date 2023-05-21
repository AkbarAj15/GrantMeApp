package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class penerima_profil_informasi_pribadi extends AppCompatActivity {
    ImageButton btnKembali;
    ImageView fotoProfil;
    TextView namaProfil, statusProfil;
    EditText namaLengkap, email, ttl,jenkel,noTelp;
    Button ubah;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penerima_informasi_pribadi);
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
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
        //======= mengambil nilai data penerima
        Intent intent = getIntent();
        String namaPenerima = intent.getStringExtra("namaLengkap");
        String email1 = intent.getStringExtra("email");
        String ttl1 = intent.getStringExtra("ttl");
        String jenKel1 = intent.getStringExtra("jenKel");
        String noTelp1 = intent.getStringExtra("noTelp");
        String username1 = intent.getStringExtra("username");
        String id = intent.getStringExtra("userId");
        namaLengkap.setText(namaPenerima);
        email.setText(email1);
        ttl.setText(ttl1);
        jenkel.setText(jenKel1);
        noTelp.setText(noTelp1);
        namaProfil.setText(username1);

        // merubah data penerima
        ubah = findViewById(R.id.ubahPenerima);
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaPenerima = namaLengkap.getText().toString();
                String email1 = email.getText().toString();
                String ttl1 = ttl.getText().toString();
                String jenKel1 = jenkel.getText().toString();
                String noTelp1 = noTelp.getText().toString();
                mDatabase.child("Penerima").child(id).child("namaLengkap").setValue(namaPenerima);
                mDatabase.child("Penerima").child(id).child("email").setValue(email1);
                mDatabase.child("Penerima").child(id).child("jenKel").setValue(jenKel1);
                mDatabase.child("Penerima").child(id).child("noTelepon").setValue(noTelp1);
                mDatabase.child("Penerima").child(id).child("ttl").setValue(ttl1);
                Toast.makeText(penerima_profil_informasi_pribadi.this, "Perubahan Anda Berhasil!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}