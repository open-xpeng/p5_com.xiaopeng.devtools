package com.amap.api.services.a;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log.java */
/* loaded from: classes11.dex */
public class p {
    public static final String a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    public static final String e = "e";
    public static final String f = "f";

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + a + str;
    }

    public static Class<? extends ag> G(int i) {
        switch (i) {
            case 0:
                return ab.class;
            case 1:
                return ad.class;
            case 2:
                return aa.class;
            default:
                return null;
        }
    }

    public static ag H(int i) {
        switch (i) {
            case 0:
                return new ab();
            case 1:
                return new ad();
            case 2:
                return new aa();
            default:
                return null;
        }
    }

    public static String I(int i) {
        switch (i) {
            case 0:
                return c;
            case 1:
                return b;
            case 2:
                return d;
            default:
                return "";
        }
    }

    static v a(Context context, int i) {
        switch (i) {
            case 0:
                return new t(i);
            case 1:
                return new u(i);
            case 2:
                return new s(i);
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(final Context context, final Throwable th, final int i, final String str, final String str2) {
        try {
            ExecutorService bo = r.bo();
            if (bo != null && !bo.isShutdown()) {
                bo.submit(new Runnable() { // from class: com.amap.api.services.a.p.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            v a2 = p.a(context, i);
                            if (a2 == null) {
                                return;
                            }
                            a2.a(context, th, str, str2);
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e2) {
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context) {
        try {
            v a2 = a(context, 2);
            if (a2 == null) {
                return;
            }
            a2.b(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(final Context context) {
        try {
            ExecutorService bo = r.bo();
            if (bo != null && !bo.isShutdown()) {
                bo.submit(new Runnable() { // from class: com.amap.api.services.a.p.2
                    /* JADX WARN: Removed duplicated region for block: B:55:0x009c  */
                    /* JADX WARN: Removed duplicated region for block: B:57:0x00a1  */
                    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
                    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void run() {
                        /*
                            r7 = this;
                            r0 = 0
                            android.content.Context r1 = r1     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L66 java.util.concurrent.RejectedExecutionException -> L95
                            r2 = 0
                            com.amap.api.services.a.v r1 = com.amap.api.services.a.p.a(r1, r2)     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> L66 java.util.concurrent.RejectedExecutionException -> L95
                            android.content.Context r2 = r1     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L58 java.util.concurrent.RejectedExecutionException -> L5d
                            r3 = 1
                            com.amap.api.services.a.v r2 = com.amap.api.services.a.p.a(r2, r3)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L58 java.util.concurrent.RejectedExecutionException -> L5d
                            android.content.Context r3 = r1     // Catch: java.lang.Throwable -> L46 java.lang.Throwable -> L4b java.util.concurrent.RejectedExecutionException -> L50
                            r4 = 2
                            com.amap.api.services.a.v r3 = com.amap.api.services.a.p.a(r3, r4)     // Catch: java.lang.Throwable -> L46 java.lang.Throwable -> L4b java.util.concurrent.RejectedExecutionException -> L50
                            android.content.Context r0 = r1     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            r1.c(r0)     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            android.content.Context r0 = r1     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            r2.c(r0)     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            android.content.Context r0 = r1     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            r3.c(r0)     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            android.content.Context r0 = r1     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            com.amap.api.services.a.bm.a(r0)     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            android.content.Context r0 = r1     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            com.amap.api.services.a.bl.a(r0)     // Catch: java.lang.Throwable -> L41 java.util.concurrent.RejectedExecutionException -> L43 java.lang.Throwable -> L84
                            if (r1 == 0) goto L38
                            r1.c()
                        L38:
                            if (r2 == 0) goto L3d
                            r2.c()
                        L3d:
                            if (r3 == 0) goto L82
                            goto L7f
                        L41:
                            r0 = move-exception
                            goto L6b
                        L43:
                            r0 = move-exception
                            goto L99
                        L46:
                            r3 = move-exception
                            r6 = r3
                            r3 = r0
                            r0 = r6
                            goto L85
                        L4b:
                            r3 = move-exception
                            r6 = r3
                            r3 = r0
                            r0 = r6
                            goto L6b
                        L50:
                            r3 = move-exception
                            r3 = r0
                            goto L99
                        L53:
                            r2 = move-exception
                            r3 = r0
                            r0 = r2
                            r2 = r3
                            goto L85
                        L58:
                            r2 = move-exception
                            r3 = r0
                            r0 = r2
                            r2 = r3
                            goto L6b
                        L5d:
                            r2 = move-exception
                            r2 = r0
                            goto L98
                        L60:
                            r1 = move-exception
                            r2 = r0
                            r3 = r2
                            r0 = r1
                            r1 = r3
                            goto L85
                        L66:
                            r1 = move-exception
                            r2 = r0
                            r3 = r2
                            r0 = r1
                            r1 = r3
                        L6b:
                            java.lang.String r4 = "Log"
                            java.lang.String r5 = "processLog"
                            com.amap.api.services.a.o.a(r0, r4, r5)     // Catch: java.lang.Throwable -> L84
                            if (r1 == 0) goto L78
                            r1.c()
                        L78:
                            if (r2 == 0) goto L7d
                            r2.c()
                        L7d:
                            if (r3 == 0) goto L82
                        L7f:
                            r3.c()
                        L82:
                            return
                        L84:
                            r0 = move-exception
                        L85:
                            if (r1 == 0) goto L8a
                            r1.c()
                        L8a:
                            if (r2 == 0) goto L8f
                            r2.c()
                        L8f:
                            if (r3 == 0) goto L94
                            r3.c()
                        L94:
                            throw r0
                        L95:
                            r1 = move-exception
                            r1 = r0
                            r2 = r1
                        L98:
                            r3 = r2
                        L99:
                            if (r1 == 0) goto L9f
                            r1.c()
                        L9f:
                            if (r2 == 0) goto La4
                            r2.c()
                        La4:
                            if (r3 == 0) goto La9
                            r3.c()
                        La9:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.p.AnonymousClass2.run():void");
                    }
                });
            }
        } catch (Throwable th) {
            o.a(th, "Log", "processLog");
        }
    }
}
