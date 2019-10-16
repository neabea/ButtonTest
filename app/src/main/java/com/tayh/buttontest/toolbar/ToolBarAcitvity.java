package com.tayh.buttontest.toolbar;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tayh.buttontest.R;
import com.tayh.buttontest.connectrecycle.LeftAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author LZY
 * @time 2019/5/7
 */
public class ToolBarAcitvity extends AppCompatActivity {
    Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tl_expand)
    View expandView;
    @BindView(R.id.tl_collapse)
    View colView;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    @BindView(R.id.imageview)
    ImageView imageView;
    List<String> list = new ArrayList<>();
    LeftAdapter leftAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_toolbar);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((View v) -> {
            finish();
        });
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            list.add("列表名" + i);
        }
    }

    private void initView() {
        leftAdapter = new LeftAdapter(this, R.layout.left_item, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(leftAdapter);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int offset = Math.abs(i);
                int total = appBarLayout.getTotalScrollRange();
                if (offset <= total * 0.5) {
                    expandView.setVisibility(View.VISIBLE);
                    colView.setVisibility(View.GONE);
                } else {
                    expandView.setVisibility(View.GONE);
                    colView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ObjectAnimator.ofFloat(imageView,"rotationX",360).setDuration(2000).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
