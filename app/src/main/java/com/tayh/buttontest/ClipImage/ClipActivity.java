package com.tayh.buttontest.ClipImage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import android.widget.ImageView;
import android.widget.Toast;

import com.tayh.buttontest.R;

public class ClipActivity extends Activity {

    /**
     * ImageView分割
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_clip);
//        直接获取宽高为0
//        ViewGroup.LayoutParams para = imageView.getLayoutParams();
//        int w=para.width;
//        int h=para.height;
//        Log.i("111","w="+w);
//        Log.i("111","h="+h);

        // 等待ImageVivew加载完成
        ImageView imageView2 = findViewById(R.id.imageview2);
        imageView2.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // ImageView的宽和高，
                Log.i("zeyu", "iv_W = " + imageView2.getWidth() + ", iv_H = " + imageView2.getHeight());
                // 显示的逻辑密度==密度/160
                float density = ClipActivity.this.getResources().getDisplayMetrics().density;
                // 通过getIntrinsic 获得ImageView中Image的真实宽高，
                int dw = (int) (imageView2.getDrawable().getBounds().width() / density);
                int dh = (int) (imageView2.getDrawable().getBounds().height() / density);
                Log.i("zeyu", ClipActivity.this.getResources().getDisplayMetrics().density
                        + "drawable_X = " + dw + ", drawable_Y = " + dh);
                // 通过get获得ImageView中Image的真实宽高，
                int inDw = (int) (imageView2.getDrawable().getIntrinsicWidth()/density); //单位是dp
                int inDh = (int) (imageView2.getDrawable().getIntrinsicHeight()/density); //单位是dp
                Log.i("zeyu", ClipActivity.this.getResources().getDisplayMetrics().density
                        +"Intrinsic_drawable_X = " + inDw + ", Intrinsic_drawable_Y = " + inDh);
            }
        });
    }


    public void showClickArea(int area) {
        Toast.makeText(this, "您点击到了第" + area + "块区域！", Toast.LENGTH_SHORT).show();
    }
}
