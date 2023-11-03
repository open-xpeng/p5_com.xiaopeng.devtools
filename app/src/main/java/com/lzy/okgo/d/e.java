package com.lzy.okgo.d;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* compiled from: DBUtils.java */
/* loaded from: classes11.dex */
public class e {
    public static boolean a(SQLiteDatabase sQLiteDatabase, f fVar) {
        if (a(sQLiteDatabase, fVar.mb)) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + fVar.mb, null);
            if (rawQuery == null) {
                return false;
            }
            try {
                int columnCount = fVar.getColumnCount();
                if (columnCount == rawQuery.getColumnCount()) {
                    for (int i = 0; i < columnCount; i++) {
                        if (fVar.getColumnIndex(rawQuery.getColumnName(i)) != -1) {
                        }
                    }
                    return false;
                }
                return true;
            } finally {
                rawQuery.close();
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.database.sqlite.SQLiteDatabase r6, java.lang.String r7) {
        /*
            r0 = 0
            if (r7 == 0) goto L52
            if (r6 == 0) goto L52
            boolean r1 = r6.isOpen()
            if (r1 != 0) goto Lc
            goto L52
        Lc:
            r1 = 0
            r2 = 1
            java.lang.String r3 = "SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            java.lang.String r5 = "table"
            r4[r0] = r5     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r4[r2] = r7     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            android.database.Cursor r6 = r6.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            boolean r7 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
            if (r7 != 0) goto L2b
        L25:
            if (r6 == 0) goto L2a
            r6.close()
        L2a:
            return r0
        L2b:
            int r7 = r6.getInt(r0)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
            if (r6 == 0) goto L47
            r6.close()
            goto L47
        L35:
            r7 = move-exception
            r1 = r6
            goto L4c
        L38:
            r7 = move-exception
            r1 = r6
            goto L3e
        L3b:
            r7 = move-exception
            goto L4c
        L3d:
            r7 = move-exception
        L3e:
            com.lzy.okgo.f.d.f(r7)     // Catch: java.lang.Throwable -> L3b
            if (r1 == 0) goto L46
            r1.close()
        L46:
            r7 = r0
        L47:
            if (r7 <= 0) goto L4b
            r0 = r2
        L4b:
            return r0
        L4c:
            if (r1 == 0) goto L51
            r1.close()
        L51:
            throw r7
        L52:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lzy.okgo.d.e.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }
}
