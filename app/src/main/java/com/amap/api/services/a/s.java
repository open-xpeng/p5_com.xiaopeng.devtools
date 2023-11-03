package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;

/* compiled from: AnrLogProcessor.java */
/* loaded from: classes11.dex */
public class s extends v {
    private static boolean a = true;
    private String[] b;
    private int c;
    private boolean d;
    private int e;

    /* JADX INFO: Access modifiers changed from: protected */
    public s(int i) {
        super(i);
        this.b = new String[10];
        this.c = 0;
        this.d = false;
        this.e = 0;
    }

    @Override // com.amap.api.services.a.v
    protected boolean a(Context context) {
        if (g.s(context) == 1 && a) {
            a = false;
            synchronized (Looper.getMainLooper()) {
                ai aiVar = new ai(context);
                aj bp = aiVar.bp();
                if (bp == null) {
                    return true;
                }
                if (bp.c()) {
                    bp.c(false);
                    aiVar.a(bp);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0176 A[RETURN] */
    @Override // com.amap.api.services.a.v
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.String h(java.util.List<com.amap.api.services.a.l> r10) {
        /*
            Method dump skipped, instructions count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.s.h(java.util.List):java.lang.String");
    }

    private String d() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = this.c; i < 10 && i <= 9; i++) {
                sb.append(this.b[i]);
            }
            for (int i2 = 0; i2 < this.c; i2++) {
                sb.append(this.b[i2]);
            }
        } catch (Throwable th) {
            o.a(th, "ANRWriter", "getLogInfo");
        }
        return sb.toString();
    }

    private void b(String str) {
        try {
            if (this.c > 9) {
                this.c = 0;
            }
            this.b[this.c] = str;
            this.c++;
        } catch (Throwable th) {
            o.a(th, "ANRWriter", "addData");
        }
    }
}
