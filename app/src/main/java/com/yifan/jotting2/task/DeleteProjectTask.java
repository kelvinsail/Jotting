package com.yifan.jotting2.task;

import com.yifan.utils.base.BaseAsyncTask;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.utils.database.datahalper.InventoriesDataHelper;
import com.yifan.jotting2.utils.database.datahalper.ProjectsDataHelp;

/**
 * 删除项目 异步任务
 *
 * Created by yifan on 2016/10/31.
 */
public class DeleteProjectTask extends BaseAsyncTask<Project, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Project... projects) {
        //判断项目ID
        if (null != projects) {
            for (Project project : projects) {
                switch (project.getProjectType()) {
                    case Project.PROJECT_TYPE_NORMAL:
                        //TODO 删除普通项目成员、活动记录数据
                        break;
                    case Project.PROJECT_TYPE_DAYBOOK:
                        //TODO 删除流水账数据
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
