package com.amap.api.services.a;

import android.content.Context;
import java.lang.Thread;

/* compiled from: BasicLogHandler.java */
/* loaded from: classes11.dex */
public class o {
    protected static o eH;
    protected boolean c = true;
    protected Thread.UncaughtExceptionHandler eI;

    public static void a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (eH != null) {
            eH.a(th, 1, str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Throwable th, int i, String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Context context, l lVar, boolean z) {
    }
}
