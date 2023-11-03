package net.lingala.zip4j.a.a;

import net.lingala.zip4j.d.f;

/* compiled from: PBKDF2Engine.java */
/* loaded from: classes13.dex */
public class b {
    private c alD;
    private d alE;

    public b(c cVar) {
        this(cVar, null);
    }

    public b(c cVar, d dVar) {
        this.alD = cVar;
        this.alE = dVar;
    }

    public byte[] a(char[] cArr, int i) {
        if (cArr == null) {
            throw new NullPointerException();
        }
        I(f.b(cArr));
        if (i == 0) {
            i = this.alE.ss();
        }
        return a(this.alE, this.alD.getSalt(), this.alD.getIterationCount(), i);
    }

    private void I(byte[] bArr) {
        if (this.alE == null) {
            this.alE = new a(this.alD.st());
        }
        this.alE.init(bArr);
    }

    private byte[] a(d dVar, byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (bArr == null) {
            bArr2 = new byte[0];
        } else {
            bArr2 = bArr;
        }
        int ss = dVar.ss();
        int E = E(i2, ss);
        int i3 = i2 - ((E - 1) * ss);
        byte[] bArr3 = new byte[E * ss];
        int i4 = 0;
        for (int i5 = 1; i5 <= E; i5++) {
            a(bArr3, i4, dVar, bArr2, i, i5);
            i4 += ss;
        }
        if (i3 < ss) {
            byte[] bArr4 = new byte[i2];
            System.arraycopy(bArr3, 0, bArr4, 0, i2);
            return bArr4;
        }
        return bArr3;
    }

    private int E(int i, int i2) {
        int i3;
        if (i % i2 > 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        return (i / i2) + i3;
    }

    private void a(byte[] bArr, int i, d dVar, byte[] bArr2, int i2, int i3) {
        int ss = dVar.ss();
        byte[] bArr3 = new byte[ss];
        byte[] bArr4 = new byte[bArr2.length + 4];
        System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        e(bArr4, bArr2.length, i3);
        for (int i4 = 0; i4 < i2; i4++) {
            bArr4 = dVar.doFinal(bArr4);
            e(bArr3, bArr4);
        }
        System.arraycopy(bArr3, 0, bArr, i, ss);
    }

    private void e(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    protected void e(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 / 16777216);
        bArr[i + 1] = (byte) (i2 / 65536);
        bArr[i + 2] = (byte) (i2 / 256);
        bArr[i + 3] = (byte) i2;
    }
}
