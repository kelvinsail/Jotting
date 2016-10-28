package com.yifan.jotting2.pojo;

/**
 * 数据库数据修改数据传递类
 *
 * Created by yifan on 2016/10/27.
 */
public class DataEvent {

    /**
     * 删除
     */
    public static final int ALERT_ACTION_DELETE = 0x101;

    /**
     * 修改
     */
    public static final int ALERT_ACTION_ALERT = 0x102;

    /**
     * 插入
     */
    public static final int ALERT_ACTION_INSERT = 0x103;

    /**
     * 操作标示
     */
    public int action;

    /**
     * 修改后的数据
     */
    public Object data;

    public DataEvent(int action, Object data) {
        this.action = action;
        this.data = data;
    }
}
