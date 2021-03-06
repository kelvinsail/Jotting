package com.yifan.jotting2.model;

import com.yifan.jotting2.pojo.Companion;
import com.yifan.jotting2.utils.database.DataBaseManager;
import com.yifan.jotting2.utils.database.datahalper.DataHelper;
import com.yifan.jotting2.utils.database.gen.CompanionDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 普通项目同伴数据库 数据管理工具
 *
 * Created by yifan on 2016/11/6.
 */
public class CompanionModel extends DataHelper<Companion> {

    private static class CompanionHelper {

        public static CompanionModel mInstance = new CompanionModel();

    }

    public static CompanionModel getInstance() {
        return CompanionHelper.mInstance;
    }

    public CompanionModel() {
        super();
    }

    @Override
    public long insert(Companion companion) {
        long id = 0;
        if (null != companion) {
            id = getDao().insert(companion);
            companion.setId(id);
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, companion));
        }
        return id;
    }

    @Override
    public long[] insert(Companion... companions) {
        long[] ids = null;
        if (null != companions && companions.length > 0) {
            ids = new long[companions.length];
            for (int i = 0; i < companions.length; i++) {
                Companion companion = companions[i];
                ids[i] = getDao().insert(companion);
                companion.setId(ids[i]);
            }
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_INSERT, companions));
        }
        return ids;
    }

    @Override
    public void delete(Companion companion) {
        if (null != companion) {
            getDao().delete(companion);
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_DELETE, companion));
        }
    }

    @Override
    public void alert(Companion companion) {
        if (null != companion) {
            getDao().update(companion);
//            notifyDataChanged(new DataEvent(DataEvent.ALERT_ACTION_ALERT, companion));
        }
    }

    @Override
    public List<Companion> query(int count, String... values) {
        QueryBuilder builder = getDao().queryBuilder();
        if (count > 0) {
            builder.where(CompanionDao.Properties.Id.notEq(count), CompanionDao.Properties.ProjectID.eq(values[0]));
        } else {
            builder.where(CompanionDao.Properties.ProjectID.eq(values[0]));
        }
        return builder.orderAsc(CompanionDao.Properties.Id).build().list();
    }

    @Override
    public AbstractDao getDao() {
        return DataBaseManager.getInstance().getCompanionDao();
    }


    /**
     * 根据项目id查询所有同伴数据
     *
     * @param projectID 项目id
     * @return
     */
    public List<Companion> getAllCompanionForProject(long projectID) {
        return query(Integer.MAX_VALUE, String.valueOf(projectID));
    }
}
