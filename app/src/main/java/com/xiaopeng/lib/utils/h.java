package com.xiaopeng.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

/* compiled from: SharedPreferencesUtils.java */
/* loaded from: classes12.dex */
public class h {
    private static volatile h Ws = null;
    private static int Wt = 0;
    private SharedPreferences Wu;
    private SharedPreferences.Editor Wv;

    private h(@NonNull Context context, String str, int i) {
        str = TextUtils.isEmpty(str) ? context.getPackageName() : str;
        str = TextUtils.isEmpty(str) ? "shared_pref" : str;
        Log.v("SharedPreferencesUtils", "spName=" + str);
        this.Wu = context.getSharedPreferences(str, 0);
        this.Wv = this.Wu.edit();
        this.Wv.apply();
    }

    private static void init(@NonNull Context context) {
        init(context, null);
    }

    private static void init(@NonNull Context context, String str) {
        if (Ws == null) {
            synchronized (h.class) {
                if (Ws == null) {
                    Ws = new h(context.getApplicationContext(), str, 0);
                    Wt++;
                }
            }
        }
    }

    public static h av(@NonNull Context context) {
        init(context);
        return Ws;
    }

    public void putString(String str, String str2) {
        this.Wv.putString(str, str2).commit();
    }

    public String getString(String str) {
        return getString(str, null);
    }

    public String getString(String str, String str2) {
        return this.Wu.getString(str, str2);
    }

    public void putFloat(String str, float f) {
        this.Wv.putFloat(str, f).commit();
    }

    public float getFloat(String str, float f) {
        return this.Wu.getFloat(str, f);
    }
}
