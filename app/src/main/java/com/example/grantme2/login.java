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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // mengkoneksikan dengan firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
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
                cekPenerima(new LoginCallback() {
                    @Override
                    public void onLoginSuccess() {
                        Toast.makeText(login.this, "Berhasil Masuk Sebagai Penerima!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onLoginFailure() {
                        cekPenyedia(new LoginCallback() {
                            @Override
                            public void onLoginSuccess() {
                                Toast.makeText(login.this, "Berhasil Masuk Sebagai Penyedia!", Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onLoginFailure() {
                                Toast.makeText(login.this, "Data Penyedia tidak ditemukan", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });

    }
    public void cekPenerima(LoginCallback callback){
        String username = edtUser.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        if(username.isEmpty()){
            edtUser.setError("username kosong");
        }else if (password.isEmpty()) {
            edtPass.setError("Password kosong");
        }else{
        mDatabase.child("Penerima").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot : snapshot.getChildren()){
                    String id = userSnapshot.getKey();
                    String encryptedUsername = snapshot.child(id).child("username").getValue(String.class);
                    String encryptedPassword = snapshot.child(id).child("password").getValue(String.class);

                    if (encryptedUsername != null && encryptedPassword != null) {
                        // Data ditemukan di path "Penerima"
                        String decryptedUsername = AES.decrypt(encryptedUsername);
                        String decryptedPassword = AES.decrypt(encryptedPassword);

                        if (decryptedUsername != null && decryptedPassword != null) {
                            if (username.equals(decryptedUsername) && password.equals(decryptedPassword)) {
                                String namaLkpFb = snapshot.child(id).child("namaLengkap").getValue(String.class);
                                String email = snapshot.child(id).child("email").getValue(String.class);
                                String ttl = snapshot.child(id).child("ttl").getValue(String.class);
                                String jenkel = snapshot.child(id).child("jenKel").getValue(String.class);
                                String noTelp = snapshot.child(id).child("noTelepon").getValue(String.class);
                                // mengirim data ke halaman informasi pribadi
                                Intent i = new Intent(login.this, penerima_home.class);
                                i.putExtra("namaLengkap", namaLkpFb);
                                i.putExtra("email", email);
                                i.putExtra("ttl", ttl);
                                i.putExtra("jenKel", jenkel);
                                i.putExtra("noTelp", noTelp);
                                i.putExtra("username", decryptedUsername);
                                i.putExtra("password", decryptedPassword);
                                startActivity(i);
                                callback.onLoginSuccess();
                            } else {
                                callback.onLoginFailure();
                            }
                        } else {
                            Toast.makeText(login.this, "Gagal mendekripsi username atau password", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(login.this, "Data penerima tidak ditemukan", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }
    public void cekPenyedia(LoginCallback callback){
        String username = edtUser.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        if (username.isEmpty()){
            edtUser.setError("Username kosong");
        } else if (password.isEmpty()) {
            edtPass.setError("Password kosong");
        }else{
        mDatabase.child("Penyedia").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot : snapshot.getChildren()){
                    String id = userSnapshot.getKey();
                    String encryptedUsernamePenyedia = snapshot.child(id).child("username").getValue(String.class);
                    String encryptedPasswordPenyedia = snapshot.child(id).child("password").getValue(String.class);

                    if (encryptedUsernamePenyedia != null && encryptedPasswordPenyedia != null) {
                        // Data ditemukan di path "Penyedia"
                        String decryptedUsernamePenyedia = AES.decrypt(encryptedUsernamePenyedia);
                        String decryptedPasswordPenyedia = AES.decrypt(encryptedPasswordPenyedia);

                        if (decryptedUsernamePenyedia != null && decryptedPasswordPenyedia != null) {
                            if (username.equals(decryptedUsernamePenyedia) && password.equals(decryptedPasswordPenyedia)) {
                                String namaInstansi = snapshot.child(id).child("namaInstansi").getValue(String.class);
                                String emailIns = snapshot.child(id).child("emailInstansi").getValue(String.class);
                                String noTelpIns = snapshot.child(id).child("noTelpInstansi").getValue(String.class);
                                Intent i = new Intent(login.this, penyedia_home.class);
                                i.putExtra("namaIns", namaInstansi);
                                i.putExtra("emailIns", emailIns);
                                i.putExtra("noTelpIns", noTelpIns);
                                i.putExtra("username", decryptedUsernamePenyedia);
                                i.putExtra("password", decryptedPasswordPenyedia);
                                i.putExtra("userId", id);
                                startActivity(i);
                                callback.onLoginSuccess();
                            } else {
                            }
                        } else {
                            Toast.makeText(login.this, "Gagal mendekripsi username atau password", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        callback.onLoginFailure();
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }
    public interface LoginCallback {
        void onLoginSuccess();
        void onLoginFailure();
    }
}

