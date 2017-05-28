package com.yifan.jotting2.model;

import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.database.DataBaseManager;
import com.yifan.jotting2.utils.database.datahalper.DataHelper;
import com.yifan.jotting2.utils.database.gen.ProjectDao;

import java.util.List;

/**
 * 项目数据管理工具
 *
 * Created by yifan on 2016/7/20.
 */
public class ProjectsModel extends DataHelper<Project> {

    private static class ProjectHelp {

        public static ProjectsModel mInstance = new ProjectsModel();

    }

    public static ProjectsModel getInstance() {
        return ProjectHelp.mInstance;
    }

    private ProjectsModel() {
        super();
    }

    @Override
    public long insert(Project project) {
        long id = 0;
        if (null != project) {
            id = getDao().insert(project);
            project.setId(id);
        }
        return id;
    }

    @Override
    public long[] insert(Project... projects) {
        long[] ids = null;
        if (null != projects && projects.length > 0) {
            ids = new long[projects.length];
            for (int i = 0; i < projects.length; i++) {
                Project project = projects[i];
                ids[i] = getDao().insert(project);
                project.setId(ids[i]);
            }
        }
        return ids;
    }

    @Override
    public void delete(Project project) {
        if (null != project) {
            getDao().delete(project);
        }
    }

    @Override
    public void alert(Project project) {
        if (null != project) {
            getDao().update(project);
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
