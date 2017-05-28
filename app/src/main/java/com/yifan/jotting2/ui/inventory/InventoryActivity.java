package com.yifan.jotting2.ui.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.yifan.jotting2.utils.EventBusManager;
import com.yifan.utils.base.TitleBarActivity;
import com.yifan.utils.base.widget.BaseRecyclerAdapter;
import com.yifan.utils.utils.WidgetUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.model.InventoriesModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 清单列表界面
 *
 * Created by yifan on 2016/9/21.
 */
public class InventoryActivity extends TitleBarActivity implements BaseRecyclerAdapter.OnItemClickListener,
        BaseRecyclerAdapter.OnItemLongClickListener {

    public static final String TAG = "InventoryActivity";

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
        EventBusManager.register(this);
        mProject = getIntent().getParcelableExtra(Constans.BUNDLE_KEY_PROJECT);
        mData = InventoriesModel.getInstance().query(100, String.valueOf(null != mProject ? mProject.getId() : 0));
        mInventoriesListView = new RecyclerView(this);
        setContentView(mInventoriesListView, 0, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusManager.unregister(this);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle(mProject.getProjectName());
        mAdapter = new IncentoriesAdapter(mData);
        mInventoriesListView.setBackgroundResource(R.color.background_main);
        mInventoriesListView.setLayoutManager(new LinearLayoutManager(this));
        mInventoriesListView.setAdapter(mAdapter);
        mInventoriesListView.setPadding(0, 0, 0, WidgetUtils.getNavigationBarHeight());
        mInventoriesListView.setClipToPadding(false);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
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
            AlertInventoryDialog.newInstance(mProject, null).show(getSupportFragmentManager(), AlertInventoryDialog.TAG);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(DataEvent event) {
        if (null != event && null != event.data) {
            switch (event.action) {
                case DataEvent.ALERT_ACTION_INSERT:
                    if (event.data instanceof Inventory) {
                        mData.add((Inventory) event.data);
                    } else if (event.data instanceof List && ((List) event.data).size() > 0
                            && ((List) event.data).get(0) instanceof Inventory) {
                        mData.addAll(((List) event.data));
                    }
                    //在UI线程执行刷新代码
                    mAdapter.refresh();
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
                            }
                        }
                        //在UI线程执行刷新代码
                        mAdapter.refresh();
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onItemLongClick(View view, int itemType, int position) {
        if (position >= 0 && position < mData.size()) {
            Inventory inventory = mData.get(position);
            AlertInventoryDialog.newInstance(mProject, inventory).show(getSupportFragmentManager(), AlertInventoryDialog.TAG);
            return true;
        }
        return false;
    }


}
