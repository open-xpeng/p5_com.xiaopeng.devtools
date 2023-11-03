package com.activeandroid.content;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseArray;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Configuration;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public class ContentProvider extends android.content.ContentProvider {
    private static String sAuthority;
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private static final SparseArray<Class<? extends Model>> TYPE_CODES = new SparseArray<>();
    private static SparseArray<String> sMimeTypeCache = new SparseArray<>();

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        ActiveAndroid.initialize(getConfiguration());
        sAuthority = getAuthority();
        ArrayList arrayList = new ArrayList(Cache.getTableInfos());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            TableInfo tableInfo = (TableInfo) arrayList.get(i);
            int i2 = i * 2;
            int i3 = i2 + 1;
            int i4 = i2 + 2;
            URI_MATCHER.addURI(sAuthority, tableInfo.getTableName().toLowerCase(), i3);
            TYPE_CODES.put(i3, tableInfo.getType());
            UriMatcher uriMatcher = URI_MATCHER;
            String str = sAuthority;
            uriMatcher.addURI(str, tableInfo.getTableName().toLowerCase() + "/#", i4);
            TYPE_CODES.put(i4, tableInfo.getType());
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        String str = sMimeTypeCache.get(match);
        if (str != null) {
            return str;
        }
        Class<? extends Model> modelType = getModelType(uri);
        boolean z = match % 2 == 0;
        StringBuilder sb = new StringBuilder();
        sb.append("vnd");
        sb.append(".");
        sb.append(sAuthority);
        sb.append(".");
        sb.append(z ? "item" : "dir");
        sb.append("/");
        sb.append("vnd");
        sb.append(".");
        sb.append(sAuthority);
        sb.append(".");
        sb.append(Cache.getTableName(modelType));
        sMimeTypeCache.append(match, sb.toString());
        return sb.toString();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Class<? extends Model> modelType = getModelType(uri);
        Long valueOf = Long.valueOf(Cache.openDatabase().insert(Cache.getTableName(modelType), null, contentValues));
        if (valueOf == null || valueOf.longValue() <= 0) {
            return null;
        }
        Uri createUri = createUri(modelType, valueOf);
        notifyChange(createUri);
        return createUri;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int update = Cache.openDatabase().update(Cache.getTableName(getModelType(uri)), contentValues, str, strArr);
        notifyChange(uri);
        return update;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int delete = Cache.openDatabase().delete(Cache.getTableName(getModelType(uri)), str, strArr);
        notifyChange(uri);
        return delete;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor query = Cache.openDatabase().query(Cache.getTableName(getModelType(uri)), strArr, str, strArr2, null, null, str2);
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    public static Uri createUri(Class<? extends Model> cls, Long l) {
        StringBuilder sb = new StringBuilder();
        sb.append("content://");
        sb.append(sAuthority);
        sb.append("/");
        sb.append(Cache.getTableName(cls).toLowerCase());
        if (l != null) {
            sb.append("/");
            sb.append(l.toString());
        }
        return Uri.parse(sb.toString());
    }

    protected String getAuthority() {
        return getContext().getPackageName();
    }

    protected Configuration getConfiguration() {
        return new Configuration.Builder(getContext()).create();
    }

    private Class<? extends Model> getModelType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        if (match != -1) {
            return TYPE_CODES.get(match);
        }
        return null;
    }

    private void notifyChange(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }
}
