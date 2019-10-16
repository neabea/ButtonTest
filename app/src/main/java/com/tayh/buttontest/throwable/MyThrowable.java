package com.tayh.buttontest.throwable;

/**
 * @author LZY
 * @time 2019/8/13
 */
public class MyThrowable extends Throwable{

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
