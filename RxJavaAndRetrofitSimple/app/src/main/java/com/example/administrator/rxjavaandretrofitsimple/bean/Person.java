package com.example.administrator.rxjavaandretrofitsimple.bean;

import android.support.annotation.NonNull;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/4/24
 * <p>
 * 类描述：
 */

public class Person implements Comparable<Person>{
    public int age;
    public String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    /**
     *重写compare方法，先比较年龄，
     * 年龄相同比较name的ascii码值顺序
     */
    @Override
    public int compareTo(@NonNull Person person) {
        int flag = this.age - person.age;
        if (flag == 0) {
            flag = this.name.compareTo(person.name);
        }
        return flag;
    }
}

