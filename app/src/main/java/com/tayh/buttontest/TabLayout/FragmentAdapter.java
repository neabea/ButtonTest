package com.tayh.buttontest.TabLayout;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author LZY
 * @time 2019/3/15
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragment;
    private List<String> mTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> mFragment, List<String> mTitles) {
        super(fm);
        this.mFragment = mFragment;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
