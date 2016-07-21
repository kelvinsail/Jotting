package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseFragment;
import com.yifan.jotting2.pojo.Projects;
import com.yifan.jotting2.utils.ResourcesUtils;
import com.yifan.jotting2.utils.database.DataBaseManager;
import com.yifan.jotting2.utils.database.ProjectsDataHelp;
import com.yifan.jotting2.utils.database.gen.ProjectsDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 所有项目列表
 * <p/>
 * Created by yifan on 2016/7/16.
 */
public class ProjectsFragment extends BaseFragment implements Observer {

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
    List<Projects> mList;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    public ProjectsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取列表数据
        mList = ProjectsDataHelp.getInstance().getProject(999);
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
            if (o instanceof Projects) {
                if (null != mList) {
                    mList.add((Projects) o);
                    mProjectsAdapter.notifyDataSetChanged();
                }
            } else if (o instanceof List && ((List) o).size() > 0 && ((List) o).get(0) instanceof Projects) {
                if (null != mList) {
                    mList.addAll((List) o);
                    mProjectsAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
