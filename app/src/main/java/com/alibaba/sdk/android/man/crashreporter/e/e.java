package com.alibaba.sdk.android.man.crashreporter.e;

import java.io.File;

/* loaded from: classes11.dex */
public class e {
    public static void i(String str) {
        if (i.a((CharSequence) str)) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
