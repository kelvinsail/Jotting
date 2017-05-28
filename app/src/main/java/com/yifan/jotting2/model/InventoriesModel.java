package com.yifan.jotting2.model;

import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.utils.database.DataBaseManager;
import com.yifan.jotting2.utils.database.datahalper.DataHelper;
import com.yifan.jotting2.utils.database.gen.InventoryDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 清单数据管理器
 *
 * Created by yifan on 2016/9/21.
 */
public class InventoriesModel extends DataHelper<Inventory> {

    private static class InventoriesDataHelp {

        public static InventoriesModel mInstance = new InventoriesModel();

    }

    public static InventoriesModel getInstance() {
        return InventoriesDataHelp.mInstance;
    }

    private InventoriesModel() {
        super();
    }

    /**
     * 插入新数据
     *
     * @param name
     * @param description
     * @param date
     * @param money
     * @param count
     * @param projectID
     */
    public long insert(String name, String description, long date,
                       double money, int count, long projectID) {
        Inventory inventory = new Inventory(null, name, description, date, money, count, false, 0, projectID);
        return insert(inventory);
    }

    @Override
    public long insert(Inventory inventory) {
        long id = 0;
        if (null != inventory) {
            id = getDao().insert(inventory);
            inventory.setId(id);
            //通知所有观察者
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, inventory));
        }
        return id;
    }

    @Override
    public long[] insert(Inventory... inventories) {
        long[] ids = null;
        if (null != inventories && inventories.length > 0) {
            ids = new long[inventories.length];
            for (int i = 0; i < inventories.length; i++) {
                Inventory inventory = inventories[i];
                ids[i] = getDao().insert(inventory);
                inventory.setId(ids[i]);
            }
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, inventories));
        }
        return ids;
    }


    @Override
    public void delete(Inventory inventory) {
        if (null != inventory) {
            getDao().delete(inventory);
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_DELETE, inventory));
        }
    }

    /**
     * 删除项目的所有清单数据
     *
     * @param projectID
     */
    public void deleteByProjectID(long projectID) {
        if (projectID >= 0) {
            List<Inventory> list = query(0, String.valueOf(projectID));
//            for (Inventory inventory : list) {
//                delete(inventory);
//            }
            getDao().deleteInTx(list);
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_DELETE, null));
        }
    }

    @Override
    public void alert(Inventory inventory) {
        if (null != inventory) {
            getDao().update(inventory);
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_ALERT, inventory));
        }
    }

    @Override
    public List<Inventory> query(int count, String... values) {
        QueryBuilder builder = getDao().queryBuilder();
        if (count > 0) {
            builder.where(InventoryDao.Properties.Id.notEq(count), InventoryDao.Properties.ProjectID.eq(values[0]));
        } else {
            builder.where(InventoryDao.Properties.ProjectID.eq(values[0]));
        }
        return builder.orderAsc(InventoryDao.Properties.Id).build().list();
    }

    @Override
    public InventoryDao getDao() {
        return DataBaseManager.getInstance().getInventoryDao();
    }

}
