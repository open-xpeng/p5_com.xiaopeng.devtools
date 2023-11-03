package com.xiaopeng.devtools.utils;

import android.content.SharedPreferences;
import com.xiaopeng.devtools.MyApplication;

/* compiled from: SharedUtils.java */
/* loaded from: classes12.dex */
public class q {
    private SharedPreferences mSharedPreferences;

    private q() {
        this.mSharedPreferences = MyApplication.getContext().getSharedPreferences("x_s_p", 0);
    }

    public static q lg() {
        return a.BU;
    }

    /* compiled from: SharedUtils.java */
    /* loaded from: classes12.dex */
    private static class a {
        private static final q BU = new q();
    }

    public void putBoolean(String str, boolean z) {
        this.mSharedPreferences.edit().putBoolean(str, z).commit();
    }

    public boolean getBoolean(String str) {
        return this.mSharedPreferences.getBoolean(str, false);
    }

    public void putInt(String str, int i) {
        this.mSharedPreferences.edit().putInt(str, i).commit();
    }

    public int getInt(String str) {
        return this.mSharedPreferences.getInt(str, -1);
    }

    public SharedPreferences lh() {
        return this.mSharedPreferences;
    }
}
