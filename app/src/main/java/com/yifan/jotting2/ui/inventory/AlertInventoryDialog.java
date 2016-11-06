package com.yifan.jotting2.ui.inventory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseMeasureDialog;
import com.yifan.jotting2.pojo.Inventory;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.Constans;
import com.yifan.jotting2.utils.database.datahalper.InventoriesDataHelper;

/**
 * 新增清单项弹窗
 *
 * Created by yifan on 2016/10/22.
 */
public class AlertInventoryDialog extends BaseMeasureDialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String TAG = "AlertInventoryDialog";

    /**
     * 项目数据键值对key
     */
    public static final String BUNDLE_KEY_PROJECT = "project";

    /**
     * 根布局
     */
    private View mRootView;

    /**
     * 清单内容名称控件
     */
    private EditText mNameEdit;

    /**
     * 高级内容表格
     */
    private CheckBox mAdvanceBox;

    /**
     * 高级选项布局
     */
    private GridLayout mAdvanceLayout;

    /**
     * 数量填写控件
     */
    private EditText mCountEdit;

    /**
     * 价格填写控件
     */
    private EditText mMoneyEdit;

    /**
     * 时间填写控件
     */
    private EditText mDescriptionEdit;

    /**
     * 标签颜色选择器
     */
    private RadioGroup mRadioLayout;

    /**
     * 要修改的数据
     */
    private Inventory mInventory;

    /**
     * 判断是否为修改数据
     */
    private boolean isAlerting;

    /**
     * 所属项目的数据
     */
    private Project mProject;

    /**
     * 标签颜色
     */
    private int[] mLabelColors = new int[]{
            ResourcesUtils.getColor(R.color.label_green),
            ResourcesUtils.getColor(R.color.label_red),
            ResourcesUtils.getColor(R.color.label_blue),
            ResourcesUtils.getColor(R.color.label_cyan),
            ResourcesUtils.getColor(R.color.label_yellow)
    };

    @Override
    public String getTAG() {
        return TAG;
    }

    public static AlertInventoryDialog newInstance(Project project, Inventory inventory) {
        Bundle args = new Bundle();
        args.putParcelable(Constans.BUNDLE_KEY_DATA, inventory);
        args.putParcelable(BUNDLE_KEY_PROJECT, project);
        AlertInventoryDialog fragment = new AlertInventoryDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public AlertInventoryDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProject = getArguments().getParcelable(BUNDLE_KEY_PROJECT);
        mInventory = getArguments().getParcelable(Constans.BUNDLE_KEY_DATA);
        isAlerting = null != mInventory;
    }

    @Override
    public void initView() {
        super.initView();
        getViewStub().setLayoutResource(R.layout.dialog_new_inventory);
        mRootView = getViewStub().inflate();
        mNameEdit = (EditText) mRootView.findViewById(R.id.et_dialog_new_inventory_name);
        mAdvanceBox = (CheckBox) mRootView.findViewById(R.id.cb_dialog_new_inventory_advance);
        mAdvanceLayout = (GridLayout) mRootView.findViewById(R.id.layout_dialog_new_inventory_advance);
        mCountEdit = (EditText) mRootView.findViewById(R.id.et_dialog_new_inventory_count);
        mMoneyEdit = (EditText) mRootView.findViewById(R.id.et_dialog_new_inventory_money);
        mDescriptionEdit = (EditText) mRootView.findViewById(R.id.et_dialog_new_inventory_description);
        mRadioLayout = (RadioGroup) mRootView.findViewById(R.id.rg_dialog_new_inventory_label_color);

        //添加颜色标签选项
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, ResourcesUtils.getDisplayMetrics());
        int selected = 0;
        for (int i = 0; i < mLabelColors.length; i++) {
            int colorRes = mLabelColors[i];
            RadioButton button = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.item_radio_button_color_label, null);
            button.setBackgroundColor(colorRes);
            if (isAlerting && null != mInventory && colorRes == mInventory.getLabelColor()) {
                selected = i;
            }
            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(size, size);
            if (mRadioLayout.getChildCount() > 0) {
                lp.leftMargin = ResourcesUtils.getDimensionPixelSize(R.dimen.padding_normal);
            }
            button.setId(i);
            mRadioLayout.addView(button, lp);
        }
        mRadioLayout.check(selected);

        //添加按钮
        addButton(R.id.dialog_button_cancel, ResourcesUtils.getString(R.string.cancel), this);
        if (!isAlerting) {
            addButton(R.id.dialog_button_next, ResourcesUtils.getString(R.string.next), this);
        }
        addButton(R.id.dialog_button_measure, ResourcesUtils.getString(R.string.measure), this);
        mAdvanceBox.setOnCheckedChangeListener(this);

        if (isAlerting && null != mInventory) {
            mNameEdit.setText(mInventory.getName());
            mCountEdit.setText(String.valueOf(mInventory.getCount()));
            mMoneyEdit.setText(String.valueOf(mInventory.getMoney()));
            mDescriptionEdit.setText(mInventory.getDescription());
        }

        //自动弹出软键盘
        mNameEdit.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) mNameEdit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mNameEdit, 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_cancel:
                break;
            case R.id.dialog_button_next:
                if (!addNewInventory()) {
                    return;
                }
                clearInput(mNameEdit, mCountEdit, mMoneyEdit, mDescriptionEdit);
                return;
            case R.id.dialog_button_measure:
                if (!addNewInventory()) {
                    return;
                }
                break;
        }
        dismiss();
    }

    /**
     * 添加新的清单内容
     *
     * @return true：添加成功；false：添加失败
     */
    private boolean addNewInventory() {
        String name = mNameEdit.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), ResourcesUtils.getString(R.string.inventory_name_hint),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        Inventory inventory;
        if (isAlerting) {
            inventory = mInventory;
        } else {
            inventory = new Inventory();
        }
        inventory.setName(name);
        inventory.setDate(System.currentTimeMillis());
        inventory.setDescription(mDescriptionEdit.getText().toString());
        inventory.setLabelColor(mLabelColors[mRadioLayout.getCheckedRadioButtonId()]);
        inventory.setProjectID(mProject.getId());
        String moneyText = mMoneyEdit.getText().toString();
        if (!TextUtils.isEmpty(moneyText)) {
            inventory.setMoney(Double.valueOf(moneyText));
        }
        String count = mCountEdit.getText().toString();
        if (!TextUtils.isEmpty(count)) {
            inventory.setCount(Integer.valueOf(count));
        }
        //判断是修改还是插入
        if (isAlerting) {
            InventoriesDataHelper.getInstance().alert(inventory);
        } else {
            InventoriesDataHelper.getInstance().insert(inventory);
        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mAdvanceLayout.setVisibility(View.VISIBLE);
        } else {
            mAdvanceLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 清空已输入的文本
     *
     * @param editTexts
     */
    private void clearInput(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setText(null);
        }
    }
}
