package com.yifan.jotting2.presenter;

import android.text.TextUtils;

import com.yifan.jotting2.base.Presenter;
import com.yifan.jotting2.base.impl.IView;
import com.yifan.jotting2.model.InventoriesModel;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.utils.EventBusManager;

/**
 * Created by wuyifan on 2017/5/26.
 */

public class InventoryPresenter extends Presenter {

    private IView<Boolean> mIView;

    public InventoryPresenter() {
    }

    public InventoryPresenter(IView<Boolean> iView) {
        this.mIView = iView;
    }

    public void addInventory(String name, long projectID, String description, int labelColor, double money, int count) {
        if (isNameEmpty(name)) {
            return;
        }
        Inventory inventory = new Inventory();
        inventory.setName(name);
        inventory.setDate(System.currentTimeMillis());
        inventory.setDescription(description);
        inventory.setLabelColor(labelColor);
        inventory.setCount(count);
        inventory.setMoney(money);
        addInventory(inventory);
    }

    /**
     * 插入新的清单项
     *
     * @param inventory
     */
    public void addInventory(Inventory inventory) {
        if (isNameEmpty(inventory.getName())) {
            return;
        }
        long id = InventoriesModel.getInstance().insert(inventory);
        if (null != mIView) {
            mIView.onSuccess(id >= 0);
        }
        EventBusManager.post(new DataEvent(DataEvent.ALERT_ACTION_INSERT, inventory));

    }

    /**
     * 修改清单项
     *
     * @param inventory
     * @param isPostEvent
     */
    public void alertInventory(Inventory inventory, boolean isPostEvent) {
        if (isNameEmpty(inventory.getName())) {
            return;
        }
        InventoriesModel.getInstance().alert(inventory);
        if (null != mIView) {
            mIView.onSuccess(true);
        }
        if (isPostEvent) {
            EventBusManager.post(new DataEvent(DataEvent.ALERT_ACTION_ALERT, inventory));
        }
    }

    private boolean isNameEmpty(String name) {
        if (TextUtils.isEmpty(name)) {
            mIView.onError(new NullPointerException());
            return true;
        }
        return false;
    }

}
