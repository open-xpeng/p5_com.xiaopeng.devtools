package com.google.zxing.common.a;

/* compiled from: GenericGF.java */
/* loaded from: classes11.dex */
public final class a {
    public static final a jo = new a(4201, 4096, 1);
    public static final a jp = new a(1033, 1024, 1);
    public static final a jq = new a(67, 64, 1);
    public static final a jr = new a(19, 16, 1);
    public static final a js = new a(285, 256, 0);
    public static final a jt = new a(301, 256, 1);
    public static final a ju = jt;
    public static final a jv = jq;
    private final int jA;
    private final int jB;
    private final int[] jw;
    private final int[] jx;
    private final b jy;
    private final b jz;
    private final int size;

    public a(int i, int i2, int i3) {
        this.jA = i;
        this.size = i2;
        this.jB = i3;
        this.jw = new int[i2];
        this.jx = new int[i2];
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            this.jw[i5] = i4;
            i4 *= 2;
            if (i4 >= i2) {
                i4 = (i4 ^ i) & (i2 - 1);
            }
        }
        for (int i6 = 0; i6 < i2 - 1; i6++) {
            this.jx[this.jw[i6]] = i6;
        }
        this.jy = new b(this, new int[]{0});
        this.jz = new b(this, new int[]{1});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b cl() {
        return this.jy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b g(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.jy;
        }
        int[] iArr = new int[i + 1];
        iArr[0] = i2;
        return new b(this, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(int i, int i2) {
        return i ^ i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int bq(int i) {
        return this.jw[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int br(int i) {
        if (i == 0) {
            throw new IllegalArgumentException();
        }
        return this.jx[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int bs(int i) {
        if (i == 0) {
            throw new ArithmeticException();
        }
        return this.jw[(this.size - this.jx[i]) - 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return this.jw[(this.jx[i] + this.jx[i2]) % (this.size - 1)];
    }

    public int cm() {
        return this.jB;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.jA) + ',' + this.size + ')';
    }
}
