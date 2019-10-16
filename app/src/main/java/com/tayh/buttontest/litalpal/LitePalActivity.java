package com.tayh.buttontest.litalpal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tayh.buttontest.R;

import org.litepal.LitePal;

import java.util.List;

/**
 * @author LZY
 * @time 2019/6/16
 */
public class LitePalActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal);
        button = findViewById(R.id.btn);
        initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Image> images2=LitePal.findAll(Image.class);
                List<Image> images = LitePal.where("ids = ?", "1").order("saveTime desc").find(Image.class);
                Log.i("zeyu",images.toString());
            }
        });
    }
    private void initData(){
        LitePal.deleteAll(Image.class);
        Image image1 = new Image();
        image1.setIds(1);
        image1.setSaveTime(System.currentTimeMillis());
        image1.setType(0);
        image1.setImage("image1");
        image1.save();
        Image image2 = new Image();
        image2.setIds(1);
        image2.setSaveTime(System.currentTimeMillis());
        image2.setType(0);
        image2.setImage("image2");
        image2.save();
        Image image3 = new Image();
        image3.setIds(2);
        image3.setSaveTime(System.currentTimeMillis());
        image3.setType(0);
        image3.setImage("image3");
        image3.save();

        Image vedio1 = new Image();
        vedio1.setIds(1);
        vedio1.setSaveTime(System.currentTimeMillis());
        vedio1.setType(1);
        vedio1.setVedio("vedio1");
        vedio1.save();
        Image vedio2 = new Image();
        vedio2.setIds(1);
        vedio2.setSaveTime(System.currentTimeMillis());
        vedio2.setType(1);
        vedio2.setVedio("vedio2");
        vedio2.save();
        Image vedio3 = new Image();
        vedio3.setIds(2);
        vedio3.setSaveTime(System.currentTimeMillis());
        vedio3.setType(1);
        vedio3.setVedio("vedio3");
        vedio3.save();

    }
}
