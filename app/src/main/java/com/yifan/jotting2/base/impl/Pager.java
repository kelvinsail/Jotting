package com.yifan.jotting2.base.impl;

/**
 * 界面上下文环境统一接口
 *
 * Created by yifan on 2016/7/15.
 */
public interface Pager {

    /**
     * 初始化控件
     */
    void initView();

    /**
     * 设置监听器
     */
    void setListener();

    /**
     * 是否打印生命周期
     *
     * @return
     */
    boolean isPrintLifeCycle();

    /**
     * 是否处于活动状态
     *
     * @return
     */
    boolean isActived();

    /**
     * 获取界面标记标签字符串，用于日志输出或友盟统计，一般为类名，但不可用class.getSimpleName()
     *
     * @return
     */
    String getTAG();
}
