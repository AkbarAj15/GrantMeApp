package com.example.grantme2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class penyedia_TambahBeasiswa extends AppCompatActivity {
    EditText tanggalBuka, tanggalTutup, uploadButton, uploadCaption;
    ImageButton uploadPoster;
    private Uri imageUri;
    Button btntambahbea;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("poster");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_tambah_beasiswa);

        btntambahbea = findViewById(R.id.btntambahbeasiswa);
        uploadCaption = findViewById(R.id.inputnamabeasiswa);
        uploadPoster = findViewById(R.id.btnunggahpost);
        tanggalBuka = findViewById(R.id.tanggalbuka);
        tanggalBuka.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TampilTanggal();
            }
        });
        tanggalTutup = findViewById(R.id.tanggaltutup);
        tanggalTutup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TampilTanggal1();
            }
        });
        uploadPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Save the Penerima object to the Firebase database


                // Navigate to the next activity
                Intent i = new Intent(getApplicationContext(), UploadActivity.class);
                startActivity(i);

            }
        });
    }

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
                tanggalBuka.setText(text);
            }
        });
    }
    public void TampilTanggal1(){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String tahun = ""+datePicker.getYear();
                String bulan = ""+(datePicker.getMonth()+1);
                String hari = ""+datePicker.getDayOfMonth();
                String text = hari+" / "+bulan+" / "+tahun;
                tanggalTutup.setText(text);
            }
        });
    }

}