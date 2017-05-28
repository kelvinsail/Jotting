package com.yifan.jotting2.ui.projects;

import com.yifan.jotting2.base.impl.IView;
import com.yifan.utils.base.BaseDialogFragment;

import java.lang.ref.WeakReference;

/**
 * IView接口
 * Created by wuyifan on 2017/5/28.
 */

public class ProjectDialogView implements IView<Boolean> {

    private WeakReference<BaseDialogFragment> mDialog;

    public ProjectDialogView(WeakReference<BaseDialogFragment> dialog) {
        this.mDialog = dialog;
    }

    @Override
    public void onSuccess(Boolean object) {
        if (null != mDialog.get()) {
            mDialog.get().dismiss();
        }
    }

    @Override
    public void onError(Throwable error) {

    }
}