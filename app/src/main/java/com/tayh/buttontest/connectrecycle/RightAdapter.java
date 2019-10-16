package com.tayh.buttontest.connectrecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.tayh.buttontest.R;

import java.util.List;

/**
 * @author LZY
 * @time 2019/4/30
 */
public class RightAdapter extends BaseMultiItemQuickAdapter<RightBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RightAdapter(List<RightBean> data) {
        super(data);
        addItemType(DetailBean.TITLES, R.layout.right_item_title);
        addItemType(DetailBean.DETILES, R.layout.right_item_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, RightBean item) {
        if (helper.getItemViewType() == RightBean.TITLES) {
            helper.setText(R.id.tv_title_right, item.getTitleBean().getTitle());
        } else if (helper.getItemViewType() == RightBean.DETILES) {
            helper.setText(R.id.tv_detail_right, item.getDetailsBean().getDetail());
        }
    }

}
