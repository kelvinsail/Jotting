package com.yifan.jotting2.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.base.BaseMeasureDialog;

/**
 * 删除确认按钮
 *
 * Created by yifan on 2016/10/30.
 */
public class ConfirmDialog extends BaseMeasureDialog implements View.OnClickListener {

    private static final String TAG = "ConfirmDialog";
    public static final String BUNDLE_KEY_TITLE = "title";
    public static final String BUNDLE_KEY_MESSAGE = "message";

    /**
     * 确认弹窗标题文本控件
     */
    private TextView mTitleText;

    /**
     * 确认弹窗内容文本控件
     */
    private TextView mMessageText;

    /**
     * 确认按钮点击事件监听
     */
    private OnConfirmClickListener mListener;

    @Override
    public String getTAG() {
        return TAG;
    }

    public ConfirmDialog() {
    }

    public static ConfirmDialog newInstance(String dialogTitle, String dialogMsg) {
        Bundle args = new Bundle();
        ConfirmDialog fragment = new ConfirmDialog();
        args.putString(BUNDLE_KEY_TITLE, dialogTitle);
        args.putString(BUNDLE_KEY_MESSAGE, dialogMsg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        super.initView();
        getViewStub().setLayoutResource(R.layout.dialog_confirm);
        View view = getViewStub().inflate();
        mTitleText = (TextView) view.findViewById(R.id.tv_dialog_confirm_title);
        mMessageText = (TextView) view.findViewById(R.id.tv_dialog_confirm_msg);
        if (null != getArguments()) {
            mTitleText.setText(getArguments().getString(BUNDLE_KEY_TITLE, ResourcesUtils.getString(R.string.confirm)));
            mMessageText.setText(getArguments().getString(BUNDLE_KEY_MESSAGE, null));
        }

        addButton(R.id.dialog_button_cancel, ResourcesUtils.getString(R.string.cancel), this);
        addButton(R.id.dialog_button_measure, ResourcesUtils.getString(R.string.measure), this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_measure:
                if (null != mListener) {
                    if (mListener.onConfirm()) {
                        return;
                    }
                }
                break;
        }
        if (isCancelable()) {
            dismiss();
        }
    }

    /**
     * 设置确认按钮点击按钮事件监听
     *
     * @param listener
     */
    public void setOnMeasureClickListener(OnConfirmClickListener listener) {
        this.mListener = listener;
    }

    /**
     * 确认按钮点击事件监听
     */
    public interface OnConfirmClickListener {

        /**
         * @return true：拦截dialog销毁动作，自行处理l；false：点击之后dialog自动销毁
         */
        boolean onConfirm();
    }
}
