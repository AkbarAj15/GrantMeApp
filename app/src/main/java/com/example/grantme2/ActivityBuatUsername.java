package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kodeJava.AES;

// Activity untuk membuat username bagi penerima
public class ActivityBuatUsername extends AppCompatActivity {
    Button btnDaftar;
    ImageButton btnKembali;
    EditText edtUsername, edtPassword;
    // membuat objek firebase
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_username);
        // menghubungkan firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
        int userId = getIntent().getIntExtra("userId", 1);
        // tombol kembali ke halaman *RegistrasiPenerima
        btnKembali = findViewById(R.id.backUserPnrma);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityRegistrasiPenerima.class);
                startActivity(i);
            }
        });
        // tombol untuk melakukan pendaftaran
        btnDaftar = findViewById(R.id.daftarPnrma);
        edtUsername = findViewById(R.id.buatusername);
        edtPassword = findViewById(R.id.buatpassword);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                // mengenkripsi username dan password menggunakan AES algoritma
                String encryptedUsername = AES.encrypt(username);
                String encryptedPassword = AES.encrypt(password);
                // membuat path untuk firebase
                mDatabase.child("Penerima").child(String.valueOf(userId)).child("username").setValue(encryptedUsername);
                mDatabase.child("Penerima").child(String.valueOf(userId)).child("password").setValue(encryptedPassword);
                // memberikan objek untuk class AES sehingga data diambil di AES
                Toast.makeText(ActivityBuatUsername.this, "Registrasi Anda Berhasil!", Toast.LENGTH_LONG).show();
                // membuat intent untuk ke halaman home
                Intent intent = new Intent(getApplicationContext(), penerima_home.class);
                startActivity(intent);

            }
        });
    }
}