package com.yifan.jotting2.base;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yifan.jotting2.R;
import com.yifan.jotting2.utils.ResourcesUtils;

/**
 * 带确认取消按钮的弹窗基类
 * <p/>
 * Created by yifan on 2016/7/24.
 */
public abstract class BaseMeasureDialog extends BaseFullScreenDialog {

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
        View view = setContentView(R.layout.dialog_measure);
        mStub = (ViewStubCompat) view.findViewById(R.id.vsc_measure_dialog_content);
        mLayoutBtns = (LinearLayout) view.findViewById(R.id.layout_measure_dialog_btns);
    }

    @Override
    public final View setContentView(@LayoutRes int resID) {
        return super.setContentView(resID);
    }

    @Override
    public ViewStubCompat getViewStubCompat() {
        return mStub;
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
