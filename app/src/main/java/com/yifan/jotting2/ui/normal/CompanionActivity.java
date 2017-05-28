package com.yifan.jotting2.ui.normal;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedDrawable;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Companion;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.ui.normal.dialog.AddCompanionDailog;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.model.CompanionModel;
import com.yifan.jotting2.utils.EventBusManager;
import com.yifan.jotting2.ui.widget.AddFloatingButton;
import com.yifan.utils.base.TitleBarActivity;
import com.yifan.utils.base.widget.BaseRecyclerAdapter;
import com.yifan.utils.base.widget.BaseRecyclerHolder;
import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.utils.utils.WidgetUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 同伴列表界面
 *
 * Created by yifan on 2016/11/7.
 */
public class CompanionActivity extends TitleBarActivity implements
        View.OnClickListener, BaseRecyclerAdapter.OnItemClickListener {

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

    /**
     * 项目数据
     */
    private Project mProject;

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusManager.register(this);
        //初始化数据数组
        mProject = getIntent().getParcelableExtra(Constans.BUNDLE_KEY_PROJECT);
        mData = CompanionModel.getInstance().getAllCompanionForProject(mProject.getId());
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
        mListView.setPadding(mListView.getPaddingLeft(),
                mListView.getPaddingTop(),
                mListView.getPaddingRight(),
                WidgetUtils.getNavigationBarHeight());
        mListView.setClipToPadding(false);
        //设置列表页控件
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CompanionAdapter();
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        mAddCompanionBtn.setOnClickListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusManager.unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_new_companion:
                AddCompanionDailog.newInstance(mProject).show(getSupportFragmentManager(), AddCompanionDailog.TAG);
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(DataEvent event) {
        if (null != event) {
            switch (event.action) {
                case DataEvent.ALERT_ACTION_INSERT:
                    if (null != event.data && event.data instanceof Companion) {
                        mData.add((Companion) event.data);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    break;
                case DataEvent.ALERT_ACTION_ALERT:
                    if (null != event.data && event.data instanceof Companion) {
                        for (int i = 0; i < mData.size(); i++) {
                            Companion companion = mData.get(i);
                            if (companion.getId() == ((Companion) event.data).getId()) {
                                mData.set(i, (Companion) event.data);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAdapter.notifyDataSetChanged();
                                    }
                                });
                                break;
                            }
                        }
                    }
                    break;
                case DataEvent.ALERT_ACTION_DELETE:
                    if (null != event.data && event.data instanceof Companion) {
                        for (int i = 0; i < mData.size(); i++) {
                            Companion companion = mData.get(i);
                            if (companion.getId() == ((Companion) event.data).getId()) {
                                mData.remove(i);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAdapter.notifyDataSetChanged();
                                    }
                                });
                                break;
                            }
                        }
                    }
                    break;
            }

        }
    }

    @Override
    public void onItemClick(View view, int itemType, int position) {

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
            viewHolder.setData(mData.get(realPosition));
        }

        @Override
        public int getRealItemCount() {
            return mData.size();
        }

        @Override
        public CompanionHolder getFakeHolder(View view) {
            return null;
        }

        /**
         * 列表item复用holder类
         */
        public class CompanionHolder extends BaseRecyclerHolder {

            /**
             * 成员头像
             */
            ImageView userIcon;

            /**
             * 成员姓名
             */
            TextView userName;

            public CompanionHolder(View itemView) {
                super(itemView);
                userIcon = (ImageView) itemView.findViewById(R.id.iv_item_companion_icon);
                userName = (TextView) itemView.findViewById(R.id.tv_item_companion_name);
            }

            /**
             * 设置数据
             *
             * @param companion
             */
            public void setData(Companion companion) {
                RoundedDrawable drawable = (RoundedDrawable) RoundedDrawable.fromDrawable(ResourcesUtils.getDrawable(R.drawable.icon_companions));
                drawable.setOval(true);
                drawable.setBorderColor(ResourcesUtils.getColor(R.color.background_main));
                drawable.setBorderWidth(ResourcesUtils.getDimensionPixelSize(R.dimen.border_width));
                RoundedDrawable background = (RoundedDrawable) RoundedDrawable.fromDrawable(new ColorDrawable(ResourcesUtils.getColor(R.color.background_gray)));
                background.setOval(true);
                userIcon.setImageDrawable(drawable);
                userIcon.setBackgroundDrawable(background);

                userName.setText(companion.getName());
            }

        }
    }
}
