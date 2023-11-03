package com.xiaopeng.commonfunc.utils;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: UIUtil.java */
/* loaded from: classes11.dex */
public class q {
    public static void b(final Context context, final int i) {
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$q$J_RIhWQzs1tgwoiQPzjCRgOmx08
            @Override // java.lang.Runnable
            public final void run() {
                q.c(context, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Context context, int i) {
        com.xiaopeng.xui.app.g.i(context.getString(i));
    }

    public static void bd(final String str) {
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$q$hPZA-MUZzRNO1Reti1aEVwmkYx8
            @Override // java.lang.Runnable
            public final void run() {
                q.be(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void be(String str) {
        if (!TextUtils.isEmpty(str)) {
            com.xiaopeng.xui.app.g.i(str);
        }
    }
}
