package com.thinksky.utils.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.thinksky.utils.R;
import com.thinksky.utils.base.lifecycle.LifeCycleDialogFragment;

/**
 * BaseDialogFragment DialogFragment基类
 *
 * Created by yifan on 2016/9/18.
 */
public abstract class BaseDialogFragment extends LifeCycleDialogFragment {

    @Override
    public abstract String getTAG();

    @Override
    public void initView() {
    }

    @Override
    public void setListener() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.BaseDialog);
        if (getLayoutResID() > 0) {
            dialog.getWindow().setContentView(
                    LayoutInflater.from(getActivity()).inflate(getLayoutResID(), null));
        } else {
            dialog.getWindow().setContentView(getLayoutView());
        }
        dialog.setCancelable(isCancelable());
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
        return dialog;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    /**
     * 获取资源布局ID
     *
     * @return 资源布局ID
     */
    public abstract int getLayoutResID();

    /**
     * 获取布局控件对象
     *
     * @return
     */
    public abstract View getLayoutView();

    /**
     * 窗口是否可以消除
     *
     * @return
     */
    public boolean isCancelable() {
        return true;
    }


    /**
     * 点击窗口外区域是否可以消除
     *
     * @return
     */
    public boolean isCanceledOnTouchOutside() {
        return true;
    }

}
