package com.a9be.annuar.testnine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    private boolean mula;

    Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mula = true;
        test = new Test();

//        test.bukaJson(R.id.outer_layout,this,this);
        test.bukaJsonTab(R.id.outer_layout, this);


//        test.editText(R.id.outer_layout,this,0,1,1,"Annuar Zaini Abdullah");
//        test.editText(R.id.outer_layout,this,0,2,0,"A quick brown fox jumps over the lazy dog");
//        test.editText(R.id.outer_layout,this,1,2,0,"Timestamp");


    }

    public void onEdit(View v){
        Log.d("logTag", "haaaa" + v.getId() );
    }





}








//    LinearLayout layoutMyLayout =  findViewById(R.id.outer_layout);
//    Test.ViewHolder vh;
//
//        if (layoutMyLayout != null)
//                {
//                for (int x = 0; x < layoutMyLayout.getChildCount(); x++)
//        {
//        View viewChild1 = layoutMyLayout.getChildAt(x);
//        Class classChild1 = viewChild1.getClass();
//        if (classChild1 == LinearLayout.class)
//        {
//        LinearLayout layoutChild1 = (LinearLayout) viewChild1;
//        for (int y = 0; y < layoutChild1.getChildCount(); y++)
//        {
//        View viewC = layoutChild1.getChildAt(y);
//        Class classChild2 = viewC.getClass();
//        if(classChild2 == LinearLayout.class){
//        LinearLayout LL = (LinearLayout) viewC;
//        for(int z = 0; z < LL.getChildCount(); z++){
//        View viewD = LL.getChildAt(z);
//        vh = (Test.ViewHolder) viewD.getTag();
//        if(vh != null){
//        Log.d("logTag", "ViewHolder tag= " + vh.tag + "  id=" + vh.id  + " x" + x  + " y" + y  + " z" + z  );
//        }
//
//
//
////                                if(viewD instanceof TextView){
////                                    Log.d("logTag", "TextView = " + ((TextView) viewD).getText());
////                                    ((TextView) viewD).setText("aza");
////
////                                }
////                                else if(viewD instanceof ImageView){
////                                    Log.d("logTag", "ImageView = ");// + ((TextView) viewD).getText());
////                                }
//
//
//        }
//        }
//        }
//        }
//        }
//        }