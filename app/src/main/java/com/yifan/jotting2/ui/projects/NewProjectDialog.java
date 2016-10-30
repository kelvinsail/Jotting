package com.yifan.jotting2.ui.projects;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseMeasureDialog;
import com.yifan.jotting2.utils.database.ProjectsDataHelp;

/**
 * 新建项目弹窗
 *
 * Created by yifan on 2016/7/24.
 */
public class NewProjectDialog extends BaseMeasureDialog implements View.OnClickListener {

    public static final String TAG = "NewProjectDialog";

    /**
     * 项目类型列表数据
     */
    static String[] mTypes = ResourcesUtils.getStringArray(R.array.project_types);

    /**
     * 主要布局
     */
    View mRootView;

    /**
     * 项目名字输入框
     */
    EditText mProjectNameEdit;

    /**
     * 项目类型选择
     */
    AppCompatSpinner mProjectTypeSpinner;

    /**
     * 项目总金额文本
     */
    TextView mTatolMoneyText;

    /**
     * 项目总金额输入框
     */
    EditText mTotalMoneyEdit;

    public static NewProjectDialog newInstance() {
        NewProjectDialog fragment = new NewProjectDialog();
        return fragment;
    }

    public NewProjectDialog() {
    }

    @Override
    public void initView() {
        super.initView();
        //初始化布局
        getViewStub().setLayoutResource(R.layout.dialog_new_project);
        mRootView = getViewStub().inflate();
        mProjectNameEdit = (EditText) mRootView.findViewById(R.id.et_dialog_new_project_name);
        mProjectTypeSpinner = (AppCompatSpinner) mRootView.findViewById(R.id.sp_dialog_new_project_type);
        mTotalMoneyEdit = (EditText) mRootView.findViewById(R.id.et_dialog_new_project_total_money);
        mTatolMoneyText = (TextView) mRootView.findViewById(R.id.tv_dialog_new_project_total_money);
        //设置选项
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item, mTypes);
        adapter.setDropDownViewResource(android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item);
        mProjectTypeSpinner.setAdapter(adapter);

        //添加按钮
        addButton(R.id.dialog_button_cancel, ResourcesUtils.getString(R.string.cancel), this);
        addButton(R.id.dialog_button_measure, ResourcesUtils.getString(R.string.measure), this);

        //自动弹出软键盘
        mProjectNameEdit.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager)
                        mProjectNameEdit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mProjectNameEdit, 0);
            }
        });
    }

    @Override
    public void setListener() {
        mProjectTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mTotalMoneyEdit.setVisibility(i != 2 ? View.VISIBLE : View.GONE);
                mTatolMoneyText.setVisibility(i != 2 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_button_measure:
                if (null != mProjectNameEdit) {
                    if (TextUtils.isEmpty(mProjectNameEdit.getText())) {
                        Toast.makeText(getActivity(), ResourcesUtils.getString(R.string.project_name_hint),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //项目总金额文本
                    String totalMoney = mTotalMoneyEdit.getText().toString();
                    if (TextUtils.isEmpty(totalMoney) ||
                            mProjectTypeSpinner.getSelectedItemPosition() == 1) {
                        totalMoney = String.valueOf(0);
                    }
                    //插入数据
                    ProjectsDataHelp.getInstance().insertNewData(mProjectTypeSpinner.getSelectedItemPosition()
                            , mProjectNameEdit.getText().toString(), null, Double.valueOf(totalMoney),
                            System.currentTimeMillis(), System.currentTimeMillis(), false);
                }
                break;
            case R.id.dialog_button_cancel:
                break;
        }
        dismiss();
    }
}
