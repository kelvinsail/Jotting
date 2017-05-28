package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseMeasureDialog;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.ui.widget.RadiusLayout;

/**
 * 项目详情Dialog
 *
 * Created by yifan on 2016/7/20.
 */
public class ProjectDetailsDialog extends BaseMeasureDialog implements View.OnClickListener {

    public static final String TAG = "ProjectDetailsDialog";

    /**
     * 项目名字文本控件
     */
    private TextView mProjectNameText;

    /**
     * 项目详细信息文本控件
     */
    private TextView mProjectDesText;

    /**
     * 根布局
     */
    private View mView;

    /**
     * 项目数据
     */
    private Project mProject;

    public static ProjectDetailsDialog newInstance(Project project) {
        ProjectDetailsDialog f = new ProjectDetailsDialog();
        Bundle data = new Bundle();
        data.putParcelable(Constans.BUNDLE_KEY_DATA, project);
        f.setArguments(data);
        return f;
    }

    public ProjectDetailsDialog() {
    }

    @Override
    public void initView() {
        super.initView();
        ((RadiusLayout) getRootView()).setContentPadding(0, 0, 0, 0);
        getViewStub().setLayoutResource(R.layout.dialog_project_details);
        mView = getViewStub().inflate();
        mProjectNameText = (TextView) mView.findViewById(R.id.tv_project_details_name);
        mProjectDesText = (TextView) mView.findViewById(R.id.tv_project_details_description);
        //实现滚动
        mProjectDesText.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (null != getArguments()) {
            mProject = getArguments().getParcelable(Constans.BUNDLE_KEY_DATA);
            if (null != mProject) {
                mProjectNameText.setText(mProject.getProjectName());
                StringBuilder str = new StringBuilder();
                str.append(ResourcesUtils.getString(R.string.description_total_money, mProject.getTotalMoney()));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_state, mProject.getIsEnded() ?
                        ResourcesUtils.getString(R.string.description_state_end) :
                        ResourcesUtils.getString(R.string.description_state_normal)));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_startTime,
                        mProject.getStartTime(), mProject.getStartTime()));
                str.append("\n").append(ResourcesUtils.getString(R.string.description_modifyTime,
                        mProject.getModifyTime(), mProject.getModifyTime()));
                mProjectDesText.setText(str);
            }
        }
        //添加按钮
        addButton(R.id.dialog_button_alert, ResourcesUtils.getString(R.string.to_alert), this);
        addButton(R.id.dialog_button_delete, ResourcesUtils.getString(R.string.delete), this);
    }

    @Override
    public void setListener() {
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_alert:
                AlertProjectDialog.newInstance(mProject).show(getActivity().getSupportFragmentManager(), AlertProjectDialog.TAG);
                break;
            case R.id.dialog_button_delete:
                DeleteProjectDialog.newInstance(mProject).show(getActivity().getSupportFragmentManager(),DeleteProjectDialog.TAG);
                break;
        }
        dismiss();
    }
}
