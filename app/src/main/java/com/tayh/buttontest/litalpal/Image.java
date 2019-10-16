package com.tayh.buttontest.litalpal;

import org.litepal.crud.LitePalSupport;

/**
 * @author LZY
 * @time 2019/6/16
 */
public class Image extends LitePalSupport {
    private String image;
    private String vedio;
    private int type;
    private int ids;
    private long saveTime;

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
