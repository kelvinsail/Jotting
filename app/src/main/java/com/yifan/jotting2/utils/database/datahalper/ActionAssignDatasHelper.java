package com.yifan.jotting2.utils.database.datahalper;

import com.yifan.jotting2.pojo.ActionAssigns;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.utils.database.DataBaseManager;
import com.yifan.jotting2.utils.database.datahalper.impl.DataHelper;
import com.yifan.jotting2.utils.database.gen.ActionAssignsDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 活动支出分配记录数据管理工具
 *
 * Created by yifan on 2016/11/6.
 */
public class ActionAssignDatasHelper extends DataHelper<ActionAssigns> {


    private static class ActionAssignsHelper {

        public static ActionAssignDatasHelper mInstance = new ActionAssignDatasHelper();

    }

    public static ActionAssignDatasHelper getInstance() {
        return ActionAssignsHelper.mInstance;
    }


    public ActionAssignDatasHelper() {
        super();
    }

    @Override
    public long insert(ActionAssigns actionAssigns) {
        long id = 0;
        if (null != actionAssigns) {
            id = getDao().insert(actionAssigns);
            actionAssigns.setId(id);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, actionAssigns));
        }
        return id;
    }

    @Override
    public long[] insert(ActionAssigns... actionAssignses) {
        long[] ids = null;
        if (null != actionAssignses && actionAssignses.length > 0) {
            for (int i = 0; i < actionAssignses.length; i++) {
                ActionAssigns actionAssigns = actionAssignses[i];
                ids[i] = getDao().insert(actionAssigns);
                actionAssigns.setId(ids[i]);
            }
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, actionAssignses));
        }
        return new long[0];
    }

    @Override
    public void delete(ActionAssigns actionAssigns) {
        if (null != actionAssigns) {
            getDao().delete(actionAssigns);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_DELETE, actionAssigns));
        }
    }

    @Override
    public void alert(ActionAssigns actionAssigns) {
        if (null != actionAssigns) {
            getDao().update(actionAssigns);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_ALERT, actionAssigns));
        }
    }

    @Override
    public List<ActionAssigns> query(int count, String... values) {
        QueryBuilder builder = getDao().queryBuilder();
        if (count > 0) {
            builder.where(ActionAssignsDao.Properties.Id.notEq(count), ActionAssignsDao.Properties.ActionID.eq(values[0]));
        } else {
            builder.where(ActionAssignsDao.Properties.ActionID.eq(values[0]));
        }
        return builder.orderAsc(ActionAssignsDao.Properties.Id).build().list();
    }

    @Override
    public AbstractDao getDao() {
        return DataBaseManager.getInstance().getActionAssignsDao();
    }
}
