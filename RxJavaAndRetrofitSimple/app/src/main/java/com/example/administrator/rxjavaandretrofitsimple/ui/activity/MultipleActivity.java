package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.Person;
import com.example.administrator.rxjavaandretrofitsimple.bean.PersonComparator;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.MultiplePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.MultipleView;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basewithoutmodel.BaseStatusMvpActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/4/13
 * <p>
 * 类描述：
 */

public class MultipleActivity extends BaseStatusMvpActivity<MultipleView, MultiplePresenter> implements MultipleView {


    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MultipleActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected MultiplePresenter createPresenter() {
        return new MultiplePresenter();
    }

    @Override
    protected void onViewCreated() {
        Set<Person> personList = new TreeSet<Person>(new PersonComparator());
        personList.add(new Person("张三", 18));
        personList.add(new Person("李四", 17));
        personList.add(new Person("赵六", 18));
        personList.add(new Person("王五", 19));
        StringBuffer sb = new StringBuffer();
        for (Person person : personList) {
            sb.append(person.name + ":" + person.age + "........");
        }
        Log.d("person----->", sb.toString());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple;
    }

    @Override
    protected void initTitleBar() {
        showTitle(false);
        setTitleCenter("综合");
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    public void refreshCompleted() {

    }

    @Override
    public void displayEmptyPage() {

    }

    @Override
    public void displayErrorPage() {

    }

    @Override
    public void displayNoMoreTip() {

    }
}
