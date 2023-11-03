package com.alibaba.sdk.android.man.crashreporter.e;

/* loaded from: classes11.dex */
public class c {
    public static byte[] d(int i) {
        byte[] bArr = {(byte) ((r3 >> 8) % 256), (byte) (r3 % 256), (byte) (r3 % 256), (byte) (i % 256)};
        int i2 = i >> 8;
        int i3 = i2 >> 8;
        return bArr;
    }

    public static byte[] e(int i) {
        return new byte[]{(byte) ((i >> 8) % 256), (byte) (i % 256)};
    }
}
