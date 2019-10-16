package com.tayh.buttontest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * @author LZY
 * @time 2019/3/8
 */
public class DrawActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private Context mContext;
    private DrawerLayout mDlMain;
    private FrameLayout mFlContent;
    private LinearLayout mRlLeft, mRlRight;
    private ListView mLvLeft;
    private TextView mTvRight;
    private String[] leftMenuNames = {"left_item1", "left_item2",
            "left_item3", "left_item4"};
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable_layout);
        mContext = this;
        initView();
    }

    private void initView() {
        mDlMain = findViewById(R.id.drawer_layout);
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mRlLeft = (LinearLayout) findViewById(R.id.rl_left);
        mRlRight = (LinearLayout) findViewById(R.id.rl_right);
        mLvLeft = (ListView) findViewById(R.id.lv_left);
        mLvLeft.setAdapter(new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, leftMenuNames));//给左边菜单写入数据
//        mTvRight = (TextView) findViewById(R.id.tv_right);
//        mTvRight.setText("right_content");//给右边菜单内容赋值
        mLvLeft.setOnItemClickListener(this);
        button = findViewById(R.id.btn_open_all_drawer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });
    }

    private void openAlbum(){
        PictureSelector.create(this).openGallery(PictureMimeType.ofImage())
                .maxSelectNum(6)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia path : selectList) {
                        Log.d("zeyu",path.getPath());
                    }
                    break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment lFragment = new MenuFragment();
        Fragment lFragment2 = new BtnFragment();
        Bundle lBundle = new Bundle();
        lBundle.putString("menu_str", "item_" + (position + 1));
        lFragment.setArguments(lBundle);
        FragmentManager fragmentManager = getFragmentManager();
        if(position==0||position==3){
            fragmentManager.beginTransaction().replace(R.id.l_fram, lFragment).commit();
        }
        if(position==1||position==2){
            fragmentManager.beginTransaction().replace(R.id.l_fram, lFragment2).commit();
        }
        mLvLeft.setItemChecked(position, true);
        mDlMain.closeDrawers();//关闭抽屉
    }
}
