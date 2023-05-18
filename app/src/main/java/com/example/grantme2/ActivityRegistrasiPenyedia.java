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

import kodeJava.Penyedia;


public class ActivityRegistrasiPenyedia extends AppCompatActivity {
    ImageButton btnKembali;
    Button btnLanjut;
    EditText edtNamaInstansi, edtEmail, edtNotelp;
    // mendeklarasikan objek dari database
    private DatabaseReference mDatabase;
    private int userId =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_penyedia);
        // membuat path database bernama penyedia
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
        // tombol kembali ke halaman *RegistrasiPenerima
        btnKembali = findViewById(R.id.backregispenyedia);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityWelcome2.class);
                startActivity(i);
            }
        });
        // tombol untuk melakukan pendaftaran
        btnLanjut = findViewById(R.id.lanjutpenyedia);
        // objek untuk setiap editText
        edtNamaInstansi = findViewById(R.id.namainstansi);
        edtEmail = findViewById(R.id.emailinstansi);
        edtNotelp = findViewById(R.id.notelinstansi);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mengambil nilai dari edittext ke dalam variabel
                String namaIns = edtNamaInstansi.getText().toString();
                String emailIns = edtEmail.getText().toString();
                String noTelpIns = edtNotelp.getText().toString();
                // memberikan nilai melalui class penyedia
                Penyedia penyedia = new Penyedia(namaIns, emailIns, noTelpIns);
                String userIdString1 = String.valueOf(userId);
                mDatabase.child("Penyedia").child(userIdString1).setValue(penyedia);
                Intent intent = new Intent(ActivityRegistrasiPenyedia.this, BuatUsername2.class);
                startActivity(intent);
                //membuat intent untuk mengirimkan nilai ke halaman buat username
                Intent i = new Intent(ActivityRegistrasiPenyedia.this, BuatUsername2.class);
                i.putExtra("userId", userId);
                startActivity(intent);
                userId++;
            }
        });
    }
}