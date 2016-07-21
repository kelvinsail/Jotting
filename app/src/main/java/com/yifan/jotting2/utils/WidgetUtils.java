package com.yifan.jotting2.utils;

import android.util.TypedValue;

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

}
