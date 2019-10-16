package com.tayh.buttontest.connectrecycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author LZY
 * @time 2019/4/30
 */
public class RightBean implements MultiItemEntity {
    public static final int TITLES = 0;
    public static final int DETILES = 1;
    private int type;
    private TitleBean titleBean;
    private DetailsBean detailsBean;

    public TitleBean getTitleBean() {
        return titleBean;
    }

    public void setTitleBean(TitleBean titleBean) {
        this.titleBean = titleBean;
    }

    public DetailsBean getDetailsBean() {
        return detailsBean;
    }

    public void setDetailsBean(DetailsBean detailsBean) {
        this.detailsBean = detailsBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class TitleBean{
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class DetailsBean{
        private String detail;

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    @Override
    public int getItemType() {
        return type;
    }
}
