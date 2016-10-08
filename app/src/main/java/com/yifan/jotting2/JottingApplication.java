package com.yifan.jotting2;

import android.app.Application;

import com.thinksky.utils.base.BaseApplication;
import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.utils.database.DataBaseManager;

import java.lang.ref.WeakReference;

/**
 * Application实例
 *
 * Created by yifan on 2016/7/16.
 */
public class JottingApplication extends BaseApplication {

    /**
     * 全局单例模式
     */
    public static JottingApplication mSingleton;

    /**
     * 是否为开发调试状态
     */
    public static final boolean DEBUG = true;


    public static JottingApplication getInstance() {
        return mSingleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleton = this;
        //初始化数据库
        DataBaseManager.getInstance().init();
    }
}
