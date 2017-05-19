package com.example.administrator.rxjavaandretrofitsimple.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.qzy.mylibrary.Person;

public class SampleTest extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Person p = new Person("张三",18,"北京");
    }
}
