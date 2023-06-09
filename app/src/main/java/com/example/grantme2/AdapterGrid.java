package com.example.grantme2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kodeJava.Beasiswa;

public class AdapterGrid extends BaseAdapter {

    private ArrayList<Beasiswa> dataList;
    private Context context;
    LayoutInflater layoutInflater;
    public AdapterGrid(Context context, ArrayList<Beasiswa> dataList) {
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
            view = layoutInflater.inflate(R.layout.grid_item2, null);
        }
        ImageView gridImage = view.findViewById(R.id.grid_imagehor);
        Glide.with(context).load(dataList.get(i).getImageURL()).into(gridImage);
        return view;
    }
}
