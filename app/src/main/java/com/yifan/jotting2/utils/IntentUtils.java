package com.yifan.jotting2.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yifan.jotting2.MainActivity;

/**
 * Intent统一管理工具
 * <p/>
 * Created by yifan on 2016/7/16.
 */
public class IntentUtils {

    /**
     * 返回首页
     *
     * @param context
     * @return
     */
    public static Intent getMainPagerIntent(Context context) {
        return getMainPagerIntent(context, MainActivity.INTENT_MAIN_ACTION_PROJECTS);
    }

    /**
     * 离开App
     *
     * @param context
     * @return
     */
    public static Intent getExitIntent(Context context) {
        return getMainPagerIntent(context, MainActivity.INTENT_MAIN_ACTION_EXIT);
    }

    /**
     * 获取主界面Intent
     *
     * @param context
     * @param actionID
     * @return
     */
    public static Intent getMainPagerIntent(Context context, int actionID) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.INTENT_MAIN_ACTION, actionID);
        return intent;
    }
}
