package com.lzy.okgo.d;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.cache.CacheEntity;
import java.util.List;

/* compiled from: CacheManager.java */
/* loaded from: classes11.dex */
public class b extends com.lzy.okgo.d.a<CacheEntity<?>> {
    public static b cW() {
        return a.lQ;
    }

    /* compiled from: CacheManager.java */
    /* loaded from: classes11.dex */
    private static class a {
        private static final b lQ = new b();
    }

    private b() {
        super(new d());
    }

    @Override // com.lzy.okgo.d.a
    /* renamed from: b */
    public CacheEntity<?> c(Cursor cursor) {
        return CacheEntity.b(cursor);
    }

    @Override // com.lzy.okgo.d.a
    /* renamed from: a */
    public ContentValues f(CacheEntity<?> cacheEntity) {
        return CacheEntity.a(cacheEntity);
    }

    @Override // com.lzy.okgo.d.a
    public String getTableName() {
        return "cache";
    }

    public CacheEntity<?> aF(String str) {
        if (str == null) {
            return null;
        }
        List<CacheEntity<?>> c = c("key=?", new String[]{str});
        if (c.size() > 0) {
            return c.get(0);
        }
        return null;
    }

    public boolean remove(String str) {
        if (str == null) {
            return false;
        }
        return b("key=?", new String[]{str});
    }

    public <T> CacheEntity<T> a(String str, CacheEntity<T> cacheEntity) {
        cacheEntity.setKey(str);
        e(cacheEntity);
        return cacheEntity;
    }
}
