package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: OfflineLocManager.java */
/* loaded from: classes11.dex */
public class bl {
    public static void a(Context context) {
        try {
            if (!e(context)) {
                return;
            }
            a(context, System.currentTimeMillis());
            String b = b(context);
            if (TextUtils.isEmpty(b)) {
                return;
            }
            bb.bw().b(new q(m.p(m.a(b)), "6"));
        } catch (Throwable th) {
            o.a(th, "OfflineLocManager", "updateOfflineLocData");
        }
    }

    private static void a(Context context, long j) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            File file = new File(p.a(context, "f.log"));
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(m.a(String.valueOf(j)));
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        } catch (Throwable th3) {
            fileOutputStream2 = fileOutputStream;
            th = th3;
            try {
                o.a(th, "OfflineLocManager", "updateLogUpdateTime");
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            } catch (Throwable th5) {
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Throwable th6) {
                        th6.printStackTrace();
                    }
                }
                throw th5;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    private static String a(String str) {
        ax axVar;
        String[] list;
        StringBuilder sb = new StringBuilder();
        ax axVar2 = 0;
        try {
            try {
                boolean z = true;
                axVar = ax.a(new File(str), 1, 1, 204800L);
                try {
                    axVar2 = new File(str);
                    if (axVar2.exists()) {
                        for (String str2 : axVar2.list()) {
                            if (str2.contains(".0")) {
                                String a = m.a(bn.a(axVar, str2.split("\\.")[0]));
                                if (z) {
                                    z = false;
                                } else {
                                    sb.append(",");
                                }
                                sb.append("{\"log\":\"");
                                sb.append(a);
                                sb.append("\"}");
                            }
                        }
                    }
                    if (axVar != null) {
                        try {
                            axVar.close();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e = e;
                    axVar2 = axVar;
                    o.a(e, "StatisticsManager", "getContent");
                    if (axVar2 != null) {
                        try {
                            axVar2.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                    return sb.toString();
                } catch (Throwable th3) {
                    th = th3;
                    if (axVar != null) {
                        try {
                            axVar.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            } catch (Throwable th5) {
                th = th5;
            }
            return sb.toString();
        } catch (Throwable th6) {
            th = th6;
            axVar = axVar2;
        }
    }

    private static String b(Context context) {
        String a = a(p.a(context, p.f));
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        String f = f(context);
        return "{\"pinfo\":\"" + f + "\",\"els\":[" + a + "]}";
    }

    private static int n(Context context) {
        try {
            File file = new File(p.a(context, p.f));
            if (!file.exists()) {
                return 0;
            }
            return file.list().length;
        } catch (Throwable th) {
            o.a(th, "OfflineLocManager", "getFileNum");
            return 0;
        }
    }

    private static long L(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        File file = new File(p.a(context, "f.log"));
        if (!file.exists()) {
            return 0L;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                long parseLong = Long.parseLong(m.a(bArr));
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return parseLong;
            } catch (Throwable th3) {
                th = th3;
                try {
                    o.a(th, "OfflineLocManager", "getUpdateTime");
                    if (file.exists()) {
                        file.delete();
                    }
                    return System.currentTimeMillis();
                } finally {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th5) {
            fileInputStream = null;
            th = th5;
        }
    }

    private static boolean e(Context context) {
        try {
            if (g.s(context) == 1) {
                if (System.currentTimeMillis() - L(context) > 604800000) {
                    return true;
                }
                return n(context) >= 100;
            }
        } catch (Throwable th) {
            o.a(th, "StatisticsManager", "isUpdate");
        }
        return false;
    }

    private static String f(Context context) {
        return f.b(context, m.a(g(context)));
    }

    private static String g(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"");
            sb.append(d.f(context));
            sb.append("\",\"platform\":\"android\",\"diu\":\"");
            sb.append(g.w(context));
            sb.append("\",\"mac\":\"");
            sb.append(g.i(context));
            sb.append("\",\"tid\":\"");
            sb.append(g.f(context));
            sb.append("\",\"manufacture\":\"");
            sb.append(Build.MANUFACTURER);
            sb.append("\",\"device\":\"");
            sb.append(Build.DEVICE);
            sb.append("\",\"sim\":\"");
            sb.append(g.x(context));
            sb.append("\",\"pkg\":\"");
            sb.append(d.c(context));
            sb.append("\",\"model\":\"");
            sb.append(Build.MODEL);
            sb.append("\",\"appversion\":\"");
            sb.append(d.d(context));
            sb.append("\"");
        } catch (Throwable th) {
            o.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }
}
