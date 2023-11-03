package com.lzy.okgo.d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaopeng.lib.apirouter.ClientConstants;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: DBHelper.java */
/* loaded from: classes11.dex */
class d extends SQLiteOpenHelper {
    static final Lock lN = new ReentrantLock();
    private f lX;
    private f lY;
    private f lZ;
    private f ma;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d() {
        this(com.lzy.okgo.a.cE().getContext());
    }

    d(Context context) {
        super(context, "okgo.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.lX = new f("cache");
        this.lY = new f("cookie");
        this.lZ = new f("download");
        this.ma = new f("upload");
        this.lX.a(new c("key", "VARCHAR", true, true)).a(new c("localExpire", "INTEGER")).a(new c("head", "BLOB")).a(new c("data", "BLOB"));
        this.lY.a(new c("host", "VARCHAR")).a(new c(ClientConstants.ALIAS.P_NAME, "VARCHAR")).a(new c("domain", "VARCHAR")).a(new c("cookie", "BLOB")).a(new c("host", ClientConstants.ALIAS.P_NAME, "domain"));
        this.lZ.a(new c("tag", "VARCHAR", true, true)).a(new c("url", "VARCHAR")).a(new c("folder", "VARCHAR")).a(new c("filePath", "VARCHAR")).a(new c("fileName", "VARCHAR")).a(new c("fraction", "VARCHAR")).a(new c("totalSize", "INTEGER")).a(new c("currentSize", "INTEGER")).a(new c("status", "INTEGER")).a(new c("priority", "INTEGER")).a(new c("date", "INTEGER")).a(new c("request", "BLOB")).a(new c("extra1", "BLOB")).a(new c("extra2", "BLOB")).a(new c("extra3", "BLOB"));
        this.ma.a(new c("tag", "VARCHAR", true, true)).a(new c("url", "VARCHAR")).a(new c("folder", "VARCHAR")).a(new c("filePath", "VARCHAR")).a(new c("fileName", "VARCHAR")).a(new c("fraction", "VARCHAR")).a(new c("totalSize", "INTEGER")).a(new c("currentSize", "INTEGER")).a(new c("status", "INTEGER")).a(new c("priority", "INTEGER")).a(new c("date", "INTEGER")).a(new c("request", "BLOB")).a(new c("extra1", "BLOB")).a(new c("extra2", "BLOB")).a(new c("extra3", "BLOB"));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.lX.cY());
        sQLiteDatabase.execSQL(this.lY.cY());
        sQLiteDatabase.execSQL(this.lZ.cY());
        sQLiteDatabase.execSQL(this.ma.cY());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (e.a(sQLiteDatabase, this.lX)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache");
        }
        if (e.a(sQLiteDatabase, this.lY)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cookie");
        }
        if (e.a(sQLiteDatabase, this.lZ)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS download");
        }
        if (e.a(sQLiteDatabase, this.ma)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS upload");
        }
        onCreate(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }
}
