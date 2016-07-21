package com.yifan.jotting2;

import android.app.Application;

import com.yifan.jotting2.utils.database.DataBaseManager;

/**
 * Application实例
 * <p/>
 * Created by yifan on 2016/7/16.
 */
public class JottingApplication extends Application {

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
