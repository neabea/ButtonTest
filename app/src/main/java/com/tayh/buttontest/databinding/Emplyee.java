package com.tayh.buttontest.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

/**
 * @author LZY
 * @time 2019/8/15
 */
public class Emplyee extends BaseObservable {
    private String firstName;
    private String secName;
    public ObservableBoolean isFire = new ObservableBoolean();//不初始化报错

    public ObservableArrayList getArrayList() {
        return arrayList;
    }

    public void addArrayList(String str) {
        this.arrayList.add(str);
    }

    private ObservableArrayList arrayList = new ObservableArrayList();
    /**
     * 必须通过get set方式
     * @param isFire
     */
    public void setIsFire(boolean isFire) {
        this.isFire.set(isFire);
    }


    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.tayh.buttontest.BR.firstName);
    }

    @Bindable
    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
        notifyPropertyChanged(com.tayh.buttontest.BR.secName);
    }


}
