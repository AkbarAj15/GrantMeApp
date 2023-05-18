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

// Activity untuk membuat username bagi penyedia
public class BuatUsername2 extends AppCompatActivity {

    Button btnDaftar;
    ImageButton btnKembali;
    EditText edtUsername, edtPassword;
    // koneksi firebase
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_username2);
        // firbase data koneksi
        mDatabase = FirebaseDatabase.getInstance().getReference("Penyedia");
        int userId = getIntent().getIntExtra("userId", 1);
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
        edtUsername = findViewById(R.id.buatusername2);
        edtPassword = findViewById(R.id.buatpassword2);
        btnDaftar = findViewById(R.id.daftarPenyedia);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // memberikan objek untuk edittext
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                // mengenkripsi username dan password menggunakan AES algoritma
                String encryptedUsername = AES.encrypt(username);
                String encryptedPassword = AES.encrypt(password);
                mDatabase.child(String.valueOf(userId)).child("username").setValue(encryptedUsername);
                mDatabase.child(String.valueOf(userId)).child("password").setValue(encryptedPassword);
                // memberikan objek untuk class AES sehingga data diambil di AES
                Toast.makeText(BuatUsername2.this, "Registrasi Anda Berhasil!", Toast.LENGTH_LONG).show();
                // membuat intent untuk ke halaman home
                Intent intent = new Intent(getApplicationContext(), penerima_home.class);
                startActivity(intent);
            }
        });
    }
}