package com.example.grantme2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

public class ActivityProfile extends AppCompatActivity {
    Button keluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Memulai transaksi fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Menambahkan fragment ke dalam layout dengan ID "fragment_container"
        penerima_FragProfil fragment = new penerima_FragProfil();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);

        // Menyelesaikan transaksi fragment
        fragmentTransaction.commit();
    }
}