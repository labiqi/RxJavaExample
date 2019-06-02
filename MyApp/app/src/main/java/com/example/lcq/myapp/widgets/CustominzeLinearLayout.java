package com.example.lcq.myapp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static com.example.lcq.myapp.activity.SecondActivity.tag1;

public class CustominzeLinearLayout extends LinearLayout {
    public CustominzeLinearLayout(Context context) {
        super(context);
    }

    public CustominzeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustominzeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public CustominzeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(tag1,"ViewGroup dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(tag1,"ViewGroup onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(tag1,"ViewGroup onTouchEvent");
        return super.onTouchEvent(ev);
        //        return false;
//        return true;
    }
}
