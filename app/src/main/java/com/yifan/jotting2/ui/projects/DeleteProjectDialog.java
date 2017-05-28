package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.yifan.jotting2.base.impl.IView;
import com.yifan.jotting2.presenter.ProjectPresenter;
import com.yifan.utils.base.BaseDialogFragment;
import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.ui.dialog.ConfirmDialog;
import com.yifan.jotting2.utils.Constans;


import java.lang.ref.WeakReference;

/**
 * 删除项目确认Dialog
 *
 * Created by yifan on 2016/10/30.
 */
public class DeleteProjectDialog extends ConfirmDialog
        implements ConfirmDialog.OnConfirmClickListener {

    public static final String TAG = "DeleteProjectDialog";

    private ProjectPresenter mPresenter;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onConfirm() {
        if (null != getArguments()) {
            Project project = getArguments().getParcelable(Constans.BUNDLE_KEY_DATA);
            if (null != project) {
                setCancelable(false);
                if (null == mPresenter) {
                    mPresenter = new ProjectPresenter(new ProjectDialogView(new WeakReference<BaseDialogFragment>(this)));
                }
                mPresenter.deleteProjects(project);
                return true;
            }
        }
        return false;
    }

}
