package com.a9be.annuar.testnine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    private LinearLayout outerLinearLayout;

    private LinearLayout parentLinearLayout;


    private void bukaJson()
    {

        String json = loadJSONFromAsset();
//        JSONObject main = new JSONObject(json);




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







//        Log.d("logTag", jj);

        outerLinearLayout = (LinearLayout) findViewById(R.id.outer_layout);

        View rowView;
        TextView textValue;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //--------------------------------------------------------------------------
        rowView = inflater.inflate(R.layout.nb_row_group_000,null);
        outerLinearLayout.addView(rowView,outerLinearLayout.getChildCount());
        parentLinearLayout = (LinearLayout) rowView;


        rowView = inflater.inflate(R.layout.nb_row_type_000,null);
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
        textValue.setId(123);
        parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());


    }

    public void onEdit(View v){
        Log.d("logTag", "haaaa" + v.getId());






    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getApplicationContext().getAssets().
                    open("baseData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.d("json", "dpt baca");
        return json;
    }
}
