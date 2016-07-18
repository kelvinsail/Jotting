package com.yifan.jotting2.base.lifecycle;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.base.impl.PagerImpl;

/**
 * 生命周期Activity
 * <p/>
 * Created by yifan on 2016/7/14.
 */
public abstract class LifeCycleActivity extends AppCompatActivity implements PagerImpl {

    /**
     * 是否为活动状态
     */
    private boolean mIsActived;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onCreate");
        }
    }

    @Override
    protected void onDestroy() {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onDestroy");
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onPause");
        }
        mIsActived = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActived = true;
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onResume");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onStart");
        }
    }

    @Override
    protected void onStop() {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onStop");
        }
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onRestart");
        }
    }

    @Override
    public boolean isPrintLifeCycle() {
        return false;
    }

    @Override
    public boolean isActived() {
        return mIsActived;
    }

    @Override
    public abstract String getTAG();
}
