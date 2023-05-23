package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                if (username.isEmpty()){
                    edtUsername.setError("username tidak boleh kosong");
                } else if (password.isEmpty()) {
                    edtPassword.setError("Password Tidak boleh kosong");
                }else {
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("userId");
                    // mengenkripsi username dan password menggunakan AES algoritma
                    String encryptedUsername = AES.encrypt(username);
                    String encryptedPassword = AES.encrypt(password);
                    // membuat path untuk firebase
                    mDatabase.child("Penerima").child(id).child("username").setValue(encryptedUsername);
                    mDatabase.child("Penerima").child(id).child("password").setValue(encryptedPassword);
                    // memberikan objek untuk class AES sehingga data diambil di AES
                    Toast.makeText(ActivityBuatUsername.this, "Registrasi Anda Berhasil!", Toast.LENGTH_LONG).show();
                    // membuat intent untuk ke halaman home
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String namaLkpFb = snapshot.child("Penerima").child(id).child("namaLengkap").getValue(String.class);
                            String email = snapshot.child("Penerima").child(id).child("email").getValue(String.class);
                            String ttl = snapshot.child("Penerima").child(id).child("ttl").getValue(String.class);
                            String jenkel = snapshot.child("Penerima").child(id).child("jenKel").getValue(String.class);
                            String noTelp = snapshot.child("Penerima").child(id).child("noTelepon").getValue(String.class);
                            String ktp = snapshot.child("Penerima").child(id).child("Dokumen Saya").child("KTP").getValue(String.class);
                            String kk = snapshot.child("Penerima").child(id).child("Dokumen Saya").child("Kartu Keluarga").getValue(String.class);
                            String fotoProfil = snapshot.child("Penerima").child("Penerima").child(id).child("Dokumen Saya").child("Foto Profil").getValue(String.class);
                            String ijazah = snapshot.child("Penerima").child(id).child("Dokumen Saya").child("Ijazah Terakhir").getValue(String.class);
                            String transkrip = snapshot.child("Penerima").child(id).child("Dokumen Saya").child("Transkrip Nilai").getValue(String.class);
                            Intent i = new Intent(getApplicationContext(), penerima_home.class);
                            String decryptedUsername = AES.decrypt(encryptedUsername);
                            String decryptedPassword = AES.decrypt(encryptedPassword);
                            i.putExtra("namaLengkap", namaLkpFb);
                            i.putExtra("email", email);
                            i.putExtra("ttl", ttl);
                            i.putExtra("jenKel", jenkel);
                            i.putExtra("noTelp", noTelp);
                            i.putExtra("username", decryptedUsername);
                            i.putExtra("password", decryptedPassword);
                            i.putExtra("userId", id);
                            i.putExtra("ktp", ktp);
                            i.putExtra("kk", kk);
                            i.putExtra("fotoProfil", fotoProfil);
                            i.putExtra("ijazah", ijazah);
                            i.putExtra("transkrip", transkrip);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }
}