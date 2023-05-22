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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import kodeJava.Beasiswa;

public class penyedia_TambahBeasiswa extends AppCompatActivity {
    EditText etTanggalBuka, etTanggalTutup,etNamaBeasiswa, etJenisBeasiswa, etKuota, etKriteria, etUnggahPoster;
    ImageButton uploadPoster;
    private Uri imageUri;
    Button btntambahbea;
    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Beasiswa");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_tambah_beasiswa);
        // menghubungkan objek xml
        btntambahbea = findViewById(R.id.btntambahbeasiswa);
        // editext input
        etNamaBeasiswa = findViewById(R.id.inputnamabeasiswa);
        etJenisBeasiswa = findViewById(R.id.inputjenisbeasiswa);
        etKuota = findViewById(R.id.inputkuota);

        etUnggahPoster = findViewById(R.id.unggahposter);

        uploadPoster = findViewById(R.id.btnunggahpost);

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
                            Toast.makeText(penyedia_TambahBeasiswa.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        etTanggalBuka = findViewById(R.id.tanggalbuka);
        etTanggalBuka.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TampilTanggal();
            }
        });
        etTanggalTutup = findViewById(R.id.tanggaltutup);
        etTanggalTutup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TampilTanggal1();
            }
        });

        uploadPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        btntambahbea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                } else  {
                    Toast.makeText(penyedia_TambahBeasiswa.this, "Poster belum ada!", Toast.LENGTH_SHORT).show();
                }
            }
        });

}
    private void uploadToFirebase(Uri uri){
        String namaBeasiswa = etNamaBeasiswa.getText().toString();
        String jenisBeasiswa = etJenisBeasiswa.getText().toString();
        String tanggalBuka = etTanggalBuka.getText().toString();
        String tanggalTutup = etTanggalTutup.getText().toString();
        String kuota = etKuota.getText().toString();
        String kriteria = etKriteria.getText().toString();

        final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Beasiswa beasiswa = new Beasiswa(uri.toString(), namaBeasiswa, jenisBeasiswa,
                                tanggalBuka, tanggalTutup, kuota, kriteria);
                        String key = mDatabase.push().getKey();
                        mDatabase.child(key).setValue(beasiswa);
                        Toast.makeText(penyedia_TambahBeasiswa.this, "Beasiswa Ditambahkan", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(penyedia_TambahBeasiswa.this, penyedia_TambahBeasiswa.class);
                        startActivity(i);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(penyedia_TambahBeasiswa.this, "Sedang Diproses...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(penyedia_TambahBeasiswa.this, "Tambah Beasiswa Gagal!", Toast.LENGTH_SHORT).show();
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