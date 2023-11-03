package com.xiaopeng.devtools.a.a;

import android.content.Context;
import com.xiaopeng.devtools.utils.l;

/* compiled from: CmdHandler.java */
/* loaded from: classes12.dex */
public abstract class a {
    protected Context context;
    protected String CLASS_NAME = "CmdHandler";
    protected String vd = "CMD_NAME";
    protected final String DELIMITER = ",";

    public a(Context context) {
        this.context = context;
    }

    public synchronized String f(String[] strArr) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String bL(String str) {
        return l.N(this.vd, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String bM(String str) {
        return l.O(this.vd, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String bN(String str) {
        return l.P(this.vd, str);
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
