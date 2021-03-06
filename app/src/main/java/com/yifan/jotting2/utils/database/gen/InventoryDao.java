package com.yifan.jotting2.utils.database.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yifan.jotting2.pojo.Inventory;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "INVENTORY".
*/
public class InventoryDao extends AbstractDao<Inventory, Long> {

    public static final String TABLENAME = "INVENTORY";

    /**
     * Properties of entity Inventory.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Description = new Property(2, String.class, "description", false, "DESCRIPTION");
        public final static Property Date = new Property(3, long.class, "date", false, "DATE");
        public final static Property Money = new Property(4, double.class, "money", false, "MONEY");
        public final static Property Count = new Property(5, int.class, "count", false, "COUNT");
        public final static Property IsChecked = new Property(6, boolean.class, "isChecked", false, "IS_CHECKED");
        public final static Property LabelColor = new Property(7, int.class, "labelColor", false, "LABEL_COLOR");
        public final static Property ProjectID = new Property(8, long.class, "projectID", false, "PROJECT_ID");
    }


    public InventoryDao(DaoConfig config) {
        super(config);
    }
    
    public InventoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INVENTORY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"DESCRIPTION\" TEXT," + // 2: description
                "\"DATE\" INTEGER NOT NULL ," + // 3: date
                "\"MONEY\" REAL NOT NULL ," + // 4: money
                "\"COUNT\" INTEGER NOT NULL ," + // 5: count
                "\"IS_CHECKED\" INTEGER NOT NULL ," + // 6: isChecked
                "\"LABEL_COLOR\" INTEGER NOT NULL ," + // 7: labelColor
                "\"PROJECT_ID\" INTEGER NOT NULL );"); // 8: projectID
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INVENTORY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Inventory entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(3, description);
        }
        stmt.bindLong(4, entity.getDate());
        stmt.bindDouble(5, entity.getMoney());
        stmt.bindLong(6, entity.getCount());
        stmt.bindLong(7, entity.getIsChecked() ? 1L: 0L);
        stmt.bindLong(8, entity.getLabelColor());
        stmt.bindLong(9, entity.getProjectID());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Inventory entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(3, description);
        }
        stmt.bindLong(4, entity.getDate());
        stmt.bindDouble(5, entity.getMoney());
        stmt.bindLong(6, entity.getCount());
        stmt.bindLong(7, entity.getIsChecked() ? 1L: 0L);
        stmt.bindLong(8, entity.getLabelColor());
        stmt.bindLong(9, entity.getProjectID());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Inventory readEntity(Cursor cursor, int offset) {
        Inventory entity = new Inventory( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // description
            cursor.getLong(offset + 3), // date
            cursor.getDouble(offset + 4), // money
            cursor.getInt(offset + 5), // count
            cursor.getShort(offset + 6) != 0, // isChecked
            cursor.getInt(offset + 7), // labelColor
            cursor.getLong(offset + 8) // projectID
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Inventory entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDescription(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDate(cursor.getLong(offset + 3));
        entity.setMoney(cursor.getDouble(offset + 4));
        entity.setCount(cursor.getInt(offset + 5));
        entity.setIsChecked(cursor.getShort(offset + 6) != 0);
        entity.setLabelColor(cursor.getInt(offset + 7));
        entity.setProjectID(cursor.getLong(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Inventory entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Inventory entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Inventory entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
