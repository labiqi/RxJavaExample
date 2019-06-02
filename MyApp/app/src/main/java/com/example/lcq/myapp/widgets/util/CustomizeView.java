package com.example.lcq.myapp.widgets.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import android.widget.Button;

import static com.example.lcq.myapp.activity.SecondActivity.tag1;

@SuppressLint("AppCompatCustomView")
public class CustomizeView extends Button {

    public CustomizeView(Context context) {
        super(context);
    }

    public CustomizeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomizeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    public CustomizeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(tag1,"View dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(tag1,"View onTouchEvent");
//        return super.onTouchEvent(ev);
        //        return false;
        return true;
    }
}
