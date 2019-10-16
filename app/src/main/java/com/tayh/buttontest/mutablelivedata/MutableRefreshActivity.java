package com.tayh.buttontest.mutablelivedata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tayh.buttontest.R;

/**
 * @author LZY
 * @time 2019/8/13
 */
public class MutableRefreshActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutablerefresh);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadNotify.getInstance().notify("123");
            }
        });
    }
}
