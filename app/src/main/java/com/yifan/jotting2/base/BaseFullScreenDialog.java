package com.yifan.jotting2.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ViewStubCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.lifecycle.LifeCycleDialogFragment;

/**
 * 全屏自定义布局Dialog
 * <p/>
 * Created by yifan on 2016/7/20.
 */
public abstract class BaseFullScreenDialog extends LifeCycleDialogFragment {

    /**
     * 布局加载器
     */
    private ViewStubCompat mViewStubCompat;

    /**
     * 根布局
     */
    public View mContentView;

    /**
     * 布局Id
     */
    public int mLayoutID;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog_FullScreen);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        mViewStubCompat = new ViewStubCompat(getActivity(), null);
        dialog.setContentView(mViewStubCompat);
        initView();
        setListener();
        return dialog;
    }


    /**
     * 获取布局加载器
     *
     * @return
     */
    public ViewStubCompat getViewStubCompat() {
        return mViewStubCompat;
    }

    /**
     * 设置根布局
     *
     * @param resID
     * @return
     */
    public View setContentView(@LayoutRes int resID) {
        if (null != mViewStubCompat) {
            mViewStubCompat.setLayoutInflater(LayoutInflater.from(getActivity()));
            mViewStubCompat.setLayoutResource(R.layout.dialog_project_details);
            mContentView = mViewStubCompat.inflate();
        }
        return mContentView;
    }

    /**
     * 获取根布局
     *
     * @return
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * 查找控件
     *
     * @param viewID
     * @return
     */
    public View findViewByID(@IdRes int viewID) {
        if (null != mContentView) {
            return mContentView.findViewById(viewID);
        }
        return null;
    }
}
