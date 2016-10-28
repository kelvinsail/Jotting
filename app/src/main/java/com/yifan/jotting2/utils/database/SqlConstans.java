package com.yifan.jotting2.utils.database;

import com.yifan.jotting2.utils.database.gen.InventoryDao;

/**
 * Created by yifan on 2016/10/29.
 */
public class SqlConstans {


    /**
     * 数据库语句模板 - 添加列
     */
    private static final String UPDATE_INVENTORY_TABLE_TO_ADD = "alter table %s add column %s %s";

    /**
     * 字段数据类型 - Integer
     */
    public static final String COLUMN_VALUE_TYPE_INTEGER = "Integer";

    /**
     * 字段数据类型 - Long
     */
    public static final String COLUMN_VALUE_TYPE_LONG = "Long";

    /**
     * 升级清单项数据库，增加标签颜色字段
     */
    public static final String UPDATE_INVENTORY_TABLE_TO_ADD_LABELCOLOR =
            String.format(UPDATE_INVENTORY_TABLE_TO_ADD, InventoryDao.TABLENAME, InventoryDao.Properties.LabelColor.columnName, COLUMN_VALUE_TYPE_INTEGER);

    /**
     * 升级清单项数据库，增加所属项目ID字段
     */
    public static final String UPDATE_INVENTORY_TABLE_TO_ADD_PROJECTID =
            String.format(UPDATE_INVENTORY_TABLE_TO_ADD, InventoryDao.TABLENAME, InventoryDao.Properties.ProjectID.columnName, COLUMN_VALUE_TYPE_LONG);
}
