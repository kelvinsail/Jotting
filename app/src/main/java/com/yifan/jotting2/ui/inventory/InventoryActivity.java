package com.yifan.jotting2.ui.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.thinksky.utils.base.TitleBarActivity;
import com.thinksky.utils.base.widget.BaseRecyclerAdapter;
import com.thinksky.utils.base.widget.BaseRecyclerHolder;
import com.thinksky.utils.utils.ResourcesUtils;
import com.thinksky.utils.utils.WidgetUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.database.InventoriesDataHelper;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 清单列表界面
 *
 * Created by yifan on 2016/9/21.
 */
public class InventoryActivity extends TitleBarActivity implements BaseRecyclerAdapter.OnItemClickListener,
        BaseRecyclerAdapter.OnItemLongClickListener, Observer {

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
        mData = InventoriesDataHelper.getInstance().query(100);
        mProject = getIntent().getParcelableExtra(BUNDLE_KEY_PROJECT);
        mInventoriesListView = new RecyclerView(this);
        setContentView(mInventoriesListView, 0, false);
        InventoriesDataHelper.getInstance().regesiterDataObserver(this);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle(mProject.getProjectName());
        mAdapter = new IncentoriesAdapter();
        mInventoriesListView.setBackgroundResource(R.color.background_main);
        mInventoriesListView.setLayoutManager(new LinearLayoutManager(this));
        mInventoriesListView.setAdapter(mAdapter);
        mInventoriesListView.setPadding(0, 0, 0, WidgetUtils.getNavigationBarHeight());
        mInventoriesListView.setClipToPadding(false);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_new_inventory) {
            AlertInventoryDialog.newInstance(null).show(getSupportFragmentManager(), AlertInventoryDialog.TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int itemType, int position) {
        if (position >= 0 && position < mData.size()) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.cb_inventory);
            checkBox.toggle();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (null != arg && arg instanceof DataEvent && null != ((DataEvent) arg).data) {
            DataEvent event = (DataEvent) arg;
            switch (event.action) {
                case DataEvent.ALERT_ACTION_INSERT:
                    if (event.data instanceof Inventory) {
                        mData.add((Inventory) event.data);
                    } else if (event.data instanceof List && ((List) event.data).size() > 0
                            && ((List) event.data).get(0) instanceof Inventory) {
                        mData.addAll(((List) event.data));
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                //                case DataEvent.ALERT_ACTION_ALERT:
                //                case DataEvent.ALERT_ACTION_DELETE:
                default:
                    if (event.data instanceof Inventory) {
                        for (int i = 0; i < mData.size(); i++) {
                            Inventory inventory = mData.get(i);
                            if (inventory.getId() == ((Inventory) event.data).getId()) {
                                if (event.action == DataEvent.ALERT_ACTION_ALERT) {
                                    mData.remove(i);
                                    mData.add(i, inventory);
                                } else if (event.action == DataEvent.ALERT_ACTION_DELETE) {
                                    mData.remove(i);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onLongClick(View v, int itemType, int position) {
        if (position >= 0 && position < mData.size()) {
            Inventory inventory = mData.get(position);
            AlertInventoryDialog.newInstance(inventory).show(getSupportFragmentManager(), AlertInventoryDialog.TAG);
            return true;
        }
        return false;
    }

    /**
     * 清单列表数据适配器
     */
    private class IncentoriesAdapter extends BaseRecyclerAdapter<IncentoriesAdapter.BaseHolder> implements CompoundButton.OnCheckedChangeListener {

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
            BaseHolder holder = new BaseHolder(mLayoutInflater.inflate(R.layout.item_inventory, parent, false));
            ((CheckBox) holder.itemView.findViewById(R.id.cb_inventory)).setOnCheckedChangeListener(this);
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
            return new BaseHolder(view);
        }

        @Override
        public void onBindViewHolder(BaseHolder holder, int position) {
            super.onBindViewHolder(holder, position);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (null != buttonView && null != buttonView.getParent()) {
                int position = BaseRecyclerHolder.getPositionFroView((View) buttonView.getParent());
                if (position >= 0) {
                    Inventory inventory = mData.get(position);
                    inventory.setChecked(isChecked);
                    InventoriesDataHelper.getInstance().alert(inventory);
                }
            }
        }

        /**
         * ViewHolder基类
         */
        class BaseHolder extends BaseRecyclerHolder {

            /**
             * 文本以及选中状态展示控件
             */
            CheckBox mCheckView;

            /**
             * 颜色标签控件
             */
            View mLabelView;

            public BaseHolder(View itemView) {
                super(itemView);
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
                mCheckView.setChecked(inventory.isChecked());
                if (inventory.getLabelColor() == 0) {
                    //mLabelColors[Integer.valueOf((int) (Math.random()*mLabelColors.length))]
                    inventory.setLabelColor(ResourcesUtils.getColor(R.color.label_cyan));
                    InventoriesDataHelper.getInstance().alert(inventory);
                }
                mLabelView.setBackgroundColor(inventory.getLabelColor());
            }
        }
    }
}
