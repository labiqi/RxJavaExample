package com.example.lcq.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lcq.myapp.Interface.GetRequest_Interface;
import com.example.lcq.myapp.Interface.GetRequest_interface2;
import com.example.lcq.myapp.R;
import com.example.lcq.myapp.mode.LoginEntity;
import com.example.lcq.myapp.mode.RegisterEntity;
import com.example.lcq.myapp.mode.Translation;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaActivity extends Activity {
    private static final String TAG = "Rxjava";
    private TextView tx1;
    private Button btn2;
    private Button btn3;
    // 定义Observable接口类型的网络请求对象
    Observable<RegisterEntity> observable1;
    Observable<LoginEntity> observable2;
    private String memoryCache =null;
    private String diskCache = "从磁盘中获取缓存数据";
//private String diskCache = null;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_activity_layout);
        tx1 = findViewById(R.id.tx1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
//        分步骤实现方法
//         创建被观察者 Observable 对象
//         create() 是 RxJava 最基本的创造事件序列的方法
//         此处传入了一个 OnSubscribe 对象参数
//         当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
//         即观察者会依次调用对应事件的复写方法从而响应事件
//         从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式
//        Observable<String> observable =Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            //重写的subscribe（）里定义需要发送的事件
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                // 通过 ObservableEmitter类对象产生事件并通知观察者
//                // ObservableEmitter类介绍
//                // a. 定义：事件发射器
//                // b. 作用：定义需要发送的事件 & 向观察者发送事件
//                Log.d(TAG,"调用传递数据");
//                e.onNext("123");
//                e.onNext("abc");
//                e.onNext("wwww");
//                e.onComplete();
////                e.onError(null);
//            }
//        });
//         创建观察者 （Observer ）对象
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            // 创建对象时通过对应复写对应事件方法 从而 响应对应事件
//            // 观察者接收事件前，默认最先调用复写 onSubscribe（）
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
//            public void onNext(String value) {
//                Log.d(TAG, "对Next事件"+ value +"作出响应"  );
//            }
//
//            @Override
//            // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
//            public void onError(Throwable e) {
//                Log.d(TAG, "对Error事件作出响应");
//            }
//
//            //****注意****
//            //onError方法与onComplete()不能同时调用，是互斥的，不然会导致闪退
//
//            @Override
//            // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
//            public void onComplete() {
//                Log.d(TAG, "对Complete事件作出响应");
//            }
//        };
//        observable.subscribe(observer);
//        扩展方法
//        Observable observable1 = Observable.just("A","B","C");
//        // 将会依次调用：
//        // onNext("A");
//        // onNext("B");
//        // onNext("C");
//        // onCompleted();
//        String[] words = {"a","b","c"};
//        Observable observable2 = Observable.fromArray(words);
//        observable1.subscribe(observer);
//        observable2.subscribe(observer);
        //基于事件流的链式调用方式
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            // 1. 创建被观察者 & 生产事件
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//            }
//            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
//            // 3. 创建观察者 & 定义响应事件的行为
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            // 默认最先调用复写的 onSubscribe（）
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                Log.d(TAG, "对Next事件"+ value +"作出响应"  );
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "对Complete事件作出响应");
//            }
//        });
    }

    /**
     * RxJava 轮询应用场景
     */
    public void clickPull(View view) {
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        // 该例子发送的事件特点：延迟1s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
      Observable.interval(1,1,TimeUnit.SECONDS)
              /*
               * 步骤2：每次发送数字前发送1次网络请求（doOnNext（）在执行Next事件前调用）
               * 即每隔1秒产生1个数字前，就发送1次网络请求，从而实现轮询需求
               **/
              .doOnNext(new Consumer<Long>() {
          @Override
          public void accept(Long aLong) throws Exception {
             tx1.setText("第 " + aLong + " 次轮询");
              /*
               * 步骤3：通过Retrofit发送网络请求
               **/
              // a. 创建Retrofit对象
              Retrofit retrofit = new Retrofit.Builder()
                      .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                      .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                      .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                      .build();
              // b. 创建 网络请求接口 的实例
              GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
              // c. 采用Observable<...>形式 对 网络请求 进行封装
              Observable<Translation> observable = request.getCall();
              // d. 通过线程切换发送网络请求
              observable.subscribeOn(Schedulers.io())
                      // 切换到IO线程进行网络请求
                      // 切换回到主线程 处理请求结果
                      .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Translation>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(Translation value) {
                      value.show();
                  }

                  @Override
                  public void onError(Throwable e) {

                  }

                  @Override
                  public void onComplete() {

                  }
              });

          }
      }).safeSubscribe(new Observer<Long>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(Long value) {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
      });
    }

    /**
     * RxJava 网络请求嵌套回调应用场景
     */
    public void registerAndLogin(View view) {
        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤2：创建 网络请求接口 的实例
        GetRequest_interface2 request = retrofit.create(GetRequest_interface2.class);

        // 步骤3：采用Observable<...>形式 对 2个网络请求 进行封装
        observable1 = request.getRegister();
        observable2 = request.getLogin();

        observable1.subscribeOn(Schedulers.io())               // （初始被观察者）切换到IO线程进行网络请求1
                .observeOn(AndroidSchedulers.mainThread())  // （新观察者）切换到主线程 处理网络请求1的结果
                .doOnNext(new Consumer<RegisterEntity>() {
                    @Override
                    public void accept(RegisterEntity result) throws Exception {
                        btn2.setText("激活成功");
                        Toast.makeText(getApplicationContext(),"激活成功",Toast.LENGTH_SHORT).show();
                    }
                })

                .observeOn(Schedulers.io())                 // （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求
                // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
                // 但对于初始观察者，它则是新的被观察者
                .flatMap(new Function<RegisterEntity, ObservableSource<LoginEntity>>() { // 作变换，即作嵌套网络请求
                    @Override
                    public ObservableSource<LoginEntity> apply(RegisterEntity result) throws Exception {
                        // 将网络请求1转换成网络请求2，即发送网络请求2
                        return observable2.delay(2,TimeUnit.MILLISECONDS);
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())  // （初始观察者）切换到主线程 处理网络请求2的结果
//                .delay(2,TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<LoginEntity>() {
                    @Override
                    public void accept(LoginEntity result) throws Exception {
                        btn2.setText("登录成功");
                        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        btn2.setText("登录失败");
                        Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * RxJava 从缓存或者磁盘中获取网络缓存数据
     */
    public void getCache(View view){
        Observable<String> cache = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                  if(memoryCache != null) {
                      e.onNext(memoryCache);
                  } else {
                      e.onComplete();
                  }
            }
        });

        Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
               if(diskCache != null)  {
                   e.onNext(diskCache);
               } else {
                   e.onComplete();
               }
            }
        });

        Observable<String> netWork = Observable.just("从网络中获取数据");
       Observable.concat(cache,disk,netWork).firstElement().subscribe(new Consumer<String>() {
           @Override
           public void accept(String s) throws Exception {
               btn3.setText(s);
           }
       });
    }
}
