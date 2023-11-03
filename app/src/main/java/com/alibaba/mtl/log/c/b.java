package com.alibaba.mtl.log.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alibaba.mtl.log.d.i;
import com.xiaopeng.lib.apirouter.ClientConstants;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LogSqliteStore.java */
/* loaded from: classes11.dex */
public class b implements com.alibaba.mtl.log.c.a {
    String cE = "SELECT * FROM %s ORDER BY %s ASC LIMIT %s";
    String cF = "SELECT count(*) FROM %s";
    String cG = "DELETE FROM log where _id in ( select _id from log  ORDER BY _id ASC LIMIT %d )";
    a cH;

    /* JADX INFO: Access modifiers changed from: protected */
    public b(Context context) {
        this.cH = new a(context);
    }

    @Override // com.alibaba.mtl.log.c.a
    public synchronized boolean c(List<com.alibaba.mtl.log.model.a> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase sQLiteDatabase = null;
                boolean z = false;
                try {
                    SQLiteDatabase writableDatabase = this.cH.getWritableDatabase();
                    try {
                        if (writableDatabase != null) {
                            writableDatabase.beginTransaction();
                            int i = 0;
                            while (true) {
                                try {
                                    if (i >= list.size()) {
                                        z = true;
                                        break;
                                    }
                                    com.alibaba.mtl.log.model.a aVar = list.get(i);
                                    if (aVar != null) {
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put("eventId", aVar.X);
                                        contentValues.put("priority", aVar.dZ);
                                        contentValues.put(ClientConstants.BINDER.SCHEME, aVar.j());
                                        contentValues.put("time", aVar.eb);
                                        contentValues.put("_index", aVar.ec);
                                        long insert = writableDatabase.insert("log", "", contentValues);
                                        if (insert == -1) {
                                            break;
                                        }
                                        i.a("UTSqliteLogStore", "[insert] ", aVar.ec, " isSuccess:", true, "ret", Long.valueOf(insert));
                                    }
                                    i++;
                                } catch (Throwable th) {
                                    th = th;
                                    z = true;
                                    sQLiteDatabase = writableDatabase;
                                    i.a("UTSqliteLogStore", "insert error", th);
                                    com.alibaba.mtl.appmonitor.b.b.m16a(th);
                                    if (sQLiteDatabase != null) {
                                        try {
                                            sQLiteDatabase.setTransactionSuccessful();
                                        } catch (Throwable th2) {
                                        }
                                        try {
                                            sQLiteDatabase.endTransaction();
                                        } catch (Throwable th3) {
                                        }
                                    }
                                    this.cH.a(sQLiteDatabase);
                                    return z;
                                }
                            }
                        } else {
                            i.a("UTSqliteLogStore", "db is null");
                        }
                        if (writableDatabase != null) {
                            try {
                                writableDatabase.setTransactionSuccessful();
                            } catch (Throwable th4) {
                            }
                            try {
                                writableDatabase.endTransaction();
                            } catch (Throwable th5) {
                            }
                        }
                        this.cH.a(writableDatabase);
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
                return z;
            }
        }
        return true;
    }

    @Override // com.alibaba.mtl.log.c.a
    public synchronized int d(List<com.alibaba.mtl.log.model.a> list) {
        boolean z;
        int i;
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = this.cH.getWritableDatabase();
                if (writableDatabase != null) {
                    writableDatabase.beginTransaction();
                    i = 0;
                    z = true;
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        long delete = writableDatabase.delete("log", "_id=?", new String[]{list.get(i2).id + ""});
                        if (delete <= 0) {
                            i.a("UTSqliteLogStore", "[delete]  ", Integer.valueOf(list.get(i2).id), " ret:", Long.valueOf(delete));
                            z = false;
                        } else if (!"6005".equalsIgnoreCase(list.get(i2).X)) {
                            i++;
                        }
                    }
                    try {
                        writableDatabase.setTransactionSuccessful();
                    } catch (Throwable th) {
                    }
                    try {
                        writableDatabase.endTransaction();
                    } catch (Throwable th2) {
                    }
                    this.cH.a(writableDatabase);
                } else {
                    i.a("UTSqliteLogStore", "db is null");
                    z = false;
                    i = 0;
                }
                i.a("UTSqliteLogStore", "delete ", Integer.valueOf(list.size()), " isSuccess:", Boolean.valueOf(z));
                return i;
            }
        }
        return 0;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // com.alibaba.mtl.log.c.a
    /* renamed from: h */
    public synchronized java.util.ArrayList<com.alibaba.mtl.log.model.a> g(java.lang.String r9, int r10) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.c.b.g(java.lang.String, int):java.util.ArrayList");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // com.alibaba.mtl.log.c.a
    public synchronized int az() {
        /*
            r6 = this;
            monitor-enter(r6)
            com.alibaba.mtl.log.c.b$a r0 = r6.cH     // Catch: java.lang.Throwable -> L56
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch: java.lang.Throwable -> L56
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L49
            r3 = 0
            java.lang.String r4 = r6.cF     // Catch: java.lang.Throwable -> L3f
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = "log"
            r1[r2] = r5     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = java.lang.String.format(r4, r1)     // Catch: java.lang.Throwable -> L3f
            android.database.Cursor r1 = r0.rawQuery(r1, r3)     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L2f
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L2c
            int r3 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L2c
            r2 = r3
            goto L2f
        L29:
            r2 = move-exception
            r3 = r1
            goto L36
        L2c:
            r3 = move-exception
            r3 = r1
            goto L40
        L2f:
            r6.a(r1)     // Catch: java.lang.Throwable -> L56
            com.alibaba.mtl.log.c.b$a r1 = r6.cH     // Catch: java.lang.Throwable -> L56
            goto L45
        L35:
            r2 = move-exception
        L36:
            r6.a(r3)     // Catch: java.lang.Throwable -> L56
            com.alibaba.mtl.log.c.b$a r1 = r6.cH     // Catch: java.lang.Throwable -> L56
            r1.a(r0)     // Catch: java.lang.Throwable -> L56
            throw r2     // Catch: java.lang.Throwable -> L56
        L3f:
            r1 = move-exception
        L40:
            r6.a(r3)     // Catch: java.lang.Throwable -> L56
            com.alibaba.mtl.log.c.b$a r1 = r6.cH     // Catch: java.lang.Throwable -> L56
        L45:
            r1.a(r0)     // Catch: java.lang.Throwable -> L56
            goto L54
        L49:
            java.lang.String r0 = "UTSqliteLogStore"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "db is null"
            r1[r2] = r3     // Catch: java.lang.Throwable -> L56
            com.alibaba.mtl.log.d.i.a(r0, r1)     // Catch: java.lang.Throwable -> L56
        L54:
            monitor-exit(r6)
            return r2
        L56:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.c.b.az():int");
    }

    @Override // com.alibaba.mtl.log.c.a
    public synchronized void clear() {
        SQLiteDatabase writableDatabase = this.cH.getWritableDatabase();
        if (writableDatabase != null) {
            writableDatabase.delete("log", null, null);
            this.cH.a(writableDatabase);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LogSqliteStore.java */
    /* loaded from: classes11.dex */
    public class a extends SQLiteOpenHelper {
        private AtomicInteger cI;
        private SQLiteDatabase cJ;

        a(Context context) {
            super(context, "ut.db", (SQLiteDatabase.CursorFactory) null, 2);
            this.cI = new AtomicInteger();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            try {
                b.this.a(sQLiteDatabase.rawQuery("PRAGMA journal_mode=DELETE", null));
            } catch (Throwable th) {
                b.this.a(null);
            }
            super.onOpen(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS log (_id INTEGER PRIMARY KEY AUTOINCREMENT, eventId TEXT,priority TEXT, streamId TEXT, time TEXT, content TEXT, _index TEXT )");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 1 && i2 == 2) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE log ADD COLUMN _index TEXT ");
                } catch (Throwable th) {
                    i.a("UTSqliteLogStore", "DB Upgrade Error", th);
                }
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public synchronized SQLiteDatabase getWritableDatabase() {
            try {
                if (this.cI.incrementAndGet() == 1) {
                    this.cJ = super.getWritableDatabase();
                }
            }
            return this.cJ;
        }

        public synchronized void a(SQLiteDatabase sQLiteDatabase) {
            if (sQLiteDatabase == null) {
                return;
            }
            try {
                if (this.cI.decrementAndGet() == 0 && this.cJ != null) {
                    this.cJ.close();
                    this.cJ = null;
                }
            } catch (Throwable th) {
            }
        }
    }

    @Override // com.alibaba.mtl.log.c.a
    public synchronized void c(String str, String str2) {
        SQLiteDatabase writableDatabase = this.cH.getWritableDatabase();
        if (writableDatabase != null) {
            writableDatabase.delete("log", str + " < ?", new String[]{String.valueOf(str2)});
            this.cH.a(writableDatabase);
        } else {
            i.a("UTSqliteLogStore", "db is null");
        }
    }

    @Override // com.alibaba.mtl.log.c.a
    public void C(int i) {
        if (i <= 0) {
            return;
        }
        SQLiteDatabase writableDatabase = this.cH.getWritableDatabase();
        if (writableDatabase != null) {
            try {
                writableDatabase.execSQL(String.format(this.cG, Integer.valueOf(i)));
            } catch (Throwable th) {
            }
            this.cH.a(writableDatabase);
            return;
        }
        i.a("UTSqliteLogStore", "db is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable th) {
            }
        }
    }
}
