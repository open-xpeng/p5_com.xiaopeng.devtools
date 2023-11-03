package com.alibaba.sdk.android.man.crashreporter.d;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import java.io.File;

/* loaded from: classes11.dex */
public class a extends b {
    private final Context a;
    private com.alibaba.sdk.android.man.crashreporter.c environment;
    private final String TOMBSTONE_PATH = "tombstone";
    private final String FILENAME = com.alibaba.sdk.android.man.crashreporter.handler.c.a.MODULE;
    private final String t = ".base";

    public a(Context context, com.alibaba.sdk.android.man.crashreporter.c cVar) {
        this.environment = null;
        this.a = context;
        this.environment = cVar;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.b, com.alibaba.sdk.android.man.crashreporter.d.c
    public String h() {
        try {
            BaseDataContent a = a();
            if (a != null && a.userNick != null) {
                return a.userNick;
            }
            return "";
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("get local user nick err!", e);
            return "";
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.b, com.alibaba.sdk.android.man.crashreporter.d.c
    public void a(BaseDataContent baseDataContent) {
        if (baseDataContent == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.g("base data object is null!");
            return;
        }
        Context context = this.a;
        Context context2 = this.a;
        File dir = context.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            try {
                com.alibaba.sdk.android.man.crashreporter.d.a.a.a(baseDataContent, new File(String.format("%s/%s%s", dir, com.alibaba.sdk.android.man.crashreporter.handler.c.a.MODULE, ".base")));
                com.alibaba.sdk.android.man.crashreporter.b.a.e("base data succ");
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("base data write error.", e);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0054, code lost:
        if (r2 != null) goto L19;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x007d: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:39:0x007d */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0080  */
    @Override // com.alibaba.sdk.android.man.crashreporter.d.b, com.alibaba.sdk.android.man.crashreporter.d.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent a() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            android.content.Context r2 = r6.a     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            java.lang.String r3 = "tombstone"
            android.content.Context r4 = r6.a     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            java.io.File r2 = r2.getDir(r3, r0)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            java.lang.String r3 = "%s/%s%s"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            java.lang.String r2 = r2.getPath()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            r4[r0] = r2     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            r2 = 1
            java.lang.String r5 = "crashreporter"
            r4[r2] = r5     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            r2 = 2
            java.lang.String r5 = ".base"
            r4[r2] = r5     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            java.lang.String r2 = java.lang.String.format(r3, r4)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            boolean r2 = r3.exists()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            if (r2 == 0) goto L53
            boolean r2 = r3.isFile()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            if (r2 == 0) goto L53
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c java.io.FileNotFoundException -> L67
            java.lang.Object r3 = com.alibaba.sdk.android.man.crashreporter.d.a.a.a(r2)     // Catch: java.lang.Exception -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L7c
            if (r3 == 0) goto L54
            boolean r4 = r3 instanceof com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent     // Catch: java.lang.Exception -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L7c
            if (r4 == 0) goto L54
            com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent r3 = (com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent) r3     // Catch: java.lang.Exception -> L4f java.io.FileNotFoundException -> L51 java.lang.Throwable -> L7c
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r2)
            return r3
        L4f:
            r0 = move-exception
            goto L5e
        L51:
            r3 = move-exception
            goto L69
        L53:
            r2 = r1
        L54:
            if (r2 == 0) goto L59
        L56:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r2)
        L59:
            goto L66
        L5a:
            r0 = move-exception
            goto L7e
        L5c:
            r0 = move-exception
            r2 = r1
        L5e:
            java.lang.String r3 = "read base data file error."
            com.alibaba.sdk.android.man.crashreporter.b.a.d(r3, r0)     // Catch: java.lang.Throwable -> L7c
            if (r2 == 0) goto L59
            goto L56
        L66:
            return r1
        L67:
            r2 = move-exception
            r2 = r1
        L69:
            java.lang.String r3 = "Trying to load crash report but base data not found."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L7c
            java.lang.String r0 = java.lang.String.format(r3, r0)     // Catch: java.lang.Throwable -> L7c
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r0)     // Catch: java.lang.Throwable -> L7c
            if (r2 == 0) goto L7a
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r2)
        L7a:
            return r1
        L7c:
            r0 = move-exception
            r1 = r2
        L7e:
            if (r1 == 0) goto L83
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
        L83:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.d.a.a():com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent");
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.b, com.alibaba.sdk.android.man.crashreporter.d.c
    public void b(boolean z) {
        BaseDataContent a;
        if (z && (a = a()) != null) {
            a.hashCode = null;
            a.path = null;
            a.times = 0;
            a(a);
        }
    }
}
