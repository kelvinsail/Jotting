package com.yifan.jotting2.base.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yifan.jotting2.base.impl.PagerImpl;

/**
 * Created by yifan on 2016/7/16.
 */
public abstract class LifeCycleFragment extends Fragment implements PagerImpl {

    /**
     * 是否为活动状态
     */
    private boolean mIsActived;


    public LifeCycleFragment() {
        super();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onHiddenChanged: " + hidden);
        }
        mIsActived = !hidden;
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onActivityCreated");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onViewCreated");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onStart");
        }
    }

    @Override
    public void onDestroyView() {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onDestroyView");
        }
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onStop");
        }
        super.onStop();
    }

    @Override
    public void onPause() {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onPause");
        }
        mIsActived = false;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsActived = true;
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onResume");
        }
    }

    @Override
    public void onDestroy() {
        if (isPrintLifeCycle() && !TextUtils.isEmpty(getTAG())) {
            Log.i(getTAG(), "onDestroy");
        }
        super.onDestroy();
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
