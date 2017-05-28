package com.yifan.jotting2.base.impl;

import android.view.View;

import com.yifan.jotting2.R;

/**
 * 弹窗接口抽取
 *
 * Created by yifan on 2016/7/18.
 */
public interface Load {

    /**
     * 显示加载Loading弹窗
     */
    void createLoadingdialog();

    /**
     * 显示加载Loading弹窗
     *
     * @param message 提示语
     */
    void createLoadingdialog(String message);

    /**
     * 显示加载Loading弹窗
     *
     * @param message                提示语
     * @param canelable              是否可以取消
     * @param touchOutsideCancelable 点击区域外是否可取消
     */
    void createLoadingdialog(String message, boolean canelable, boolean touchOutsideCancelable);

    /**
     * 取消弹窗
     */
    void dissmissLoadingDialog();
}
