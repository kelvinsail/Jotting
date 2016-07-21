package com.yifan.jotting2.utils;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;

import com.yifan.jotting2.JottingApplication;

/**
 * 资源获取统一入口
 * <p/>
 * Created by yifan on 2016/7/16.
 */
public class ResourcesUtils {

    public static Resources getResources() {
        return JottingApplication.getInstance().getResources();
    }

    public static String getString(@StringRes int resID) {
        return JottingApplication.getInstance().getResources().getString(resID);
    }

//    public static String getString(@StringRes int resID, Object... formatArgs) {
//        return JottingApplication.getInstance().getResources().getString(resID, formatArgs);
//    }

    /**
     * 获取定义的字符串
     *
     * @param id
     * @return
     */
    public static String getString(@StringRes int id, Object... formatArgs) {
        return JottingApplication.getInstance().getResources().getString(id, formatArgs);
    }



    public static String[] getStringArray(@ArrayRes int id) {
        return JottingApplication.getInstance().getResources().getStringArray(id);
    }

    public static int[] getIntArray(@ArrayRes int id) {
        return JottingApplication.getInstance().getResources().getIntArray(id);
    }


    public static float getDimension(@DimenRes int id) {
        return JottingApplication.getInstance().getResources().getDimension(id);
    }

    public static int getDimensionPixelOffset(@DimenRes int id) {
        return JottingApplication.getInstance().getResources().getDimensionPixelOffset(id);
    }

    public static int getDimensionPixelSize(@DimenRes int id) {
        return JottingApplication.getInstance().getResources().getDimensionPixelSize(id);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static Drawable getDrawable(@DrawableRes int id) {
        return JottingApplication.getInstance().getResources().getDrawable(id);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(@DrawableRes int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return JottingApplication.getInstance().getResources().getDrawable(id, theme);
        }
        return JottingApplication.getInstance().getResources().getDrawable(id);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static int getColor(@ColorRes int id) {
        return JottingApplication.getInstance().getResources().getColor(id);
    }

    public static int getColor(@ColorRes int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return JottingApplication.getInstance().getResources().getColor(id, theme);
        }
        return JottingApplication.getInstance().getResources().getColor(id);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static ColorStateList getColorStateList(@ColorRes int id) {
        return JottingApplication.getInstance().getResources().getColorStateList(id);
    }

    public static ColorStateList getColorStateList(@ColorRes int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return JottingApplication.getInstance().getResources().getColorStateList(id, theme);
        }
        return JottingApplication.getInstance().getResources().getColorStateList(id);
    }

    public static boolean getBoolean(@BoolRes int id) throws Resources.NotFoundException {
        return JottingApplication.getInstance().getResources().getBoolean(id);
    }

    public static int getInteger(@IntegerRes int id) throws Resources.NotFoundException {
        return JottingApplication.getInstance().getResources().getInteger(id);
    }

}
