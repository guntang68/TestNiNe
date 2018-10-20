package com.a9be.annuar.testnine;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity{

    private boolean mula;

    Test test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mula = true;
        test = new Test();

//        bukaJson(R.id.outer_layout);

        test.bukaJson(R.id.outer_layout,this,this);
    }

    public void onEdit(View v){
        Log.d("logTag", "haaaa" + v.getId() );
    }





}






