package com.xiaopeng.commonfunc.utils;

import android.content.Context;
import android.os.PowerManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/* compiled from: ProcessUtil.java */
/* loaded from: classes11.dex */
public class l {
    public static void y(String str, String str2) {
        aZ("screencap -p " + str + File.separator + str2 + ".png");
    }

    public static Process aZ(String str) {
        Process process;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", str);
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();
            try {
                com.xiaopeng.lib.utils.c.f("ProcessUtil", "execProcess:" + str);
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                return process;
            }
        } catch (IOException e2) {
            e = e2;
            process = null;
        }
        return process;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.io.Closeable, java.io.Reader, java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Process ba(java.lang.String r8) {
        /*
            java.lang.String r0 = "ProcessUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "execCommand: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.xiaopeng.lib.utils.c.f(r0, r1)
            r0 = 0
            java.lang.Process r8 = aZ(r8)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L71
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.io.InputStream r2 = r8.getInputStream()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
        L2d:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            if (r3 == 0) goto L4a
            java.lang.String r4 = "ProcessUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            r5.<init>()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            java.lang.String r6 = "readLine(): "
            r5.append(r6)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            r5.append(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            com.xiaopeng.lib.utils.c.f(r4, r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            goto L2d
        L4a:
            r8.waitFor()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L85
            com.xiaopeng.commonfunc.utils.k.closeQuietly(r1)
            com.xiaopeng.commonfunc.utils.k.closeQuietly(r2)
            if (r8 == 0) goto L58
            r8.destroy()
        L58:
            return r8
        L59:
            r3 = move-exception
            goto L75
        L5b:
            r2 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L86
        L60:
            r3 = move-exception
            r2 = r0
            goto L75
        L63:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L86
        L68:
            r3 = move-exception
            r1 = r0
            goto L74
        L6b:
            r8 = move-exception
            r1 = r0
            r2 = r1
            r0 = r8
            r8 = r2
            goto L86
        L71:
            r3 = move-exception
            r8 = r0
            r1 = r8
        L74:
            r2 = r1
        L75:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L85
            com.xiaopeng.commonfunc.utils.k.closeQuietly(r1)
            com.xiaopeng.commonfunc.utils.k.closeQuietly(r2)
            if (r8 == 0) goto L84
            r8.destroy()
        L84:
            return r0
        L85:
            r0 = move-exception
        L86:
            com.xiaopeng.commonfunc.utils.k.closeQuietly(r1)
            com.xiaopeng.commonfunc.utils.k.closeQuietly(r2)
            if (r8 == 0) goto L91
            r8.destroy()
        L91:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.commonfunc.utils.l.ba(java.lang.String):java.lang.Process");
    }

    public static String eF() {
        return bb("phytool get link_status");
    }

    public static String eG() {
        return bb("phytool get sqi");
    }

    private static String bb(String str) {
        String str2;
        Exception e;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Process aZ = aZ(str);
        if (aZ == null) {
            return "";
        }
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(aZ.getInputStream()));
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    try {
                        com.xiaopeng.lib.utils.c.f("ProcessUtil", "getShellCmdResult result:" + readLine);
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        aZ.destroy();
                        return readLine;
                    } catch (Exception e3) {
                        e = e3;
                        bufferedReader2 = bufferedReader;
                        str2 = readLine;
                        bufferedReader3 = bufferedReader2;
                        com.xiaopeng.lib.utils.c.f("ProcessUtil", "fail to getShellCmdResult");
                        e.printStackTrace();
                        if (bufferedReader3 != null) {
                            try {
                                bufferedReader3.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        aZ.destroy();
                        return str2;
                    }
                } catch (Exception e5) {
                    bufferedReader2 = bufferedReader;
                    str2 = "";
                    e = e5;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader3 = bufferedReader;
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                aZ.destroy();
                throw th;
            }
        } catch (Exception e7) {
            str2 = "";
            e = e7;
        }
    }

    public static void f(final Context context, final String str) {
        com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$l$F-cZ-_kcgtDs5IBJSl3rQRBZ_ug
            @Override // java.lang.Runnable
            public final void run() {
                l.g(context, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(Context context, String str) {
        ((PowerManager) context.getSystemService("power")).reboot(str);
    }

    public static void bc(String str) {
        i.aX(str);
        i.aW(str);
        ba(m.i("root", m.ps, "/data/etc/ssh/id_rsa"));
        ba(m.a("root", m.ps, "/data/etc/ssh/id_rsa", "/mnt/sdcard/tbox_all_log.tar.gz", com.xiaopeng.commonfunc.a.nA));
        ba(m.a("root", m.ps, "/mnt/sdcard/tbox_all_log.tar.gz", str, "/data/etc/ssh/id_rsa"));
        ba(m.c("root", m.ps, "/mnt/sdcard/tbox_all_log.tar.gz", "/data/etc/ssh/id_rsa"));
    }
}
