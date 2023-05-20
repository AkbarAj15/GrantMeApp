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

public class login extends AppCompatActivity {
    Button btnMasuk;
    EditText edtUser, edtPass;
    ImageButton btnKembali;
    // deklarasi firebase
    private DatabaseReference mDatabase;
    private int userId =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // mengkoneksikan dengan firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Penerima");
        // membuat tombol kembali
        btnKembali = findViewById(R.id.backLogin);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityWelcome2.class);
                startActivity(i);
            }
        });
        // objek untuk login
        edtUser = findViewById(R.id.userlogin);
        edtPass = findViewById(R.id.passlogin);
        btnMasuk = findViewById(R.id.login);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userIdS = String.valueOf(userId);
                String username = edtUser.getText().toString().trim();
                String password = edtPass.getText().toString().trim();
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String encryptedUsername = snapshot.child(userIdS).child("username").getValue(String.class);
                        String encryptedPassword = snapshot.child(userIdS).child("password").getValue(String.class);
                        if (encryptedUsername != null && encryptedPassword != null) {
                            String decryptedUsername = AES.decrypt(encryptedUsername);
                            String decryptedPassword = AES.decrypt(encryptedPassword);
                            if (decryptedUsername != null && decryptedPassword != null) {
                                if (username.equals(decryptedUsername) && password.equals(decryptedPassword)) {
                                    Intent i = new Intent(login.this, penerima_home.class);
                                    startActivity(i);
                                    Toast.makeText(login.this, "Login Anda Berhasil!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(login.this, "Login Anda Gagal!", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(login.this, "Gagal mendekripsi username atau password", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(login.this, "Data pengguna tidak ditemukan", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}

