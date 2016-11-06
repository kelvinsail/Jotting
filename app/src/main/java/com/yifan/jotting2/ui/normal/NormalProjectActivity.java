package com.yifan.jotting2.ui.normal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.thinksky.utils.base.TitleBarActivity;
import com.thinksky.utils.base.widget.BaseRecyclerAdapter;
import com.thinksky.utils.base.widget.BaseRecyclerHolder;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Action;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.widget.AddFloatingButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通项目列表
 *
 * Created by yifan on 2016/11/6.
 */
public class NormalProjectActivity extends TitleBarActivity implements
        BaseRecyclerAdapter.OnItemClickListener, View.OnClickListener {

    private static final String TAG = "NormalProjectActivity";

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
                break;
        }
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
