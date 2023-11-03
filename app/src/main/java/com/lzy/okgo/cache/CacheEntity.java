package com.lzy.okgo.cache;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.f.c;
import com.lzy.okgo.model.HttpHeaders;
import java.io.Serializable;

/* loaded from: classes11.dex */
public class CacheEntity<T> implements Serializable {
    private static final long serialVersionUID = -4337711009801627866L;
    private T data;
    private boolean isExpire;
    private String key;
    private long localExpire;
    private HttpHeaders responseHeaders;

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public HttpHeaders cP() {
        return this.responseHeaders;
    }

    public void a(HttpHeaders httpHeaders) {
        this.responseHeaders = httpHeaders;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public long cQ() {
        return this.localExpire;
    }

    public void e(long j) {
        this.localExpire = j;
    }

    public boolean cR() {
        return this.isExpire;
    }

    public void w(boolean z) {
        this.isExpire = z;
    }

    public boolean a(CacheMode cacheMode, long j, long j2) {
        return cacheMode == CacheMode.DEFAULT ? cQ() < j2 : j != -1 && cQ() + j < j2;
    }

    public static <T> ContentValues a(CacheEntity<T> cacheEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", cacheEntity.getKey());
        contentValues.put("localExpire", Long.valueOf(cacheEntity.cQ()));
        contentValues.put("head", c.i(cacheEntity.cP()));
        contentValues.put("data", c.i(cacheEntity.getData()));
        return contentValues;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> CacheEntity<T> b(Cursor cursor) {
        CacheEntity<T> cacheEntity = (CacheEntity<T>) new CacheEntity();
        cacheEntity.setKey(cursor.getString(cursor.getColumnIndex("key")));
        cacheEntity.e(cursor.getLong(cursor.getColumnIndex("localExpire")));
        cacheEntity.a((HttpHeaders) c.s(cursor.getBlob(cursor.getColumnIndex("head"))));
        cacheEntity.setData(c.s(cursor.getBlob(cursor.getColumnIndex("data"))));
        return cacheEntity;
    }

    public String toString() {
        return "CacheEntity{key='" + this.key + "', responseHeaders=" + this.responseHeaders + ", data=" + this.data + ", localExpire=" + this.localExpire + '}';
    }
}
