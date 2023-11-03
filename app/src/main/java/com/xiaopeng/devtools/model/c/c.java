package com.xiaopeng.devtools.model.c;

import android.os.SystemProperties;
import android.text.TextUtils;

/* compiled from: FactoryTestUtils.java */
/* loaded from: classes12.dex */
public class c {
    public static boolean bs(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !str.startsWith("*#0000*") && str.length() > 4 && str.startsWith("*#") && str.endsWith("#*");
    }

    public static boolean a(String str, String str2, boolean z) {
        String[] split = SystemProperties.get(str, "").split(",");
        String str3 = "";
        boolean z2 = false;
        for (int i = 0; i < split.length; i++) {
            try {
                if (split[i].contains(str2)) {
                    String[] split2 = split[i].split(":");
                    split2[1] = z ? "0" : String.valueOf(Integer.valueOf(split2[1]).intValue() + 1);
                    split[i] = split2[0] + ":" + split2[1];
                    z2 = true;
                }
                str3 = split[i] + ",";
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (!z2) {
            str3 = str3 + str2 + ":1";
        }
        if (str3.endsWith(",")) {
            str3 = str3.substring(0, str3.length() - 1);
        }
        SystemProperties.set(str, str3);
        return true;
    }
}
