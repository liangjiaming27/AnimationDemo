package com.example.administrator.animationdemo;

import android.view.View;

public class WrapperView {

    private View view;

    public WrapperView(View view) {
        this.view =view;
    }

    public int getWidth(){
        return view.getLayoutParams().width;
    }

    public void setWidth(int width) {
        view.getLayoutParams().width = width;
        view.requestLayout();
    }
}
