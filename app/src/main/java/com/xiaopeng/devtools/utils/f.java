package com.xiaopeng.devtools.utils;

import org.greenrobot.eventbus.EventBus;

/* compiled from: EventBusUtil.java */
/* loaded from: classes12.dex */
public class f {
    public static void k(Object obj) {
        if (!EventBus.getDefault().isRegistered(obj)) {
            EventBus.getDefault().register(obj);
        }
    }

    public static void l(Object obj) {
        if (EventBus.getDefault().isRegistered(obj)) {
            EventBus.getDefault().unregister(obj);
        }
    }
}
