package com.yifan.jotting2.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * 事件总线管理类
 *
 * Created by wuyifan on 2017/5/27.
 */

public class EventBusManager {

    /**
     * 注册订阅者
     *
     * @param subcriber
     */
    public static void register(Object subcriber) {
        EventBus.getDefault().register(subcriber);
    }

    /**
     * 注销订阅者
     *
     * @param subcriber
     */
    public static void unregister(Object subcriber) {
        EventBus.getDefault().unregister(subcriber);
    }

    /**
     * 发送消息
     *
     * @param object
     */
    public static void post(Object object) {
        EventBus.getDefault().post(object);
    }
}
