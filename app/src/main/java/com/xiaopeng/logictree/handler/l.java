package com.xiaopeng.logictree.handler;

import android.app.Application;
import android.text.TextUtils;

/* compiled from: UsbInfo.java */
/* loaded from: classes12.dex */
public class l extends h {
    public l(Application application) {
        super(application);
        this.CLASS_NAME = "UsbInfo";
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "check whether tbox attached ? ");
            if (com.xiaopeng.commonfunc.utils.i.a(this.context, Integer.parseInt(this.YA[1]), Integer.parseInt(this.YA[2]))) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        } else if (a(this.YA, new String[]{"2"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "check whether udisk attached ? ");
            if (!TextUtils.isEmpty(com.xiaopeng.commonfunc.utils.i.V(this.context))) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        } else if (a(this.YA, new String[]{"3"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "get udisk fsType");
            com.xiaopeng.commonfunc.system.a.a.sleep(3000L);
            String W = com.xiaopeng.commonfunc.utils.i.W(this.context);
            if (!TextUtils.isEmpty(W)) {
                com.xiaopeng.logictree.d.dR(W);
            } else {
                com.xiaopeng.logictree.d.pQ();
            }
        }
        return null;
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
    }
}
