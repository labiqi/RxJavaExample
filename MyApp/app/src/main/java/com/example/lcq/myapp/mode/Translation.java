package com.example.lcq.myapp.mode;

import android.util.Log;

public class Translation {
    private int status;
    private Content content;
    private static class Content{
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    public void show() {
        Log.d("RxJava", content.out );
    }
}
