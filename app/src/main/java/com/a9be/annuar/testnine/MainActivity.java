package com.a9be.annuar.testnine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int getLayout(String type)
    {
        int X = R.layout.row_layout_000;
        try {
            X += Integer.parseInt(type);
        } catch(NumberFormatException nfe) {
            Log.d("logTag","error file");
            System.out.println("Could not parse " + nfe);
        }
        return X;
    }

    private void bukaJson(int OuterLayer)
    {
        LinearLayout outerLinearLayout;
        LinearLayout parentLinearLayout;
        LayoutInflater inflater;

        View rowView;
        View textValue = null;
        int X=0;

        parentLinearLayout = outerLinearLayout = (LinearLayout)  findViewById(OuterLayer);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        String json = loadJSONFromAsset();
        try {
            JSONObject main = new JSONObject(json);
            setTitle(main.get("n").toString());
            JSONArray rows = main.getJSONArray("rows");

            for(int i=0; i<rows.length(); i++){
                JSONObject row = rows.getJSONObject(i);
                String tab = row.getString("tab");
                String type = row.getString("type");
                JSONArray elements = row.getJSONArray("element");

                if(type.equals("0")){
                    rowView = inflater.inflate(R.layout.nb_row_group_000,null);
                    outerLinearLayout.addView(rowView,outerLinearLayout.getChildCount());
                    parentLinearLayout = (LinearLayout)  rowView;
                }

                X = getLayout(type);
                rowView = inflater.inflate(X,null);
                List<String> strings = new ArrayList<>();

                for(int y = 0; y < elements.length(); y++){
                    int target = R.id.e1;
                    target += y ;
                    JSONObject ele = elements.getJSONObject(y);
                    String ee = "e" + (y + 1);
                    String label = ele.getString(ee);
                    String alignment = ele.getString("a");
                    textValue = rowView.findViewById(target);
                    if(textValue instanceof TextView){
                        ((TextView) textValue).setText(label);
                        switch (alignment){
                            case "0":
                                ((TextView) textValue).setGravity(Gravity.START);
                                break;
                            case "1":
                                ((TextView) textValue).setGravity(Gravity.CENTER);
                                break;
                            case "2":
                                ((TextView) textValue).setGravity(Gravity.END);
                                break;
                        }
                    }
                    else if (type.equals("5") && y>0){
                        strings.add(label);
                        textValue = rowView.findViewById(R.id.e2);
                    }
                    else if (textValue instanceof ImageView) {
                        ((ImageView)textValue).setImageResource(R.drawable.gf);
                        ((ImageView)textValue).setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                }
                if (textValue instanceof Spinner) {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, strings);
                    dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    ((Spinner) textValue).setAdapter(dataAdapter);
                }
                parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());
            }
        } catch (JSONException e) {
            Log.d("logTag", "Search : " + e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bukaJson(R.id.outer_layout);
    }

    public void onEdit(View v){
        Log.d("logTag", "haaaa" + v.getId() );
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