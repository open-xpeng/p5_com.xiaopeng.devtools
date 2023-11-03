package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes11.dex */
public class b {
    private static SharedPreferences a;

    /* renamed from: a  reason: collision with other field name */
    private static boolean f21a = true;

    public static void a(boolean z) {
        f21a = z;
        if (a != null) {
            SharedPreferences.Editor edit = a.edit();
            edit.putBoolean("key_enable", z);
            edit.apply();
        }
        i.d("[EnableManager] enable: " + z);
    }

    public static boolean a() {
        return f21a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Context context) {
        if (context != null) {
            a = context.getSharedPreferences("httpdns_config_enable", 0);
            if (a != null) {
                f21a = a.getBoolean("key_enable", true);
            }
            i.d("[EnableManager] init enable: " + f21a);
        }
    }
}
