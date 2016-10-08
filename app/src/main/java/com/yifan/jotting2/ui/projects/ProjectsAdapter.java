package com.yifan.jotting2.ui.projects;

import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thinksky.utils.base.BaseActivity;
import com.thinksky.utils.base.widget.BaseRecyclerAdapter;
import com.thinksky.utils.base.widget.BaseRecyclerHolder;
import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Project;

import java.util.List;

/**
 * 所有项目列表页数据适配器
 *
 * Created by yifan on 2016/7/18.
 */
public class ProjectsAdapter extends BaseRecyclerAdapter<ProjectsAdapter.BaseHolder> {

    private static final String TAG = "ProjectsAdapter";

    /**
     * 列表数据
     */
    private List<Project> mList;

    private int[] colors = new int[]{R.color.text_orange,
            R.color.colorPrimary, R.color.text_gray};

    public ProjectsAdapter(List<Project> projectses) {
        mList = projectses;
    }

    @Override
    public int getItemViewType(int position) {
        if (null == mList) {
            return 0;
        }
        return mList.get(position).getProjectType();
    }


    @Override
    public ProjectViewHolder onCreate(ViewGroup parent, int viewType) {
        return new ProjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBind(BaseHolder viewHolder, int realPosition) {
        if (null != mList && realPosition < mList.size()) {
            viewHolder.setData(mList.get(realPosition), getItemViewType(realPosition));
        }
    }

    @Override
    public int getRealItemCount() {
        if (null == mList) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public BaseHolder getFakeHolder(View view) {
        return new BaseHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }

//    @Override
//    public boolean onLongClick(View v, int position, Object data) {
//        if (null != v.getContext() && v.getContext() instanceof BaseActivity) {
//            ProjectDetailsDialog dialog = ProjectDetailsDialog.newInstance((Project) data);
//            dialog.show(((BaseActivity) v.getContext()).getSupportFragmentManager(), ProjectDetailsDialog.TAG);
//            return true;
//        }
//        return false;
//    }

    class BaseHolder extends BaseRecyclerHolder {
        public BaseHolder(View itemView) {
            super(itemView);
        }

        public void setData(Project project, int itemViewType) {
        }
    }

    /**
     * 项目列表条目ViewHolder
     */
    public class ProjectViewHolder extends BaseHolder {

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
         * @param project
         * @param type
         */
        public void setData(Project project, int type) {
            if (null != project) {
                if (null != colors && colors.length > type) {
                    label.setBackgroundColor(ResourcesUtils.getColor(colors[type]));
                }
                //设置项目名
                if (null != projectName && null != project.getProjectName()) {
                    projectName.setText(project.getProjectName().toString());
                }
                //设置项目信息
                SpannableStringBuilder description = new SpannableStringBuilder();
                //开始时间
                if (project.getStartTime() > 0) {
                    if (description.length() > 0) {
                        description.append("\n");
                    }
                    description.append(ResourcesUtils.getString(R.string.description_startTime,
                            project.getStartTime(), project.getStartTime()));
                }
                //修改时间
                if (project.getModifyTime() > 0) {
                    if (description.length() > 0) {
                        description.append("\n");
                    }
                    description.append(ResourcesUtils.getString(R.string.description_modifyTime,
                            project.getModifyTime(), project.getModifyTime()));
                }
                if (description.length() > 0) {
                    descriptionText.setText(description);
                }
            }
        }
    }
}
