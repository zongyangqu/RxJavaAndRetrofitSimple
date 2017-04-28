package com.example.administrator.rxjavaandretrofitsimple.bean;

import java.util.Comparator;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/24
 *
 * 类描述：
 */

public class PersonComparator implements Comparator<Person> {

    /**
     *重写compare方法，先比较年龄，
     * 年龄相同比较name的ascii码值顺序
     */
    @Override
    public int compare(Person p1, Person p2) {
        int flag = p1.age - p2.age;
        if (flag == 0) {
            flag = p1.name.compareTo(p2.name);
        }
        return flag;
    }
}
