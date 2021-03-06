package com.yifan.jotting2.utils.database.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yifan.jotting2.pojo.Companion;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COMPANION".
*/
public class CompanionDao extends AbstractDao<Companion, Long> {

    public static final String TABLENAME = "COMPANION";

    /**
     * Properties of entity Companion.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Phone = new Property(2, String.class, "phone", false, "PHONE");
        public final static Property RechangeMoney = new Property(3, double.class, "rechangeMoney", false, "RECHANGE_MONEY");
        public final static Property IsDefaultPayer = new Property(4, boolean.class, "isDefaultPayer", false, "IS_DEFAULT_PAYER");
        public final static Property ProjectID = new Property(5, Long.class, "projectID", false, "PROJECT_ID");
    }


    public CompanionDao(DaoConfig config) {
        super(config);
    }
    
    public CompanionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COMPANION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"PHONE\" TEXT," + // 2: phone
                "\"RECHANGE_MONEY\" REAL NOT NULL ," + // 3: rechangeMoney
                "\"IS_DEFAULT_PAYER\" INTEGER NOT NULL ," + // 4: isDefaultPayer
                "\"PROJECT_ID\" INTEGER);"); // 5: projectID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COMPANION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Companion entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
        stmt.bindDouble(4, entity.getRechangeMoney());
        stmt.bindLong(5, entity.getIsDefaultPayer() ? 1L: 0L);
 
        Long projectID = entity.getProjectID();
        if (projectID != null) {
            stmt.bindLong(6, projectID);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Companion entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
        stmt.bindDouble(4, entity.getRechangeMoney());
        stmt.bindLong(5, entity.getIsDefaultPayer() ? 1L: 0L);
 
        Long projectID = entity.getProjectID();
        if (projectID != null) {
            stmt.bindLong(6, projectID);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Companion readEntity(Cursor cursor, int offset) {
        Companion entity = new Companion( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // phone
            cursor.getDouble(offset + 3), // rechangeMoney
            cursor.getShort(offset + 4) != 0, // isDefaultPayer
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // projectID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Companion entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRechangeMoney(cursor.getDouble(offset + 3));
        entity.setIsDefaultPayer(cursor.getShort(offset + 4) != 0);
        entity.setProjectID(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Companion entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Companion entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Companion entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
