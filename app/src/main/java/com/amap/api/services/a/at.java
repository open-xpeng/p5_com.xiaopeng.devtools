package com.amap.api.services.a;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: DynamicFileDBCreator.java */
/* loaded from: classes11.dex */
public class at implements w {
    private static at fj;

    public static synchronized at br() {
        at atVar;
        synchronized (at.class) {
            if (fj == null) {
                fj = new at();
            }
            atVar = fj;
        }
        return atVar;
    }

    private at() {
    }

    @Override // com.amap.api.services.a.w
    public String a() {
        return "dafile.db";
    }

    @Override // com.amap.api.services.a.w
    public int b() {
        return 1;
    }

    @Override // com.amap.api.services.a.w
    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sname  varchar(20), fname varchar(100),md varchar(20),version varchar(20),dversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            aw.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    @Override // com.amap.api.services.a.w
    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
