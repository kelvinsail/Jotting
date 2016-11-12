package com.yifan.jotting2.widget;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.utils.utils.WidgetUtils;
import com.yifan.jotting2.R;

/**
 * 添加新数据，浮动按钮
 *
 * Created by yifan on 2016/11/6.
 */
public class AddFloatingButton extends FloatingActionButton {

    public AddFloatingButton(Context context) {
        this(context, null);
    }

    public AddFloatingButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddFloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
//        //设置图片
//        setImageResource(R.drawable.icon_add);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        //设置边距
        int margin = ResourcesUtils.getDimensionPixelOffset(R.dimen.fab_margin_bottom);
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) params;
        lp.rightMargin = margin;
        lp.bottomMargin = margin + WidgetUtils.getNavigationBarHeight();
        super.setLayoutParams(lp);
    }
}
