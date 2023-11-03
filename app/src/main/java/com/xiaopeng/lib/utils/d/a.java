package com.xiaopeng.lib.utils.d;

import android.content.Context;
import android.widget.Toast;

/* compiled from: ToastUtils.java */
/* loaded from: classes12.dex */
public class a {
    private static Toast Xf;

    public static void b(Context context, int i) {
        Toast k;
        if (context != null && (k = k(context, context.getResources().getString(i))) != null) {
            k.show();
        }
    }

    public static Toast k(Context context, String str) {
        if (Xf == null) {
            Xf = Toast.makeText(context.getApplicationContext(), str, 0);
        } else {
            Xf.setText(str);
        }
        return Xf;
    }
}
