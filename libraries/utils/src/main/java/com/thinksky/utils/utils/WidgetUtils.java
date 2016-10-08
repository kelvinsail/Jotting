package com.thinksky.utils.utils;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import com.thinksky.utils.R;

import java.lang.reflect.Field;

/**
 * WidgetUtils
 *
 * Created by yifan on 2016/8/18.
 */
public class WidgetUtils {

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusbarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusbarHeight = ResourcesUtils.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            statusbarHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    24, ResourcesUtils.getDisplayMetrics());
        }
        return statusbarHeight;
    }


    /**
     * 根据系统版本自动设置布局点击背景效果
     *
     * @param view
     */
    public static void setItemClickBackground(View view) {
        //获取主题定义的点击效果
        TypedArray array = view.getContext().getTheme().obtainStyledAttributes(
                new int[]{R.attr.item_click_background});
        Drawable background = (Drawable) array
                .getDrawable(0);
        //设置点击效果
        if (null != background) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackground(background);
            } else {
                view.setBackgroundDrawable(background);
            }
        }
        array.recycle();
    }

    /**
     * 根据系统版本自动设置布局点击背景效果(透明底色)
     *
     * @param view
     */
    public static void setItemClickBackgroundTransParent(View view) {
        //获取主题定义的点击效果
        TypedArray array = view.getContext().getTheme().obtainStyledAttributes(
                new int[]{R.attr.item_click_background_transparent});
        Drawable background = (Drawable) array
                .getDrawable(0);
        //设置点击效果
        if (background != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackground(background);
            } else {
                view.setBackgroundDrawable(background);
            }
        }
        array.recycle();
    }
}
