package com.tayh.buttontest.bitmap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author LZY
 * @time 2019/4/12
 */
public class SimpleImageLoader {
    private LruCache<String, Bitmap>  mLruCache;

    public static SimpleImageLoader getInstance(){
        return SimpleImageHolder.sInstance;
    }

    private static class SimpleImageHolder{
       private static final SimpleImageLoader sInstance = new SimpleImageLoader();
    }

    private SimpleImageLoader(){
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mLruCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void displayImage(Context context,ImageView imageView, String url){
        Bitmap bitmap = getBitmapFromCache(url);
        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }else {
            downLoadImage(context,imageView,url);
        }
    }
    /**
     * 从缓存读取图片
     */
    private Bitmap getBitmapFromCache(String url){
        return mLruCache.get(url);
    }

    /**
     * 将下载的图片保存到缓存
     */
    private void putBitmapToCache(Bitmap bitmap,String url){
        if(bitmap!=null){
            mLruCache.put(url,bitmap);
        }
    }

    private void downLoadImage(Context context,ImageView imageView,String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                Bitmap bitmap = BitmapUtils.compress(bytes,imageView.getWidth(),imageView.getHeight());
                ((Activity)context).runOnUiThread(()->{imageView.setImageBitmap(bitmap);});
                putBitmapToCache(bitmap,url);
            }
        });
    }
}
