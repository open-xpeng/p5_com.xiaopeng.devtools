package com.xiaopeng.lib.utils.c;

import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/* compiled from: AppInfoUtils.java */
/* loaded from: classes12.dex */
public class a {
    private static String Xd = null;

    public static String getProcessName() {
        if (Xd != null) {
            return Xd;
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
                try {
                    Xd = bufferedReader2.readLine().trim();
                    com.xiaopeng.lib.utils.b.closeQuietly(bufferedReader2);
                } catch (Exception e) {
                    e = e;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    Xd = "";
                    com.xiaopeng.lib.utils.b.closeQuietly(bufferedReader);
                    return Xd;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    com.xiaopeng.lib.utils.b.closeQuietly(bufferedReader);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return Xd;
    }
}
