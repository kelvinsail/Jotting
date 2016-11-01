package com.yifan.jotting2.ui.projects;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.thinksky.utils.base.BaseAsyncTask;
import com.thinksky.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.task.DeleteProjectTask;
import com.yifan.jotting2.ui.ConfirmDialog;
import com.yifan.jotting2.utils.Constans;

import java.lang.ref.WeakReference;

/**
 * 删除项目确认Dialog
 *
 * Created by yifan on 2016/10/30.
 */
public class DeleteProjectDialog extends ConfirmDialog
        implements ConfirmDialog.OnMeasureClickListener {

    public static final String TAG = "DeleteProjectDialog";

    /**
     * 删除项目所有数据 异步任务
     */
    private DeleteProjectTask mTask;

    /**
     * 删除项目所有数据 异步任务监听
     */
    private OnDeleteProjectListener mListener;

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
    public boolean onMeasure() {
        if (null != getArguments()) {
            Project project = getArguments().getParcelable(Constans.BUNDLE_KEY_DATA);
            if (null != project) {
                setCancelable(false);
                if (null != mTask) {
                    mTask.cancel(true);
                    mTask = null;
                }
                if (null == mListener) {
                    mListener = new OnDeleteProjectListener(new WeakReference<DeleteProjectDialog>(this));
                }
                mTask = new DeleteProjectTask();
                mTask.setOnAsyncListener(mListener);
                mTask.asyncExecute(project);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除项目异步任务监听
     */
    private static class OnDeleteProjectListener implements BaseAsyncTask.OnAsyncListener {

        /**
         * 弹窗引用
         */
        private WeakReference<DeleteProjectDialog> mDialog;

        public OnDeleteProjectListener(WeakReference<DeleteProjectDialog> dialog) {
            this.mDialog = dialog;
        }

        @Override
        public void onAsyncSuccess(Object data) {
            if (null != mDialog.get()) {
                if (null != data && data instanceof Boolean) {
                    mDialog.get().setCancelable(true);
                    if ((Boolean) data) {
                        mDialog.get().dismiss();
                    } else {
                        onAsyncFail();
                    }
                }
            }
        }

        @Override
        public void onAsyncFail() {

        }

        @Override
        public void onAsyncCancelled() {

        }

        @Override
        public void onAsyncStart() {

        }

        @Override
        public void onAsyncCompleted() {
        }
    }
}
