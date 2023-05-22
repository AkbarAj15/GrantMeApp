package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kodeJava.Beasiswa;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link penyedia_FragHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class penyedia_FragHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public penyedia_FragHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment penyedia_FragHome.
     */
    // TODO: Rename and change types and number of parameters
    public static penyedia_FragHome newInstance(String param1, String param2) {
        penyedia_FragHome fragment = new penyedia_FragHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penyedia__home, container, false);
    }

    private GridView gridView_penyedia;
    private ArrayList<Beasiswa> dataList;
    private AdapterPenyedia adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Beasiswa");
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView txtUsername = (TextView) getView().findViewById(R.id.userPenyedia);
        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("username");
        txtUsername.setText(username);
        ImageView btnTambah = (ImageView) getView().findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), penyedia_TambahBeasiswa.class);
                startActivity(i);
            }
        });
        gridView_penyedia = view.findViewById(R.id.grid_home_penyedia);
//        btnUbah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = getActivity().getIntent();
//                String idBeasiswa = intent1.getStringExtra("idBeasiswa");
//                String namaBeasiswa = intent1.getStringExtra("namaBeasiswa");
//                String jenisBeasiswa = intent1.getStringExtra("jenisBeasiswa");
//                String tanggalBuka = intent1.getStringExtra("tanggalBuka");
//                String tanggalTutup = intent1.getStringExtra("tanggalTutup");
//                String kuota = intent1.getStringExtra("kuota");
//                String kriteria = intent1.getStringExtra("kriteria");
//                Intent i = new Intent(requireContext(), penyedia_UbahBeasiswa.class);
//                i.putExtra("idBeasiswa", idBeasiswa);
//                i.putExtra("namaBeasiswa", namaBeasiswa);
//                i.putExtra("jenisBeasiswa", jenisBeasiswa);
//                i.putExtra("tanggalBuka", tanggalBuka);
//                i.putExtra("tanggalTutup", tanggalTutup);
//                i.putExtra("kuota", kuota);
//                i.putExtra("kriteria", kriteria);
//                startActivity(i);
//            }
//        });
        dataList = new ArrayList<>();
        adapter = new AdapterPenyedia(getActivity(), dataList);
        gridView_penyedia.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Beasiswa beasiswa = dataSnapshot.getValue(Beasiswa.class);
                    dataList.add(beasiswa);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}