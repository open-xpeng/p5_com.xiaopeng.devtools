package com.xiaopeng.commonfunc.utils;

import android.text.TextUtils;
import com.xiaopeng.a.a;

/* compiled from: ScpUtil.java */
/* loaded from: classes11.dex */
public class m {
    public static final String ps = a.C0041a.getString("TBOX_CAN_DATA_SERVER_IP");

    public static String a(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            com.xiaopeng.lib.utils.c.i("ScpUtil", "path is empty");
            return null;
        }
        StringBuilder sb = new StringBuilder("/system/bin/scp");
        sb.append(" -v -o StrictHostKeyChecking=no -r ");
        if (i.aY(str5)) {
            sb.append(" -i ");
            sb.append(str5);
            sb.append(" ");
        }
        sb.append(str);
        sb.append("@");
        sb.append(str2);
        sb.append(":");
        sb.append(str3);
        sb.append(" ");
        sb.append(str4);
        return sb.toString();
    }

    public static String i(String str, String str2, String str3) {
        return d(str, str2, "test_remote_send DLT_SYNC", str3);
    }

    public static String a(String str, String str2, String str3, String str4, String[] strArr) {
        if (strArr == null || strArr.length <= 0 || TextUtils.isEmpty(str4)) {
            com.xiaopeng.lib.utils.c.i("ScpUtil", "srcPath or destPath is empty");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str5 : strArr) {
            sb.append(" ");
            sb.append(str5);
        }
        return d(str, str2, "tar -cvzf " + str4 + sb.toString(), str3);
    }

    public static String c(String str, String str2, String str3, String str4) {
        return d(str, str2, "rm -f " + str3, str4);
    }

    private static String d(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder("/system/bin/ssh");
        sb.append(" -v -o StrictHostKeyChecking=no ");
        if (i.aY(str4)) {
            sb.append(" -i ");
            sb.append(str4);
            sb.append(" ");
        }
        sb.append(str);
        sb.append("@");
        sb.append(str2);
        sb.append(" ");
        sb.append("\"");
        sb.append(str3);
        sb.append("\"");
        return sb.toString();
    }
}
