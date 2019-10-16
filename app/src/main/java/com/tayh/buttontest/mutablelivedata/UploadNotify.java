package com.tayh.buttontest.mutablelivedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author LZY
 * @time 2019/8/13
 */
public class UploadNotify {

    private static UploadNotify uploadNotify;
    private MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
    private boolean dataChanged=false;

    public static UploadNotify getInstance() {
        if (uploadNotify == null) {
            uploadNotify = new UploadNotify();
        }
        return uploadNotify;
    }

    public void register(LifecycleOwner lifecycleOwner,NotifyCallBack  notifyCallBack){
        mutableLiveData.observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i("zeyu","UploadNotify onChanged:"+s);
                notifyCallBack.onNotify(s);
            }

        });

    }

    public void notify(String str) {
        mutableLiveData.postValue(str);
    }

    public interface NotifyCallBack {
        void onNotify(String str);
    }
}
