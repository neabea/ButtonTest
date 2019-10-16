package com.tayh.buttontest.autoCompleteTextView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tayh.buttontest.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LZY
 * @time 2019/10/8
 */
public class AutoCompleteActivity extends AppCompatActivity {


    AutoCompleteTextView autoCompleteTextView;
    String[] data = {"111", "2222", "11,23", "张三", "李四"};
    AutoEditAdapter adapter;
    Button button;
    Button button2;
    Mythread mythread = new Mythread();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_auto_complete);
        autoCompleteTextView = findViewById(R.id.autoTv);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        adapter = new AutoEditAdapter(Arrays.asList(data), this);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout linearLayout = (LinearLayout) view;
                TextView textView = linearLayout.findViewById(R.id.tv_title);
                Log.i("zeyu", "view:" + textView.getText().toString());
                Log.i("zeyu", "adapter:" +adapter.getFilterData().get(i));
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    autoCompleteTextView.dismissDropDown();
                    return;
                }
                if (!autoCompleteTextView.isPopupShowing()) {
                    autoCompleteTextView.showDropDown();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mythread.start();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mythread.interrupt();
//                mythread.isStop = true;
            }
        });
    }

    class Mythread extends Thread{

        volatile boolean isStop = false;
        @Override
        public void run() {

            super.run();
            for(int i=0;i<10000000;i++){
                Log.i("zeyu",i+"--"+"--"+isInterrupted());
                if(isStop){
                    break;
                }
                if(isInterrupted()){
                    break;
                }
            }
        }
    }

}
