package com.xiaopeng.devtools.utils;

import java.io.Closeable;

/* compiled from: IoUtils.java */
/* loaded from: classes12.dex */
public final class k {
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }
}
