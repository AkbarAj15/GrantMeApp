package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link penerima_FragProfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class penerima_FragProfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public penerima_FragProfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragProfil.
     */
    // TODO: Rename and change types and number of parameters
    public static penerima_FragProfil newInstance(String param1, String param2) {
        penerima_FragProfil fragment = new penerima_FragProfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

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
        return inflater.inflate(R.layout.fragment_penerima_profil, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView txtNama = (TextView) getView().findViewById(R.id.namapenerima);
        Intent intent =getActivity().getIntent();
        String username = intent.getStringExtra("username");

        txtNama.setText(username);
        Button btn = (Button) getView().findViewById(R.id.keluarprofil);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), login.class);
                startActivity(i);
            }
        });
        TextView txtInformasi = (TextView) getView().findViewById(R.id.informasiPribadi);
        txtInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mengambil data login
                Intent intent = getActivity().getIntent();
                String namaLengkap = intent.getStringExtra("namaLengkap");
                String email = intent.getStringExtra("email");
                String ttl = intent.getStringExtra("ttl");
                String jenKel = intent.getStringExtra("jenKel");
                String noTelp = intent.getStringExtra("noTelp");
                String username = intent.getStringExtra("username");
                String id = intent.getStringExtra("userId");
                // mengirim ke halaman informasi pribadi
                Intent i = new Intent(requireContext(), penerima_profil_informasi_pribadi.class);
                i.putExtra("namaLengkap", namaLengkap);
                i.putExtra("email", email);
                i.putExtra("ttl", ttl);
                i.putExtra("jenKel", jenKel);
                i.putExtra("noTelp", noTelp);
                i.putExtra("username", username);
                i.putExtra("userId", id);
                startActivity(i);
            }
        });
        TextView txtDokumen = (TextView) getView().findViewById(R.id.dokumen);
        txtDokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                String username1 = intent.getStringExtra("username");
                String id = intent.getStringExtra("userId");
                String ktp = intent.getStringExtra("ktp");
                String kk = intent.getStringExtra("kk");
                String fotoProfil = intent.getStringExtra("fotoProfil");
                String transkrip = intent.getStringExtra("transkrip");
                String ijazah = intent.getStringExtra("ijazah");
                Intent i = new Intent(getContext(), penerima_profil_dokumenSaya.class);
                i.putExtra("ktp", ktp);
                i.putExtra("kk", kk);
                i.putExtra("fotoProfil", fotoProfil);
                i.putExtra("ijazah", ijazah);
                i.putExtra("transkrip", transkrip);
                i.putExtra("username", username1);
                i.putExtra("userId", id);
                startActivity(i);
            }
        });
        TextView txtKataSandi = (TextView) getView().findViewById(R.id.kataSandi);
        txtKataSandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                String username1 = intent.getStringExtra("username");
                String password1 = intent.getStringExtra("password");
                String id = intent.getStringExtra("userId");
                Intent i = new Intent(requireContext(), penerima_kata_sandi.class);
                i.putExtra("username", username1);
                i.putExtra("password", password1);
                i.putExtra("userId", id);
                startActivity(i);
            }
        });
        TextView txtBantuan = (TextView) getView().findViewById(R.id.bantuan);
        txtBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                String username1 = intent.getStringExtra("username");
                Intent i = new Intent(getContext(), penerima_profil_bantuan.class);
                i.putExtra("username", username1);
                startActivity(i);
            }
        });
        }
}