package com.tayh.buttontest;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.github.moduth.blockcanary.BlockCanary;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;

/**
 * @author LZY
 * @time 2019/3/5
 */
public class MyApplication extends Application {
    CloudPushService pushService;
    @Override
    public void onCreate() {
        super.onCreate();
        BlockCanary.install(this,new AppBlockCanaryContext()).start();
        CrashReport.initCrashReport(getApplicationContext(), "7e3728644c", true);
        LitePal.initialize(this);
        initCloudChannel(this);
    }

    private void initCloudChannel(Context applicationContext){
        PushServiceFactory.init(applicationContext);
        pushService = PushServiceFactory.getCloudPushService();
        setDefNotifIcon();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i("zeyu", "init cloudchannel success:"+pushService.getDeviceId());
            }
            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.i("zeyu", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            //String id = "8.0up";
            String id = "1";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "notification channel";
            // 用户可以看到的通知渠道的描述
            String description = "notification description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    private void setDefNotifIcon() {
        Drawable drawable = getApplicationContext().getResources().getDrawable(R.mipmap.ic_launcher);
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            pushService.setNotificationLargeIcon(bitmap);
            Log.i("zeyu", "Set notification largeIcon res id to R.");
        }
    }

}
