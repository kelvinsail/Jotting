package com.yifan.jotting2.ui.normal.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseMeasureDialog;
import com.yifan.jotting2.pojo.Companion;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.model.CompanionModel;
import com.yifan.utils.utils.ResourcesUtils;

/**
 * 添加新成员 Dialog
 *
 * Created by yifan on 2016/11/12.
 */
public class AddCompanionDailog extends BaseMeasureDialog implements View.OnClickListener {

    public static final String TAG = "AddCompanionDailog";

    /**
     * 成员姓名输入框控件
     */
    private EditText mCompanionNameEdit;

    /**
     * 手机号码输入框控件
     */
    private EditText mCompanionPhoneEdit;

    /**
     * 充值金额输入框控件
     */
    private EditText mCompanionRechargeEdit;

    /**
     * 默认支付者选中控件
     */
    private CheckBox mDefaultPayerCheck;

    /**
     * 项目数据
     */
    private Project mProject;

    @Override
    public String getTAG() {
        return TAG;
    }

    public AddCompanionDailog() {
    }

    public static AddCompanionDailog newInstance(Project project) {
        Bundle args = new Bundle();
        args.putParcelable(Constans.BUNDLE_KEY_PROJECT, project);
        AddCompanionDailog fragment = new AddCompanionDailog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProject = getArguments().getParcelable(Constans.BUNDLE_KEY_PROJECT);
    }

    @Override
    public void initView() {
        super.initView();
        getViewStub().setLayoutResource(R.layout.dialog_new_companion);
        View view = getViewStub().inflate();
        mCompanionNameEdit = (EditText) view.findViewById(R.id.et_dialog_new_companion_name);
        mCompanionPhoneEdit = (EditText) view.findViewById(R.id.et_dialog_new_companion_phone);
        mCompanionRechargeEdit = (EditText) view.findViewById(R.id.et_dialog_new_companion_recharge);
        mDefaultPayerCheck = (CheckBox) view.findViewById(R.id.cb_dialog_new_companion_payer);

        addButton(R.id.dialog_button_cancel, ResourcesUtils.getString(R.string.cancel), this);
        addButton(R.id.dialog_button_measure, ResourcesUtils.getString(R.string.measure), this);

        //自动弹出软键盘
        mCompanionNameEdit.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager)
                        mCompanionNameEdit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mCompanionNameEdit, 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_cancel:
                break;
            case R.id.dialog_button_measure:
                String name = mCompanionNameEdit.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getActivity(), R.string.companion_name_input_hint, Toast.LENGTH_SHORT).show();
                    return;
                }
                Companion companion = new Companion();
                companion.setName(name);
                companion.setProjectID(mProject.getId());
                companion.setIsDefaultPayer(mDefaultPayerCheck.isChecked());
                companion.setPhone(mCompanionPhoneEdit.getText().toString());
                try {
                    String money = mCompanionRechargeEdit.getText().toString();
                    companion.setRechangeMoney(TextUtils.isEmpty(money) ? 0 : Double.valueOf(money));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                CompanionModel.getInstance().insert(companion);
                break;
        }
        dismiss();
    }
}
