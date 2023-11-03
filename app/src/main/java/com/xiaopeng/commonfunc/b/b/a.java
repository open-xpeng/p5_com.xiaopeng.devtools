package com.xiaopeng.commonfunc.b.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.xiaopeng.a.a;
import com.xiaopeng.commonfunc.bean.aftersales.DiagnosisData;
import com.xiaopeng.commonfunc.bean.aftersales.DiagnosisErrorList;
import com.xiaopeng.commonfunc.utils.i;
import com.xiaopeng.lib.utils.c;
import java.util.LinkedList;
import java.util.List;

/* compiled from: DiagnosisCodeModel.java */
/* loaded from: classes11.dex */
public class a {
    private static final String nQ = a.c.ec("PATH_DIAGNOSIS_DB");
    private static final String[] nR = {"ERRORCODE", "TIME", "ERRORMSG", "VERSION"};
    private Context mContext;
    private SQLiteDatabase nS;
    private List<DiagnosisErrorList> nT;
    private String[] nU;

    public a(Context context, int i) {
        this.mContext = context;
        dG();
        this.nU = this.mContext.getResources().getStringArray(i);
    }

    public a(Context context) {
        this.mContext = context;
        dG();
    }

    public void dG() {
        try {
            if (i.aY(nQ)) {
                this.nS = SQLiteDatabase.openDatabase(nQ, null, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DiagnosisErrorList> dH() {
        return this.nT;
    }

    public String[] dI() {
        return this.nU;
    }

    public String bG(int i) {
        return this.nU[i];
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void dJ() {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r1 = r5.mContext     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L41
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L41
            java.lang.String r2 = "diagnosis_name.json"
            java.io.InputStream r1 = r1.open(r2)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L41
            int r0 = r1.available()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            byte[] r0 = new byte[r0]     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            r1.read(r0)     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            java.lang.String r3 = "UTF-8"
            r2.<init>(r0, r3)     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            r0.<init>()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            com.xiaopeng.commonfunc.b.b.a$1 r3 = new com.xiaopeng.commonfunc.b.b.a$1     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            r3.<init>()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            java.lang.reflect.Type r3 = r3.getType()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            java.lang.Object r0 = r0.fromJson(r2, r3)     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            java.util.List r0 = (java.util.List) r0     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            r5.nT = r0     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L54
            if (r1 == 0) goto L53
            r1.close()     // Catch: java.io.IOException -> L4e
            goto L4d
        L3a:
            r0 = move-exception
            goto L45
        L3c:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L55
        L41:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L45:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L54
            if (r1 == 0) goto L53
            r1.close()     // Catch: java.io.IOException -> L4e
        L4d:
            goto L53
        L4e:
            r0 = move-exception
            r0.printStackTrace()
            goto L4d
        L53:
            return
        L54:
            r0 = move-exception
        L55:
            if (r1 == 0) goto L5f
            r1.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L5b:
            r1 = move-exception
            r1.printStackTrace()
        L5f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.commonfunc.b.b.a.dJ():void");
    }

    public void dK() {
        if (this.nS == null) {
            c.i("DiagnosisCodeModel", "mSQLiteDatabase is null");
            return;
        }
        for (int i = 0; i < com.xiaopeng.commonfunc.a.nI.length; i++) {
            this.nT.get(i).clearDiagnosisData();
            Cursor query = this.nS.query(com.xiaopeng.commonfunc.a.nI[i], nR, null, null, null, null, "ID DESC");
            try {
                if (query.getCount() > 0) {
                    while (query.moveToNext()) {
                        this.nT.get(i).addDiagnosisData(new DiagnosisData(query.getInt(0), query.getString(1), query.getString(2), query.getString(3)));
                    }
                }
                query.close();
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
    }

    public List<Integer> a(String str, long j, long j2) {
        LinkedList linkedList = new LinkedList();
        if (this.nS == null) {
            c.i("DiagnosisCodeModel", "mSQLiteDatabase is null");
        } else {
            Cursor query = this.nS.query(str, new String[]{"ERRORCODE"}, "TIMESTAMP between ? and ?", new String[]{String.valueOf(j), String.valueOf(j2)}, null, null, "ID DESC");
            try {
                if (query.getCount() > 0) {
                    while (query.moveToNext()) {
                        int i = query.getInt(0);
                        if (!linkedList.contains(Integer.valueOf(i))) {
                            linkedList.add(Integer.valueOf(i));
                        }
                    }
                }
            } finally {
                query.close();
            }
        }
        return linkedList;
    }

    public List<DiagnosisData> b(String str, long j, long j2) {
        LinkedList linkedList = new LinkedList();
        if (this.nS == null) {
            c.i("DiagnosisCodeModel", "mSQLiteDatabase is null");
        } else {
            Cursor query = this.nS.query(str, nR, "TIMESTAMP between ? and ?", new String[]{String.valueOf(j), String.valueOf(j2)}, null, null, "ID DESC");
            try {
                if (query.getCount() > 0) {
                    while (query.moveToNext()) {
                        linkedList.add(new DiagnosisData(query.getInt(0), query.getString(1), query.getString(2), query.getString(3)));
                    }
                }
            } finally {
                query.close();
            }
        }
        return linkedList;
    }

    public void onDestroy() {
        if (this.nS != null) {
            this.nS.close();
        }
        if (this.nT != null && !this.nT.isEmpty()) {
            this.nT.clear();
        }
    }
}
