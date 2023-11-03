package com.alibaba.sdk.android.utils.crashdefend;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CrashDefendUtils.java */
/* loaded from: classes11.dex */
class e {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, a aVar, List<c> list) {
        String str;
        String str2;
        if (context == null) {
            return;
        }
        synchronized (list) {
            FileOutputStream fileOutputStream = null;
            try {
                JSONObject jSONObject = new JSONObject();
                if (aVar != null) {
                    jSONObject.put("startSerialNumber", aVar.a);
                }
                if (list != null) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        for (c cVar : list) {
                            if (cVar != null) {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("sdkId", cVar.f113c);
                                jSONObject2.put("sdkVersion", cVar.f114d);
                                jSONObject2.put("crashLimit", cVar.b);
                                jSONObject2.put("crashCount", cVar.crashCount);
                                jSONObject2.put("waitTime", cVar.c);
                                jSONObject2.put("registerSerialNumber", cVar.f112b);
                                jSONObject2.put("startSerialNumber", cVar.a);
                                jSONObject2.put("restoreCount", cVar.d);
                                jSONArray.put(jSONObject2);
                            }
                        }
                        jSONObject.put("sdkList", jSONArray);
                    } catch (JSONException e) {
                        Log.e("CrashUtils", "save sdk json fail:", e);
                    }
                }
                String jSONObject3 = jSONObject.toString();
                fileOutputStream = a(context) ? context.openFileOutput("com_alibaba_aliyun_crash_defend_sdk_info", 0) : context.openFileOutput("com_alibaba_aliyun_crash_defend_sdk_info_" + b(context), 0);
                fileOutputStream.write(jSONObject3.getBytes());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        str = "CrashUtils";
                        str2 = "save sdk io fail:";
                        Log.e(str, str2, e);
                    }
                }
            } catch (IOException e3) {
                Log.e("CrashUtils", "save sdk io fail:", e3);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e = e4;
                        str = "CrashUtils";
                        str2 = "save sdk io fail:";
                        Log.e(str, str2, e);
                    }
                }
            } catch (Exception e5) {
                Log.e("CrashUtils", "save sdk exception:", e5);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e = e6;
                        str = "CrashUtils";
                        str2 = "save sdk io fail:";
                        Log.e(str, str2, e);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x009c A[Catch: all -> 0x0138, DONT_GENERATE, TRY_LEAVE, TryCatch #7 {, blocks: (B:19:0x004a, B:50:0x0096, B:52:0x009c, B:54:0x009e, B:55:0x00b8, B:57:0x00be, B:59:0x00c4, B:61:0x0117, B:68:0x012f, B:64:0x011e, B:66:0x0127, B:23:0x0053, B:30:0x0064, B:38:0x0078, B:46:0x008c, B:8:0x000c, B:10:0x0012, B:13:0x0034, B:14:0x0039, B:16:0x003f, B:12:0x001a, B:28:0x005b, B:36:0x006f, B:44:0x0083), top: B:83:0x000c, inners: #0, #12 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a  reason: collision with other method in class */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m67a(android.content.Context r9, com.alibaba.sdk.android.utils.crashdefend.a r10, java.util.List<com.alibaba.sdk.android.utils.crashdefend.c> r11) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.crashdefend.e.m67a(android.content.Context, com.alibaba.sdk.android.utils.crashdefend.a, java.util.List):boolean");
    }

    private static boolean a(Context context) {
        if (context.getPackageName().equalsIgnoreCase(b(context))) {
            return true;
        }
        return false;
    }

    private static String b(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        String d = d(context);
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        String c = c();
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        return c(context);
    }

    private static String c(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return "";
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String c() {
        /*
            int r0 = android.os.Process.myPid()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r3.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r4 = "/proc/"
            r3.append(r4)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r3.append(r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r0 = "/cmdline"
            r3.append(r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            boolean r0 = r2.exists()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            if (r0 == 0) goto L3e
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6b
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6b
            r1 = r2
            goto L3f
        L3c:
            r2 = move-exception
            goto L4e
        L3e:
            r0 = r1
        L3f:
            if (r0 == 0) goto L6a
            r0.close()     // Catch: java.io.IOException -> L45
        L44:
            goto L6a
        L45:
            r0 = move-exception
            r0.printStackTrace()
            goto L44
        L4a:
            r0 = move-exception
            goto L6f
        L4c:
            r2 = move-exception
            r0 = r1
        L4e:
            java.lang.String r3 = "CrashUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b
            r4.<init>()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r5 = "getProcessNameByPid error: "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6b
            r4.append(r2)     // Catch: java.lang.Throwable -> L6b
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L6b
            android.util.Log.d(r3, r2)     // Catch: java.lang.Throwable -> L6b
            if (r0 == 0) goto L6a
            r0.close()     // Catch: java.io.IOException -> L45
            goto L44
        L6a:
            return r1
        L6b:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L6f:
            if (r1 == 0) goto L79
            r1.close()     // Catch: java.io.IOException -> L75
            goto L79
        L75:
            r1 = move-exception
            r1.printStackTrace()
        L79:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.utils.crashdefend.e.c():java.lang.String");
    }

    private static String d(Context context) {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, context.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.d("CrashUtils", "getProcessNameByActivityThread error: " + e);
            return null;
        }
    }
}
