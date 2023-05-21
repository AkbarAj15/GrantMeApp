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

public class penyedia_profil_informasiPribadi extends AppCompatActivity {
    TextView txtUsername;
    EditText etUsername, etPassword, etEmail, etNamaIns, etNotelp;
    ImageButton btnKembali;
    Button ubah;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_profil_informasi_pribadi);
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
        // kembali ke halaman profil penyedia
        btnKembali = findViewById(R.id.kembaliInformasi);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etNamaIns = findViewById(R.id.namainstansi);
        etEmail = findViewById(R.id.emailpenyediaprofil);
        etUsername = findViewById(R.id.usernamepenyediaprofil);
        etPassword = findViewById(R.id.passpenyediaprofil);
        etNotelp = findViewById(R.id.noTelpPenyedia);
        Intent intent = getIntent();
        String namaIns = intent.getStringExtra("namaIns");
        String emailIns = intent.getStringExtra("emailIns");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String noTelp1 = intent.getStringExtra("noTelpIns");
        String id = intent.getStringExtra("userId");

        etNamaIns.setText(namaIns);
        etEmail.setText(emailIns);
        etUsername.setText(username);
        etPassword.setText(password);
        etNotelp.setText(noTelp1);
        ubah = findViewById(R.id.ubahProfil);
        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaIns = etNamaIns.getText().toString();
                String emailIns = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String noTelp = etNotelp.getText().toString();
                // mengenkripsi username dan password menggunakan AES algoritma
                String encryptedUsername = AES.encrypt(username);
                String encryptedPassword = AES.encrypt(password);
                mDatabase.child("Penyedia").child(id).child("username").setValue(encryptedUsername);
                mDatabase.child("Penyedia").child(id).child("password").setValue(encryptedPassword);
                mDatabase.child("Penyedia").child(id).child("namaInstansi").setValue(namaIns);
                mDatabase.child("Penyedia").child(id).child("emailInstansi").setValue(emailIns);
                mDatabase.child("Penyedia").child(id).child("noTeleponInstansi").setValue(noTelp);
                Toast.makeText(penyedia_profil_informasiPribadi.this, "Perubahan Anda Berhasil!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}