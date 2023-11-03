package com.google.zxing.common;

import java.util.Arrays;

/* compiled from: BitArray.java */
/* loaded from: classes11.dex */
public final class a implements Cloneable {
    private int[] iI;
    private int size;

    public a() {
        this.size = 0;
        this.iI = new int[1];
    }

    a(int[] iArr, int i) {
        this.iI = iArr;
        this.size = i;
    }

    public int getSize() {
        return this.size;
    }

    public int ci() {
        return (this.size + 7) / 8;
    }

    private void ensureCapacity(int i) {
        if (i > this.iI.length * 32) {
            int[] bp = bp(i);
            System.arraycopy(this.iI, 0, bp, 0, this.iI.length);
            this.iI = bp;
        }
    }

    public boolean get(int i) {
        return ((1 << (i & 31)) & this.iI[i / 32]) != 0;
    }

    public void u(boolean z) {
        ensureCapacity(this.size + 1);
        if (z) {
            int[] iArr = this.iI;
            int i = this.size / 32;
            iArr[i] = iArr[i] | (1 << (this.size & 31));
        }
        this.size++;
    }

    public void e(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        ensureCapacity(this.size + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            u(z);
            i2--;
        }
    }

    public void a(a aVar) {
        int i = aVar.size;
        ensureCapacity(this.size + i);
        for (int i2 = 0; i2 < i; i2++) {
            u(aVar.get(i2));
        }
    }

    public void b(a aVar) {
        if (this.iI.length != aVar.iI.length) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        for (int i = 0; i < this.iI.length; i++) {
            int[] iArr = this.iI;
            iArr[i] = iArr[i] ^ aVar.iI[i];
        }
    }

    public void a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        int i5 = 0;
        while (i5 < i3) {
            int i6 = 0;
            int i7 = i4;
            for (int i8 = 0; i8 < 8; i8++) {
                if (get(i7)) {
                    i6 |= 1 << (7 - i8);
                }
                i7++;
            }
            bArr[i2 + i5] = (byte) i6;
            i5++;
            i4 = i7;
        }
    }

    private static int[] bp(int i) {
        return new int[(i + 31) / 32];
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            return this.size == aVar.size && Arrays.equals(this.iI, aVar.iI);
        }
        return false;
    }

    public int hashCode() {
        return (31 * this.size) + Arrays.hashCode(this.iI);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.size);
        for (int i = 0; i < this.size; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i) ? 'X' : '.');
        }
        return sb.toString();
    }

    /* renamed from: cj */
    public a clone() {
        return new a((int[]) this.iI.clone(), this.size);
    }
}
