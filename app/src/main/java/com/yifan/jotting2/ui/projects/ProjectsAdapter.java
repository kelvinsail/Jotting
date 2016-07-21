package com.yifan.jotting2.ui.projects;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseActivity;
import com.yifan.jotting2.base.BaseFragment;
import com.yifan.jotting2.base.widget.BaseRecyclerAdapter;
import com.yifan.jotting2.pojo.Projects;
import com.yifan.jotting2.utils.ResourcesUtils;

import java.util.List;

/**
 * 所有项目列表页数据适配器
 * <p/>
 * Created by yifan on 2016/7/18.
 */
public class ProjectsAdapter extends BaseRecyclerAdapter<ProjectsAdapter.ProjectViewHolder>
        implements BaseRecyclerAdapter.OnItemClickListener, BaseRecyclerAdapter.OnItemLongClickListener {

    private static final String TAG = "ProjectsAdapter";

    private List<Projects> mList;

    private int[] colors = new int[]{R.color.text_orange,
            R.color.colorPrimary, R.color.text_gray};

    public ProjectsAdapter(List<Projects> projectses) {
        mList = projectses;
        setOnItemClickListener(this);
        setOnOnItemLongClickListener(this);
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_projects, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (null == mList) {
            return 0;
        }
        return mList.get(position).getProjectType();
    }

    @Override
    public int getItemCount() {
        if (null == mList) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public Object getItem(int postion) {
        if (null == mList) {
            return null;
        } else {
            if (postion >= mList.size()) {
                return null;
            } else {
                return mList.get(postion);
            }
        }
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (null != mList && position < mList.size()) {
            holder.setData(mList.get(position), getItemViewType(position));
        }
    }

    @Override
    public void onClick(View v, int position, Object data) {
    }

    @Override
    public boolean onLongClick(View v, int position, Object data) {
        if (null != v.getContext() && v.getContext() instanceof BaseActivity) {
            ProjectDetailsDialog dialog = ProjectDetailsDialog.newInstance((Projects) data);
            dialog.show(((BaseActivity) v.getContext()).getSupportFragmentManager(), ProjectDetailsDialog.TAG);
            return true;
        }
        return false;
    }

    /**
     * 项目列表条目ViewHolder
     */
    public class ProjectViewHolder extends BaseRecyclerAdapter.BaseViewHolder {

        /**
         * 项目名字
         */
        TextView projectName;

        /**
         * 项目描述
         */
        TextView descriptionText;

        /**
         * 颜色标签
         */
        View label;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.view_project_label);
            projectName = (TextView) itemView.findViewById(R.id.tv_project_name);
            descriptionText = (TextView) itemView.findViewById(R.id.tv_description_text);
        }

        /**
         * 设置数据
         *
         * @param projects
         * @param type
         */
        public void setData(Projects projects, int type) {
            if (null != projects) {
                if (null != colors && colors.length > type) {
                    label.setBackgroundColor(ResourcesUtils.getColor(colors[type]));
                }
                //设置项目名
                if (null != projectName && null != projects.getProjectName()) {
                    projectName.setText(projects.getProjectName().toString());
                }
                //设置项目信息
                SpannableStringBuilder description = new SpannableStringBuilder();
                //开始时间
                if (projects.getStartTime() > 0) {
                    if (description.length() > 0) {
                        description.append("\n");
                    }
                    description.append(ResourcesUtils.getString(R.string.description_startTime,
                            projects.getStartTime(), projects.getStartTime()));
                }
                //修改时间
                if (projects.getModifyTime() > 0) {
                    if (description.length() > 0) {
                        description.append("\n");
                    }
                    description.append(ResourcesUtils.getString(R.string.description_modifyTime,
                            projects.getModifyTime(), projects.getModifyTime()));
                }
                if (description.length() > 0) {
                    descriptionText.setText(description);
                }
            }
        }
    }
}
