package com.yifan.jotting2.base;

import android.support.annotation.IdRes;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yifan.utils.base.BaseDialogFragment;
import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;


/**
 * 带确认取消按钮的弹窗基类
 *
 * Created by yifan on 2016/7/24.
 */
public abstract class BaseMeasureDialog extends BaseDialogFragment {

    /**
     * 布局加载器
     */
    ViewStubCompat mStub;

    /**
     * 按钮布局
     */
    LinearLayout mLayoutBtns;

    @Override
    public void initView() {
        mStub = (ViewStubCompat) getDialog().findViewById(R.id.vsc_measure_dialog_content);
        mLayoutBtns = (LinearLayout) getDialog().findViewById(R.id.layout_measure_dialog_btns);
    }

    public ViewStubCompat getViewStub() {
        return mStub;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.dialog_measure;
    }

    @Override
    public View getLayoutView() {
        return null;
    }

    /**
     * 添加新的按钮
     *
     * @param id
     * @param text
     * @param listener
     */
    public void addButton(@IdRes int id, String text, View.OnClickListener listener) {
        Button button = new Button(getActivity(), null, R.attr.dialog_measure_button_style);
        button.setId(id);
        button.setText(text);
        button.setOnClickListener(listener);
        if (mLayoutBtns.getChildCount() > 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(ResourcesUtils.getDimensionPixelSize(R.dimen.padding_large), 0, 0, 0);
            mLayoutBtns.addView(button, layoutParams);
        } else {
            mLayoutBtns.addView(button);
        }
    }
}
