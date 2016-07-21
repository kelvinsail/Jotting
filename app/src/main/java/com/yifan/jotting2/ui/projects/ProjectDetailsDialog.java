package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseFullScreenDialog;
import com.yifan.jotting2.pojo.Projects;
import com.yifan.jotting2.utils.ResourcesUtils;

/**
 * 项目详情Dialog
 * <p/>
 * Created by yifan on 2016/7/20.
 */
public class ProjectDetailsDialog extends BaseFullScreenDialog {

    public static final String TAG = "ProjectDetailsDialog";

    /**
     * 项目名字文本控件
     */
    private TextView mProjectNameText;

    /**
     * 项目详细信息文本控件
     */
    private TextView mProjectDesText;

    public static ProjectDetailsDialog newInstance(Projects projects) {
        ProjectDetailsDialog f = new ProjectDetailsDialog();
        Bundle data = new Bundle();
        data.putParcelable("data", projects);
        f.setArguments(data);
        return f;
    }

    public ProjectDetailsDialog() {
    }

    @Override
    public void initView() {
        setContentView(R.layout.dialog_project_details);
        mProjectNameText = (TextView) findViewByID(R.id.tv_project_details_name);
        mProjectDesText = (TextView) findViewByID(R.id.tv_project_details_description);
        //实现滚动
        mProjectDesText.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (null != getArguments()) {
            Projects projects = getArguments().getParcelable("data");
            if (null != projects) {
                mProjectNameText.setText(projects.getProjectName());
                StringBuilder str = new StringBuilder();
                str.append(ResourcesUtils.getString(R.string.description_total_money, projects.getTotalMoney()));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_state, projects.getIsEnded() ?
                        ResourcesUtils.getString(R.string.description_state_end) :
                        ResourcesUtils.getString(R.string.description_state_normal)));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_startTime,
                        projects.getStartTime(), projects.getStartTime()));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_modifyTime,
                        projects.getModifyTime(), projects.getModifyTime()));
                mProjectDesText.setText(str);
            }
        }
    }

    @Override
    public void setListener() {
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
