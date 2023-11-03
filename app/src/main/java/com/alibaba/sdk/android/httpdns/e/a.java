package com.alibaba.sdk.android.httpdns.e;

import android.util.Log;
import java.util.Random;

/* loaded from: classes11.dex */
public class a {
    private String q;

    /* renamed from: com.alibaba.sdk.android.httpdns.e.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    private static final class C0018a {
        private static final a a = new a();
    }

    private a() {
        try {
            Random random = new Random();
            char[] cArr = new char[12];
            for (int i = 0; i < 12; i++) {
                cArr[i] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length()));
            }
            this.q = new String(cArr);
        } catch (Exception e) {
            Log.d("SessionTrackMgr", e.getMessage(), e);
        }
    }

    public static a a() {
        return C0018a.a;
    }

    public String getSessionId() {
        return this.q;
    }

    public String h() {
        return com.alibaba.sdk.android.httpdns.c.a.a().h();
    }

    public String k() {
        int networkType = com.alibaba.sdk.android.httpdns.c.a.a().getNetworkType();
        if (networkType != 255) {
            switch (networkType) {
                case 0:
                    return "unknown";
                case 1:
                    return "wifi";
                case 2:
                    return "2g";
                case 3:
                    return "3g";
                case 4:
                    return "4g";
                default:
                    return "unknown";
            }
        }
        return "unknown";
    }
}
