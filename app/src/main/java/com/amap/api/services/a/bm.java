package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* compiled from: StatisticsManager.java */
/* loaded from: classes11.dex */
public class bm {
    private static boolean a = true;

    private static byte[] M(Context context) {
        byte[] N = N(context);
        byte[] O = O(context);
        byte[] bArr = new byte[N.length + O.length];
        System.arraycopy(N, 0, bArr, 0, N.length);
        System.arraycopy(O, 0, bArr, N.length, O.length);
        return a(context, bArr);
    }

    public static void a(Context context) {
        try {
            if (!Q(context)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
            stringBuffer.append(" ");
            stringBuffer.append(UUID.randomUUID().toString());
            stringBuffer.append(" ");
            if (stringBuffer.length() != 53) {
                return;
            }
            byte[] a2 = m.a(stringBuffer.toString());
            byte[] M = M(context);
            byte[] bArr = new byte[a2.length + M.length];
            System.arraycopy(a2, 0, bArr, 0, a2.length);
            System.arraycopy(M, 0, bArr, a2.length, M.length);
            bb.bw().b(new q(m.p(bArr), "2"));
        } catch (Throwable th) {
            o.a(th, "StatisticsManager", "updateStaticsData");
        }
    }

    private static byte[] a(Context context, byte[] bArr) {
        try {
            return f.a(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] N(Context context) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            m.a(byteArrayOutputStream, "1.2.13.6");
            m.a(byteArrayOutputStream, "Android");
            m.a(byteArrayOutputStream, g.w(context));
            m.a(byteArrayOutputStream, g.i(context));
            m.a(byteArrayOutputStream, g.f(context));
            m.a(byteArrayOutputStream, Build.MANUFACTURER);
            m.a(byteArrayOutputStream, Build.MODEL);
            m.a(byteArrayOutputStream, Build.DEVICE);
            m.a(byteArrayOutputStream, g.x(context));
            m.a(byteArrayOutputStream, d.c(context));
            m.a(byteArrayOutputStream, d.d(context));
            m.a(byteArrayOutputStream, d.f(context));
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                th.printStackTrace();
                return byteArray;
            }
        } catch (Throwable th2) {
            try {
                o.a(th2, "StatisticsManager", "getHeader");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                return bArr;
            } catch (Throwable th4) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
                throw th4;
            }
        }
    }

    private static int o(Context context) {
        try {
            File file = new File(p.a(context, p.e));
            if (!file.exists()) {
                return 0;
            }
            return file.list().length;
        } catch (Throwable th) {
            o.a(th, "StatisticsManager", "getFileNum");
            return 0;
        }
    }

    private static byte[] O(Context context) {
        ax axVar;
        String[] list;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        String a2 = p.a(context, p.e);
        ax axVar2 = null;
        try {
            try {
                axVar = ax.a(new File(a2), 1, 1, OSSConstants.MIN_PART_SIZE_LIMIT);
                try {
                    File file = new File(a2);
                    if (file.exists()) {
                        for (String str : file.list()) {
                            if (str.contains(".0")) {
                                byteArrayOutputStream.write(bn.a(axVar, str.split("\\.")[0]));
                            }
                        }
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (axVar != null) {
                        try {
                            axVar.close();
                            return byteArray;
                        } catch (Throwable th) {
                            th.printStackTrace();
                            return byteArray;
                        }
                    }
                    return byteArray;
                } catch (IOException e2) {
                    e = e2;
                    axVar2 = axVar;
                    o.a(e, "StatisticsManager", "getContent");
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    if (axVar2 != null) {
                        try {
                            axVar2.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    if (axVar != null) {
                        try {
                            axVar.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (Throwable th6) {
            th = th6;
            axVar = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(android.content.Context r2, long r3) {
        /*
            java.lang.String r0 = "c.log"
            java.lang.String r2 = com.amap.api.services.a.p.a(r2, r0)
            java.io.File r0 = new java.io.File
            r0.<init>(r2)
            java.io.File r2 = r0.getParentFile()
            boolean r2 = r2.exists()
            if (r2 != 0) goto L1c
            java.io.File r2 = r0.getParentFile()
            r2.mkdirs()
        L1c:
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L40 java.io.FileNotFoundException -> L52
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L40 java.io.FileNotFoundException -> L52
            java.lang.String r2 = java.lang.String.valueOf(r3)     // Catch: java.io.IOException -> L38 java.io.FileNotFoundException -> L3a java.lang.Throwable -> L66
            byte[] r2 = com.amap.api.services.a.m.a(r2)     // Catch: java.io.IOException -> L38 java.io.FileNotFoundException -> L3a java.lang.Throwable -> L66
            r1.write(r2)     // Catch: java.io.IOException -> L38 java.io.FileNotFoundException -> L3a java.lang.Throwable -> L66
            r1.close()     // Catch: java.lang.Throwable -> L33
            goto L63
        L33:
            r2 = move-exception
            r2.printStackTrace()
            goto L63
        L38:
            r2 = move-exception
            goto L43
        L3a:
            r2 = move-exception
            goto L55
        L3c:
            r3 = move-exception
            r1 = r2
            r2 = r3
            goto L67
        L40:
            r3 = move-exception
            r1 = r2
            r2 = r3
        L43:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L66
            if (r1 == 0) goto L63
            r1.close()     // Catch: java.lang.Throwable -> L4d
            goto L63
        L4d:
            r2 = move-exception
            r2.printStackTrace()
            goto L63
        L52:
            r3 = move-exception
            r1 = r2
            r2 = r3
        L55:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L66
            if (r1 == 0) goto L63
            r1.close()     // Catch: java.lang.Throwable -> L5f
            goto L63
        L5f:
            r2 = move-exception
            r2.printStackTrace()
        L63:
            return
        L66:
            r2 = move-exception
        L67:
            if (r1 == 0) goto L71
            r1.close()     // Catch: java.lang.Throwable -> L6d
            goto L71
        L6d:
            r3 = move-exception
            r3.printStackTrace()
        L71:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bm.a(android.content.Context, long):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static long P(android.content.Context r7) {
        /*
            java.lang.String r0 = "c.log"
            java.lang.String r7 = com.amap.api.services.a.p.a(r7, r0)
            java.io.File r0 = new java.io.File
            r0.<init>(r7)
            boolean r7 = r0.exists()
            r1 = 0
            if (r7 != 0) goto L14
            return r1
        L14:
            r7 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3d java.lang.Throwable -> L41 java.io.IOException -> L68 java.io.FileNotFoundException -> L7e
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L3d java.lang.Throwable -> L41 java.io.IOException -> L68 java.io.FileNotFoundException -> L7e
            int r7 = r3.available()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.lang.Throwable -> L96
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.lang.Throwable -> L96
            r3.read(r7)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.lang.Throwable -> L96
            java.lang.String r7 = com.amap.api.services.a.m.a(r7)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.lang.Throwable -> L96
            long r4 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.lang.Throwable -> L96
            r3.close()     // Catch: java.lang.Throwable -> L31
            goto L35
        L31:
            r7 = move-exception
            r7.printStackTrace()
        L35:
            return r4
        L37:
            r7 = move-exception
            goto L45
        L39:
            r7 = move-exception
            goto L6b
        L3b:
            r7 = move-exception
            goto L81
        L3d:
            r0 = move-exception
            r3 = r7
            r7 = r0
            goto L97
        L41:
            r3 = move-exception
            r6 = r3
            r3 = r7
            r7 = r6
        L45:
            java.lang.String r4 = "StatisticsManager"
            java.lang.String r5 = "getUpdateTime"
            com.amap.api.services.a.o.a(r7, r4, r5)     // Catch: java.lang.Throwable -> L96
            boolean r7 = r0.exists()     // Catch: java.lang.Throwable -> L56
            if (r7 == 0) goto L5b
            r0.delete()     // Catch: java.lang.Throwable -> L56
            goto L5b
        L56:
            r7 = move-exception
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L96
            goto L5c
        L5b:
        L5c:
            if (r3 == 0) goto L93
            r3.close()     // Catch: java.lang.Throwable -> L63
            goto L93
        L63:
            r7 = move-exception
            r7.printStackTrace()
            goto L93
        L68:
            r0 = move-exception
            r3 = r7
            r7 = r0
        L6b:
            java.lang.String r0 = "StatisticsManager"
            java.lang.String r4 = "getUpdateTime"
            com.amap.api.services.a.o.a(r7, r0, r4)     // Catch: java.lang.Throwable -> L96
            if (r3 == 0) goto L93
            r3.close()     // Catch: java.lang.Throwable -> L79
            goto L93
        L79:
            r7 = move-exception
            r7.printStackTrace()
            goto L93
        L7e:
            r0 = move-exception
            r3 = r7
            r7 = r0
        L81:
            java.lang.String r0 = "StatisticsManager"
            java.lang.String r4 = "getUpdateTime"
            com.amap.api.services.a.o.a(r7, r0, r4)     // Catch: java.lang.Throwable -> L96
            if (r3 == 0) goto L93
            r3.close()     // Catch: java.lang.Throwable -> L8f
            goto L93
        L8f:
            r7 = move-exception
            r7.printStackTrace()
        L93:
            return r1
        L96:
            r7 = move-exception
        L97:
            if (r3 == 0) goto La1
            r3.close()     // Catch: java.lang.Throwable -> L9d
            goto La1
        L9d:
            r0 = move-exception
            r0.printStackTrace()
        La1:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bm.P(android.content.Context):long");
    }

    private static boolean Q(Context context) {
        try {
            if (g.s(context) != 1 || !a || o(context) < 100) {
                return false;
            }
            long P = P(context);
            long time = new Date().getTime();
            if (time - P < 3600000) {
                return false;
            }
            a(context, time);
            a = false;
            return true;
        } catch (Throwable th) {
            o.a(th, "StatisticsManager", "isUpdate");
        }
        return false;
    }
}
