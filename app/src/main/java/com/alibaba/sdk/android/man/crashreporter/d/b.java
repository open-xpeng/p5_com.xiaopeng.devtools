package com.alibaba.sdk.android.man.crashreporter.d;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import java.io.File;

/* loaded from: classes11.dex */
public class b implements c {
    private final String TOMBSTONE_PATH = "tombstone";
    private final String MOTU_PATH = "motu";
    private final String t = ".stacktrace";
    private final String u = "-waitsend";
    private Context a = null;

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public boolean c(Context context) {
        try {
            this.a = context;
            return true;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("init storer err", e);
            return false;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String[] a(int i) {
        if (this.a == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to load crash report but context is null.");
            return null;
        } else if (i == 0 || i == 2) {
            return com.alibaba.sdk.android.man.crashreporter.d.a.a.a(this.a, "tombstone");
        } else {
            if (i == 1) {
                return com.alibaba.sdk.android.man.crashreporter.d.a.a.a(this.a, String.format("%s/%s", this.a.getDir("tombstone", 0).getAbsolutePath(), "motu"), ".stacktrace");
            }
            return null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0072: MOVE  (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:32:0x0072 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0075  */
    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave b(java.lang.String r6) {
        /*
            r5 = this;
            android.content.Context r0 = r5.a
            r1 = 0
            if (r0 != 0) goto Lb
            java.lang.String r6 = "Trying to load crash report but context is null."
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r6)
            return r1
        Lb:
            r0 = 0
            android.content.Context r2 = r5.a     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.lang.String r3 = "tombstone"
            java.io.File r2 = r2.getDir(r3, r0)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            r3.<init>()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.lang.String r2 = r2.getPath()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            r3.append(r2)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.lang.String r2 = "/"
            r3.append(r2)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            r3.append(r6)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.io.File r2 = new java.io.File     // Catch: java.lang.Exception -> L4f java.lang.Throwable -> L52
            r2.<init>(r6)     // Catch: java.lang.Exception -> L4f java.lang.Throwable -> L52
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L4f java.lang.Throwable -> L52
            r3.<init>(r2)     // Catch: java.lang.Exception -> L4f java.lang.Throwable -> L52
            java.lang.Object r2 = com.alibaba.sdk.android.man.crashreporter.d.a.a.a(r3)     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L71
            boolean r4 = r2 instanceof com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L71
            if (r4 == 0) goto L47
            com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave r2 = (com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave) r2     // Catch: java.lang.Exception -> L4d java.lang.Throwable -> L71
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r3)
            return r2
        L47:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r3)
            return r1
        L4d:
            r2 = move-exception
            goto L57
        L4f:
            r2 = move-exception
            r3 = r1
            goto L57
        L52:
            r6 = move-exception
            goto L73
        L54:
            r6 = move-exception
            r6 = r1
            r3 = r6
        L57:
            java.lang.String r2 = "Trying to load crash report but file:%s not found."
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L71
            r4[r0] = r6     // Catch: java.lang.Throwable -> L71
            java.lang.String r0 = java.lang.String.format(r2, r4)     // Catch: java.lang.Throwable -> L71
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r0)     // Catch: java.lang.Throwable -> L71
            if (r6 == 0) goto L6a
            com.alibaba.sdk.android.man.crashreporter.e.e.i(r6)     // Catch: java.lang.Throwable -> L71
        L6a:
            if (r3 == 0) goto L70
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r3)
        L70:
            return r1
        L71:
            r6 = move-exception
            r1 = r3
        L73:
            if (r1 == 0) goto L78
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
        L78:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.d.b.b(java.lang.String):com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave");
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public boolean a(CrashReportDataForSave crashReportDataForSave, int i) {
        if (crashReportDataForSave.path == null && i == 1) {
            return true;
        }
        if (crashReportDataForSave == null || i.a((CharSequence) crashReportDataForSave.path)) {
            return false;
        }
        String str = crashReportDataForSave.path;
        File file = new File(str);
        if (!file.exists()) {
            file = new File(str + "-waitsend");
            if (file.exists() && file.isFile()) {
                crashReportDataForSave.path = str + "-waitsend";
                com.alibaba.sdk.android.man.crashreporter.b.a.e("file exists!");
                return true;
            }
        }
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        String str2 = str + "-waitsend";
        if (!file.renameTo(new File(str2))) {
            return false;
        }
        crashReportDataForSave.path = str2;
        com.alibaba.sdk.android.man.crashreporter.b.a.e("file exists!");
        return true;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public void b(CrashReportDataForSave crashReportDataForSave) {
        if (this.a == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to load crash report but context is null.");
            return;
        }
        try {
            File a = a(crashReportDataForSave.fileName);
            if (a != null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.b("save crash file: ", a.getAbsolutePath());
                com.alibaba.sdk.android.man.crashreporter.d.a.a.a(crashReportDataForSave, a);
                com.alibaba.sdk.android.man.crashreporter.b.a.e("save crash file succ ");
            } else {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("store crash report file failure!");
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("crash data save error.", e);
        }
    }

    private File a(String str) {
        String format;
        File dir = this.a.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            try {
                if (str.contains(".stacktrace")) {
                    format = String.format("%s/%s", dir.getPath(), str);
                } else {
                    format = String.format("%s/%s%s", dir.getPath(), str, ".stacktrace");
                }
                return new File(format);
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("data build error.", e);
                return null;
            }
        }
        return null;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String i() {
        File dir = this.a.getDir("tombstone", 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.canWrite()) {
            return dir.getPath();
        }
        return null;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String a(long j) {
        return String.format("%s_%s", Integer.toString(i.a(i.a(com.alibaba.sdk.android.man.crashreporter.e.a.a(this.a), ""))), Long.valueOf(j));
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String j() {
        return ".stacktrace";
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L7e
            int r1 = r5.length()
            if (r1 != 0) goto Lb
            goto L7e
        Lb:
            java.io.File r1 = new java.io.File
            r1.<init>(r5)
            boolean r5 = r1.exists()
            if (r5 == 0) goto L7d
            boolean r5 = r1.isFile()
            if (r5 == 0) goto L7d
            boolean r5 = r1.canRead()
            if (r5 == 0) goto L7d
            boolean r5 = r1.canWrite()
            if (r5 == 0) goto L7d
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L46 java.io.FileNotFoundException -> L49
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L46 java.io.FileNotFoundException -> L49
            java.lang.Object r2 = com.alibaba.sdk.android.man.crashreporter.d.a.a.a(r5)     // Catch: java.io.FileNotFoundException -> L44 java.lang.Throwable -> L76
            boolean r3 = r2 instanceof com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave     // Catch: java.io.FileNotFoundException -> L44 java.lang.Throwable -> L76
            if (r3 == 0) goto L3e
            com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave r2 = (com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave) r2     // Catch: java.io.FileNotFoundException -> L44 java.lang.Throwable -> L76
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
            goto L5d
        L3e:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
            return r0
        L44:
            r2 = move-exception
            goto L4b
        L46:
            r6 = move-exception
            r5 = r0
            goto L77
        L49:
            r5 = move-exception
            r5 = r0
        L4b:
            java.lang.String r2 = "Trying to load deduplication crash report but file not found."
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L76
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch: java.lang.Throwable -> L76
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r2)     // Catch: java.lang.Throwable -> L76
            if (r5 == 0) goto L5c
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
        L5c:
            r2 = r0
        L5d:
            if (r2 == 0) goto L75
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L6e
            r2.times = r5     // Catch: java.lang.Exception -> L6e
            com.alibaba.sdk.android.man.crashreporter.d.a.a.a(r2, r1)     // Catch: java.lang.Exception -> L6e
            java.lang.String r5 = "save deduplication file succ "
            com.alibaba.sdk.android.man.crashreporter.b.a.e(r5)     // Catch: java.lang.Exception -> L6e
            return r2
        L6e:
            r5 = move-exception
            java.lang.String r6 = "deduplicationFile build error."
            com.alibaba.sdk.android.man.crashreporter.b.a.d(r6, r5)
            goto L7d
        L75:
            goto L7d
        L76:
            r6 = move-exception
        L77:
            if (r5 == 0) goto L7c
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r5)
        L7c:
            throw r6
        L7d:
            return r0
        L7e:
            java.lang.String r5 = "load file failure"
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.d.b.a(java.lang.String, int):com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave");
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public String h() {
        return "";
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public BaseDataContent a() {
        return null;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public void a(BaseDataContent baseDataContent) {
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.d.c
    public void b(boolean z) {
    }
}
