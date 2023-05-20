package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kodeJava.Penerima;

public class ActivityRegistrasiPenerima extends AppCompatActivity {
    Button btnLanjut;
    EditText edtNama, edtEmail, edtNoTelp, textjenkel, texttl;
    ImageView tombolkalender;
    TextView btnMasuk;
    ImageButton btnKembali;
    // objek untuk mengkoneksikan firebase
    private DatabaseReference mDatabase;
    // membuat userId
    private int userId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_penerima);
        mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
        // Kembali ke Halaman Pilihan Penyedia atau penerima
        btnKembali = findViewById(R.id.backregispenerima);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityWelcome2.class);
                startActivity(i);
            }
        });
        // membuat objek edtiText namalengkap, sehingga dapat diisi
        edtNama = findViewById(R.id.edtNamalengkap);
        edtEmail = findViewById(R.id.emailpenerima);
        edtNoTelp = findViewById(R.id.notelepon);

        // objek tampil pilihan ttl penerima
        texttl = findViewById(R.id.ttlpenerima);

        // membuat kalender
        tombolkalender = findViewById(R.id.buttonkalender);
        tombolkalender.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TampilTanggal();
            }
        });
        // membuat dropdown jenis kelamin
        textjenkel = findViewById(R.id.jenkel);
        textjenkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), textjenkel);
                dropDownMenu.getMenuInflater().inflate(R.menu.dropdown_menu, dropDownMenu.getMenu());
                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        textjenkel.setText(menuItem.getTitle());
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });
        textjenkel.clearFocus();
        // menuju ke halaman selanjutnya *buat username dan password
        btnLanjut = findViewById(R.id.lanjutPenerima);
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mengambil nilai edittext kedalam String
                String namaLengkap = edtNama.getText().toString();
                String email = edtEmail.getText().toString();
                String noTelp = edtNoTelp.getText().toString();
                String ttl = texttl.getText().toString();
                String jenKel = textjenkel.getText().toString();
                // membuat nilai nya ke halaman selanjutnya
                Penerima penerima = new Penerima(namaLengkap, email, noTelp,
                        ttl, jenKel);
                String userIdString1 = String.valueOf(userId);
                mDatabase.child("Penerima").child(userIdString1).setValue(penerima);
                // Save the Penerima object to the Firebase database
                //membuat intent untuk mengirimkan nilai ke halaman buat username
                Intent i = new Intent(ActivityRegistrasiPenerima.this, ActivityBuatUsername.class);
                i.putExtra("userId", userId);
                startActivity(i);
                userId++;

            }
        });
        // menuju ke halaman login jika sudah memiliki akun
        btnMasuk = findViewById(R.id.txtlogin);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        });
    }
    // fungsi untuk menampilkan tanggal yang ada
    public void TampilTanggal(){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String tahun = ""+datePicker.getYear();
                String bulan = ""+(datePicker.getMonth()+1);
                String hari = ""+datePicker.getDayOfMonth();
                String text = hari+" / "+bulan+" / "+tahun;
                texttl.setText(text);
            }
        });
    }
}
