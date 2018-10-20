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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean mula;

    Test test;

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






