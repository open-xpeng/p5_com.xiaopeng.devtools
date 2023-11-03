package net.lingala.zip4j.d;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* compiled from: RawIO.java */
/* loaded from: classes13.dex */
public class e {
    private final byte[] apS = new byte[2];
    private final byte[] amd = new byte[4];
    private final byte[] amr = new byte[8];

    public long c(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.amr);
        return l(this.amr, 0);
    }

    public long b(RandomAccessFile randomAccessFile, int i) throws IOException {
        S(this.amr);
        randomAccessFile.readFully(this.amr, 0, i);
        return l(this.amr, 0);
    }

    public long l(byte[] bArr, int i) {
        if (bArr.length - i < 8) {
            S(this.amr);
        }
        System.arraycopy(bArr, i, this.amr, 0, bArr.length < 8 ? bArr.length - i : 8);
        return ((((((((((((((0 | (this.amr[7] & 255)) << 8) | (this.amr[6] & 255)) << 8) | (this.amr[5] & 255)) << 8) | (this.amr[4] & 255)) << 8) | (this.amr[3] & 255)) << 8) | (this.amr[2] & 255)) << 8) | (this.amr[1] & 255)) << 8) | (this.amr[0] & 255);
    }

    public int d(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.amd);
        return R(this.amd);
    }

    public int R(byte[] bArr) {
        return m(bArr, 0);
    }

    public int m(byte[] bArr, int i) {
        return ((((bArr[3 + i] & 255) << 8) | (bArr[2 + i] & 255)) << 16) | (bArr[i] & 255) | ((bArr[1 + i] & 255) << 8);
    }

    public int e(RandomAccessFile randomAccessFile) throws IOException {
        randomAccessFile.readFully(this.apS);
        return n(this.apS, 0);
    }

    public int n(byte[] bArr, int i) {
        return ((bArr[1 + i] & 255) << 8) | (bArr[i] & 255);
    }

    public void a(OutputStream outputStream, int i) throws IOException {
        f(this.apS, 0, i);
        outputStream.write(this.apS);
    }

    public void f(byte[] bArr, int i, int i2) {
        bArr[i + 1] = (byte) (i2 >>> 8);
        bArr[i] = (byte) (i2 & 255);
    }

    public void b(OutputStream outputStream, int i) throws IOException {
        g(this.amd, 0, i);
        outputStream.write(this.amd);
    }

    public void g(byte[] bArr, int i, int i2) {
        bArr[i + 3] = (byte) (i2 >>> 24);
        bArr[i + 2] = (byte) (i2 >>> 16);
        bArr[i + 1] = (byte) (i2 >>> 8);
        bArr[i] = (byte) (i2 & 255);
    }

    public void a(OutputStream outputStream, long j) throws IOException {
        a(this.amr, 0, j);
        outputStream.write(this.amr);
    }

    public void a(byte[] bArr, int i, long j) {
        bArr[i + 7] = (byte) (j >>> 56);
        bArr[i + 6] = (byte) (j >>> 48);
        bArr[i + 5] = (byte) (j >>> 40);
        bArr[i + 4] = (byte) (j >>> 32);
        bArr[i + 3] = (byte) (j >>> 24);
        bArr[i + 2] = (byte) (j >>> 16);
        bArr[i + 1] = (byte) (j >>> 8);
        bArr[i] = (byte) (j & 255);
    }

    private void S(byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
    }
}
