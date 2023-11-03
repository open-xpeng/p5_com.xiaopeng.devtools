package com.xiaopeng.lib.security;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Base64;

/* compiled from: SecurityCommon.java */
/* loaded from: classes12.dex */
public final class b {
    private static Boolean Vp;

    public static boolean am(@Nullable Context context) {
        if (Vp == null) {
            if (context == null) {
                return false;
            }
            try {
                Vp = Boolean.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).uid == 1000);
            } catch (Exception e) {
                com.xiaopeng.lib.utils.c.b("SecurityCommon", "init uid error!", e);
                Vp = false;
            }
        }
        return Vp.booleanValue();
    }

    public static String ol() {
        if (com.xiaopeng.lib.utils.c.b.pk()) {
            return "1";
        }
        return "2";
    }

    public static String E(byte[] bArr) {
        return new String(Base64.encode(bArr, 11)).split("=")[0].replace('+', '-').replace('/', '_');
    }

    public static String D(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(2 * bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append("0123456789ABCDEF".charAt((bArr[i] >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(bArr[i] & 15));
        }
        return stringBuffer.toString();
    }

    public static byte[] dD(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = 2 * i;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }
}
