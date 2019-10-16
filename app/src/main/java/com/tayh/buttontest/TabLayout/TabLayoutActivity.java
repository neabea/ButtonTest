package com.tayh.buttontest.TabLayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.tayh.buttontest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZY
 * @time 2019/3/15
 */
public class TabLayoutActivity extends AppCompatActivity {
    private DrawerLayout mdDrawLayout;
    private ViewPager mViewPage;
    private TabLayout mTablayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_layout);
        mdDrawLayout = findViewById(R.id.drawer_layout);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPage = findViewById(R.id.viewpager);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.block_canary_notification);
        ab.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nv_main);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    String title = menuItem.getTitle().toString();
                    Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
                    mdDrawLayout.closeDrawers();
                    return true;
                }
            });
        }
        initViewPage();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mdDrawLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPage() {
        mTablayout = findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("购物");
        titles.add("测试");
        titles.add("简单");
        for (int i = 0; i < titles.size(); i++) {
            mTablayout.addTab(mTablayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new com.tayh.buttontest.TabLayout.ListFragment());
        }
        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPage.setAdapter(mFragmentAdapter);
        mTablayout.setupWithViewPager(mViewPage);
        mTablayout.setTabsFromPagerAdapter(mFragmentAdapter);
        mViewPage.setCurrentItem(1);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                Log.i("zeyu", "onPageScrolled:" + i + "---" + v + "---" + i1);
            }

            @Override
            public void onPageSelected(int i) {
//                Log.i("zeyu", "onPageSelected:" + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                Log.i("zeyu", "onPageScrollStateChanged:" + i);
            }
        });
    }
}
