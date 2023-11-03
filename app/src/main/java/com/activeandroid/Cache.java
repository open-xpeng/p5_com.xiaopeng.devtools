package com.activeandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LruCache;
import com.activeandroid.serializer.TypeSerializer;
import com.activeandroid.util.Log;
import java.util.Collection;

/* loaded from: classes11.dex */
public final class Cache {
    public static final int DEFAULT_CACHE_SIZE = 1024;
    private static Context sContext;
    private static DatabaseHelper sDatabaseHelper;
    private static LruCache<String, Model> sEntities;
    private static boolean sIsInitialized = false;
    private static ModelInfo sModelInfo;

    private Cache() {
    }

    public static synchronized void initialize(Configuration configuration) {
        synchronized (Cache.class) {
            if (sIsInitialized) {
                Log.v("ActiveAndroid already initialized.");
                return;
            }
            sContext = configuration.getContext();
            sModelInfo = new ModelInfo(configuration);
            sDatabaseHelper = new DatabaseHelper(configuration);
            sEntities = new LruCache<>(configuration.getCacheSize());
            openDatabase();
            sIsInitialized = true;
            Log.v("ActiveAndroid initialized successfully.");
        }
    }

    public static synchronized void clear() {
        synchronized (Cache.class) {
            sEntities.evictAll();
            Log.v("Cache cleared.");
        }
    }

    public static synchronized void dispose() {
        synchronized (Cache.class) {
            closeDatabase();
            sEntities = null;
            sModelInfo = null;
            sDatabaseHelper = null;
            sIsInitialized = false;
            Log.v("ActiveAndroid disposed. Call initialize to use library.");
        }
    }

    public static boolean isInitialized() {
        return sIsInitialized;
    }

    public static synchronized SQLiteDatabase openDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (Cache.class) {
            writableDatabase = sDatabaseHelper.getWritableDatabase();
        }
        return writableDatabase;
    }

    public static synchronized void closeDatabase() {
        synchronized (Cache.class) {
            sDatabaseHelper.close();
        }
    }

    public static Context getContext() {
        return sContext;
    }

    public static String getIdentifier(Class<? extends Model> cls, Long l) {
        return getTableName(cls) + "@" + l;
    }

    public static String getIdentifier(Model model) {
        return getIdentifier(model.getClass(), model.getId());
    }

    public static synchronized void addEntity(Model model) {
        synchronized (Cache.class) {
            sEntities.put(getIdentifier(model), model);
        }
    }

    public static synchronized Model getEntity(Class<? extends Model> cls, long j) {
        Model model;
        synchronized (Cache.class) {
            model = sEntities.get(getIdentifier(cls, Long.valueOf(j)));
        }
        return model;
    }

    public static synchronized void removeEntity(Model model) {
        synchronized (Cache.class) {
            sEntities.remove(getIdentifier(model));
        }
    }

    public static synchronized Collection<TableInfo> getTableInfos() {
        Collection<TableInfo> tableInfos;
        synchronized (Cache.class) {
            tableInfos = sModelInfo.getTableInfos();
        }
        return tableInfos;
    }

    public static synchronized TableInfo getTableInfo(Class<? extends Model> cls) {
        TableInfo tableInfo;
        synchronized (Cache.class) {
            tableInfo = sModelInfo.getTableInfo(cls);
        }
        return tableInfo;
    }

    public static synchronized TypeSerializer getParserForType(Class<?> cls) {
        TypeSerializer typeSerializer;
        synchronized (Cache.class) {
            typeSerializer = sModelInfo.getTypeSerializer(cls);
        }
        return typeSerializer;
    }

    public static synchronized String getTableName(Class<? extends Model> cls) {
        String tableName;
        synchronized (Cache.class) {
            tableName = sModelInfo.getTableInfo(cls).getTableName();
        }
        return tableName;
    }
}
