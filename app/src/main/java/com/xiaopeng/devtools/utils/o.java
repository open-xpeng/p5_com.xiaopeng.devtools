package com.xiaopeng.devtools.utils;

import android.text.TextUtils;

/* compiled from: ScpUtil.java */
/* loaded from: classes12.dex */
public class o {
    public static String a(String str, String str2, String[] strArr, String str3, String str4) {
        if (strArr.length <= 0 || TextUtils.isEmpty(str3)) {
            com.xiaopeng.lib.utils.c.i("ScpUtil", "srcLogPath is empty");
            return null;
        }
        StringBuilder sb = new StringBuilder("/system/bin/scp");
        sb.append(" -v -o StrictHostKeyChecking=no -r ");
        if (g.aY(str4)) {
            sb.append(" -i ");
            sb.append(str4);
            sb.append(" ");
        }
        for (String str5 : strArr) {
            sb.append(str);
            sb.append("@");
            sb.append(str2);
            sb.append(":");
            sb.append(str5);
            sb.append(" ");
        }
        sb.append(str3);
        return sb.toString();
    }
}
