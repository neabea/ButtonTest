package com.tayh.buttontest.connectrecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tayh.buttontest.R;
import com.tayh.buttontest.TabLayout.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author LZY
 * @time 2019/4/30
 */
public class ConnectAcitivity extends AppCompatActivity implements TitleInterface{
    private Unbinder unbinder;
    @BindView(R.id.rv_left)
    RecyclerView leftRecyclerView;
    @BindView(R.id.rv_right)
    RecyclerView rightRecyclerView;
    List<String> leftList = new ArrayList<>();
    List<RightBean> rightList = new ArrayList<>();
    LeftAdapter leftAdapter;
    RightAdapter rightAdapter;
    LinearLayoutManager linearLayoutManager;
    TitleDecoration titleDecoration;
    private boolean move = false;
    private int mIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_connect);
        unbinder = ButterKnife.bind(this);
        initData();
        initLeftView();
        initRightView();
//        initRightView2();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            leftList.add("列表名" + i);
        }
        for (int i = 0; i < 10; i++) {
            RightBean rightBean = new RightBean();
            rightBean.setType(0);
            RightBean.TitleBean titleBean = new RightBean.TitleBean();
            titleBean.setTitle("标题" + i);
            rightBean.setTitleBean(titleBean);
            rightList.add(rightBean);
            for (int j = 0; j < 5; j++) {
                RightBean rightBean2 = new RightBean();
                rightBean2.setType(1);
                RightBean.DetailsBean detailsBean = new RightBean.DetailsBean();
                detailsBean.setDetail("内容" + j);
                rightBean2.setDetailsBean(detailsBean);
                rightList.add(rightBean2);
            }
        }
    }

    private void initLeftView() {
        leftAdapter = new LeftAdapter(this, R.layout.left_item, leftList);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        leftRecyclerView.setAdapter(leftAdapter);
//        leftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if(view.getId()==R.id.tv_left){
//                    Toast.makeText(ConnectAcitivity.this, "点击了左边" + position, Toast.LENGTH_SHORT).show();
//                }
//                if(view.getId()==R.id.tv_left_other){
//                    Toast.makeText(ConnectAcitivity.this, "点击了右边" + position, Toast.LENGTH_SHORT).show();
//                }
//                view.setBackgroundColor(getResources().getColor(R.color.blue));
//            }
//        });
        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ConnectAcitivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                setChecked(position, true);
            }
        });
    }

    private void initRightView2(){
        RecyclerViewAdapter recycleViewAdapter = new RecyclerViewAdapter(this,leftList);
        linearLayoutManager = new LinearLayoutManager(this);
        rightRecyclerView.setLayoutManager(linearLayoutManager);
        rightRecyclerView.setAdapter(recycleViewAdapter);
    }

    private void initRightView() {
        rightAdapter = new RightAdapter(rightList);
        linearLayoutManager = new LinearLayoutManager(this);
        rightRecyclerView.setLayoutManager(linearLayoutManager);
        rightRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        titleDecoration = new TitleDecoration(this,rightList);
        titleDecoration.setListener(this);
        rightRecyclerView.addItemDecoration(titleDecoration);
        rightRecyclerView.setAdapter(rightAdapter);
        rightRecyclerView.addOnScrollListener(new RecyclerViewListener());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void setChecked(int position, boolean isLeft) {
        if (isLeft) {
            leftAdapter.setSelectPosition(position);
            int count = 0, currentPos = 0;
            for (int i = 0; i < rightList.size(); i++) {
                if (currentPos <= position) {
                    count++;
                    if (rightList.get(i).getItemType() == RightBean.TITLES) {
                        currentPos++;
                    }
                } else {
                    break;
                }
            }
            mIndex = count-1;
            rightRecyclerView.stopScroll();
            smoothMoveToPosition(mIndex);
            titleDecoration.setLeftClick(true);
        }else {
            int count = 0;
            while(position>=0){
                if(rightList.get(position).getTitleBean()!=null){
                    count++;
                }
                position--;
            }
            Log.i("zeyu","count:"+count);
            titleDecoration.setLeftClick(false);
            leftAdapter.setSelectPosition(count-1);
        }
    }

    private void smoothMoveToPosition(int n) {
        Log.i("zeyu","smoothMoveToPosition:"+n);
        int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = linearLayoutManager.findLastVisibleItemPosition();
        Log.i("zeyu","first--->"+String.valueOf(firstItem));
        Log.i("zeyu","last--->"+String.valueOf(lastItem));
        if (n <= firstItem) {
            rightRecyclerView.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            Log.i("zeyu","pos---->"+ String.valueOf(n) + "VS" + firstItem);
            int top = rightRecyclerView.getChildAt(n - firstItem).getTop();
            Log.i("zeyu","top---->"+ String.valueOf(top));
            rightRecyclerView.scrollBy(0, top);
        } else {
            //只会在底部出现，滚动停止后，在执行2步骤
            rightRecyclerView.smoothScrollToPosition(n);
            move = true;
        }
    }

    @Override
    public void setSelected(int position) {
        setChecked(position,false);
    }

    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
//            Log.i("zeyu","newState:"+newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                move = false;
                int n = mIndex - linearLayoutManager.findFirstVisibleItemPosition();
                Log.d("n---->", String.valueOf(n));
                if (0 <= n && n < rightRecyclerView.getChildCount()) {
                    int top = rightRecyclerView.getChildAt(n).getTop();
                    Log.d("top--->", String.valueOf(top));
                    rightRecyclerView.smoothScrollBy(0, top);
                }
            }else if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
                Log.i("zeyu","newState:"+newState);
                titleDecoration.setLeftClick(false);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int adapterNowPos = linearLayoutManager.findFirstVisibleItemPosition();
//            Log.i("zeyu","pos:"+adapterNowPos);
        }
    }
}
