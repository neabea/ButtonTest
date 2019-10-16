package com.tayh.buttontest.viewgroup;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.tayh.buttontest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author LZY
 * @time 2019/5/8
 */
public class ViewDeliverActivity extends Activity {
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_viewdeliver);
        ButterKnife.bind(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("zeyu", "Acitvity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
