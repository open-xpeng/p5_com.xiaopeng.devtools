package com.xiaopeng.commonfunc.utils;

import org.greenrobot.eventbus.EventBus;

/* compiled from: EventBusUtil.java */
/* loaded from: classes11.dex */
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
