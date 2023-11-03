package com.xiaopeng.devtools.utils;

import android.content.Context;
import android.os.PowerManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* compiled from: ProcessUtil.java */
/* loaded from: classes12.dex */
public class m {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] cD(java.lang.String r8) {
        /*
            r0 = 0
            java.lang.String r1 = "ProcessUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r2.<init>()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.String r3 = "getProcessInfo processName = ps -elf | grep "
            r2.append(r3)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r2.append(r8)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            com.xiaopeng.lib.utils.c.f(r1, r2)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r3 = 0
            java.lang.String r4 = "/bin/sh"
            r2[r3] = r4     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r3 = 1
            java.lang.String r4 = "-c"
            r2[r3] = r4     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r3 = 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r4.<init>()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.String r5 = "ps -elf | grep "
            r4.append(r5)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r4.append(r8)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r2[r3] = r4     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8b java.io.IOException -> L8f
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L8b java.io.IOException -> L8f
            java.io.InputStream r4 = r1.getInputStream()     // Catch: java.lang.Throwable -> L8b java.io.IOException -> L8f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L8b java.io.IOException -> L8f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L8b java.io.IOException -> L8f
        L51:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            if (r3 == 0) goto L7b
            java.lang.String r4 = "ProcessUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            r5.<init>()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            java.lang.String r6 = "getProcessInfo result "
            r5.append(r6)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            r5.append(r3)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            com.xiaopeng.lib.utils.c.f(r4, r5)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            boolean r4 = r3.contains(r8)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            if (r4 == 0) goto L51
            java.lang.String r4 = "\\s+"
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Throwable -> L84 java.io.IOException -> L86
            r0 = r3
            goto L51
        L7b:
            if (r1 == 0) goto L80
            r1.destroy()
        L80:
            com.xiaopeng.devtools.utils.k.closeQuietly(r2)
            goto La7
        L84:
            r8 = move-exception
            goto L8d
        L86:
            r3 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L9a
        L8b:
            r8 = move-exception
            r2 = r0
        L8d:
            r0 = r1
            goto Lc7
        L8f:
            r3 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L9a
        L94:
            r8 = move-exception
            r2 = r0
            goto Lc7
        L97:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L9a:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> Lc6
            if (r0 == 0) goto La2
            r0.destroy()
        La2:
            com.xiaopeng.devtools.utils.k.closeQuietly(r2)
            r0 = r1
        La7:
            java.lang.String r1 = "ProcessUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "getProcessInfo processName = "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r8 = ", res = "
            r2.append(r8)
            r2.append(r0)
            java.lang.String r8 = r2.toString()
            com.xiaopeng.lib.utils.c.f(r1, r8)
            return r0
        Lc6:
            r8 = move-exception
        Lc7:
            if (r0 == 0) goto Lcc
            r0.destroy()
        Lcc:
            com.xiaopeng.devtools.utils.k.closeQuietly(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.utils.m.cD(java.lang.String):java.lang.String[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] Q(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.utils.m.Q(java.lang.String, java.lang.String):java.lang.String[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0072, code lost:
        if (r6 == null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void cE(java.lang.String r6) {
        /*
            java.lang.String[] r0 = cD(r6)
            java.lang.String r1 = "ProcessUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "killProcess processName = "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = ",  mProcessInfo = "
            r2.append(r6)
            java.lang.String r6 = java.util.Arrays.toString(r0)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.xiaopeng.lib.utils.c.f(r1, r6)
            if (r0 == 0) goto L7e
            int r6 = r0.length
            r1 = 1
            if (r6 <= r1) goto L7e
            r6 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            r3.<init>()     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            java.lang.String r4 = "kill -9 "
            r3.append(r4)     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            r0 = r0[r1]     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            r3.append(r0)     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            java.lang.Process r0 = r2.exec(r0)     // Catch: java.lang.Throwable -> L65 java.lang.InterruptedException -> L67 java.io.IOException -> L6e
            r0.waitFor()     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L5b java.io.IOException -> L60
            if (r0 == 0) goto L7e
            r0.destroy()
            goto L7e
        L56:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L78
        L5b:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L68
        L60:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L6f
        L65:
            r0 = move-exception
            goto L78
        L67:
            r0 = move-exception
        L68:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L65
            if (r6 == 0) goto L7e
            goto L74
        L6e:
            r0 = move-exception
        L6f:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L65
            if (r6 == 0) goto L7e
        L74:
            r6.destroy()
            goto L7e
        L78:
            if (r6 == 0) goto L7d
            r6.destroy()
        L7d:
            throw r0
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.utils.m.cE(java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x007a, code lost:
        if (r5 == null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void R(java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.String[] r0 = Q(r5, r6)
            java.lang.String r1 = "ProcessUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "killProcess processName = "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r5 = ", param = "
            r2.append(r5)
            r2.append(r6)
            java.lang.String r5 = ",  mProcessInfo = "
            r2.append(r5)
            java.lang.String r5 = java.util.Arrays.toString(r0)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.xiaopeng.lib.utils.c.f(r1, r5)
            if (r0 == 0) goto L86
            int r5 = r0.length
            r6 = 1
            if (r5 <= r6) goto L86
            r5 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            r2.<init>()     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            java.lang.String r3 = "kill -9 "
            r2.append(r3)     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            r6 = r0[r6]     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            int r6 = java.lang.Integer.parseInt(r6)     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            r2.append(r6)     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            java.lang.Process r6 = r1.exec(r6)     // Catch: java.lang.Throwable -> L6d java.lang.InterruptedException -> L6f java.io.IOException -> L76
            r6.waitFor()     // Catch: java.lang.Throwable -> L5e java.lang.InterruptedException -> L63 java.io.IOException -> L68
            if (r6 == 0) goto L86
            r6.destroy()
            goto L86
        L5e:
            r5 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
            goto L80
        L63:
            r5 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
            goto L70
        L68:
            r5 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
            goto L77
        L6d:
            r6 = move-exception
            goto L80
        L6f:
            r6 = move-exception
        L70:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            if (r5 == 0) goto L86
            goto L7c
        L76:
            r6 = move-exception
        L77:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            if (r5 == 0) goto L86
        L7c:
            r5.destroy()
            goto L86
        L80:
            if (r5 == 0) goto L85
            r5.destroy()
        L85:
            throw r6
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.utils.m.R(java.lang.String, java.lang.String):void");
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
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0078  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.Closeable, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Process ba(java.lang.String r8) {
        /*
            r0 = 0
            java.lang.Process r8 = aZ(r8)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L5b
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L52
            java.io.InputStream r2 = r8.getInputStream()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L52
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L52
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4a
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L4a
        L17:
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            if (r3 == 0) goto L34
            java.lang.String r4 = "ProcessUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            r5.<init>()     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            java.lang.String r6 = "readLine(): "
            r5.append(r6)     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            r5.append(r3)     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            com.xiaopeng.lib.utils.c.f(r4, r3)     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            goto L17
        L34:
            r8.waitFor()     // Catch: java.lang.Exception -> L43 java.lang.Throwable -> L6f
            com.xiaopeng.devtools.utils.k.closeQuietly(r1)
            com.xiaopeng.devtools.utils.k.closeQuietly(r2)
            if (r8 == 0) goto L42
            r8.destroy()
        L42:
            return r8
        L43:
            r3 = move-exception
            goto L5f
        L45:
            r2 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L70
        L4a:
            r3 = move-exception
            r2 = r0
            goto L5f
        L4d:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L70
        L52:
            r3 = move-exception
            r1 = r0
            goto L5e
        L55:
            r8 = move-exception
            r1 = r0
            r2 = r1
            r0 = r8
            r8 = r2
            goto L70
        L5b:
            r3 = move-exception
            r8 = r0
            r1 = r8
        L5e:
            r2 = r1
        L5f:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L6f
            com.xiaopeng.devtools.utils.k.closeQuietly(r1)
            com.xiaopeng.devtools.utils.k.closeQuietly(r2)
            if (r8 == 0) goto L6e
            r8.destroy()
        L6e:
            return r0
        L6f:
            r0 = move-exception
        L70:
            com.xiaopeng.devtools.utils.k.closeQuietly(r1)
            com.xiaopeng.devtools.utils.k.closeQuietly(r2)
            if (r8 == 0) goto L7b
            r8.destroy()
        L7b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.utils.m.ba(java.lang.String):java.lang.Process");
    }

    public static String eF() {
        return bb("phytool get link_stat");
    }

    public static String lf() {
        return bb("phytool get master");
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

    /* JADX WARN: Code restructure failed: missing block: B:18:0x009c, code lost:
        if (r0.toString().indexOf("TTL") > 0) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean cF(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.utils.m.cF(java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Process process) {
        com.xiaopeng.lib.utils.c.f("ProcessUtil", "sendPing timeout");
        process.destroy();
    }

    public static void f(final Context context, final String str) {
        com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.devtools.utils.-$$Lambda$m$ztq6ABE4BhxWl7Xazi9q5cAHMyg
            @Override // java.lang.Runnable
            public final void run() {
                m.g(context, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(Context context, String str) {
        ((PowerManager) context.getSystemService("power")).reboot(str);
    }
}
