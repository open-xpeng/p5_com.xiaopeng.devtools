package com.xiaopeng.xui.d;

import android.app.Dialog;

/* compiled from: XDialogUtils.java */
/* loaded from: classes13.dex */
public class d {
    public static void a(Dialog dialog) {
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(15);
        }
    }
}
