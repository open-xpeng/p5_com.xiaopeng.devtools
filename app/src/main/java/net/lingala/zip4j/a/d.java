package net.lingala.zip4j.a;

import java.security.SecureRandom;
import net.lingala.zip4j.exception.ZipException;

/* compiled from: StandardEncrypter.java */
/* loaded from: classes13.dex */
public class d implements c {
    private final net.lingala.zip4j.a.b.b alI = new net.lingala.zip4j.a.b.b();
    private byte[] alJ;

    public d(char[] cArr, long j) throws ZipException {
        a(cArr, j);
    }

    private void a(char[] cArr, long j) throws ZipException {
        if (cArr == null || cArr.length <= 0) {
            throw new ZipException("input password is null or empty, cannot initialize standard encrypter");
        }
        this.alI.a(cArr);
        this.alJ = su();
        this.alI.a(cArr);
        this.alJ[11] = (byte) (j >>> 24);
        this.alJ[10] = (byte) (j >>> 16);
        J(this.alJ);
    }

    public int J(byte[] bArr) throws ZipException {
        if (bArr == null) {
            throw new NullPointerException();
        }
        return d(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.a.c
    public int d(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 < 0) {
            throw new ZipException("invalid length specified to decrpyt data");
        }
        for (int i3 = i; i3 < i + i2; i3++) {
            bArr[i3] = c(bArr[i3]);
        }
        return i2;
    }

    protected byte c(byte b) {
        byte sw = (byte) ((this.alI.sw() & 255) ^ b);
        this.alI.d(b);
        return sw;
    }

    protected byte[] su() {
        byte[] bArr = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = c((byte) secureRandom.nextInt(256));
        }
        return bArr;
    }

    public byte[] sv() {
        return this.alJ;
    }
}
