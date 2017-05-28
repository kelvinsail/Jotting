package com.yifan.jotting2.presenter;

import android.util.Log;

import com.yifan.jotting2.base.Presenter;
import com.yifan.jotting2.base.impl.IView;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.model.InventoriesModel;
import com.yifan.jotting2.model.ProjectsModel;
import com.yifan.jotting2.utils.EventBusManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Project Presenter
 *
 * Created by wuyifan on 2017/5/26.
 */

public class ProjectPresenter extends Presenter {

    private static final String TAG = "";

    /**
     * IView接口
     */
    private IView mView;

    public ProjectPresenter() {
    }

    public ProjectPresenter(IView iView) {
        this.mView = iView;
    }

    /**
     * 删除Project
     *
     * @param projects
     */
    public void deleteProjects(Project... projects) {
        Observable.fromArray(projects)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Project, Project>() {

                    @Override
                    public Project apply(@NonNull Project project) throws Exception {
                        switch (project.getProjectType()) {
                            case Project.PROJECT_TYPE_NORMAL:
                                //TODO 删除普通项目成员、活动记录数据
                                break;
                            case Project.PROJECT_TYPE_DAYBOOK:
                                //TODO 删除流水账数据
                                break;
                            case Project.PROJECT_TYPE_INVENTORY:
                                InventoriesModel.getInstance().deleteByProjectID(project.getId());
                                break;
                        }
                        ProjectsModel.getInstance().delete(project);
                        return project;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Project>() {

                    @Override
                    public void accept(@NonNull Project project) throws Exception {
                        EventBusManager.post(new DataEvent(DataEvent.ALERT_ACTION_DELETE, project));
                        if (null != mView) {
                            mView.onSuccess(true);
                        }
                    }
                });
    }

    /**
     * 添加Project
     *
     * @param projectType
     * @param projectName
     * @param description
     * @param totalMoney
     * @param startTime
     * @param modifyTime
     * @param isEnded
     */
    public void addProject(int projectType, String projectName, String description,
                           double totalMoney, long startTime, long modifyTime, boolean isEnded) {
        //插入数据
        Project project = new Project(null, projectType,
                projectName, description, totalMoney, startTime, modifyTime, isEnded);
        Observable.fromArray(project)
                .map(new Function<Project, Project>() {
                    @Override
                    public Project apply(@NonNull Project project) throws Exception {
                        long id = ProjectsModel.getInstance().insert(project);
                        project.setId(id);
                        return project;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Project>() {
                    @Override
                    public void accept(@NonNull Project project) throws Exception {
                        //通知所有观察者
                        EventBusManager.post(new DataEvent(DataEvent.ALERT_ACTION_INSERT, project));
                        if (null != mView) {
                            mView.onSuccess(true);
                        }
                    }
                });

    }

    /**
     * 修改Project
     *
     * @param project
     */
    public void alertProject(Project project) {
        Observable.fromArray(project)
                .map(new Function<Project, Project>() {
                    @Override
                    public Project apply(@NonNull Project project) throws Exception {
                        ProjectsModel.getInstance().alert(project);
                        return project;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Project>() {
                    @Override
                    public void accept(@NonNull Project project) throws Exception {
                        //通知所有观察者
                        EventBusManager.post(new DataEvent(DataEvent.ALERT_ACTION_ALERT, project));
                        if (null != mView) {
                            mView.onSuccess(true);
                        }
                    }
                });
    }


    /**
     * 获取列表页数据
     *
     * @param start 起始位置
     * @param end   结束位置
     */
    public void loadProjectList(int start, int end) {
        List<Project> list = ProjectsModel.getInstance().query(end);
        if (null != mView) {
            mView.onSuccess(list);
        }
    }
}
