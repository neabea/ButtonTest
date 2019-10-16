package com.tayh.buttontest.mutablelivedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tayh.buttontest.R;

/**
 * @author LZY
 * @time 2019/8/13
 */
public class MutableLiveActivity extends AppCompatActivity {

    TextView tvText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutablelive);
        tvText = findViewById(R.id.tv_text);
        UploadNotify.getInstance().register(this, new UploadNotify.NotifyCallBack() {
            @Override
            public void onNotify(String str) {
//                BuglyLog.i("zeyu","onNotify:"+str);
                Log.i("zeyu","onNotify:"+str);
                tvText.setText(str);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UploadNotify.getInstance().notify("1234");
                Intent intent =  new Intent(MutableLiveActivity.this,MutableRefreshActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
