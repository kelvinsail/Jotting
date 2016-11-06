package com.yifan.jotting2.utils.database.datahalper;

import com.yifan.jotting2.pojo.Action;
import com.yifan.jotting2.pojo.DataEvent;
import com.yifan.jotting2.utils.database.DataBaseManager;
import com.yifan.jotting2.utils.database.datahalper.impl.DataHelpObservable;
import com.yifan.jotting2.utils.database.datahalper.impl.DataHelper;
import com.yifan.jotting2.utils.database.gen.ActionDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 活动数据管理工具
 *
 * Created by yifan on 2016/11/6.
 */
public class ActionDataHelper extends DataHelper<Action>{

    private static class ActionHelp {

        public static ActionDataHelper mInstance = new ActionDataHelper();

    }

    public static ActionDataHelper getInstance() {
        return ActionHelp.mInstance;
    }

    private ActionDataHelper() {
        super();
    }


    @Override
    public long insert(Action action) {
        long id = 0;
        if (null != action) {
            id = getDao().insert(action);
            action.setId(id);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, action));
        }
        return id;
    }

    @Override
    public long[] insert(Action... actions) {
        long[] ids = null;
        if (null != actions && actions.length > 0) {
            ids = new long[actions.length];
            for (int i = 0; i < actions.length; i++) {
                Action action = actions[i];
                ids[i] = getDao().insert(action);
                action.setId(ids[i]);
            }
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, actions));
        }
        return ids;
    }

    @Override
    public void delete(Action action) {
        if (null != action) {
            getDao().delete(action);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_DELETE, action));
        }
    }

    @Override
    public void alert(Action action) {
        if (null != action) {
            getDao().update(action);
            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_ALERT, action));
        }
    }

    @Override
    public List<Action> query(int count, String... values) {
        QueryBuilder builder = getDao().queryBuilder();
        if (count > 0) {
            builder.where(ActionDao.Properties.Id.notEq(count), ActionDao.Properties.ProjectID.eq(values[0]));
        } else {
            builder.where(ActionDao.Properties.ProjectID.eq(values[0]));
        }
        return builder.orderAsc(ActionDao.Properties.Id).build().list();
    }

    @Override
    public AbstractDao getDao() {
        return DataBaseManager.getInstance().getActionDao();
    }
}
