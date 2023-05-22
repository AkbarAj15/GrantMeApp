package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kodeJava.Beasiswa;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link penerima_FragHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class penerima_FragHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public penerima_FragHome() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static penerima_FragHome newInstance(String param1, String param2) {
        penerima_FragHome fragment = new penerima_FragHome();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penerima_home, container, false);
    }
    FloatingActionButton fab;
    private GridView gridView, gridView_atas;
    private ArrayList<Beasiswa> dataList;
    private MyAdapter adapter;
    private  AdapterGrid adapter2;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Beasiswa");
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtNama = (TextView) getView().findViewById(R.id.namauser);
        Intent intent =getActivity().getIntent();
        String username = intent.getStringExtra("username");
        txtNama.setText(username);
        gridView = view.findViewById(R.id.grid_home);
        gridView_atas = view.findViewById(R.id.grid_image_horizontal);
        dataList = new ArrayList<>();

        adapter = new MyAdapter(getActivity(), dataList);
        adapter2 = new AdapterGrid(getActivity(),dataList);

        gridView.setAdapter(adapter);
        gridView_atas.setAdapter(adapter2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Beasiswa beasiswa = dataSnapshot.getValue(Beasiswa.class);
                    dataList.add(beasiswa);
                }
                adapter2.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}