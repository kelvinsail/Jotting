package com.yifan.jotting2.ui.projects;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.thinksky.utils.base.BaseDialogFragment;
import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseMeasureDialog;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.utils.database.ProjectsDataHelp;

/**
 * Project修改弹窗
 *
 * Created by yifan on 2016/10/30.
 */
public class AlertProjectDialog extends BaseMeasureDialog implements View.OnClickListener {

    public static final String TAG = "AlertProjectDialog";

    /**
     * 根布局
     */
    private View mView;

    /**
     * 项目名字
     */
    private EditText mNameEdit;

    /**
     * 项目总金额
     */
    private EditText mTotalMoneyEdit;

    /**
     * 项目信息
     */
    private Project mProject;

    @Override
    public String getTAG() {
        return TAG;
    }

    public AlertProjectDialog() {
    }

    public static AlertProjectDialog newInstance(Project project) {
        Bundle args = new Bundle();
        AlertProjectDialog fragment = new AlertProjectDialog();
        args.putParcelable(Constans.BUNDLE_KEY_DATA, project);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mProject = getArguments().getParcelable(Constans.BUNDLE_KEY_DATA);
        }
    }

    @Override
    public void initView() {
        super.initView();
        getViewStub().setLayoutResource(R.layout.dialog_new_project);
        mView = getViewStub().inflate();
        mView.findViewById(R.id.tv_dialog_new_project_type).setVisibility(View.GONE);
        mView.findViewById(R.id.sp_dialog_new_project_type).setVisibility(View.GONE);
        ((TextView) mView.findViewById(R.id.tv_dialog_new_project_title)).setText(R.string.alert_project);
        mNameEdit = (EditText) mView.findViewById(R.id.et_dialog_new_project_name);
        mTotalMoneyEdit = (EditText) mView.findViewById(R.id.et_dialog_new_project_total_money);
        if (mProject.getProjectType() == 2) {
            mView.findViewById(R.id.tv_dialog_new_project_total_money).setVisibility(View.GONE);
            mTotalMoneyEdit.setVisibility(View.GONE);
        }

        //添加按钮
        addButton(R.id.dialog_button_cancel, ResourcesUtils.getString(R.string.cancel), this);
        addButton(R.id.dialog_button_measure, ResourcesUtils.getString(R.string.measure), this);

        //自动弹出软键盘
        mNameEdit.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager)
                        mNameEdit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mNameEdit, 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_cancel:
                break;
            case R.id.dialog_button_measure:
                String name = mNameEdit.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    break;
                }
                mProject.setProjectName(name);
                String totalMoaney = mTotalMoneyEdit.getText().toString();
                if (!TextUtils.isEmpty(totalMoaney) && mProject.getProjectType() != 2) {
                    mProject.setTotalMoney(Double.valueOf(totalMoaney));
                }
                ProjectsDataHelp.getInstance().alert(mProject);
                break;
        }
        dismiss();
    }
}
