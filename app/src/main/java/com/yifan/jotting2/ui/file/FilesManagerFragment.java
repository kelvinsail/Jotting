package com.yifan.jotting2.ui.file;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifan.utils.base.BaseFragment;
import com.yifan.utils.utils.ResourcesUtils;
import com.yifan.jotting2.R;

/**
 * 备份文件管理器
 *
 * Created by yifan on 2016/7/16.
 */
public class FilesManagerFragment extends BaseFragment {

    public static final String TAG = "FilesManagerFragment";

    public static FilesManagerFragment newInstance() {
        return new FilesManagerFragment();
    }

    public FilesManagerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_files, container, false);
        return view;
    }

    @Override
    public boolean isPrintLifeCycle() {
        return true;
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public String getTitleName() {
        return ResourcesUtils.getString(R.string.title_name_files);
    }
}
