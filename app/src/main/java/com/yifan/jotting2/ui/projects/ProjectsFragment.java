package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseFragment;
import com.yifan.jotting2.utils.ResourcesUtils;

/**
 * 所有项目列表
 * <p/>
 * Created by yifan on 2016/7/16.
 */
public class ProjectsFragment extends BaseFragment {

    public static final String TAG = "ProjectsFragment";

    /**
     * 列表页控件
     */
    RecyclerView mListView;

    /**
     * 列表数据适配器
     */
    ProjectsAdapter mProjectsAdapter;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    public ProjectsFragment() {
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
            mProjectsAdapter = new ProjectsAdapter();
            mListView.setAdapter(mProjectsAdapter);
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
}
