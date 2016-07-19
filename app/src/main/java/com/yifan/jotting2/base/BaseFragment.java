package com.yifan.jotting2.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.base.impl.LoadImpl;
import com.yifan.jotting2.base.lifecycle.LifeCycleFragment;

/**
 * Fragmnet基类
 * <p/>
 * Created by yifan on 2016/7/16.
 */
public abstract class BaseFragment extends LifeCycleFragment implements LoadImpl {

    /**
     * 获取标题的名字
     *
     * @return
     */
    public abstract String getTitleName();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
