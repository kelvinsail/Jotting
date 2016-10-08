package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.thinksky.utils.base.BaseDialogFragment;
import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Project;

/**
 * 项目详情Dialog
 *
 * Created by yifan on 2016/7/20.
 */
public class ProjectDetailsDialog extends BaseDialogFragment {

    public static final String TAG = "ProjectDetailsDialog";

    /**
     * 项目名字文本控件
     */
    private TextView mProjectNameText;

    /**
     * 项目详细信息文本控件
     */
    private TextView mProjectDesText;

    public static ProjectDetailsDialog newInstance(Project project) {
        ProjectDetailsDialog f = new ProjectDetailsDialog();
        Bundle data = new Bundle();
        data.putParcelable("data", project);
        f.setArguments(data);
        return f;
    }

    public ProjectDetailsDialog() {
    }

    @Override
    public void initView() {
        mProjectNameText = (TextView) getDialog().findViewById(R.id.tv_project_details_name);
        mProjectDesText = (TextView) getDialog().findViewById(R.id.tv_project_details_description);
        //实现滚动
        mProjectDesText.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (null != getArguments()) {
            Project project = getArguments().getParcelable("data");
            if (null != project) {
                mProjectNameText.setText(project.getProjectName());
                StringBuilder str = new StringBuilder();
                str.append(ResourcesUtils.getString(R.string.description_total_money, project.getTotalMoney()));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_state, project.getIsEnded() ?
                        ResourcesUtils.getString(R.string.description_state_end) :
                        ResourcesUtils.getString(R.string.description_state_normal)));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_startTime,
                        project.getStartTime(), project.getStartTime()));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_modifyTime,
                        project.getModifyTime(), project.getModifyTime()));
                mProjectDesText.setText(str);
            }
        }
    }

    @Override
    public void setListener() {
    }

    @Override
    public int getLayoutResID() {
        return R.layout.dialog_project_details;
    }

    @Override
    public View getLayoutView() {
        return null;
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
