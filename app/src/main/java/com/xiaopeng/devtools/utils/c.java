package com.xiaopeng.devtools.utils;

import android.content.Context;
import android.content.Intent;

/* compiled from: BroadcastUtil.java */
/* loaded from: classes12.dex */
public class c {
    public static void ac(Context context) {
        Intent intent = new Intent("com.xiaopeng.action.CHANGE_LOG_PERMISSION");
        intent.setPackage("com.telenav.app.arp");
        context.sendBroadcast(intent);
    }
}
