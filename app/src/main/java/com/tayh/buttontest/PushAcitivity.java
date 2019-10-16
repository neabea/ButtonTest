package com.tayh.buttontest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author LZY
 * @time 2019/9/19
 */
public class PushAcitivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.et_text)
    EditText editText;
    private CloudPushService pushService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPush();
            }
        });
    }

    private void initPush() {
        pushService = PushServiceFactory.getCloudPushService();
        pushService.bindAccount(editText.getText().toString(), new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i("zeyu", "bingaccount(" + editText.getText().toString()+ ") success :" + s);
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.i("zeyu", s + s1);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        pushService = PushServiceFactory.getCloudPushService();
//        pushService.unbindAccount(new CommonCallback() {
//            @Override
//            public void onSuccess(String s) {
//                Log.i("zeyu","unbindAccount success");
//            }
//
//            @Override
//            public void onFailed(String s, String s1) {
//                Log.i("zeyu","unbindAccount failed");
//            }
//        });
    }
}
