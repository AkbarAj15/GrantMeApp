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
import android.widget.ImageButton;
import android.widget.TextView;
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

public class penerima_profil_dokumenSaya extends AppCompatActivity {
    ImageButton btnKembali, btnKtp, btnKK, btnFoto, btnIjazah, btnTranskrip;
    TextView namaPengguna;
    TextView txtStatusKtp, txtStatusKK, txtStatusFoto, txtStatusIjzah, txtStatusTranskrip;
    Button simpan;
    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Pengguna");
    private Uri imageUri;
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokumen_saya);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                        } else {
                            Toast.makeText(penerima_profil_dokumenSaya.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        // inisialisasi text tiap id
        txtStatusKtp = findViewById(R.id.statusktp);
        txtStatusKK = findViewById(R.id.statuskk);
        txtStatusFoto = findViewById(R.id.statusfoto);
        txtStatusIjzah = findViewById(R.id.statusijazah);
        txtStatusTranskrip = findViewById(R.id.statustranskrip);

        btnKembali = findViewById(R.id.kembaliDokumen);
        namaPengguna = findViewById(R.id.namaPnrama2);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String kk = intent.getStringExtra("kk");
        String fotoProfil = intent.getStringExtra("fotoProfil");
        String transkrip = intent.getStringExtra("transkrip");
        String ijazah = intent.getStringExtra("ijazah");
        String ktp = intent.getStringExtra("ktp");
        String username1 = intent.getStringExtra("username");
        namaPengguna.setText(username1);
        if(ktp != null){
            txtStatusKtp.setText("Sudah Upload");
        }
        if(fotoProfil != null){
            txtStatusFoto.setText("Sudah Upload");
        }
        if(transkrip != null){
            txtStatusTranskrip.setText("Sudah Upload");
        }
        if(ijazah != null){
            txtStatusIjzah.setText("Sudah Upload");
        }
        if(kk != null){
            txtStatusKK.setText("Sudah Upload");
        }

        // insisialisasi button tiap id
        btnKtp = findViewById(R.id.upktp);
        btnKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("application/pdf");
                activityResultLauncher.launch(photoPicker);
                if (imageUri != null){
                    uploadToFirebase(imageUri, "KTP");
                }

            }
        });
        btnFoto = findViewById(R.id.upfoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
                if (imageUri != null){
                    uploadToFirebase(imageUri, "fotoProfil");
                }
            }
        });
        btnKK = findViewById(R.id.upkk);
        btnKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("application/pdf");
                activityResultLauncher.launch(photoPicker);
                if (imageUri != null){
                    uploadToFirebase(imageUri, "kartuKeluarga");
                }
            }
        });
        btnIjazah = findViewById(R.id.upijazah);
        btnIjazah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("application/pdf");
                activityResultLauncher.launch(photoPicker);
                if (imageUri != null){
                    uploadToFirebase(imageUri, "ijazahTerakhir");
                }
            }
        });
        btnTranskrip = findViewById(R.id.uptranskrip);
        btnTranskrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("application/pdf");
                activityResultLauncher.launch(photoPicker);
                if (imageUri != null){
                    uploadToFirebase(imageUri, "transkripNilai");
                }
            }
        });
        simpan = findViewById(R.id.simpandokumen);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(penerima_profil_dokumenSaya.this, "Dokumen Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void uploadToFirebase(Uri uri, String dokumen){
        // Generate nama file unik untuk diunggah
        String fileName = System.currentTimeMillis() + "." + getFileExtension(uri);

        // Mendapatkan referensi storage untuk file yang akan diunggah
        StorageReference fileReference = storageReference.child(fileName);

        // Mengunggah file ke Firebase Storage
        fileReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Mendapatkan URL download file yang diunggah
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUri) {
                        Intent intent = getIntent();
                        String id = intent.getStringExtra("userId");
                        // Menyimpan path dokumen ke Firebase Realtime Database
                        mDatabase.child("Penerima").child(id).child("dokumenSaya").child(dokumen).setValue(downloadUri.toString());
                        Toast.makeText(penerima_profil_dokumenSaya.this, "Dokumen Diunggah", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(penerima_profil_dokumenSaya.this, dokumen + "Sedang Diunggah....", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(penerima_profil_dokumenSaya.this, "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
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