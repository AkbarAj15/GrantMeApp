package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link penyedia_FragProfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class penyedia_FragProfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public penyedia_FragProfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment penyedia_FragProfil.
     */
    // TODO: Rename and change types and number of parameters
    public static penyedia_FragProfil newInstance(String param1, String param2) {
        penyedia_FragProfil fragment = new penyedia_FragProfil();
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
        return inflater.inflate(R.layout.fragment_penyedia__profil, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView txtUsername = (TextView) getView().findViewById(R.id.namapenyedia);
        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("username");
        txtUsername.setText(username);
        TextView txtInformasi = (TextView) getView().findViewById(R.id.infoPenyedia);
        txtInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                String namaIns = intent.getStringExtra("namaIns");
                String emailIns = intent.getStringExtra("emailIns");
                String noTelpIns = intent.getStringExtra("noTelpIns");
                String username = intent.getStringExtra("username");
                String password = intent.getStringExtra("password");
                String id = intent.getStringExtra("userId");
                Intent i = new Intent(getContext(), penyedia_profil_informasiPribadi.class);
                i.putExtra("namaIns", namaIns);
                i.putExtra("emailIns", emailIns);
                i.putExtra("noTelpIns", noTelpIns);
                i.putExtra("username", username);
                i.putExtra("password", password);
                i.putExtra("userId", id);
                startActivity(i);
            }
        });
        TextView txtBantuan = (TextView) getView().findViewById(R.id.bantuanPenyedia);
        txtBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                String username = intent.getStringExtra("username");
                Intent i = new Intent(getContext(), penyedia_profil_bantuan.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });
    }
}