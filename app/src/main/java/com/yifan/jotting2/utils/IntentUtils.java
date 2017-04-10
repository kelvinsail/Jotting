package com.yifan.jotting2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yifan.jotting2.MainActivity;
import com.yifan.jotting2.pojo.Project;
import com.yifan.jotting2.ui.inventory.InventoryActivity;
import com.yifan.jotting2.ui.normal.ActionsActivity;
import com.yifan.jotting2.ui.normal.CompanionActivity;
import com.yifan.jotting2.ui.normal.EditActionActivity;

/**
 * Intent统一管理工具
 *
 * Created by yifan on 2016/7/16.
 */
public class IntentUtils {

    /**
     * 返回首页
     *
     * @param context
     */
    public static void getMainPagerIntent(Context context) {
        context.startActivity(getMainPagerIntent(context,
                MainActivity.INTENT_MAIN_ACTION_PROJECTS));
    }

    /**
     * 离开App
     *
     * @param context
     */
    public static void exitApplication(Context context) {
        context.startActivity(getMainPagerIntent(context,
                MainActivity.INTENT_MAIN_ACTION_EXIT));
    }

    /**
     * 获取主界面Intent
     *
     * @param context
     * @param actionID
     * @return
     */
    public static Intent getMainPagerIntent(Context context, int actionID) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.INTENT_MAIN_ACTION, actionID);
        return intent;
    }

    /**
     * 根据项目数据打开相应的界面
     *
     * @param context 上下文环境
     * @param project 项目数据
     */
    public static void openProject(Context context, Project project) {
        Intent intent = new Intent();
        intent.putExtra(Constans.BUNDLE_KEY_PROJECT, project);
        switch (project.getProjectType()) {
            case Project.PROJECT_TYPE_NORMAL:
                intent.setClass(context, ActionsActivity.class);
                context.startActivity(intent);
                break;
            case Project.PROJECT_TYPE_INVENTORY:
                intent.setClass(context, InventoryActivity.class);
                context.startActivity(intent);
                break;
            case Project.PROJECT_TYPE_DAYBOOK:
                break;
        }
    }

    /**
     * 打开普通项目活动编辑界面
     *
     * @param context 上下文环境
     * @param project 项目数据
     */
    public static void openActionEditPage(Context context, Project project) {
        Intent intent = new Intent();
        intent.setClass(context, EditActionActivity.class);
        intent.putExtra(Constans.BUNDLE_KEY_PROJECT, project);
//                intent.putExtra(Constans.BUNDLE_KEY_ACTION, null);
        context.startActivity(intent);
    }

    /**
     * 打开项目成员编辑界面，并接瘦返回数据
     *
     * @param activity 接收返回数据的{@link Activity} 对象实例
     * @param project  项目数据
     */
    public static void openProjectCompanionsPage(Activity activity, Project project) {
        Intent intent = new Intent(activity, CompanionActivity.class);
        intent.putExtra(Constans.BUNDLE_KEY_PROJECT, project);
        activity.startActivityForResult(intent, ActionsActivity.REQUEST_CODE_COMPANIONS);
    }
}
