package com.yifan.jotting2.ui.normal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Companion;
import com.yifan.jotting2.widget.AddFloatingButton;
import com.yifan.utils.base.TitleBarActivity;
import com.yifan.utils.base.widget.BaseRecyclerAdapter;
import com.yifan.utils.base.widget.BaseRecyclerHolder;
import com.yifan.utils.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 同伴列表界面
 *
 * Created by yifan on 2016/11/7.
 */
public class CompanionActivity extends TitleBarActivity implements View.OnClickListener {

    private static final String TAG = "CompanionActivity";

    /**
     * 列表页控件
     */
    private RecyclerView mListView;

    /**
     * 添加成员点击按钮
     */
    private AddFloatingButton mAddCompanionBtn;

    /**
     * 列表数据适配器
     */
    private CompanionAdapter mAdapter;

    /**
     * 列表数据
     */
    private List<Companion> mData;

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据数组
        mData = new ArrayList<>();
        //设置界面
        setContentView(R.layout.activity_companion, 0, false);
    }

    @Override
    public void initView() {
        super.initView();
        //设置标题
        setTitle(R.string.my_companions);
        //初始化控件
        mListView = (RecyclerView) findViewById(R.id.rv_companion);
        mAddCompanionBtn = (AddFloatingButton) findViewById(R.id.fab_add_new_companion);
        //适配NovagationBar
        mListView.setPadding(0, 0, 0, WidgetUtils.getNavigationBarHeight());
        mListView.setClipToPadding(false);
        //设置列表页控件
        mListView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new CompanionAdapter();
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 成员列表数据适配器
     */
    private class CompanionAdapter extends BaseRecyclerAdapter<CompanionAdapter.CompanionHolder> {

        /**
         * 布局加载器
         */
        private LayoutInflater mLayoutInflater;

        public CompanionAdapter() {
            this.mLayoutInflater = LayoutInflater.from(CompanionActivity.this);
        }

        @Override
        public CompanionHolder onCreate(ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.item_companion, parent, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new CompanionHolder(view);
        }

        @Override
        public void onBind(CompanionHolder viewHolder, int realPosition) {

        }

        @Override
        public int getRealItemCount() {
            return 10;
        }

        @Override
        public CompanionHolder getFakeHolder(View view) {
            return null;
        }

        public class CompanionHolder extends BaseRecyclerHolder {
            public CompanionHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
