package com.alibaba.sdk.android.beacon;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes11.dex */
final class c {
    private static final char[] a = "0123456789abcdef".toCharArray();

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:8:0x0018
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    static java.lang.String a(android.content.Context r4) {
        /*
            java.lang.String r0 = ""
            if (r4 == 0) goto L23
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch: java.lang.Exception -> L1f
            java.lang.String r4 = r4.getPackageName()     // Catch: java.lang.Exception -> L1f
            r2 = 0
            android.content.pm.PackageInfo r4 = r1.getPackageInfo(r4, r2)     // Catch: java.lang.Exception -> L1f
            java.lang.String r4 = r4.versionName     // Catch: java.lang.Exception -> L1f
            if (r4 != 0) goto L1d
            java.lang.String r0 = ""
            return r0
        L18:
            r0 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L20
        L1d:
            r0 = r4
            return r0
        L1f:
            r4 = move-exception
        L20:
            r4.printStackTrace()
        L23:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.beacon.c.a(android.content.Context):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str, String str2) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "HmacSHA1");
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            return a(mac.doFinal(str2.getBytes()));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = a[i2 >>> 4];
            cArr[i3 + 1] = a[i2 & 15];
        }
        return new String(cArr);
    }
}
