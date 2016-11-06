package com.yifan.jotting2.utils.database.datahalper.impl;

import java.util.Observable;
import java.util.Observer;

/**
 * 数据观察者接口
 *
 * Created by yifan on 2016/7/20.
 */
public interface DataHelpObservable {

    /**
     * 注册新的观察者
     *
     * @param o
     */
    void regesiterDataObserver(Observer o);

    /**
     * 移除所有观察者
     */
    void unregesiterDataObservers();

    /**
     * 移除某个观察者
     *
     * @param o
     */
    void unregesiterDataObserver(Observer o);

    /**
     * 通知数据变更
     */
    void notifyDataChanged();

    /**
     * 通知数据变更
     *
     * @param arg 变更的数据
     */
    void notifyDataChanged(Object arg);
}
