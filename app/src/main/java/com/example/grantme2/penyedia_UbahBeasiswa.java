package com.example.grantme2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class penyedia_UbahBeasiswa extends AppCompatActivity {
    EditText etTanggalBuka, etTanggalTutup,etNamaBeasiswa, etJenisBeasiswa, etKuota, etKriteria, etUnggahPoster;
    ImageButton uploadPoster,btnKembali;
    private Uri imageUri;
    Button btnUbahBeasiswa;
    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Beasiswa");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_ubah_beasiswa);
        // kembali ke halaman sebelumnya
        btnKembali = findViewById(R.id.kembaliUbah);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // menghubungkan edittext ubah
        etNamaBeasiswa = findViewById(R.id.ubahnamabeasiswa);
        etJenisBeasiswa = findViewById(R.id.ubahjenisbeasiswa);
        etUnggahPoster = findViewById(R.id.ubahPoster);
        etTanggalBuka = findViewById(R.id.ubahTanggalBuka);
        etTanggalTutup = findViewById(R.id.ubahTanggalTutup);
        etKuota = findViewById(R.id.ubahKuota);
        etKriteria = findViewById(R.id.ubahKriteria);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                            String imagePath = getFileNameFromUri(imageUri); // Mendapatkan nama file dari URI gambar
                            etUnggahPoster.setText(imagePath);
                        } else {
                            Toast.makeText(penyedia_UbahBeasiswa.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        etTanggalBuka.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TampilTanggal();
            }
        });
        etTanggalTutup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TampilTanggal1();
            }
        });
        uploadPoster = findViewById(R.id.btnUbahPoster);
        uploadPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });


    }
    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
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
                etTanggalBuka.setText(text);
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
                etTanggalTutup.setText(text);
            }
        });
    }
    private String getFileNameFromUri(Uri uri) {
        String fileName = "";
        Cursor cursor = null;
        try {
            String[] projection = {MediaStore.Images.Media.DISPLAY_NAME};
            cursor = getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
                fileName = cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return fileName;
    }
}