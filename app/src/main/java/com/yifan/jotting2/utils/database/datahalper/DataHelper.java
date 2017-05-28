package com.yifan.jotting2.utils.database.datahalper;

import android.os.Parcelable;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

/**
 * 数据操作接口
 *
 * Created by yifan on 2016/9/22.
 */
public abstract class DataHelper<VH extends Parcelable>{

    /**
     * 插入数据
     *
     * @param object 需要插入的数据
     */
    public abstract long insert(VH object);

    /**
     * 插入多个数据
     *
     * @param objects 需要插入的数据
     */
    public abstract long[] insert(VH... objects);

    /**
     * 删除数据
     *
     * @param object 需要删除的数据
     */
    public abstract void delete(VH object);

    /**
     * 修改某条数据
     *
     * @param object 需要修改的数据
     */
    public abstract void alert(VH object);

    /**
     * 查询数据
     *
     * @param count 数量
     * @param values 查询参数
     * @return
     */
    public abstract List<VH> query(int count, String... values);

    /**
     * 获取DAO处理对象
     * @return
     */
    public abstract AbstractDao getDao();

}
