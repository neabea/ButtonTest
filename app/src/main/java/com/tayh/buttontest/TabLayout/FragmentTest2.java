package com.tayh.buttontest.TabLayout;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * @author LZY
 * @time 2019/4/18
 */
public class FragmentTest2 extends FragmentStatePagerAdapter {
    public FragmentTest2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

}
