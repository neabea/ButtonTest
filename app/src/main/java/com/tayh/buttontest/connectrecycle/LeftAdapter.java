package com.tayh.buttontest.connectrecycle;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tayh.buttontest.R;

import java.util.List;

/**
 * @author LZY
 * @time 2019/4/30
 */
public class LeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context context;
    private int selectPosition;

    public LeftAdapter(Context context, int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.context = context;
    }

    public void setSelectPosition(int position) {
        selectPosition = position;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_left, item);
        int position = helper.getAdapterPosition();
        if (position == selectPosition) {
            helper.setBackgroundColor(R.id.tv_left, Color.parseColor("#f3f3f3"));
        }else {
            helper.setBackgroundColor(R.id.tv_left, Color.parseColor("#ffffff"));
        }
//        helper.addOnClickListener(R.id.tv_left);
//        helper.addOnClickListener(R.id.tv_left_other);
    }

}
