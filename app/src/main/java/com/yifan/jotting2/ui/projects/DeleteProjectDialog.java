package com.yifan.jotting2.ui.projects;

import android.os.Bundle;

import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.ui.ConfirmDialog;
import com.yifan.jotting2.utils.Constans;

/**
 * 删除项目确认Dialog
 *
 * Created by yifan on 2016/10/30.
 */
public class DeleteProjectDialog extends ConfirmDialog implements ConfirmDialog.OnMeasureClickListener {

    public static final String TAG = "DeleteProjectDialog";

    public static DeleteProjectDialog newInstance(Project project) {
        Bundle args = new Bundle();
        args.putString(ConfirmDialog.BUNDLE_KEY_TITLE, ResourcesUtils.getString(R.string.confirm));
        args.putString(ConfirmDialog.BUNDLE_KEY_MESSAGE,
                ResourcesUtils.getString(R.string.delete_project_tips));
        args.putParcelable(Constans.BUNDLE_KEY_DATA, project);
        DeleteProjectDialog fragment = new DeleteProjectDialog();
        fragment.setOnMeasureClickListener(fragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onMeasure() {
        if (null != getArguments()) {
            Project project = getArguments().getParcelable(Constans.BUNDLE_KEY_DATA);
            if (null != project) {

            }
        }
    }
}
