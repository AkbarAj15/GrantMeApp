package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kodeJava.AES;

public class penerima_kata_sandi extends AppCompatActivity {
    TextView namaPengguna;
    EditText username, password;
    ImageButton btnKembali;
    Button ubah;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penerima_kata_sandi);
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
        // kembali ke halaman sebelumnya
        btnKembali = findViewById(R.id.kembaliSandi);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        namaPengguna = findViewById(R.id.namaPnrama3);
        username = findViewById(R.id.usrnameSandi);
        password = findViewById(R.id.passwordSandi);
        Intent intent = getIntent();
        String username1 = intent.getStringExtra("username");
        String password1 = intent.getStringExtra("password");
        String id = intent.getStringExtra("userId");
        namaPengguna.setText(username1);
        username.setText(username1);
        password.setText(password1);
        ubah = findViewById(R.id.ubahUser);
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username2 = username.getText().toString();
                String password2 = password.getText().toString();
                // mengenkripsi username dan password menggunakan AES algoritma
                String encryptedUsername = AES.encrypt(username2);
                String encryptedPassword = AES.encrypt(password2);
                // membuat path untuk firebase
                mDatabase.child("Penerima").child(id).child("username").setValue(encryptedUsername);
                mDatabase.child("Penerima").child(id).child("password").setValue(encryptedPassword);
                Toast.makeText(penerima_kata_sandi.this, "Perubahan Anda Berhasil!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}