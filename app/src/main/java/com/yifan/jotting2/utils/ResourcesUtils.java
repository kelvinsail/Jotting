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

    public static String getString(@StringRes int resID) {
        return JottingApplication.getInstance().getResources().getString(resID);
    }

    public static String getString(@StringRes int resID, Object... formatArgs) {
        return JottingApplication.getInstance().getResources().getString(resID, formatArgs);
    }


    public String[] getStringArray(@ArrayRes int id) {
        return JottingApplication.getInstance().getResources().getStringArray(id);
    }

    public int[] getIntArray(@ArrayRes int id) {
        return JottingApplication.getInstance().getResources().getIntArray(id);
    }


    public float getDimension(@DimenRes int id) {
        return JottingApplication.getInstance().getResources().getDimension(id);
    }

    public int getDimensionPixelOffset(@DimenRes int id) {
        return JottingApplication.getInstance().getResources().getDimensionPixelOffset(id);
    }

    public int getDimensionPixelSize(@DimenRes int id) {
        return JottingApplication.getInstance().getResources().getDimensionPixelSize(id);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public Drawable getDrawable(@DrawableRes int id) {
        return JottingApplication.getInstance().getResources().getDrawable(id);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Drawable getDrawable(@DrawableRes int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return JottingApplication.getInstance().getResources().getDrawable(id, theme);
        }
        return JottingApplication.getInstance().getResources().getDrawable(id);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public int getColor(@ColorRes int id) {
        return JottingApplication.getInstance().getResources().getColor(id);
    }

    public int getColor(@ColorRes int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return JottingApplication.getInstance().getResources().getColor(id, theme);
        }
        return JottingApplication.getInstance().getResources().getColor(id);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public ColorStateList getColorStateList(@ColorRes int id) {
        return JottingApplication.getInstance().getResources().getColorStateList(id);
    }

    public ColorStateList getColorStateList(@ColorRes int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return JottingApplication.getInstance().getResources().getColorStateList(id, theme);
        }
        return JottingApplication.getInstance().getResources().getColorStateList(id);
    }

    public boolean getBoolean(@BoolRes int id) throws Resources.NotFoundException {
        return JottingApplication.getInstance().getResources().getBoolean(id);
    }

    public int getInteger(@IntegerRes int id) throws Resources.NotFoundException {
        return JottingApplication.getInstance().getResources().getInteger(id);
    }

}
