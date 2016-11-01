package com.yifan.jotting2.utils.database;

import com.yifan.jotting2.JottingApplication;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.database.gen.ProjectDao;

import java.util.List;

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
        if (null != project) {
            project.setId(getDao().insert(project));
            //通知所有观察者
            notifyDataChanged(project);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, project));
        }
    }

    @Override
    public void insert(Project... projects) {
        if (null != projects && projects.length > 0) {
            for (Project project : projects) {
                project.setId(getDao().insert(project));
            }
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, projects));
        }
    }

    @Override
    public void delete(Project project) {
        if (null != project) {
            getDao().delete(project);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_DELETE, project));
        }
    }

    @Override
    public void alert(Project project) {
        if (null != project) {
            getDao().update(project);
            notifyDataChanged(project);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_ALERT, project));
        }
    }

    @Override
    public List<Project> query(int count, String... values) {
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
