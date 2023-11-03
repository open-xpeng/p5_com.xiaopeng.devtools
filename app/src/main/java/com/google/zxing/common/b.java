package com.google.zxing.common;

import java.util.Arrays;

/* compiled from: BitMatrix.java */
/* loaded from: classes11.dex */
public final class b implements Cloneable {
    private final int height;
    private final int[] iI;
    private final int iJ;
    private final int width;

    public b(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = i;
        this.height = i2;
        this.iJ = (i + 31) / 32;
        this.iI = new int[this.iJ * i2];
    }

    private b(int i, int i2, int i3, int[] iArr) {
        this.width = i;
        this.height = i2;
        this.iJ = i3;
        this.iI = iArr;
    }

    public boolean f(int i, int i2) {
        return ((this.iI[(i2 * this.iJ) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public void b(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i4 < 1 || i3 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i5 = i3 + i;
        int i6 = i4 + i2;
        if (i6 > this.height || i5 > this.width) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (i2 < i6) {
            int i7 = this.iJ * i2;
            for (int i8 = i; i8 < i5; i8++) {
                int[] iArr = this.iI;
                int i9 = (i8 / 32) + i7;
                iArr[i9] = iArr[i9] | (1 << (i8 & 31));
            }
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof b) {
            b bVar = (b) obj;
            return this.width == bVar.width && this.height == bVar.height && this.iJ == bVar.iJ && Arrays.equals(this.iI, bVar.iI);
        }
        return false;
    }

    public int hashCode() {
        return (31 * ((((((this.width * 31) + this.width) * 31) + this.height) * 31) + this.iJ)) + Arrays.hashCode(this.iI);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.height * (this.width + 1));
        for (int i = 0; i < this.height; i++) {
            for (int i2 = 0; i2 < this.width; i2++) {
                sb.append(f(i2, i) ? "X " : "  ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /* renamed from: ck */
    public b clone() {
        return new b(this.width, this.height, this.iJ, (int[]) this.iI.clone());
    }
}
