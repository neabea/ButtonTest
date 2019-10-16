package com.tayh.buttontest;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TimeUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    Button button;
    LinearLayout linear;
    Button rightbtn;
    private int screenWidth, screenHeight;//屏幕的宽度，高度
    private static HomeWatcherReceiver mHomeKeyReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
       getSupportActionBar().hide();
       initView();
    }
    private void initView(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        screenHeight = outMetrics.heightPixels;
        button = findViewById(R.id.test_btn);
        rightbtn = findViewById(R.id.right_btn);
        linear = findViewById(R.id.linear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPosition();
            }
        });
        rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"这就对了！",Toast.LENGTH_SHORT).show();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        finish();
//                    }
//                }, 2000);
               Thread thread = Looper.getMainLooper().getThread();
//                SystemClock.sleep(10000);
            }
        });
    }

    private void setPosition(){
        int viewWidth = button.getWidth();
        int viewHeight = button.getHeight();
        //随机生成一个屏内的位置来显示动画
        int x = getRandomInt(screenWidth - viewWidth);
        int y =linear.getHeight()+ getRandomInt(screenHeight -linear.getHeight() - viewHeight);
        button.setX(x);
        button.setY(y);
    }

    private int getRandomInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK||keyCode ==KeyEvent.KEYCODE_HOME){
            Toast.makeText(this,"不点好，出不去哦~",Toast.LENGTH_SHORT).show();
            return true;
        }
            return super.onKeyDown(keyCode, event);
    }


    private static void registerHomeKeyReceiver(Context context) {
        Log.i("test", "registerHomeKeyReceiver");
        mHomeKeyReceiver = new HomeWatcherReceiver();
        final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        context.registerReceiver(mHomeKeyReceiver, homeFilter);
    }

    private static void unregisterHomeKeyReceiver(Context context) {
        Log.i("test", "unregisterHomeKeyReceiver");
        if (null != mHomeKeyReceiver) {
            context.unregisterReceiver(mHomeKeyReceiver);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerHomeKeyReceiver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterHomeKeyReceiver(this);
    }
//
//    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().readTimeout(5, TimeUnit.SECONDS).build();
//    private void setNet(){
//        Request request = new Request.Builder().url("www.baidu.com").get().build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
//        try {
//            Response response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
