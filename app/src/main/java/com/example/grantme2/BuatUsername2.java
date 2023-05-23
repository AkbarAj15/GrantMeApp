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
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
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
                if(username.isEmpty()){
                    edtPassword.setError("username tidak boleh kosong");
                } else if (password.isEmpty()) {
                    edtPassword.setError("password tidak boleh kosong");
                }else {
                    Intent intent = getIntent();
                    String id = intent.getStringExtra("userId");
                    // mengenkripsi username dan password menggunakan AES algoritma
                    String encryptedUsername = AES.encrypt(username);
                    String encryptedPassword = AES.encrypt(password);
                    mDatabase.child("Penyedia").child(id).child("username").setValue(encryptedUsername);
                    mDatabase.child("Penyedia").child(id).child("password").setValue(encryptedPassword);
                    // memberikan objek untuk class AES sehingga data diambil di AES
                    Toast.makeText(BuatUsername2.this, "Registrasi Anda Berhasil!", Toast.LENGTH_LONG).show();
                    // mengirimkan nilai ke semua halaman
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String namaInstansi = snapshot.child("Penyedia").child(id).child("namaInstansi").getValue(String.class);
                            String emailIns = snapshot.child("Penyedia").child(id).child("emailInstansi").getValue(String.class);
                            String noTelpIns = snapshot.child("Penyedia").child(id).child("noTelpInstansi").getValue(String.class);
                            String decryptedUsername = AES.decrypt(encryptedUsername);
                            String decryptedPassword = AES.decrypt(encryptedPassword);
                            Intent i = new Intent(getApplicationContext(), penyedia_home.class);
                            i.putExtra("namaIns", namaInstansi);
                            i.putExtra("emailIns", emailIns);
                            i.putExtra("noTelpIns", noTelpIns);
                            i.putExtra("username", decryptedUsername);
                            i.putExtra("password", decryptedPassword);
                            i.putExtra("userId", id);
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