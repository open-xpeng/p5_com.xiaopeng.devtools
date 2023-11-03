package com.activeandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.activeandroid.content.ContentProvider;
import com.activeandroid.query.Delete;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.activeandroid.serializer.TypeSerializer;
import com.activeandroid.util.Log;
import com.activeandroid.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes11.dex */
public abstract class Model {
    private static final int HASH_PRIME = 739;
    private Long mId = null;
    private final TableInfo mTableInfo = Cache.getTableInfo(getClass());
    private final String idName = this.mTableInfo.getIdName();

    public final Long getId() {
        return this.mId;
    }

    public final void delete() {
        SQLiteDatabase openDatabase = Cache.openDatabase();
        String tableName = this.mTableInfo.getTableName();
        openDatabase.delete(tableName, this.idName + "=?", new String[]{getId().toString()});
        Cache.removeEntity(this);
        Cache.getContext().getContentResolver().notifyChange(ContentProvider.createUri(this.mTableInfo.getType(), this.mId), null);
    }

    public final Long save() {
        TypeSerializer parserForType;
        SQLiteDatabase openDatabase = Cache.openDatabase();
        ContentValues contentValues = new ContentValues();
        for (Field field : this.mTableInfo.getFields()) {
            String columnName = this.mTableInfo.getColumnName(field);
            Class<?> type = field.getType();
            field.setAccessible(true);
            try {
                Object obj = field.get(this);
                if (obj != null && (parserForType = Cache.getParserForType(type)) != null && (obj = parserForType.serialize(obj)) != null) {
                    type = obj.getClass();
                    if (!type.equals(parserForType.getSerializedType())) {
                        Log.w(String.format("TypeSerializer returned wrong type: expected a %s but got a %s", parserForType.getSerializedType(), type));
                    }
                }
                if (obj == null) {
                    contentValues.putNull(columnName);
                } else {
                    if (!type.equals(Byte.class) && !type.equals(Byte.TYPE)) {
                        if (!type.equals(Short.class) && !type.equals(Short.TYPE)) {
                            if (!type.equals(Integer.class) && !type.equals(Integer.TYPE)) {
                                if (!type.equals(Long.class) && !type.equals(Long.TYPE)) {
                                    if (!type.equals(Float.class) && !type.equals(Float.TYPE)) {
                                        if (!type.equals(Double.class) && !type.equals(Double.TYPE)) {
                                            if (!type.equals(Boolean.class) && !type.equals(Boolean.TYPE)) {
                                                if (!type.equals(Character.class) && !type.equals(Character.TYPE)) {
                                                    if (type.equals(String.class)) {
                                                        contentValues.put(columnName, obj.toString());
                                                    } else {
                                                        if (!type.equals(Byte[].class) && !type.equals(byte[].class)) {
                                                            if (ReflectionUtils.isModel(type)) {
                                                                contentValues.put(columnName, ((Model) obj).getId());
                                                            } else if (ReflectionUtils.isSubclassOf(type, Enum.class)) {
                                                                contentValues.put(columnName, ((Enum) obj).name());
                                                            }
                                                        }
                                                        contentValues.put(columnName, (byte[]) obj);
                                                    }
                                                }
                                                contentValues.put(columnName, obj.toString());
                                            }
                                            contentValues.put(columnName, (Boolean) obj);
                                        }
                                        contentValues.put(columnName, (Double) obj);
                                    }
                                    contentValues.put(columnName, (Float) obj);
                                }
                                contentValues.put(columnName, (Long) obj);
                            }
                            contentValues.put(columnName, (Integer) obj);
                        }
                        contentValues.put(columnName, (Short) obj);
                    }
                    contentValues.put(columnName, (Byte) obj);
                }
            } catch (IllegalAccessException e) {
                Log.e(e.getClass().getName(), e);
            } catch (IllegalArgumentException e2) {
                Log.e(e2.getClass().getName(), e2);
            }
        }
        if (this.mId == null) {
            this.mId = Long.valueOf(openDatabase.insert(this.mTableInfo.getTableName(), null, contentValues));
        } else {
            String tableName = this.mTableInfo.getTableName();
            openDatabase.update(tableName, contentValues, this.idName + "=" + this.mId, null);
        }
        Cache.getContext().getContentResolver().notifyChange(ContentProvider.createUri(this.mTableInfo.getType(), this.mId), null);
        return this.mId;
    }

    public static void delete(Class<? extends Model> cls, long j) {
        TableInfo tableInfo = Cache.getTableInfo(cls);
        From from = new Delete().from(cls);
        from.where(tableInfo.getIdName() + "=?", Long.valueOf(j)).execute();
    }

    public static <T extends Model> T load(Class<T> cls, long j) {
        TableInfo tableInfo = Cache.getTableInfo(cls);
        From from = new Select().from(cls);
        return (T) from.where(tableInfo.getIdName() + "=?", Long.valueOf(j)).executeSingle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void loadFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList(Arrays.asList(cursor.getColumnNames()));
        for (Field field : this.mTableInfo.getFields()) {
            String columnName = this.mTableInfo.getColumnName(field);
            Class<?> type = field.getType();
            int indexOf = arrayList.indexOf(columnName);
            if (indexOf >= 0) {
                boolean z = true;
                field.setAccessible(true);
                try {
                    boolean isNull = cursor.isNull(indexOf);
                    TypeSerializer parserForType = Cache.getParserForType(type);
                    if (parserForType != null) {
                        type = parserForType.getSerializedType();
                    }
                    Object obj = null;
                    if (!isNull) {
                        if (!type.equals(Byte.class) && !type.equals(Byte.TYPE)) {
                            if (!type.equals(Short.class) && !type.equals(Short.TYPE)) {
                                if (!type.equals(Integer.class) && !type.equals(Integer.TYPE)) {
                                    if (!type.equals(Long.class) && !type.equals(Long.TYPE)) {
                                        if (!type.equals(Float.class) && !type.equals(Float.TYPE)) {
                                            if (!type.equals(Double.class) && !type.equals(Double.TYPE)) {
                                                if (!type.equals(Boolean.class) && !type.equals(Boolean.TYPE)) {
                                                    if (!type.equals(Character.class) && !type.equals(Character.TYPE)) {
                                                        if (type.equals(String.class)) {
                                                            obj = cursor.getString(indexOf);
                                                        } else {
                                                            if (!type.equals(Byte[].class) && !type.equals(byte[].class)) {
                                                                if (ReflectionUtils.isModel(type)) {
                                                                    long j = cursor.getLong(indexOf);
                                                                    Model entity = Cache.getEntity(type, j);
                                                                    if (entity == null) {
                                                                        entity = new Select().from(type).where(this.idName + "=?", Long.valueOf(j)).executeSingle();
                                                                    }
                                                                    obj = entity;
                                                                } else if (ReflectionUtils.isSubclassOf(type, Enum.class)) {
                                                                    obj = Enum.valueOf(type, cursor.getString(indexOf));
                                                                }
                                                            }
                                                            obj = cursor.getBlob(indexOf);
                                                        }
                                                    }
                                                    obj = Character.valueOf(cursor.getString(indexOf).charAt(0));
                                                }
                                                if (cursor.getInt(indexOf) == 0) {
                                                    z = false;
                                                }
                                                obj = Boolean.valueOf(z);
                                            }
                                            obj = Double.valueOf(cursor.getDouble(indexOf));
                                        }
                                        obj = Float.valueOf(cursor.getFloat(indexOf));
                                    }
                                    obj = Long.valueOf(cursor.getLong(indexOf));
                                }
                                obj = Integer.valueOf(cursor.getInt(indexOf));
                            }
                            obj = Integer.valueOf(cursor.getInt(indexOf));
                        }
                        obj = Integer.valueOf(cursor.getInt(indexOf));
                    } else {
                        field = null;
                    }
                    if (parserForType != null && !isNull) {
                        obj = parserForType.deserialize(obj);
                    }
                    if (obj != null) {
                        field.set(this, obj);
                    }
                } catch (IllegalAccessException e) {
                    Log.e(e.getClass().getName(), e);
                } catch (IllegalArgumentException e2) {
                    Log.e(e2.getClass().getName(), e2);
                } catch (SecurityException e3) {
                    Log.e(e3.getClass().getName(), e3);
                }
            }
        }
        if (this.mId != null) {
            Cache.addEntity(this);
        }
    }

    protected final <T extends Model> List<T> getMany(Class<T> cls, String str) {
        From from = new Select().from(cls);
        return from.where(Cache.getTableName(cls) + "." + str + "=?", getId()).execute();
    }

    public String toString() {
        return this.mTableInfo.getTableName() + "@" + getId();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Model) || this.mId == null) {
            return this == obj;
        }
        Model model = (Model) obj;
        return this.mId.equals(model.mId) && this.mTableInfo.getTableName().equals(model.mTableInfo.getTableName());
    }

    public int hashCode() {
        return ((this.mId == null ? super.hashCode() : this.mId.hashCode()) * HASH_PRIME) + HASH_PRIME + (HASH_PRIME * this.mTableInfo.getTableName().hashCode());
    }
}
