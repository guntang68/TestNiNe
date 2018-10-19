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
import java.util.Iterator;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        MyHeader header = (MyHeader) v;
        final ViewGroup parent = (ViewGroup) v.getParent();
        boolean expand;
        Log.d("logTag", "isExpanded = " + header.isExpanded());
        if (header.isExpanded()) {
            expand(parent);
            expand = false;
        }else {
            collapse(parent);
            expand = true;
        }
        header.setExpanded(expand);
    }

    private void expand(final ViewGroup v) {
        final ScrollView scroll = findViewById(R.id.ScrollViewID);

        final int initialHeight = v.getMeasuredHeight();

        for (int p=1; p<v.getChildCount(); p++) {
            v.getChildAt(p).setVisibility(View.VISIBLE);
        }
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        final Rect scrollBounds = new Rect();
        scroll.getDrawingRect(scrollBounds);

        final int screen = scroll.getHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (initialHeight + (targetHeight - initialHeight) * interpolatedTime);
                v.requestLayout();
                if (targetHeight>=screen) {
                    scroll.smoothScrollTo(0, v.getTop());
                }else if (scrollBounds.bottom<v.getBottom()) {
                    int gap = v.getTop() - (screen - targetHeight);
                    scroll.smoothScrollTo(0, gap);
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);

    }

    private void collapse(final ViewGroup v) {
        float dip = 12f;

        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                getResources().getDisplayMetrics()
        );
        final int initialHeight = (int) (v.getMeasuredHeight() + px);
        final int finalHeight = v.getMeasuredHeight() - v.getChildAt(0).getMeasuredHeight();
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    for (int p=v.getChildCount()-1; p>0; p--) {
                        v.getChildAt(p).setVisibility(View.GONE);
                    }
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(finalHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

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
        View textValue;
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

                for(int y = 0; y < elements.length(); y++){
                    int target = R.id.e1;
                    target += y ;
                    JSONObject ele = elements.getJSONObject(y);
                    String ee = "e" + (y + 1);
                    String label = ele.getString(ee);
                    textValue = rowView.findViewById(target);
                    if(textValue instanceof TextView){
                        ((TextView) textValue).setText(label);
                    }
                    else{

                        Log.d("logTag", "instanceof untuk = " + label);
                    }

                }
                parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());
            }
        } catch (JSONException e) {
            Log.d("tagLog", "Search : " + e.toString());
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