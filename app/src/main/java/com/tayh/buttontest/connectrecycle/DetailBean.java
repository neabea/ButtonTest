package com.tayh.buttontest.connectrecycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author LZY
 * @time 2019/4/30
 */
public class DetailBean implements MultiItemEntity {

    public static final int TITLES = 0;
    public static final int DETILES = 1;
    private String title;
    private String detail;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return getType();
    }
}
