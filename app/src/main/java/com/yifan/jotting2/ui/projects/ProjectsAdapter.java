package com.yifan.jotting2.ui.projects;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.widget.BaseRecyclerAdapter;

/**
 * 所有项目列表页数据适配器
 * <p/>
 * Created by yifan on 2016/7/18.
 */
public class ProjectsAdapter extends BaseRecyclerAdapter<ProjectsAdapter.ProjectViewHolder>
        implements BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

    private static final String TAG = "ProjectsAdapter";

    public ProjectsAdapter() {
        setOnItemClickListener(this);
        setOnOnItemLongClickListener(this);
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_projects, parent, false));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }

    @Override
    public void onClick(View v, int position, Object data) {
        Log.i(TAG, new StringBuilder().append("onClick: ").append(position).toString());
    }

    @Override
    public boolean onLongClick(View v, int position, Object data) {
        Log.i(TAG, new StringBuilder().append("onLongClick: ").append(position).toString());
        return true;
    }

    public class ProjectViewHolder extends BaseRecyclerAdapter.BaseViewHolder {
        public ProjectViewHolder(View itemView) {
            super(itemView);
        }
    }
}
