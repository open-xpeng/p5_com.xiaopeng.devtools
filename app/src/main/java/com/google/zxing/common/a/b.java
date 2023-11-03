package com.google.zxing.common.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GenericGFPoly.java */
/* loaded from: classes11.dex */
public final class b {
    private final a jC;
    private final int[] jD;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.jC = aVar;
        int length = iArr.length;
        if (length > 1 && iArr[0] == 0) {
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i != length) {
                this.jD = new int[length - i];
                System.arraycopy(iArr, i, this.jD, 0, this.jD.length);
                return;
            }
            this.jD = new int[]{0};
            return;
        }
        this.jD = iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] cn() {
        return this.jD;
    }

    int co() {
        return this.jD.length - 1;
    }

    boolean isZero() {
        return this.jD[0] == 0;
    }

    int bt(int i) {
        return this.jD[(this.jD.length - 1) - i];
    }

    b a(b bVar) {
        if (!this.jC.equals(bVar.jC)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero()) {
            return bVar;
        }
        if (bVar.isZero()) {
            return this;
        }
        int[] iArr = this.jD;
        int[] iArr2 = bVar.jD;
        if (iArr.length > iArr2.length) {
            iArr = iArr2;
            iArr2 = iArr;
        }
        int[] iArr3 = new int[iArr2.length];
        int length = iArr2.length - iArr.length;
        System.arraycopy(iArr2, 0, iArr3, 0, length);
        for (int i = length; i < iArr2.length; i++) {
            iArr3[i] = a.h(iArr[i - length], iArr2[i]);
        }
        return new b(this.jC, iArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b b(b bVar) {
        if (!this.jC.equals(bVar.jC)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (isZero() || bVar.isZero()) {
            return this.jC.cl();
        }
        int[] iArr = this.jD;
        int length = iArr.length;
        int[] iArr2 = bVar.jD;
        int length2 = iArr2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            for (int i3 = 0; i3 < length2; i3++) {
                int i4 = i + i3;
                iArr3[i4] = a.h(iArr3[i4], this.jC.i(i2, iArr2[i3]));
            }
        }
        return new b(this.jC, iArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b j(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.jC.cl();
        }
        int length = this.jD.length;
        int[] iArr = new int[i + length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.jC.i(this.jD[i3], i2);
        }
        return new b(this.jC, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b[] c(b bVar) {
        if (!this.jC.equals(bVar.jC)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (bVar.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        }
        b cl = this.jC.cl();
        int bs = this.jC.bs(bVar.bt(bVar.co()));
        b bVar2 = cl;
        b bVar3 = this;
        while (bVar3.co() >= bVar.co() && !bVar3.isZero()) {
            int co = bVar3.co() - bVar.co();
            int i = this.jC.i(bVar3.bt(bVar3.co()), bs);
            b j = bVar.j(co, i);
            bVar2 = bVar2.a(this.jC.g(co, i));
            bVar3 = bVar3.a(j);
        }
        return new b[]{bVar2, bVar3};
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(8 * co());
        for (int co = co(); co >= 0; co--) {
            int bt = bt(co);
            if (bt != 0) {
                if (bt < 0) {
                    sb.append(" - ");
                    bt = -bt;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (co == 0 || bt != 1) {
                    int br = this.jC.br(bt);
                    if (br == 0) {
                        sb.append('1');
                    } else if (br == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(br);
                    }
                }
                if (co != 0) {
                    if (co == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(co);
                    }
                }
            }
        }
        return sb.toString();
    }
}
