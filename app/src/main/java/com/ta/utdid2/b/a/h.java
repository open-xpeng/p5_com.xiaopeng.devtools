package com.ta.utdid2.b.a;

import android.annotation.TargetApi;
import android.content.SharedPreferences;

/* compiled from: SharedPreferenceHelper.java */
/* loaded from: classes11.dex */
public class h {
    @TargetApi(9)
    public static void a(SharedPreferences.Editor editor) {
        if (editor != null) {
            editor.apply();
        }
    }
}
