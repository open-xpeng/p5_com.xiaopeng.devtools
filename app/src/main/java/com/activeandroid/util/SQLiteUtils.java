package com.activeandroid.util;

import android.database.Cursor;
import android.os.Build;
import android.text.TextUtils;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.annotation.Column;
import com.activeandroid.serializer.TypeSerializer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public final class SQLiteUtils {
    public static final boolean FOREIGN_KEYS_SUPPORTED;
    private static final HashMap<Class<?>, SQLiteType> TYPE_MAP;
    private static HashMap<String, List<String>> sIndexGroupMap;
    private static HashMap<String, Column.ConflictAction> sOnUniqueConflictsMap;
    private static HashMap<String, List<String>> sUniqueGroupMap;

    /* loaded from: classes11.dex */
    public enum SQLiteType {
        INTEGER,
        REAL,
        TEXT,
        BLOB
    }

    static {
        FOREIGN_KEYS_SUPPORTED = Build.VERSION.SDK_INT >= 8;
        TYPE_MAP = new HashMap<Class<?>, SQLiteType>() { // from class: com.activeandroid.util.SQLiteUtils.1
            {
                put(Byte.TYPE, SQLiteType.INTEGER);
                put(Short.TYPE, SQLiteType.INTEGER);
                put(Integer.TYPE, SQLiteType.INTEGER);
                put(Long.TYPE, SQLiteType.INTEGER);
                put(Float.TYPE, SQLiteType.REAL);
                put(Double.TYPE, SQLiteType.REAL);
                put(Boolean.TYPE, SQLiteType.INTEGER);
                put(Character.TYPE, SQLiteType.TEXT);
                put(byte[].class, SQLiteType.BLOB);
                put(Byte.class, SQLiteType.INTEGER);
                put(Short.class, SQLiteType.INTEGER);
                put(Integer.class, SQLiteType.INTEGER);
                put(Long.class, SQLiteType.INTEGER);
                put(Float.class, SQLiteType.REAL);
                put(Double.class, SQLiteType.REAL);
                put(Boolean.class, SQLiteType.INTEGER);
                put(Character.class, SQLiteType.TEXT);
                put(String.class, SQLiteType.TEXT);
                put(Byte[].class, SQLiteType.BLOB);
            }
        };
    }

    public static void execSql(String str) {
        Cache.openDatabase().execSQL(str);
    }

    public static void execSql(String str, Object[] objArr) {
        Cache.openDatabase().execSQL(str, objArr);
    }

    public static <T extends Model> List<T> rawQuery(Class<? extends Model> cls, String str, String[] strArr) {
        Cursor rawQuery = Cache.openDatabase().rawQuery(str, strArr);
        List<T> processCursor = processCursor(cls, rawQuery);
        rawQuery.close();
        return processCursor;
    }

    public static int intQuery(String str, String[] strArr) {
        Cursor rawQuery = Cache.openDatabase().rawQuery(str, strArr);
        int processIntCursor = processIntCursor(rawQuery);
        rawQuery.close();
        return processIntCursor;
    }

    public static <T extends Model> T rawQuerySingle(Class<? extends Model> cls, String str, String[] strArr) {
        List rawQuery = rawQuery(cls, str, strArr);
        if (rawQuery.size() > 0) {
            return (T) rawQuery.get(0);
        }
        return null;
    }

    public static ArrayList<String> createUniqueDefinition(TableInfo tableInfo) {
        ArrayList<String> arrayList = new ArrayList<>();
        sUniqueGroupMap = new HashMap<>();
        sOnUniqueConflictsMap = new HashMap<>();
        for (Field field : tableInfo.getFields()) {
            createUniqueColumnDefinition(tableInfo, field);
        }
        if (sUniqueGroupMap.isEmpty()) {
            return arrayList;
        }
        for (String str : sUniqueGroupMap.keySet()) {
            arrayList.add(String.format("UNIQUE (%s) ON CONFLICT %s", TextUtils.join(", ", sUniqueGroupMap.get(str)), sOnUniqueConflictsMap.get(str).toString()));
        }
        return arrayList;
    }

    public static void createUniqueColumnDefinition(TableInfo tableInfo, Field field) {
        String columnName = tableInfo.getColumnName(field);
        Column column = (Column) field.getAnnotation(Column.class);
        if (field.getName().equals("mId")) {
            return;
        }
        String[] uniqueGroups = column.uniqueGroups();
        Column.ConflictAction[] onUniqueConflicts = column.onUniqueConflicts();
        if (uniqueGroups.length != onUniqueConflicts.length) {
            return;
        }
        for (int i = 0; i < uniqueGroups.length; i++) {
            String str = uniqueGroups[i];
            Column.ConflictAction conflictAction = onUniqueConflicts[i];
            if (!TextUtils.isEmpty(str)) {
                List<String> list = sUniqueGroupMap.get(str);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(columnName);
                sUniqueGroupMap.put(str, list);
                sOnUniqueConflictsMap.put(str, conflictAction);
            }
        }
    }

    public static String[] createIndexDefinition(TableInfo tableInfo) {
        ArrayList arrayList = new ArrayList();
        sIndexGroupMap = new HashMap<>();
        for (Field field : tableInfo.getFields()) {
            createIndexColumnDefinition(tableInfo, field);
        }
        if (sIndexGroupMap.isEmpty()) {
            return new String[0];
        }
        for (Map.Entry<String, List<String>> entry : sIndexGroupMap.entrySet()) {
            arrayList.add(String.format("CREATE INDEX IF NOT EXISTS %s on %s(%s);", "index_" + tableInfo.getTableName() + "_" + entry.getKey(), tableInfo.getTableName(), TextUtils.join(", ", entry.getValue())));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void createIndexColumnDefinition(TableInfo tableInfo, Field field) {
        String[] indexGroups;
        String columnName = tableInfo.getColumnName(field);
        Column column = (Column) field.getAnnotation(Column.class);
        if (field.getName().equals("mId")) {
            return;
        }
        if (column.index()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(columnName);
            sIndexGroupMap.put(columnName, arrayList);
        }
        for (String str : column.indexGroups()) {
            if (!TextUtils.isEmpty(str)) {
                List<String> list = sIndexGroupMap.get(str);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(columnName);
                sIndexGroupMap.put(str, list);
            }
        }
    }

    public static String createTableDefinition(TableInfo tableInfo) {
        ArrayList arrayList = new ArrayList();
        for (Field field : tableInfo.getFields()) {
            String createColumnDefinition = createColumnDefinition(tableInfo, field);
            if (!TextUtils.isEmpty(createColumnDefinition)) {
                arrayList.add(createColumnDefinition);
            }
        }
        arrayList.addAll(createUniqueDefinition(tableInfo));
        return String.format("CREATE TABLE IF NOT EXISTS %s (%s);", tableInfo.getTableName(), TextUtils.join(", ", arrayList));
    }

    public static String createColumnDefinition(TableInfo tableInfo, Field field) {
        StringBuilder sb = new StringBuilder();
        Class<?> type = field.getType();
        String columnName = tableInfo.getColumnName(field);
        TypeSerializer parserForType = Cache.getParserForType(field.getType());
        Column column = (Column) field.getAnnotation(Column.class);
        if (parserForType != null) {
            type = parserForType.getSerializedType();
        }
        if (TYPE_MAP.containsKey(type)) {
            sb.append(columnName);
            sb.append(" ");
            sb.append(TYPE_MAP.get(type).toString());
        } else if (ReflectionUtils.isModel(type)) {
            sb.append(columnName);
            sb.append(" ");
            sb.append(SQLiteType.INTEGER.toString());
        } else if (ReflectionUtils.isSubclassOf(type, Enum.class)) {
            sb.append(columnName);
            sb.append(" ");
            sb.append(SQLiteType.TEXT.toString());
        }
        if (!TextUtils.isEmpty(sb)) {
            if (columnName.equals(tableInfo.getIdName())) {
                sb.append(" PRIMARY KEY AUTOINCREMENT");
            } else if (column != null) {
                if (column.length() > -1) {
                    sb.append("(");
                    sb.append(column.length());
                    sb.append(")");
                }
                if (column.notNull()) {
                    sb.append(" NOT NULL ON CONFLICT ");
                    sb.append(column.onNullConflict().toString());
                }
                if (column.unique()) {
                    sb.append(" UNIQUE ON CONFLICT ");
                    sb.append(column.onUniqueConflict().toString());
                }
            }
            if (FOREIGN_KEYS_SUPPORTED && ReflectionUtils.isModel(type)) {
                sb.append(" REFERENCES ");
                sb.append(Cache.getTableInfo(type).getTableName());
                sb.append("(" + tableInfo.getIdName() + ")");
                sb.append(" ON DELETE ");
                sb.append(column.onDelete().toString().replace("_", " "));
                sb.append(" ON UPDATE ");
                sb.append(column.onUpdate().toString().replace("_", " "));
            }
        } else {
            Log.e("No type mapping for: " + type.toString());
        }
        return sb.toString();
    }

    public static <T extends Model> List<T> processCursor(Class<? extends Model> cls, Cursor cursor) {
        String idName = Cache.getTableInfo(cls).getIdName();
        ArrayList arrayList = new ArrayList();
        try {
            Constructor<? extends Model> constructor = cls.getConstructor(new Class[0]);
            if (cursor.moveToFirst()) {
                ArrayList arrayList2 = new ArrayList(Arrays.asList(cursor.getColumnNames()));
                do {
                    Model entity = Cache.getEntity(cls, cursor.getLong(arrayList2.indexOf(idName)));
                    if (entity == null) {
                        entity = constructor.newInstance(new Object[0]);
                    }
                    entity.loadFromCursor(cursor);
                    arrayList.add(entity);
                } while (cursor.moveToNext());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Your model " + cls.getName() + " does not define a default constructor. The default constructor is required for now in ActiveAndroid models, as the process to populate the ORM model is : 1. instantiate default model 2. populate fields");
        } catch (Exception e2) {
            Log.e("Failed to process cursor.", e2);
        }
        return arrayList;
    }

    private static int processIntCursor(Cursor cursor) {
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public static List<String> lexSqlScript(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder(100);
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == ';' && !z && !z2) {
                arrayList.add(sb.toString());
                sb = new StringBuilder(100);
                z = false;
                z2 = false;
            } else {
                if (charAt == '\'' && !z2) {
                    z = !z;
                }
                z2 = charAt == '\\' && !z2;
                sb.append(charAt);
            }
        }
        if (sb.length() > 0) {
            arrayList.add(sb.toString());
        }
        return arrayList;
    }
}
