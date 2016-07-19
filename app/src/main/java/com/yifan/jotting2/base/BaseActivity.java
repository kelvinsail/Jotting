package com.yifan.jotting2.base;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.base.impl.LoadImpl;
import com.yifan.jotting2.base.lifecycle.LifeCycleActivity;

/**
 * Activity功能基类
 * <p/>
 * Created by yifan on 2016/7/14.
 */
public abstract class BaseActivity extends LifeCycleActivity implements LoadImpl{

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initView();
        setListener();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        setListener();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();
        setListener();
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void createLoadingdialog() {

    }

    @Override
    public void createLoadingdialog(String message) {

    }

    @Override
    public void createLoadingdialog(String message, boolean canelable, boolean touchOutsideCancelable) {

    }

    @Override
    public void dissmissLoadingDialog() {

    }
}
