package com.alibaba.sdk.android.man.crashreporter.a.a.a.b;

import android.os.Build;
import android.os.Process;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.e.f;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes11.dex */
public class a {
    public static final int o = 8192;
    public static int p = 100;
    public static int q = 100;
    public static final int r = 10000;

    public static String a(String... strArr) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(strArr).getInputStream()), 8192);
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                } catch (IOException e) {
                    bufferedReader = bufferedReader2;
                    f.a(bufferedReader);
                    return sb.toString();
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    f.a(bufferedReader);
                    throw th;
                }
            }
            f.a(bufferedReader2);
        } catch (IOException e2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return sb.toString();
    }

    public static String f() {
        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);
            f.a(fileReader);
            f.a(bufferedReader);
            return "";
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("read /proc/cpuinfo error.", e);
            return "";
        }
    }

    public static String g() {
        return a("dumpsys", "meminfo", Integer.toString(Process.myPid()));
    }

    public static String a(String str, boolean z) {
        String str2;
        BufferedReader bufferedReader;
        ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
        if (configure != null) {
            q = configure.enableSysLogcatMaxCount;
            p = configure.enableSysLogcatLinkMaxCount;
            if (q == 0 || p == 0) {
                return "";
            }
        }
        int myPid = Process.myPid();
        BufferedReader bufferedReader2 = null;
        if (!z || myPid <= 0) {
            str2 = null;
        } else {
            str2 = Integer.toString(myPid) + "):";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("logcat");
        if (i.b((CharSequence) str)) {
            arrayList.add("-b");
            arrayList.add(str);
        }
        int i = 0;
        List asList = Arrays.asList("-t", Integer.toString(q), "-v", "time");
        int indexOf = asList.indexOf("-t");
        int i2 = -1;
        if (indexOf > -1 && indexOf < asList.size()) {
            int i3 = indexOf + 1;
            int parseInt = Integer.parseInt((String) asList.get(i3));
            if (Build.VERSION.SDK_INT < 8) {
                asList.remove(i3);
                asList.remove(indexOf);
                asList.add("-d");
            }
            i2 = parseInt;
        }
        if (i2 <= 0) {
            i2 = p;
        }
        com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.a aVar = new com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.a(i2);
        arrayList.addAll(asList);
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((String[]) arrayList.toArray(new String[arrayList.size()])).getInputStream()), 8192);
                try {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("collectLogCat Retrieving logcat output...");
                    if (!i.b((CharSequence) str)) {
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null || i >= p) {
                                break;
                            }
                            if ((str2 == null || readLine.contains(str2)) && ((readLine.contains("W/") || readLine.contains("E/")) && !readLine.contains("com.alibaba.motu.crashreporter"))) {
                                aVar.add(readLine + "\n");
                            }
                            i++;
                        }
                    } else {
                        while (true) {
                            String readLine2 = bufferedReader.readLine();
                            if (readLine2 == null || i >= p) {
                                break;
                            }
                            if (str2 == null || readLine2.contains(str2)) {
                                aVar.add(readLine2 + "\n");
                            }
                            i++;
                        }
                    }
                    f.a(bufferedReader);
                } catch (IOException e) {
                    e = e;
                    bufferedReader2 = bufferedReader;
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("MotuLogProber could not retrieve data", e);
                    f.a(bufferedReader2);
                    return aVar.toString();
                } catch (Throwable th) {
                    th = th;
                    f.a(bufferedReader);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        return aVar.toString();
    }
}
