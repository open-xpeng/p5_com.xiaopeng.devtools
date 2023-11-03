package com.lzy.okgo.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/* compiled from: BaseDao.java */
/* loaded from: classes11.dex */
public abstract class a<T> {
    protected static String TAG;
    protected Lock lN;
    protected SQLiteOpenHelper lO;
    protected SQLiteDatabase lP;

    public abstract T c(Cursor cursor);

    public abstract ContentValues f(T t);

    public abstract String getTableName();

    public a(SQLiteOpenHelper sQLiteOpenHelper) {
        TAG = getClass().getSimpleName();
        this.lN = d.lN;
        this.lO = sQLiteOpenHelper;
        this.lP = cV();
    }

    public SQLiteDatabase cV() {
        return this.lO.getWritableDatabase();
    }

    protected final void a(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return;
        }
        sQLiteDatabase.close();
    }

    public boolean b(String str, String[] strArr) {
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        this.lN.lock();
        try {
            try {
                this.lP.beginTransaction();
                this.lP.delete(getTableName(), str, strArr);
                this.lP.setTransactionSuccessful();
                return true;
            } catch (Exception e) {
                com.lzy.okgo.f.d.f(e);
                this.lP.endTransaction();
                this.lN.unlock();
                String str3 = TAG;
                com.lzy.okgo.f.d.v(str3, (System.currentTimeMillis() - currentTimeMillis) + " delete");
                return false;
            }
        } finally {
            this.lP.endTransaction();
            this.lN.unlock();
            str2 = TAG;
            com.lzy.okgo.f.d.v(str2, (System.currentTimeMillis() - currentTimeMillis) + " delete");
        }
    }

    public boolean e(T t) {
        String str;
        if (t == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.lN.lock();
        try {
            this.lP.beginTransaction();
            this.lP.replace(getTableName(), null, f(t));
            this.lP.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            com.lzy.okgo.f.d.f(e);
            return false;
        } finally {
            this.lP.endTransaction();
            this.lN.unlock();
            str = TAG;
            com.lzy.okgo.f.d.v(str, (System.currentTimeMillis() - currentTimeMillis) + " replaceT");
        }
    }

    public List<T> c(String str, String[] strArr) {
        return a(null, str, strArr, null, null, null, null);
    }

    public List<T> a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        Cursor cursor;
        String str6;
        StringBuilder sb;
        long currentTimeMillis = System.currentTimeMillis();
        this.lN.lock();
        ArrayList arrayList = new ArrayList();
        try {
            try {
                this.lP.beginTransaction();
                cursor = this.lP.query(getTableName(), strArr, str, strArr2, str2, str3, str4, str5);
                while (!cursor.isClosed() && cursor.moveToNext()) {
                    try {
                        arrayList.add(c(cursor));
                    } catch (Exception e) {
                        e = e;
                        com.lzy.okgo.f.d.f(e);
                        a(null, cursor);
                        this.lP.endTransaction();
                        this.lN.unlock();
                        str6 = TAG;
                        sb = new StringBuilder();
                        sb.append(System.currentTimeMillis() - currentTimeMillis);
                        sb.append(" query");
                        com.lzy.okgo.f.d.v(str6, sb.toString());
                        return arrayList;
                    }
                }
                this.lP.setTransactionSuccessful();
                a(null, cursor);
                this.lP.endTransaction();
                this.lN.unlock();
                str6 = TAG;
                sb = new StringBuilder();
            } catch (Exception e2) {
                e = e2;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                a(null, null);
                this.lP.endTransaction();
                this.lN.unlock();
                com.lzy.okgo.f.d.v(TAG, (System.currentTimeMillis() - currentTimeMillis) + " query");
                throw th;
            }
            sb.append(System.currentTimeMillis() - currentTimeMillis);
            sb.append(" query");
            com.lzy.okgo.f.d.v(str6, sb.toString());
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            a(null, null);
            this.lP.endTransaction();
            this.lN.unlock();
            com.lzy.okgo.f.d.v(TAG, (System.currentTimeMillis() - currentTimeMillis) + " query");
            throw th;
        }
    }
}
