package com.tayh.buttontest.bitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tayh.buttontest.R;


/**
 * @author LZY
 * @time 2019/4/12
 */
public class BitmapLruAcitvity extends Activity {

    String urls ="https://img3.mukewang.com/szimg/5c18d2d8000141c506000338.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitmap_layout);
        final ImageView imageView = findViewById(R.id.imageview);
        SimpleImageLoader.getInstance().displayImage(this,imageView,urls);
    }
}

