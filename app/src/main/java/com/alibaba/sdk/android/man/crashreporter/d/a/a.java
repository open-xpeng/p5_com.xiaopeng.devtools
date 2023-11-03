package com.alibaba.sdk.android.man.crashreporter.d.a;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.e.f;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectOutputStream;

/* loaded from: classes11.dex */
public class a {
    public static String[] a(final Context context, String str) {
        try {
            if (context == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to get crash reports but MotuCrashReporter is not initialized.");
                return new String[0];
            }
            File dir = context.getDir(str, 0);
            if (dir == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.g("Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
                return new String[0];
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Looking for error files in " + dir.getAbsolutePath());
            String[] list = dir.list(new FilenameFilter() { // from class: com.alibaba.sdk.android.man.crashreporter.d.a.a.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    String a = com.alibaba.sdk.android.man.crashreporter.e.a.a(context);
                    if (a == null) {
                        return str2.startsWith("FAILURE");
                    }
                    return str2.startsWith(Integer.toString(i.a(i.a(a, ""))));
                }
            });
            return list == null ? new String[0] : list;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("find file error.", e);
            return null;
        }
    }

    public static String[] a(Context context, String str, final String str2) {
        try {
            if (context == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.h("Trying to get crash reports but MotuCrashReporter is not initialized.");
                return new String[0];
            }
            File file = new File(str);
            com.alibaba.sdk.android.man.crashreporter.b.a.e("Looking for error files in " + file.getAbsolutePath());
            String[] list = file.list(new FilenameFilter() { // from class: com.alibaba.sdk.android.man.crashreporter.d.a.a.2
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str3) {
                    return str3.endsWith(str2);
                }
            });
            return list == null ? new String[0] : list;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("find file error.", e);
            return null;
        }
    }

    public static void a(Object obj, File file) {
        FileOutputStream fileOutputStream;
        if (obj == null || file == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.h("store file error:object or file is null!");
            return;
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                try {
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream);
                    try {
                        objectOutputStream2.writeObject(obj);
                        f.a(objectOutputStream2);
                    } catch (Exception e) {
                        e = e;
                        objectOutputStream = objectOutputStream2;
                        com.alibaba.sdk.android.man.crashreporter.b.a.d("store file error.", e);
                        if (objectOutputStream != null) {
                            f.a(objectOutputStream);
                        }
                        if (fileOutputStream == null) {
                            return;
                        }
                        f.a(fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream = objectOutputStream2;
                        if (objectOutputStream != null) {
                            f.a(objectOutputStream);
                        }
                        if (fileOutputStream != null) {
                            f.a(fileOutputStream);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
        f.a(fileOutputStream);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0028, code lost:
        if (r1 == null) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object a(java.io.InputStream r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L9
            java.lang.String r3 = "load file error:input stream is null!"
            com.alibaba.sdk.android.man.crashreporter.b.a.h(r3)
            return r0
        L9:
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L21
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L21
            java.lang.Object r3 = r1.readObject()     // Catch: java.lang.Exception -> L1c java.lang.Throwable -> L2e
            if (r3 == 0) goto L1b
        L17:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
            return r3
        L1b:
            goto L2a
        L1c:
            r3 = move-exception
            goto L23
        L1e:
            r3 = move-exception
            r1 = r0
            goto L2f
        L21:
            r3 = move-exception
            r1 = r0
        L23:
            java.lang.String r2 = "load reports error."
            com.alibaba.sdk.android.man.crashreporter.b.a.d(r2, r3)     // Catch: java.lang.Throwable -> L2e
            if (r1 == 0) goto L2d
        L2a:
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
        L2d:
            return r0
        L2e:
            r3 = move-exception
        L2f:
            if (r1 == 0) goto L34
            com.alibaba.sdk.android.man.crashreporter.e.f.a(r1)
        L34:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.man.crashreporter.d.a.a.a(java.io.InputStream):java.lang.Object");
    }
}
