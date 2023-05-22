package com.example.grantme2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kodeJava.Beasiswa;

public class AdapterPenyedia extends BaseAdapter {
    private ArrayList<Beasiswa> dataList;
    private Context context;
    LayoutInflater layoutInflater;
    public AdapterPenyedia(Context context, ArrayList<Beasiswa> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = layoutInflater.inflate(R.layout.grid_home_penyedia, null);
        }
        ImageView gridImage = view.findViewById(R.id.grid_image_penyedia);
        TextView gridCaption = view.findViewById(R.id.namaPenyedia_grid);
        Button btnUbah = view.findViewById(R.id.btnUbahB);
        Glide.with(context).load(dataList.get(i).getImageURL()).into(gridImage);
        gridCaption.setText(dataList.get(i).getNamaBeasiswa());
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, penyedia_UbahBeasiswa.class);
                intent.putExtra("beasiswa", dataList.get(i));
                context.startActivity(intent);
            }
        });
        return view;
    }
}
