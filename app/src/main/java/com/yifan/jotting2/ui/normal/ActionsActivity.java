package com.yifan.jotting2.ui.normal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.utils.base.TitleBarActivity;
import com.yifan.utils.base.widget.BaseRecyclerAdapter;
import com.yifan.utils.base.widget.BaseRecyclerHolder;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Action;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.widget.AddFloatingButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通项目活动列表界面
 *
 * Created by yifan on 2016/11/6.
 */
public class ActionsActivity extends TitleBarActivity implements
        BaseRecyclerAdapter.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "ActionsActivity";

    /**
     * Activity请求码
     */
    private static final int REQUEST_CODE_COMPANIONS = 0x001;

    /**
     * 添加新活动按钮
     */
    private AddFloatingButton mNewActionButton;

    /**
     * 列表控件
     */
    private RecyclerView mListView;

    /**
     * 项目数据
     */
    private Project mProject;

    /**
     * 数据适配器
     */
    private NormalActionAdapter mAdapter;

    /**
     * 列表数据
     */
    private List<Action> mData;

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据
        mData = new ArrayList<>();
        if (null != getIntent()) {
            mProject = getIntent().getParcelableExtra(Constans.BUNDLE_KEY_PROJECT);
        }
        setContentView(R.layout.activity_normal_project, 0, false);
    }

    @Override
    public void initView() {
        super.initView();
        //设置标题名
        setTitle(mProject.getProjectName());
        //初始化控件
        mNewActionButton = (AddFloatingButton) findViewById(R.id.fab_add_new_action);
        mListView = (RecyclerView) findViewById(R.id.rv_normal_project);
        //设置控件
        mAdapter = new NormalActionAdapter();
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        mNewActionButton.setOnClickListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int itemType, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_new_action:
                Intent intent = new Intent();
                intent.setClass(this, EditActionActivity.class);
                intent.putExtra(Constans.BUNDLE_KEY_PROJECT, mProject);
//                intent.putExtra(Constans.BUNDLE_KEY_ACTION, null);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_companions:
                Intent intent = new Intent(this, CompanionActivity.class);
                intent.putExtra(Constans.BUNDLE_KEY_PROJECT, mProject);
                startActivityForResult(intent, REQUEST_CODE_COMPANIONS);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 活动数据适配器
     */
    private class NormalActionAdapter extends BaseRecyclerAdapter<NormalActionAdapter.BaseActionHolder> {

        @Override
        public BaseActionHolder onCreate(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBind(BaseActionHolder viewHolder, int realPosition) {

        }

        @Override
        public int getRealItemCount() {
            return mData.size();
        }

        @Override
        public BaseActionHolder getFakeHolder(View view) {
            return null;
        }

        public class BaseActionHolder extends BaseRecyclerHolder {
            public BaseActionHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
