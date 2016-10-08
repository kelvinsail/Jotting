package com.yifan.jotting2.utils;

import android.util.TypedValue;

import com.thinksky.utils.utils.ResourcesUtils;

import java.lang.reflect.Method;

/**
 * Created by yifan on 2016/7/20.
 */
public class WidgetUtils {

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        //final float fontScale = context.ResourcesUtils.getDisplayMetrics().scaledDensity;
        //return (int) (spValue * fontScale + 0.5f);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, ResourcesUtils.getResources().getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        //final float scale = context.ResourcesUtils.getDisplayMetrics().density;
        //return (int) (dpValue * scale + 0.5f);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, ResourcesUtils.getResources().getDisplayMetrics());
    }

    public static int getNavigationBarHeight() {
        int navigationBarHeight = 0;
        int id = ResourcesUtils.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0 && checkDeviceHasNavigationBar()) {
            navigationBarHeight = ResourcesUtils.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    private static boolean checkDeviceHasNavigationBar() {
        boolean hasNavigationBar = false;
        int id = ResourcesUtils.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = ResourcesUtils.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasNavigationBar;

    }
}
