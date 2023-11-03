package com.alibaba.sdk.android.httpdns.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
class d extends SQLiteOpenHelper {
    private static final Object a = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Context context) {
        super(context, "aliclound_httpdns.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    private long a(SQLiteDatabase sQLiteDatabase, g gVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("host_id", Long.valueOf(gVar.h));
        contentValues.put("ip", gVar.k);
        contentValues.put("ttl", gVar.l);
        try {
            return sQLiteDatabase.insert("ip", null, contentValues);
        } catch (Exception e) {
            return 0L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x008f, code lost:
        if (r3 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ae, code lost:
        if (r3 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b0, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b3, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.alibaba.sdk.android.httpdns.b.g> a(long r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)
            java.lang.String r2 = "ip"
            r1.append(r2)
            java.lang.String r2 = " WHERE "
            r1.append(r2)
            java.lang.String r2 = "host_id"
            r1.append(r2)
            java.lang.String r2 = " =? ;"
            r1.append(r2)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getWritableDatabase()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> La6
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            r5 = 0
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            r4[r5] = r7     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            if (r7 == 0) goto L8a
            int r8 = r7.getCount()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            if (r8 <= 0) goto L8a
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
        L45:
            com.alibaba.sdk.android.httpdns.b.g r8 = new com.alibaba.sdk.android.httpdns.b.g     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r8.<init>()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r8.id = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r1 = "host_id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            int r1 = r7.getInt(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r8.h = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r1 = "ip"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r8.k = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r1 = "ttl"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r8.l = r1     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            r0.add(r8)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            boolean r8 = r7.moveToNext()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            if (r8 != 0) goto L45
            goto L8a
        L86:
            r8 = move-exception
            goto L9b
        L88:
            r8 = move-exception
            goto La9
        L8a:
            if (r7 == 0) goto L8f
            r7.close()
        L8f:
            if (r3 == 0) goto Lb3
            goto Lb0
        L92:
            r8 = move-exception
            r7 = r2
            goto L9b
        L95:
            r7 = move-exception
            r7 = r2
            goto La9
        L98:
            r8 = move-exception
            r7 = r2
            r3 = r7
        L9b:
            if (r7 == 0) goto La0
            r7.close()
        La0:
            if (r3 == 0) goto La5
            r3.close()
        La5:
            throw r8
        La6:
            r7 = move-exception
            r7 = r2
            r3 = r7
        La9:
            if (r7 == 0) goto Lae
            r7.close()
        Lae:
            if (r3 == 0) goto Lb3
        Lb0:
            r3.close()
        Lb3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.a(long):java.util.List");
    }

    private List<g> a(e eVar) {
        return a(eVar.id);
    }

    /* renamed from: a  reason: collision with other method in class */
    private void m20a(long j) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = getWritableDatabase();
        } catch (Exception e) {
            sQLiteDatabase = null;
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            sQLiteDatabase.delete("host", "id = ?", new String[]{String.valueOf(j)});
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Exception e2) {
            if (sQLiteDatabase == null) {
                return;
            }
            sQLiteDatabase.close();
        } catch (Throwable th2) {
            th = th2;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
        sQLiteDatabase.close();
    }

    private void a(g gVar) {
        b(gVar.id);
    }

    private void b(long j) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = getWritableDatabase();
        } catch (Exception e) {
            sQLiteDatabase = null;
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            sQLiteDatabase.delete("ip", "id = ?", new String[]{String.valueOf(j)});
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Exception e2) {
            if (sQLiteDatabase == null) {
                return;
            }
            sQLiteDatabase.close();
        } catch (Throwable th2) {
            th = th2;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
        sQLiteDatabase.close();
    }

    private void c(e eVar) {
        m20a(eVar.id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public long m21a(e eVar) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (a) {
            b(eVar.i, eVar.h);
            ContentValues contentValues = new ContentValues();
            try {
                sQLiteDatabase = getWritableDatabase();
            } catch (Exception e) {
                sQLiteDatabase = null;
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase = null;
            }
            try {
                sQLiteDatabase.beginTransaction();
                contentValues.put("host", eVar.h);
                contentValues.put("sp", eVar.i);
                contentValues.put("time", c.c(eVar.j));
                long insert = sQLiteDatabase.insert("host", null, contentValues);
                eVar.id = insert;
                if (eVar.a != null) {
                    Iterator<g> it = eVar.a.iterator();
                    while (it.hasNext()) {
                        g next = it.next();
                        next.h = insert;
                        next.id = a(sQLiteDatabase, next);
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
                return insert;
            } catch (Exception e2) {
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
                return 0L;
            } catch (Throwable th2) {
                th = th2;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a1, code lost:
        if (r3 == null) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009e A[Catch: all -> 0x00ca, TRY_ENTER, TryCatch #5 {, blocks: (B:4:0x0003, B:22:0x009e, B:24:0x00a3, B:42:0x00c8, B:39:0x00c2, B:32:0x00b3, B:34:0x00b8, B:35:0x00bb), top: B:50:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.alibaba.sdk.android.httpdns.b.e a(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.Object r0 = com.alibaba.sdk.android.httpdns.b.d.a
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lca
            r1.<init>()     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = " WHERE "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = "sp"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = " =? "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = " AND "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = "host"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = " =? ;"
            r1.append(r2)     // Catch: java.lang.Throwable -> Lca
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r6.getReadableDatabase()     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lbc
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> Laa
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> Laa
            r5 = 0
            r4[r5] = r7     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> Laa
            r7 = 1
            r4[r7] = r8     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> Laa
            android.database.Cursor r7 = r3.rawQuery(r1, r4)     // Catch: java.lang.Throwable -> La7 java.lang.Exception -> Laa
            if (r7 == 0) goto L9b
            int r8 = r7.getCount()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            if (r8 <= 0) goto L9b
            r7.moveToFirst()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            com.alibaba.sdk.android.httpdns.b.e r8 = new com.alibaba.sdk.android.httpdns.b.e     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            r8.<init>()     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L98
            java.lang.String r1 = "id"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            int r1 = r7.getInt(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            long r1 = (long) r1     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            r8.id = r1     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.lang.String r1 = "host"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            r8.h = r1     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.lang.String r1 = "sp"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            r8.i = r1     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.lang.String r1 = "time"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.lang.String r1 = com.alibaba.sdk.android.httpdns.b.c.d(r1)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            r8.j = r1     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.util.List r1 = r6.a(r8)     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            r8.a = r1     // Catch: java.lang.Exception -> L94 java.lang.Throwable -> L96
            goto L9c
        L94:
            r1 = move-exception
            goto Lc0
        L96:
            r8 = move-exception
            goto Lb1
        L98:
            r8 = move-exception
            r8 = r2
            goto Lc0
        L9b:
            r8 = r2
        L9c:
            if (r7 == 0) goto La1
            r7.close()     // Catch: java.lang.Throwable -> Lca
        La1:
            if (r3 == 0) goto Lc8
        La3:
            r3.close()     // Catch: java.lang.Throwable -> Lca
            goto Lc8
        La7:
            r8 = move-exception
            r7 = r2
            goto Lb1
        Laa:
            r7 = move-exception
            r7 = r2
            r8 = r7
            goto Lc0
        Lae:
            r8 = move-exception
            r7 = r2
            r3 = r7
        Lb1:
            if (r7 == 0) goto Lb6
            r7.close()     // Catch: java.lang.Throwable -> Lca
        Lb6:
            if (r3 == 0) goto Lbb
            r3.close()     // Catch: java.lang.Throwable -> Lca
        Lbb:
            throw r8     // Catch: java.lang.Throwable -> Lca
        Lbc:
            r7 = move-exception
            r7 = r2
            r8 = r7
            r3 = r8
        Lc0:
            if (r7 == 0) goto Lc5
            r7.close()     // Catch: java.lang.Throwable -> Lca
        Lc5:
            if (r3 == 0) goto Lc8
            goto La3
        Lc8:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lca
            return r8
        Lca:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lca
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.a(java.lang.String, java.lang.String):com.alibaba.sdk.android.httpdns.b.e");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0089, code lost:
        if (r4 != null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.alibaba.sdk.android.httpdns.b.e> b() {
        /*
            r7 = this;
            java.lang.Object r0 = com.alibaba.sdk.android.httpdns.b.d.a
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> Lb0
            r1.<init>()     // Catch: java.lang.Throwable -> Lb0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb0
            r2.<init>()     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r3 = "host"
            r2.append(r3)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r3 = " ; "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lb0
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r7.getReadableDatabase()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> La3
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            android.database.Cursor r2 = r4.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L8f java.lang.Exception -> L92
            if (r2 == 0) goto L84
            int r3 = r2.getCount()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            if (r3 <= 0) goto L84
            r2.moveToFirst()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
        L34:
            com.alibaba.sdk.android.httpdns.b.e r3 = new com.alibaba.sdk.android.httpdns.b.e     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.<init>()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = "id"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            int r5 = r2.getInt(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            long r5 = (long) r5     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.id = r5     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = "host"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.h = r5     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = "sp"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.i = r5     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = "time"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r5 = com.alibaba.sdk.android.httpdns.b.c.d(r5)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.j = r5     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.util.List r5 = r7.a(r3)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.a = r5     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r1.add(r3)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            if (r3 != 0) goto L34
            goto L84
        L80:
            r1 = move-exception
            goto L98
        L82:
            r3 = move-exception
            goto La6
        L84:
            if (r2 == 0) goto L89
            r2.close()     // Catch: java.lang.Throwable -> Lb0
        L89:
            if (r4 == 0) goto Lae
        L8b:
            r4.close()     // Catch: java.lang.Throwable -> Lb0
            goto Lae
        L8f:
            r1 = move-exception
            r2 = r3
            goto L98
        L92:
            r2 = move-exception
            r2 = r3
            goto La6
        L95:
            r1 = move-exception
            r2 = r3
            r4 = r2
        L98:
            if (r2 == 0) goto L9d
            r2.close()     // Catch: java.lang.Throwable -> Lb0
        L9d:
            if (r4 == 0) goto La2
            r4.close()     // Catch: java.lang.Throwable -> Lb0
        La2:
            throw r1     // Catch: java.lang.Throwable -> Lb0
        La3:
            r2 = move-exception
            r2 = r3
            r4 = r2
        La6:
            if (r2 == 0) goto Lab
            r2.close()     // Catch: java.lang.Throwable -> Lb0
        Lab:
            if (r4 == 0) goto Lae
            goto L8b
        Lae:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb0
            return r1
        Lb0:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.b.d.b():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str, String str2) {
        synchronized (a) {
            e a2 = a(str, str2);
            if (a2 != null) {
                c(a2);
                if (a2.a != null) {
                    Iterator<g> it = a2.a.iterator();
                    while (it.hasNext()) {
                        a(it.next());
                    }
                }
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE host (id INTEGER PRIMARY KEY,host TEXT,sp TEXT,time TEXT);");
            sQLiteDatabase.execSQL("CREATE TABLE ip (id INTEGER PRIMARY KEY,host_id INTEGER,ip TEXT,ttl TEXT);");
        } catch (Exception e) {
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS host;");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ip;");
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
                onCreate(sQLiteDatabase);
            } catch (Exception e) {
            }
        }
    }
}
