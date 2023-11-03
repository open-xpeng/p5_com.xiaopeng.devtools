package com.xiaopeng.logictree.handler;

import android.app.Application;
import android.content.Context;
import java.util.Arrays;

/* compiled from: LogicActionHandler.java */
/* loaded from: classes12.dex */
public abstract class h {
    protected String CLASS_NAME = "LogicActionHandler";
    protected String[] YA = null;
    protected com.xiaopeng.logictree.a Yb;
    protected Context context;
    protected Application mApplication;

    public h(Application application) {
        this.context = application.getApplicationContext();
        this.mApplication = application;
    }

    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        this.YA = aVar.pE().pI().split(",", -1);
        this.Yb = aVar;
        com.xiaopeng.lib.utils.c.i(this.CLASS_NAME, "argus : %s", Arrays.toString(this.YA));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(String[] strArr, String[] strArr2) {
        for (int i = 0; i < strArr2.length; i++) {
            if (!strArr[i].equalsIgnoreCase(strArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public void destroy() {
        com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "destroy");
    }
}
