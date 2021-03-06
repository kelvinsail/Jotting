package com.yifan.jotting2.utils.database.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yifan.jotting2.pojo.Action;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACTION".
*/
public class ActionDao extends AbstractDao<Action, Long> {

    public static final String TABLENAME = "ACTION";

    /**
     * Properties of entity Action.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ProjectID = new Property(1, Long.class, "projectID", false, "PROJECT_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Description = new Property(3, String.class, "description", false, "DESCRIPTION");
        public final static Property Date = new Property(4, long.class, "date", false, "DATE");
        public final static Property TotalMoney = new Property(5, double.class, "totalMoney", false, "TOTAL_MONEY");
        public final static Property PayerID = new Property(6, Long.class, "payerID", false, "PAYER_ID");
    }


    public ActionDao(DaoConfig config) {
        super(config);
    }
    
    public ActionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACTION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PROJECT_ID\" INTEGER," + // 1: projectID
                "\"NAME\" TEXT," + // 2: name
                "\"DESCRIPTION\" TEXT," + // 3: description
                "\"DATE\" INTEGER NOT NULL ," + // 4: date
                "\"TOTAL_MONEY\" REAL NOT NULL ," + // 5: totalMoney
                "\"PAYER_ID\" INTEGER);"); // 6: payerID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACTION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Action entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long projectID = entity.getProjectID();
        if (projectID != null) {
            stmt.bindLong(2, projectID);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
        stmt.bindLong(5, entity.getDate());
        stmt.bindDouble(6, entity.getTotalMoney());
 
        Long payerID = entity.getPayerID();
        if (payerID != null) {
            stmt.bindLong(7, payerID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Action entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long projectID = entity.getProjectID();
        if (projectID != null) {
            stmt.bindLong(2, projectID);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
        stmt.bindLong(5, entity.getDate());
        stmt.bindDouble(6, entity.getTotalMoney());
 
        Long payerID = entity.getPayerID();
        if (payerID != null) {
            stmt.bindLong(7, payerID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Action readEntity(Cursor cursor, int offset) {
        Action entity = new Action( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // projectID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // description
            cursor.getLong(offset + 4), // date
            cursor.getDouble(offset + 5), // totalMoney
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // payerID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Action entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProjectID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDescription(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDate(cursor.getLong(offset + 4));
        entity.setTotalMoney(cursor.getDouble(offset + 5));
        entity.setPayerID(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Action entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Action entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Action entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
