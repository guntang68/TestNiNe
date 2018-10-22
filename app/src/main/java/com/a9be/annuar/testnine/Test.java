package com.a9be.annuar.testnine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class Test {

    static public class ViewHolder {
        int id;
        int tag;
    }

    private boolean mula;



    public Test() {
        this.mula = true;
    }

    private void expand(final ViewGroup v, Context context) {
        final ScrollView scroll;
        scroll = ((MainActivity)context).findViewById(R.id.ScrollViewID);

        final int initialHeight = v.getMeasuredHeight();
        for (int p=1; p<v.getChildCount(); p++) {
            v.getChildAt(p).setVisibility(View.VISIBLE);
        }
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        float dip = 61f;

        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                context.getResources().getDisplayMetrics()
        );


        final Rect scrollBounds = new Rect();

        Log.d("logTag", "Sini1");

        scroll.getDrawingRect(scrollBounds);

        Log.d("logTag", "Sini2 " + scrollBounds.bottom);

        final int screen = (int) (scroll.getHeight() - px);

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

    private void collapse(final ViewGroup v, Context context) {
        float dip = 12f;

        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                context.getResources().getDisplayMetrics()
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

    public void editText(int OuterLayer, Activity activity, int x, int y, int z, String value)
    {
        LinearLayout linearLayoutOuter = activity.findViewById(OuterLayer);
        View viewOuter = linearLayoutOuter.getChildAt(x);   //x
        LinearLayout linearLayoutHeader = (LinearLayout) viewOuter;
        View viewGroup = linearLayoutHeader.getChildAt(y);  // y header group
        LinearLayout linearLayoutItem = (LinearLayout) viewGroup;

        View viewElement = linearLayoutItem.getChildAt(z);  // z
        ((TextView) viewElement).setText(value);

    }

    public void bukaJsonTab(int OuterLayer, final Context context) {
        try {
            LinearLayout outerLinearLayout;
            LinearLayout parentLinearLayout;
            View rowView;
            View v1;

            outerLinearLayout = ((MainActivity)context).findViewById(OuterLayer);

            String json = this.loadJSONFromAsset(context);// loadJSONFromAsset();

            JSONObject main = new JSONObject(json);
            JSONObject tabs = main.getJSONObject("tabs");
            JSONArray rows = main.getJSONArray("rows");
            Log.d("logTag", "tab.length : " + tabs.length());
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i=0; i<tabs.length(); i++) {
                Log.d("logTag", "tab" + i + " : " + tabs.getString(String.valueOf(i)));
                parentLinearLayout = (LinearLayout) inflater.inflate(R.layout.nb_row_group_000, null);
                outerLinearLayout.addView(parentLinearLayout, outerLinearLayout.getChildCount());
                rowView = inflater.inflate(R.layout.row_layout_000, null);
                v1 = rowView.findViewById(R.id.e1);
                String header = tabs.getString(String.valueOf(i));
                ((TextView)v1).setText(header);
                ((MyHeader)rowView).setExpanded(true);
                rowView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyHeader header = (MyHeader) v;
                        final ViewGroup parent = (ViewGroup) v.getParent();
                        boolean expand;
                        Log.d("logTag", "isExpanded = " + header.isExpanded());
                        if (header.isExpanded()) {
                            collapse(parent, context);
                            expand = false;
                        }else {
                            expand(parent, context);
                            expand = true;
                        }
                        header.setExpanded(expand);
                    }
                });
                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());
                for (int j=0; j<rows.length(); j++) {
                    JSONObject aRow = rows.getJSONObject(j);
                    String tab = aRow.getString("tab");
                    if (tab.equals(String.valueOf(i))) {
                        String type = aRow.getString("type");
                        JSONArray elements = aRow.getJSONArray("element");
                        rowView = inflater.inflate(getLayout(type), null);
                        List<String> strings = new ArrayList<>();
                        for (int k=0; k<elements.length(); k++) {
                            int target = R.id.e1;
                            target += k ;
                            JSONObject ele = elements.getJSONObject(k);
                            String ee = "e" + (k + 1);
                            String label = ele.getString(ee);
                            String attr = ele.getString("a");
                            v1 = rowView.findViewById(target);
                            if (v1 instanceof TextView) {
                                ((TextView) v1).setText(label);
                                switch (attr){
                                    case "0":
                                        ((TextView) v1).setGravity(Gravity.START);
                                        break;
                                    case "1":
                                        ((TextView) v1).setGravity(Gravity.CENTER);
                                        break;
                                    case "2":
                                        ((TextView) v1).setGravity(Gravity.END);
                                        break;
                                }
                            }
                            else if (v1 instanceof Spinner) {
                                strings.add(label);
                            }else if (v1 instanceof ImageView) {
                                ((ImageView)v1).setImageResource(R.drawable.gf);
                                ((ImageView)v1).setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                        }

                        if (v1 instanceof Spinner) {
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, R.layout.spinner_item, strings);
                            dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                            ((Spinner) v1).setAdapter(dataAdapter);
                        }

                        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount());

                    }
                }

            }

        }catch (JSONException e) {
            Log.d("logTag", "bukaJsonTab : " + e.toString());
        }

    }

    public void bukaJson(int OuterLayer, Activity activity, final Context context)
    {
        LinearLayout outerLinearLayout;
        LinearLayout parentLinearLayout;
        LayoutInflater inflater;

        View rowView;
        View textValue = null;
        int X=0;

        parentLinearLayout = outerLinearLayout = (LinearLayout) activity.findViewById(OuterLayer);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        String json = this.loadJSONFromAsset(context);// loadJSONFromAsset();

        try {
            JSONObject main = new JSONObject(json);
            activity.setTitle(main.get("n").toString());
            JSONArray rows = main.getJSONArray("rows");

            for(int i=0; i<rows.length(); i++){
                JSONObject row = rows.getJSONObject(i);
                String tab = row.getString("tab");
                String type = row.getString("type");
                JSONArray elements = row.getJSONArray("element");

                if(mula || type.equals("0")){
                    if (mula)
                        mula = false;
                    parentLinearLayout  = (LinearLayout) inflater.inflate(R.layout.nb_row_group_000,null);
                    outerLinearLayout.addView(parentLinearLayout,outerLinearLayout.getChildCount());
                }

                X = this.getLayout(type); // getLayout(type);
                rowView = inflater.inflate(X,null);
                if (type.equals("0")){
                    rowView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            MyHeader header = (MyHeader) v;
                            final ViewGroup parent = (ViewGroup) v.getParent();
                            boolean expand;
                            Log.d("logTag", "isExpanded = " + header.isExpanded());
                            if (header.isExpanded()) {
                                expand(parent, context);
                                expand = false;
                            }else {
                                collapse(parent, context);
                                expand = true;
                            }
                            header.setExpanded(expand);


                        }
                    });

                }

                List<String> strings = new ArrayList<>();

                for(int y = 0; y < elements.length(); y++){
                    int target = R.id.e1;
                    target += y ;
                    JSONObject ele = elements.getJSONObject(y);
                    String ee = "e" + (y + 1);
                    String label = ele.getString(ee);
                    textValue = rowView.findViewById(target);

                    String alignment = ele.getString("a");
                    String tag = ele.getString("i");    //--------------------------------!!additional tag info
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

//                    21/10/2018 10:09 Now
//                    Log.d("logTag", tag);   //-----DEBUG

                    ViewHolder vh = new ViewHolder();
                    vh.id = target;
                    vh.tag = Integer.parseInt(tag);
                    textValue.setTag(vh);

                }
                if (textValue instanceof Spinner) {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_item, strings);
                    dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    ((Spinner) textValue).setAdapter(dataAdapter);
                }

/// NAS habis
                parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount());
            }
        } catch (JSONException e) {
            Log.d("logTag", "Search : " + e.toString());
        }



    }

    public String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getApplicationContext().getAssets().
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

    public int getLayout(String type)
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

}
