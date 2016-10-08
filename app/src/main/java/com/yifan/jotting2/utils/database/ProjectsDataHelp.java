package com.yifan.jotting2.utils.database;

import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.database.gen.ProjectDao;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 项目数据管理工具
 *
 * Created by yifan on 2016/7/20.
 */
public class ProjectsDataHelp extends DataHelper<Project> implements DataHelpObservable {

    private static class ProjectHelp {

        public static ProjectsDataHelp mInstance = new ProjectsDataHelp();

    }

    public static ProjectsDataHelp getInstance() {
        return ProjectHelp.mInstance;
    }

    private ProjectsDataHelp() {
        super();
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
    public void insertNewData(int projectType, String projectName, String description,
                              double totalMoney, long startTime, long modifyTime, boolean isEnded) {
        //插入数据
        Project project = new Project(null, projectType,
                projectName, description, totalMoney, startTime, modifyTime, isEnded);
        insert(project);
    }

    @Override
    public void insert(Project project) {
        getDao().insert(project);
        //通知所有观察者
        notifyDataChanged(project);
    }

    @Override
    public void insert(Project... projects) {
        for (Project project : projects) {
            getDao().insert(project);
        }
        notifyDataChanged(projects);
    }

    @Override
    public void delete(Project project) {

    }

    @Override
    public void alert(Project project) {

    }

    @Override
    public List<Project> query(int count, String... value) {
        return getDao()
                .queryBuilder().where(ProjectDao.Properties.Id.notEq(count))
                .orderAsc(ProjectDao.Properties.Id)
                .build().list();
    }

    @Override
    public ProjectDao getDao() {
        return DataBaseManager.getInstance().getProjectsDao();
    }
}
