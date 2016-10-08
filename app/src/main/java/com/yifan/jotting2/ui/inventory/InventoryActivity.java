package com.yifan.jotting2.ui.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.thinksky.utils.base.TitleBarActivity;
import com.thinksky.utils.base.widget.BaseRecyclerAdapter;
import com.thinksky.utils.base.widget.BaseRecyclerHolder;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.database.InventoriesDataHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 清单列表界面
 *
 * Created by yifan on 2016/9/21.
 */
public class InventoryActivity extends TitleBarActivity {

    public static final String TAG = "InventoryActivity";
    public static final String BUNDLE_KEY_PROJECT = "project";

    /**
     * 列表控件
     */
    private RecyclerView mInventoriesListView;

    /**
     * 数据适配器
     */
    private IncentoriesAdapter mAdapter;

    /**
     * 清单条目数据
     */
    private List<Inventory> mData;

    /**
     * 所属项目数据
     */
    private Project mProject;

    @Override
    public String getTAG() {
        return TAG;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Inventory inventory = new Inventory();
        inventory.setName(DateFormat.format("yyyy-MM-dd HH:mm", System.currentTimeMillis()).toString());
        inventory.setDate(System.currentTimeMillis());
        InventoriesDataHelper.getInstance().insert(inventory);
        mData = InventoriesDataHelper.getInstance().query(100);
        mProject = getIntent().getParcelableExtra(BUNDLE_KEY_PROJECT);
        mInventoriesListView = new RecyclerView(this);
        setContentView(mInventoriesListView, 0, false);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle(mProject.getProjectName());
        mAdapter = new IncentoriesAdapter();
        mInventoriesListView.setBackgroundResource(R.color.background_main);
        mInventoriesListView.setLayoutManager(new LinearLayoutManager(this));
        mInventoriesListView.setAdapter(mAdapter);
    }

    /**
     * 清单列表数据适配器
     */
    private class IncentoriesAdapter extends BaseRecyclerAdapter<IncentoriesAdapter.BaseHolder> {

        /**
         * 布局加载器
         */
        LayoutInflater mLayoutInflater;

        public IncentoriesAdapter() {
            this.mLayoutInflater = LayoutInflater.from(InventoryActivity.this);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public BaseHolder onCreate(ViewGroup parent, int viewType) {
            return new BaseHolder(mLayoutInflater.inflate(R.layout.item_inventory, parent, false));
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
            return new BaseHolder(view);
        }

        @Override
        public void onBindViewHolder(BaseHolder holder, int position) {
            super.onBindViewHolder(holder, position);
        }

        /**
         * ViewHolder基类
         */
        class BaseHolder extends BaseRecyclerHolder {

            /**
             * 文本以及选中状态展示控件
             */
            CheckBox mCheckView;

            public BaseHolder(View itemView) {
                super(itemView);
                mCheckView = (CheckBox) itemView.findViewById(R.id.cb_inventory);
            }

            /**
             * 设置数据
             *
             * @param inventory
             */
            public void setData(Inventory inventory) {
                mCheckView.setText(inventory.getName());
            }
        }

    }
}
