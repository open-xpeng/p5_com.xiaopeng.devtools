package com.alibaba.mtl.appmonitor.d;

import android.text.TextUtils;
import java.util.Set;

/* compiled from: AccurateSampleCondition.java */
/* loaded from: classes11.dex */
public class b {
    private Set<String> bf;
    private a bg;

    /* compiled from: AccurateSampleCondition.java */
    /* loaded from: classes11.dex */
    private enum a {
        IN,
        NOT_IN
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean contains = this.bf.contains(str);
        if (this.bg == a.IN) {
            return contains;
        }
        return !contains;
    }
}
