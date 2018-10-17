package com.a9be.annuar.testnine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
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

    private String tab, type, l, a;
    private List<MyData.Element> elements;
    private List<MyData.Row> rows;

    private void tempel() {
        for (MyData.Row row : rows) {
            Log.d("tagLog", "tab = " + row.getTab());
            Log.d("tagLog", "type = " + row.getType());
            elements = row.getElement();
            for (MyData.Element element : elements) {
                Log.d("tagLog", "tab = " + row.getTab());
            }



    private void bukaJson()
    {
        String json = loadJSONFromAsset();
        try {
            JSONObject main = new JSONObject(json);
            JSONArray rows = main.getJSONArray("rows");
            for(int i=0; i<rows.length(); i++){
                JSONObject row = rows.getJSONObject(i);
                String tab = row.getString("tab");
                String type = row.getString("type");
                JSONArray elements = row.getJSONArray("element");


                Log.d("logTag", "row tab=" + tab + "\t type=" + type + "\t elemens=" + elements.length());

                for(int y=0; y<elements.length(); y++){
                    JSONObject a = elements.getJSONObject(y);
                    String label = a.getString("l");
                    String alignment = a.getString("a");
                    Log.d("logTag", "Label=" + label + "\t alignment=" + alignment);



                }
                Log.d("logTag", "------------------------------------");


            }

        } catch (JSONException e) {
            Log.d("tagLog", "Search : " + e.toString());
        }
    }

//    private void bukaJson() {
//
//        String json = loadJSONFromAsset();
//        try {
//            rows = new ArrayList<>();
//            JSONObject main = new JSONObject(json);
//            Iterator<String> keys = main.keys();
//
//            while(keys.hasNext()) {
//                String key = keys.next();
//
//                if(key.equals("rows")){
//                    JSONArray jRows = main.getJSONArray("rows");
//                    for (int i=0; i<jRows.length(); i++) {
//                        tab = "";
//                        type = "";
//                        elements = new ArrayList<>();
//                        JSONObject jInRow = jRows.getJSONObject(i);
//                        Iterator<String> rowKeys = jInRow.keys();
//
//                        while (rowKeys.hasNext()) {
//                            String rowKey = rowKeys.next();
//
//                            switch (rowKey) {
//                                case "tab":
//                                    tab = jInRow.getString("tab");
//                                    break;
//                                case "type":
//                                    type = jInRow.getString("type");
//                                    break;
//                                case "element":
//                                    JSONArray jElements = jInRow.getJSONArray("element");
//                                    for (int j = 0; j < jElements.length(); j++) {
//                                        l = "";
//                                        a = "";
//                                        JSONObject jInElement = jElements.getJSONObject(j);
//                                        Iterator<String> elementKeys = jInElement.keys();
//
//                                        while (elementKeys.hasNext()) {
//                                            String elementKey = elementKeys.next();
//
//                                            switch (elementKey) {
//                                                case "l":
//                                                    l = jInElement.getString("l");
//                                                    break;
//                                                case "a":
//                                                    a = jInElement.getString("a");
//                                                    break;
//                                            }
//
//                                        }
//
//                                        MyData.Element element = new MyData.Element();
//                                        element.setL(l);
//                                        element.setA(a);
//                                        elements.add(element);
//
//                                    }
//                                    break;
//                            }
//                        }
//
//                        MyData.Row row = new MyData.Row();
//                        row.setTab(tab);
//                        row.setType(type);
//                        row.setElement(elements);
//                        rows.add(row);
//
//                    }
//
//
//                }
//
//                Log.d("tagLog", key);
//                if (main.get(key) instanceof JSONObject) {
//                    Log.d("tagLog", main.get(key).toString());
//                }
//
//
//            }
//        } catch (JSONException e) {
//            Log.d("tagLog", "Search : " + e.toString());
//        }
//    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        bukaJson();
//        MyAdapter myAdapter = new MyAdapter(this);
//        myAdapter.niJson(loadJSONFromAsset());


        LinearLayout outerLinearLayout = (LinearLayout) findViewById(R.id.outer_layout);

        View rowView;
        TextView textValue;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //--------------------------------------------------------------------------
        rowView = inflater.inflate(R.layout.nb_row_group_000,null);
        outerLinearLayout.addView(rowView, outerLinearLayout.getChildCount());
        LinearLayout parentLinearLayout = (LinearLayout) rowView;


        int x;

        x = R.layout.nb_row_type_000;
        rowView = inflater.inflate(x,null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());


        x = R.layout.nb_row_type_002;
        rowView = inflater.inflate(x,null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());




        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_002,null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        //--------------------------------------------------------------------------
        rowView = inflater.inflate(R.layout.nb_row_group_000,null);
        outerLinearLayout.addView(rowView, outerLinearLayout.getChildCount());
        parentLinearLayout = (LinearLayout) rowView;


        rowView = inflater.inflate(R.layout.nb_row_type_000,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Header Yang Panjang");
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Ucapan");
        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Salam");
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        rowView = inflater.inflate(R.layout.nb_row_type_002,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Kepada");

        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Dunia");
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());


        //--------------------------------------------------------------------------
        rowView = inflater.inflate(R.layout.nb_row_group_000,null);
        outerLinearLayout.addView(rowView, outerLinearLayout.getChildCount());
        parentLinearLayout = (LinearLayout) rowView;


        rowView = inflater.inflate(R.layout.nb_row_type_000,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Baru tambah");
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        //====================================
        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Kena");
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        //====================================
        rowView = inflater.inflate(R.layout.nb_row_type_001,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Ucapan");
        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Salam");
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

        //====================================
        rowView = inflater.inflate(R.layout.nb_row_type_002,null);
        textValue = rowView.findViewById(R.id.label);
        textValue.setText("Kepada");
        textValue = rowView.findViewById(R.id.value);
        textValue.setText("Dunia");
//        textValue.setId(123);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());


    }

    public void onEdit(View v){
        Log.d("logTag", "haaaa" + v.getId() );







    }


}
