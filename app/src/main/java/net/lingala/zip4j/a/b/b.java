package net.lingala.zip4j.a.b;

import net.lingala.zip4j.d.f;

/* compiled from: ZipCryptoEngine.java */
/* loaded from: classes13.dex */
public class b {
    private static final int[] alT = new int[256];
    private final int[] keys = new int[3];

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                if ((i2 & 1) == 1) {
                    i2 = (i2 >>> 1) ^ (-306674912);
                } else {
                    i2 >>>= 1;
                }
            }
            alT[i] = i2;
        }
    }

    public void a(char[] cArr) {
        this.keys[0] = 305419896;
        this.keys[1] = 591751049;
        this.keys[2] = 878082192;
        for (byte b : f.b(cArr)) {
            d((byte) (b & 255));
        }
    }

    public void d(byte b) {
        this.keys[0] = a(this.keys[0], b);
        int[] iArr = this.keys;
        iArr[1] = iArr[1] + (this.keys[0] & 255);
        this.keys[1] = (this.keys[1] * 134775813) + 1;
        this.keys[2] = a(this.keys[2], (byte) (this.keys[1] >> 24));
    }

    private int a(int i, byte b) {
        return alT[(i ^ b) & 255] ^ (i >>> 8);
    }

    public byte sw() {
        int i = this.keys[2] | 2;
        return (byte) ((i * (i ^ 1)) >>> 8);
    }
}
