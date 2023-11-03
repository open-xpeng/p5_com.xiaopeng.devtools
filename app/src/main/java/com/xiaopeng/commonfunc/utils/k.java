package com.xiaopeng.commonfunc.utils;

import java.io.Closeable;

/* compiled from: IoUtils.java */
/* loaded from: classes11.dex */
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
