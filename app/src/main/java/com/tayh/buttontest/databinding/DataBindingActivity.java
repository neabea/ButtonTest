package com.tayh.buttontest.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tayh.buttontest.R;
import com.tayh.buttontest.databinding.ActivityDatabindingBinding;

/**
 * @author LZY
 * @time 2019/8/15
 */
public class DataBindingActivity extends AppCompatActivity {
    ActivityDatabindingBinding binding;
    Emplyee emplyee = new Emplyee();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        emplyee.setFirstName("111");
        emplyee.setSecName("222");
        binding.setEmplee1(emplyee);
        binding.setPresent1(new Presenters());
        binding.viewstub1.getViewStub().inflate();
    }
    public class Presenters{
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            emplyee.setFirstName(s.toString());
            binding.setEmplee1(emplyee);
        }
        public void onClick(View  view){
            emplyee.setIsFire(true);
            emplyee.addArrayList("111");
            emplyee.addArrayList("222");
            Toast.makeText(DataBindingActivity.this,"onClick",Toast.LENGTH_SHORT).show();
        }

        public void onClickBack(Emplyee emplyee){
            Toast.makeText(DataBindingActivity.this,"onClickBack:"+emplyee.getFirstName(),Toast.LENGTH_SHORT).show();
        }
    }




}
