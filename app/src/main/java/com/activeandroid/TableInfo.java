package com.activeandroid;

import android.text.TextUtils;
import android.util.Log;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes11.dex */
public final class TableInfo {
    private Map<Field, String> mColumnNames = new LinkedHashMap();
    private String mIdName;
    private String mTableName;
    private Class<? extends Model> mType;

    public TableInfo(Class<? extends Model> cls) {
        this.mIdName = Table.DEFAULT_ID_NAME;
        this.mType = cls;
        Table table = (Table) cls.getAnnotation(Table.class);
        if (table != null) {
            this.mTableName = table.name();
            this.mIdName = table.id();
        } else {
            this.mTableName = cls.getSimpleName();
        }
        this.mColumnNames.put(getIdField(cls), this.mIdName);
        LinkedList<Field> linkedList = new LinkedList(ReflectionUtils.getDeclaredColumnFields(cls));
        Collections.reverse(linkedList);
        for (Field field : linkedList) {
            if (field.isAnnotationPresent(Column.class)) {
                String name = ((Column) field.getAnnotation(Column.class)).name();
                this.mColumnNames.put(field, TextUtils.isEmpty(name) ? field.getName() : name);
            }
        }
    }

    public Class<? extends Model> getType() {
        return this.mType;
    }

    public String getTableName() {
        return this.mTableName;
    }

    public String getIdName() {
        return this.mIdName;
    }

    public Collection<Field> getFields() {
        return this.mColumnNames.keySet();
    }

    public String getColumnName(Field field) {
        return this.mColumnNames.get(field);
    }

    private Field getIdField(Class<?> cls) {
        if (cls.equals(Model.class)) {
            try {
                return cls.getDeclaredField("mId");
            } catch (NoSuchFieldException e) {
                Log.e("Impossible!", e.toString());
                return null;
            }
        } else if (cls.getSuperclass() != null) {
            return getIdField(cls.getSuperclass());
        } else {
            return null;
        }
    }
}
