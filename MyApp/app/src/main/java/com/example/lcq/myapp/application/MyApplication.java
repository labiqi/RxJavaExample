package com.example.lcq.myapp.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class MyApplication extends Application {
    private static final String VALUE="APPLICATION";
    private String value;
    @Override
    public void onCreate() {
        super.onCreate();
        setValue(VALUE);
        if(LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public  String getValue() {
        return this.value;
    }
}
