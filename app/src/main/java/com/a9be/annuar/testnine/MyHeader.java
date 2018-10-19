package com.a9be.annuar.testnine;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.List;

public class MyHeader extends RelativeLayout {

    private boolean expanded;
    private List<String> child;
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public List<String> getChild() {
        return child;
    }

    public void setChild(List<String> child) {
        this.child = child;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public MyHeader(Context context) {
        super(context);
    }

    public MyHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
