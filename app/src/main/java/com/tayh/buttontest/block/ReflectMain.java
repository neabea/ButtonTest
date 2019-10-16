package com.tayh.buttontest.block;

import java.lang.reflect.Field;


/**
 * @author LZY
 * @time 2019/4/10
 */
public class ReflectMain {
    @TestInterface(12)
    public int age;
    public static void main(String[] args){
        ReflectMain main = new ReflectMain();
        TestInterface testInterface = null;
        Class clazz = main.getClass();
        try {
            Field field = clazz.getField("age");
            testInterface = field.getAnnotation(TestInterface.class);
            System.out.print(testInterface.value());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
