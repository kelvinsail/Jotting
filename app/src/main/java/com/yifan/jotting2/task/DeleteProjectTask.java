package com.yifan.jotting2.task;

import com.thinksky.utils.base.BaseAsyncTask;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.database.InventoriesDataHelper;
import com.yifan.jotting2.utils.database.ProjectsDataHelp;

/**
 * 删除项目 异步任务
 *
 * Created by yifan on 2016/10/31.
 */
public class DeleteProjectTask extends BaseAsyncTask<Project, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Project... projects) {
        //判断项目ID
        if (null != projects ) {
            for (Project project:projects){
                switch (project.getProjectType()) {
                    case Project.PROJECT_TYPE_NORMAL:
                        break;
                    case Project.PROJECT_TYPE_DAYBOOK:
                        break;
                    case Project.PROJECT_TYPE_INVENTORY:
                        InventoriesDataHelper.getInstance().deleteByProjectID(project.getId());
                        break;
                }
                ProjectsDataHelp.getInstance().delete(project);
            }
            return true;
        }
        return false;
    }

}
