package com.yifan.jotting2.ui.projects;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thinksky.utils.base.BaseActivity;
import com.thinksky.utils.base.BaseFragment;
import com.thinksky.utils.base.widget.BaseRecyclerAdapter;
import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.ui.inventory.InventoryActivity;
import com.yifan.jotting2.utils.database.ProjectsDataHelp;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 所有项目列表
 *
 * Created by yifan on 2016/7/16.
 */
public class ProjectsFragment extends BaseFragment implements Observer, BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

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

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    public ProjectsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取列表数据
        mList = ProjectsDataHelp.getInstance().query(999);
        ProjectsDataHelp.getInstance().regesiterDataObserver(this);
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
            mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mProjectsAdapter = new ProjectsAdapter(mList);
            mListView.setAdapter(mProjectsAdapter);
            mProjectsAdapter.setOnItemClickListener(this);
            mProjectsAdapter.setOnOnItemLongClickListener(this);
        }
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
        ProjectsDataHelp.getInstance().unregesiterDataObserver(this);
        super.onDestroy();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (null != o) {
            if (o instanceof Project) {
                if (null != mList) {
                    mList.add((Project) o);
                    mProjectsAdapter.notifyDataSetChanged();
                }
            } else if (o instanceof List && ((List) o).size() > 0 && ((List) o).get(0) instanceof Project) {
                if (null != mList) {
                    mList.addAll((List) o);
                    mProjectsAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int itemType, int position) {
        Project project = mList.get(position);
        Intent intent = new Intent(this.getActivity(), InventoryActivity.class);
        intent.putExtra(InventoryActivity.BUNDLE_KEY_PROJECT, project);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v, int itemType, int position) {
        if (null != v.getContext() && v.getContext() instanceof BaseActivity) {
            ProjectDetailsDialog dialog = ProjectDetailsDialog.newInstance(mList.get(position));
            dialog.show(((BaseActivity) v.getContext()).getSupportFragmentManager(), ProjectDetailsDialog.TAG);
            return true;
        }
        return false;
    }
}
