package com.a9be.annuar.testnine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

public class MyAdapter {

    private Context context;
    private String json;
    private MyData data;

    public MyAdapter() {
    }

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void niJson(String json) {
        this.data = new Gson().fromJson(json, MyData.class);
    }

    public MyData getData() {
        return data;
    }

    public void tempel() {

        LinearLayout outerLinearLayout = (LinearLayout) ((MainActivity)context).findViewById(R.id.outer_layout);
        LinearLayout parentLinearLayout;

        View rowView;
        TextView textValue;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //--------------------------------------------------------------------------
        rowView = inflater.inflate(R.layout.nb_row_group_000,null);
        outerLinearLayout.addView(rowView,outerLinearLayout.getChildCount());
        parentLinearLayout = (LinearLayout)  rowView;


        int x;

        x = R.layout.nb_row_type_000;
        rowView = inflater.inflate(x,null);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());


        x = R.layout.nb_row_type_002;
        rowView = inflater.inflate(x,null);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());




        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_002,null);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        //--------------------------------------------------------------------------
        rowView = inflater.inflate(R.layout.nb_row_group_000,null);
        outerLinearLayout.addView(rowView,outerLinearLayout.getChildCount());
        parentLinearLayout = (LinearLayout) rowView;


        rowView = inflater.inflate(R.layout.nb_row_type_000,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Header Yang Panjang");
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Ucapan");
        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Salam");
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_002,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Kepada");

        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Dunia");
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());


        //--------------------------------------------------------------------------
        rowView = inflater.inflate(R.layout.nb_row_group_000,null);
        outerLinearLayout.addView(rowView,outerLinearLayout.getChildCount());
        parentLinearLayout = (LinearLayout) rowView;


        rowView = inflater.inflate(R.layout.nb_row_type_000,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Baru tambah");
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        //====================================
        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Kena");
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        //====================================
        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Ucapan");
        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Salam");
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());

        //====================================
        rowView = inflater.inflate(R.layout.nb_row_type_002,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Kepada");
        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Dunia");
//        textValue.setId(123);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());
    }


}
