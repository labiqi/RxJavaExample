package com.example.lcq.myapp.activity;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.lcq.myapp.R;
import com.example.lcq.myapp.application.MyApplication;
import com.example.lcq.myapp.base.BaseActivity;
import com.example.lcq.myapp.constant.Constants;
import com.example.lcq.myapp.service.MyService;
import com.example.lcq.myapp.util.FormatUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.lcq.myapp.util.FormatUtil.getPointNum;

public class MainActivity extends BaseActivity {

    private static final  String tag ="MainActivity";
    private boolean landscape = false;
    private Intent intentService;
    private ServiceConnection sc = new MyServiceConnection();
    private MyService.MyBinder myBinder;
    private MyService myService;
    private boolean mBound;
    private Button btn1;
    private Handler mHandler = new Handler();

    private Runnable MyRunnable = new Runnable() {
        @Override
        public void run() {
           Log.d("当前线程",Thread.currentThread().getName());
           btn1.setText("当前线程为ui线程");
        }
    };

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.w(tag, "in MyServiceConnection onServiceConnected");
            myBinder = (MyService.MyBinder) service;
            myService = myBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.w(tag, "in MyServiceConnection onServiceDisConnected");
            mBound = false;
        }
    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        btn1 = findViewById(R.id.btn1);
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("事件分发","onTouch !!");
                return true;
            }
        });

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("事件分发","OnClick !!");
//            }
//        });
    }



    public void gotoFinance(View view) {
        startActivity(new Intent(MainActivity.this,HighEndFinanceActivity.class));
    }

    public void layoutClick(View view) {
        Log.d("TAG", "点击了layout");
    }

    /*
      页面跳转
    */

    public void onClick(View view) {
//        mHandler.post(MyRunnable);
//        Log.d("TAG", "点击了页面跳转");
//        application = (MyApplication) getApplication();
//        String value = application.getValue();
//        application.setValue("main");
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
//        String mac = FormatUtil.getMacFromHardware();
    }

    public void gotoRxJava(View view) {
        Intent intent = new Intent(MainActivity.this,RxJavaActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("TAG", "点击了dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    /*
      打电话
    */

    public void call(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(("tel:10086")));
        startActivity(intent);
    }


   /*
      横竖屏切换
    */

    public void transfer(View view){
        int orientation = this.getResources().getConfiguration().orientation;
        if(landscape) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            landscape = !landscape;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            landscape = !landscape;
        }
    }


    /*
      开始service
    */

    public void startService(View view) {
        intentService = new Intent(MainActivity.this,MyService.class);
        intentService.putExtra("name","我是 service！");
        startService(intentService);
    }

    /*
      停止service
    */

    public void stopService(View view) {
        stopService(intentService);
    }

    /*
      绑定service
    */

    public void bind(View view) {
        Intent intent = new Intent(MainActivity.this,MyService.class);
        bindService(intent,sc,Context.BIND_AUTO_CREATE);
    }

    /*
      解绑service
    */

    public void unbind(View view) {
        excuteUnbindService();
    }


    /**
      跳转到pdf阅读
    */
    public void onPdfView(View view) {
        Intent intent = new Intent(MainActivity.this,PdfActivity.class);
        intent.putExtra(Constants.PDF_FROM_KEY,2);
        intent.putExtra(Constants.PDF_FROM_ADDRESS_KEY,"http://pdf.dfcfw.com/pdf/H3_AP201704010460506701_1.pdf");
        startActivity(intent);
    }

    private void excuteUnbindService() {
        if (mBound) {
            unbindService(sc);
            mBound = false;
        }
    }


    public void goWebview(View view){
        startActivity(new Intent(MainActivity.this,WebviewActivity.class));

    }


    public void goViewPagerWebview(View view) {
        startActivity(new Intent(MainActivity.this,TabLayoutActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(tag,"onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(tag,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(tag,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(tag,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(tag,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(tag,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(tag,"onDestroy");
    }
}
