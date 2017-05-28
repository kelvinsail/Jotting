package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.base.impl.IView;
import com.yifan.jotting2.presenter.ProjectPresenter;
import com.yifan.jotting2.utils.EventBusManager;
import com.yifan.jotting2.utils.IntentUtils;
import com.yifan.utils.base.BaseActivity;
import com.yifan.utils.base.BaseFragment;
import com.yifan.utils.base.widget.BaseRecyclerAdapter;
import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.utils.utils.WidgetUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.pojo.Project;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有项目列表
 *
 * Created by yifan on 2016/7/16.
 */
public class ProjectsFragment extends BaseFragment implements
        BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

    public static final String TAG = "ProjectsFragment";

    /**
     * 列表页控件
     */
    RecyclerView mListView;

    /**
     * 列表数据适配器
     */
    ProjectsAdapter mProjectsAdapter;

    /**
     * 列表页数据
     */
    List<Project> mList;

    /**
     * ProjectView接口
     */
    IView<List<Project>> mProjectView;

    /**
     * Project Presenter
     */
    ProjectPresenter mProjectPresenter;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    public ProjectsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusManager.register(this);
        mList = new ArrayList<>();
        mProjectView = new ProjectListView(new WeakReference<ProjectsFragment>(this));
        mProjectPresenter = new ProjectPresenter(mProjectView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusManager.unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_projects, container, false);
        return view;
    }

    @Override
    public void initView() {
        super.initView();
        if (null != getView()) {
            mListView = (RecyclerView) getView().findViewById(R.id.rv_projects);
            mListView.setPadding(mListView.getPaddingLeft(), mListView.getPaddingTop()
                    , mListView.getPaddingRight(), mListView.getPaddingBottom() + WidgetUtils.getNavigationBarHeight());
            mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mProjectsAdapter = new ProjectsAdapter(getActivity(), mList);
            mListView.setAdapter(mProjectsAdapter);
            mProjectsAdapter.setOnItemClickListener(this);
            mProjectsAdapter.setOnItemLongClickListener(this);
        }
        mProjectPresenter.loadProjectList(0, Integer.MAX_VALUE);
    }

    @Override
    public boolean isPrintLifeCycle() {
        return true;
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public String getTitleName() {
        return ResourcesUtils.getString(R.string.title_name_project);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(DataEvent event) {
        if (null != event && null != event.data) {
            switch (event.action) {
                case DataEvent.ALERT_ACTION_INSERT:
                    if (event.data instanceof Project) {
                        mList.add((Project) event.data);
                    } else if (event.data instanceof List && ((List) event.data).size() > 0
                            && ((List) event.data).get(0) instanceof Project) {
                        mList.addAll(((List) event.data));
                    }
                    //在UI线程执行刷新代码
                    mProjectsAdapter.notifyDataSetChanged();
                    break;
                default:
                    if (event.data instanceof Project) {
                        for (int i = 0; i < mList.size(); i++) {
                            Project project = mList.get(i);
                            if (project.getId() == ((Project) event.data).getId()) {
                                if (event.action == DataEvent.ALERT_ACTION_ALERT) {
                                    mList.remove(i);
                                    mList.add(i, project);
                                } else if (event.action == DataEvent.ALERT_ACTION_DELETE) {
                                    mList.remove(i);
                                }
                                //在UI线程执行刷新代码
                                mProjectsAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                    break;
            }
        }

    }

    @Override
    public void onItemClick(View view, int itemType, int position) {
        Project project = mList.get(position);
        IntentUtils.openProject(this.getActivity(), project);
    }

    @Override
    public boolean onItemLongClick(View view, int itemType, int position) {
        if (null != view.getContext() && view.getContext() instanceof BaseActivity) {
            ProjectDetailsDialog dialog = ProjectDetailsDialog.newInstance(mList.get(position));
            dialog.show(((BaseActivity) view.getContext()).getSupportFragmentManager(), ProjectDetailsDialog.TAG);
            return true;
        }
        return false;
    }

    /**
     * Projects列表界面View接口
     */
    private static class ProjectListView implements IView<List<Project>> {

        private WeakReference<ProjectsFragment> mFragment;

        /**
         * 是否为刷新界面
         */
        private boolean isRefresh;

        public ProjectListView(WeakReference<ProjectsFragment> fragment) {
            this.mFragment = fragment;
            this.isRefresh = true;
        }

        @Override
        public void onSuccess(List<Project> object) {
            if (null != mFragment && null != mFragment.get()) {
                if (null != object && object.size() > 0) {//有数据
                    //
                    if (isRefresh && null != mFragment.get().mList) {
                        mFragment.get().mList.clear();
                    } else if (null == mFragment.get().mList) {
                        mFragment.get().mList = new ArrayList<>();
                    }
                    mFragment.get().mList.addAll(object);
                    if (null == mFragment.get().mProjectsAdapter) {
                        mFragment.get().mProjectsAdapter = new ProjectsAdapter(
                                mFragment.get().getActivity(), mFragment.get().mList);
                        mFragment.get().mListView.setAdapter(mFragment.get().mProjectsAdapter);
                    } else {
                        mFragment.get().mProjectsAdapter.notifyDataSetChanged();
                    }
                } else {//无数据

                }
            }
        }

        @Override
        public void onError(Throwable error) {

        }

        /**
         * 是否刷新
         *
         * @param refresh
         */
        public void setRefresh(boolean refresh) {
            isRefresh = refresh;
        }

        /**
         * 是否刷新
         *
         * @return
         */
        public boolean isRefresh() {
            return isRefresh;
        }
    }
}
