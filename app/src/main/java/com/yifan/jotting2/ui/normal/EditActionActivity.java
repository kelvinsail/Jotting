package com.yifan.jotting2.ui.normal;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Action;
import com.yifan.jotting2.utils.Constans;
import com.yifan.utils.base.TitleBarActivity;
import com.yifan.utils.utils.ResourcesUtils;

/**
 * 活动编辑界面
 *
 * Created by yifan on 2016/11/7.
 */
public class EditActionActivity extends TitleBarActivity {

    private static final String TAG = "EditActionActivity";

    /**
     * 待编辑的活动数据
     */
    private Action mAction;

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent()) {
            mAction = getIntent().getParcelableExtra(Constans.BUNDLE_KEY_ACTION);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(ResourcesUtils.getColor(R.color.background_main)));
        setContentView(R.layout.activity_edit_action, 0, false);
    }

    @Override
    public void initView() {
        super.initView();
        if (null == mAction) {
            setTitle(R.string.new_action);
        } else {
            setTitle(R.string.edit_action);
        }
    }
}
