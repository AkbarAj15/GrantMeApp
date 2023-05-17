package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Activity untuk membuat username bagi penerima
public class ActivityBuatUsername extends AppCompatActivity {
    Button btnDaftar;
    ImageButton btnKembali;
    EditText edtUsername, edtPassword;
    FirebaseDatabase database;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_username);
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
                Intent intent = new Intent(getApplicationContext(), penerima_home.class);
                   startActivity(intent);
//                try {
//                    // memberikan intent untuk mengambil nilai
//                    // memberikan objek untuk edittext
//                    String username = edtUsername.getText().toString().trim();
//                    String password = edtPassword.getText().toString().trim();
//                    // mengenkripsi username dan password menggunakan AES algoritma
//                    String encryptedUsername = AES.encrypt(username);
//                    String encryptedPassword = AES.encrypt(password);
//                    // membuat path untuk firebase
//                    database = FirebaseDatabase.getInstance();
//                    ref = database.getReference("Penerima");
//                    // memberikan objek untuk class AES sehingga data diambil di AES
//                    ref.child("username").setValue(encryptedUsername);
//                    ref.child("password").setValue(encryptedPassword);
//                    Toast.makeText(ActivityBuatUsername.this, "Registrasi Anda Berhasil!", Toast.LENGTH_LONG).show();
//                    // membuat intent untuk ke halaman home
//                    Intent intent = new Intent(getApplicationContext(), penerima_home.class);
//                    startActivity(intent);
//                } catch (Exception e) {
//                    // Menampilkan pesan kesalahan jika terjadi exception saat pindah halaman
//                    Toast.makeText(ActivityBuatUsername.this, "Terjadi kesalahan: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                }

            }
        });

    }
}