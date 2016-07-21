package com.yifan.jotting2.utils.database;

import com.yifan.jotting2.pojo.Projects;
import com.yifan.jotting2.utils.database.gen.ProjectsDao;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by yifan on 2016/7/20.
 */
public class ProjectsDataHelp implements DataHelpObservable {

    ProjectsObservable mObservable = new ProjectsObservable();

    private ProjectsDataHelp() {

    }

    private static class ProjectHelp {

        public static ProjectsDataHelp mInstance = new ProjectsDataHelp();

    }

    public static ProjectsDataHelp getInstance() {
        return ProjectHelp.mInstance;
    }

    @Override
    public void regesiterDataObserver(Observer observer) {
        mObservable.addObserver(observer);
    }

    @Override
    public void unregesiterDataObservers() {
        mObservable.deleteObservers();
    }

    @Override
    public void unregesiterDataObserver(Observer observer) {
        mObservable.deleteObserver(observer);
    }

    @Override
    public void notifyDataChanged() {
        mObservable.notifyObservers();
    }

    @Override
    public void notifyDataChanged(Object object) {
        mObservable.notifyObservers(object);
    }

    /**
     * 获取所有项目数据
     *
     * @param count
     * @return
     */
    public List<Projects> getProject(long count) {
        return DataBaseManager.getInstance().getProjectsDao()
                .queryBuilder().where(ProjectsDao.Properties.Id.notEq(count))
                .orderAsc(ProjectsDao.Properties.Id)
                .build().list();
    }

    /**
     * 插入新的项目
     *
     * @param projectType
     * @param projectName
     * @param description
     * @param totalMoney
     * @param startTime
     * @param modifyTime
     * @param isEnded
     */
    public void insertNewProject(int projectType, String projectName, String description,
                                 double totalMoney, long startTime, long modifyTime, boolean isEnded) {
        //插入数据
        Projects projects = new Projects(null, projectType,
                projectName, description, totalMoney, startTime, modifyTime, isEnded);
        DataBaseManager.getInstance().getProjectsDao().insert(projects);
        //通知所有观察者
        notifyDataChanged(projects);
    }

    /**
     * 批量插入新项目
     *
     * @param projects
     */
    public void insertNewProjects(Projects... projects) {
        for (Projects pj : projects) {
            DataBaseManager.getInstance().getProjectsDao().insert(pj);
        }
        notifyDataChanged(projects);
    }

    /**
     * 观察数据者实例
     */
    public class ProjectsObservable extends Observable {

        @Override
        public void notifyObservers() {
            setChanged();
            super.notifyObservers();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
        }
    }
}
