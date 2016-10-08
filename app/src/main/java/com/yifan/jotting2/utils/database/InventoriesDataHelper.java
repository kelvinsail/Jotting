package com.yifan.jotting2.utils.database;

import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.utils.database.gen.InventoryDao;

import java.util.List;

/**
 * 清单数据管理器
 *
 * Created by yifan on 2016/9/21.
 */
public class InventoriesDataHelper extends DataHelper<Inventory> {


    private static class InventoriesDataHelp {

        public static InventoriesDataHelper mInstance = new InventoriesDataHelper();

    }

    public static InventoriesDataHelper getInstance() {
        return InventoriesDataHelp.mInstance;
    }


    private InventoriesDataHelper() {
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
     */
    public void insert(String name, String description, long date,
                       double money, int count) {
        Inventory inventory = new Inventory(null, name, description, date, money, count);
        insert(inventory);
    }

    @Override
    public void insert(Inventory inventory) {
        getDao().insert(inventory);
        //通知所有观察者
        notifyDataChanged(inventory);
    }

    @Override
    public void insert(Inventory... inventories) {
        for (Inventory inventory : inventories) {
            getDao().insert(inventory);
        }
        notifyDataChanged(inventories);
    }

    @Override
    public void delete(Inventory inventory) {

    }

    @Override
    public void alert(Inventory inventory) {

    }

    @Override
    public List<Inventory> query(int count, String... value) {
        return getDao().queryBuilder().where(InventoryDao.Properties.Id.notEq(count))
                .orderAsc(InventoryDao.Properties.Id)
                .build().list();
    }

    @Override
    public InventoryDao getDao() {
        return DataBaseManager.getInstance().getInventoryDao();
    }

}
