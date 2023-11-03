package com.amap.api.services.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DB.java */
/* loaded from: classes11.dex */
public class ac extends SQLiteOpenHelper {
    private w eV;

    public ac(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i, w wVar) {
        super(context, str, cursorFactory, i);
        this.eV = wVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.eV.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.eV.a(sQLiteDatabase, i, i2);
    }
}
