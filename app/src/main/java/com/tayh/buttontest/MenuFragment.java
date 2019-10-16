package com.tayh.buttontest;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author LZY
 * @time 2019/3/8
 */
public class MenuFragment extends android.app.Fragment {

    private View mView;
    private Context mContext;
    private TextView mTvMenuFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_fragment_menu, container, false);
        mContext = getActivity();
        Bundle lBundle = getArguments();
        mTvMenuFragment = (TextView) mView.findViewById(R.id.tv_menu_fragment);
        if (lBundle != null) {
            mTvMenuFragment.setText(lBundle.getString("menu_str"));
        }
        return mView;
    }
}
