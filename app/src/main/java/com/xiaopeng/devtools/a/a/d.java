package com.xiaopeng.devtools.a.a;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: XpengSettings.java */
/* loaded from: classes12.dex */
public class d extends a {
    private com.xiaopeng.devtools.a.b ve;

    public d(Context context, com.xiaopeng.devtools.a.b bVar) {
        super(context);
        this.vd = "SETTINGS";
        this.CLASS_NAME = "XpengSettings";
        this.ve = bVar;
    }

    @Override // com.xiaopeng.devtools.a.a.a
    public synchronized String f(String[] strArr) {
        String bN;
        if (a(strArr, new String[]{"0"})) {
            if (!TextUtils.isEmpty(strArr[1])) {
                this.ve.bJ(strArr[1]);
                bN = bM(strArr[0]);
            } else {
                bN = bL(strArr[0]);
            }
        } else {
            bN = bN(strArr[0]);
        }
        return bN;
    }

    @Override // com.xiaopeng.devtools.a.a.a
    public void destroy() {
        super.destroy();
    }
}
