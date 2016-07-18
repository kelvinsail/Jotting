package com.yifan.jotting2;

import android.app.Application;

/**
 * Application实例
 *
 * Created by yifan on 2016/7/16.
 */
public class JottingApplication extends Application {

    public static JottingApplication mSingleton;

    public static JottingApplication getInstance() {
        return mSingleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleton = this;
    }
}
