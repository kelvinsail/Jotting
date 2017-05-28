package com.yifan.jotting2.ui.inventory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.model.InventoriesModel;
import com.yifan.jotting2.presenter.InventoryPresenter;
import com.yifan.utils.base.widget.BaseRecyclerAdapter;
import com.yifan.utils.base.widget.BaseRecyclerHolder;
import com.yifan.utils.utils.ResourcesUtils;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 清单列表数据适配器
 *
 * Created by wuyifan on 2017/5/25.
 */

public class IncentoriesAdapter extends BaseRecyclerAdapter<IncentoriesAdapter.BaseHolder> implements CompoundButton.OnCheckedChangeListener {

    /**
     * 布局加载器
     */
    LayoutInflater mLayoutInflater;

    InventoryPresenter mPresenter;

    private List<Inventory> mData;

    boolean isCheckChangedListenerEnable = true;

    public IncentoriesAdapter(List<Inventory> list) {
        this.mData = list;
        this.mPresenter = new InventoryPresenter();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public BaseHolder onCreate(ViewGroup parent, int viewType) {
        if (null == mLayoutInflater) {
            this.mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        BaseHolder holder = new BaseHolder(mLayoutInflater.inflate(R.layout.item_inventory, parent, false)
                , new WeakReference<IncentoriesAdapter>(this));
        return holder;
    }

    @Override
    public void onBind(BaseHolder viewHolder, int realPosition) {
        viewHolder.setData(mData.get(realPosition));
    }

    @Override
    public int getRealItemCount() {
        return mData.size();
    }

    @Override
    public BaseHolder getFakeHolder(View view) {
        return new BaseHolder(view, new WeakReference<IncentoriesAdapter>(this));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isCheckChangedListenerEnable) {
            return;
        }
        if (null != buttonView && null != buttonView.getParent()) {
            int position = BaseRecyclerHolder.getPositionFroView((View) buttonView.getParent());
            if (position >= 0) {
                Inventory inventory = mData.get(position);
                inventory.setChecked(isChecked);
//                InventoriesModel.getInstance().alert(inventory);
                mPresenter.alertInventory(inventory, false);
            }
        }
    }

    /**
     * 刷新
     */
    public void refresh() {
        isCheckChangedListenerEnable = false;
        notifyDataSetChanged();
        isCheckChangedListenerEnable = true;
    }

    /**
     * ViewHolder基类
     */
    static class BaseHolder extends BaseRecyclerHolder {

        /**
         * 文本以及选中状态展示控件
         */
        CheckBox mCheckView;

        /**
         * 颜色标签控件
         */
        View mLabelView;

        WeakReference<IncentoriesAdapter> mAdapter;

        public BaseHolder(View itemView, WeakReference<IncentoriesAdapter> adapter) {
            super(itemView);
            this.mAdapter = adapter;
            mCheckView = (CheckBox) itemView.findViewById(R.id.cb_inventory);
            mLabelView = itemView.findViewById(R.id.view_inventory_tag);
        }

        /**
         * 设置数据
         *
         * @param inventory
         */
        public void setData(Inventory inventory) {
            mCheckView.setText(inventory.getName());
            mCheckView.setOnCheckedChangeListener(null);
            mCheckView.setChecked(inventory.isChecked());
            mCheckView.setOnCheckedChangeListener(mAdapter.get());
            if (inventory.getLabelColor() == 0) {
                //mLabelColors[Integer.valueOf((int) (Math.random()*mLabelColors.length))]
                inventory.setLabelColor(ResourcesUtils.getColor(R.color.label_cyan));
//                InventoriesModel.getInstance().alert(inventory);
                mAdapter.get().mPresenter.alertInventory(inventory, false);
            }
            mLabelView.setBackgroundColor(inventory.getLabelColor());
        }
    }
}