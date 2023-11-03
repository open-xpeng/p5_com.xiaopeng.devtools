package com.alibaba.sdk.android.oss.common.utils;

import java.lang.reflect.Array;
import java.util.zip.Checksum;

/* loaded from: classes11.dex */
public class CRC64 implements Checksum {
    private static final int GF2_DIM = 64;
    private static final long POLY = -3932672073523589310L;
    private static final long[][] table = (long[][]) Array.newInstance(long.class, 8, 256);
    private long value = 0;

    static {
        for (int i = 0; i < 256; i++) {
            long j = i;
            for (int i2 = 0; i2 < 8; i2++) {
                if ((j & 1) == 1) {
                    j = (j >>> 1) ^ POLY;
                } else {
                    j >>>= 1;
                }
            }
            table[0][i] = j;
        }
        for (int i3 = 0; i3 < 256; i3++) {
            long j2 = table[0][i3];
            for (int i4 = 1; i4 < 8; i4++) {
                j2 = (j2 >>> 8) ^ table[0][(int) (255 & j2)];
                table[i4][i3] = j2;
            }
        }
    }

    private static long gf2MatrixTimes(long[] jArr, long j) {
        int i = 0;
        long j2 = 0;
        while (j != 0) {
            if ((j & 1) == 1) {
                j2 ^= jArr[i];
            }
            j >>>= 1;
            i++;
        }
        return j2;
    }

    private static void gf2MatrixSquare(long[] jArr, long[] jArr2) {
        for (int i = 0; i < 64; i++) {
            jArr[i] = gf2MatrixTimes(jArr2, jArr2[i]);
        }
    }

    public static long combine(long j, long j2, long j3) {
        if (j3 == 0) {
            return j;
        }
        long[] jArr = new long[64];
        long[] jArr2 = new long[64];
        jArr2[0] = -3932672073523589310L;
        long j4 = 1;
        for (int i = 1; i < 64; i++) {
            jArr2[i] = j4;
            j4 <<= 1;
        }
        gf2MatrixSquare(jArr, jArr2);
        gf2MatrixSquare(jArr2, jArr);
        long j5 = j;
        long j6 = j3;
        do {
            gf2MatrixSquare(jArr, jArr2);
            if ((j6 & 1) == 1) {
                j5 = gf2MatrixTimes(jArr, j5);
            }
            long j7 = j6 >>> 1;
            if (j7 == 0) {
                break;
            }
            gf2MatrixSquare(jArr2, jArr);
            if ((j7 & 1) == 1) {
                j5 = gf2MatrixTimes(jArr2, j5);
            }
            j6 = j7 >>> 1;
        } while (j6 != 0);
        return j5 ^ j2;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return this.value;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.value = 0L;
    }

    @Override // java.util.zip.Checksum
    public void update(int i) {
        byte[] bArr = {(byte) (i & 255)};
        update(bArr, bArr.length);
    }

    public void update(byte[] bArr, int i) {
        update(bArr, 0, i);
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        this.value = ~this.value;
        while (i2 >= 8) {
            this.value = (table[1][(int) (((this.value >>> 48) & 255) ^ (bArr[i + 6] & 255))] ^ (((((table[7][(int) ((this.value & 255) ^ (bArr[i] & 255))] ^ table[6][(int) (((this.value >>> 8) & 255) ^ (bArr[i + 1] & 255))]) ^ table[5][(int) (((this.value >>> 16) & 255) ^ (bArr[i + 2] & 255))]) ^ table[4][(int) (((this.value >>> 24) & 255) ^ (bArr[i + 3] & 255))]) ^ table[3][(int) (((this.value >>> 32) & 255) ^ (bArr[i + 4] & 255))]) ^ table[2][(int) (((this.value >>> 40) & 255) ^ (bArr[i + 5] & 255))])) ^ table[0][(int) ((this.value >>> 56) ^ (bArr[i + 7] & 255))];
            i += 8;
            i2 -= 8;
        }
        while (i2 > 0) {
            this.value = table[0][(int) ((this.value ^ bArr[i]) & 255)] ^ (this.value >>> 8);
            i++;
            i2--;
        }
        this.value = ~this.value;
    }
}
