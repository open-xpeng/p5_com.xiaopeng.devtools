package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.exception.ZipException;

/* compiled from: CountingOutputStream.java */
/* loaded from: classes13.dex */
public class d extends OutputStream implements g {
    private OutputStream amM;
    private long amN = 0;

    public d(OutputStream outputStream) {
        this.amM = outputStream;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.amM.write(bArr, i, i2);
        this.amN += i2;
    }

    @Override // net.lingala.zip4j.b.b.g
    public int sB() {
        if (sE()) {
            return ((h) this.amM).sB();
        }
        return 0;
    }

    public long sC() throws IOException {
        if (this.amM instanceof h) {
            return ((h) this.amM).getFilePointer();
        }
        return this.amN;
    }

    public long sD() {
        if (sE()) {
            return ((h) this.amM).sD();
        }
        return 0L;
    }

    public boolean sE() {
        return (this.amM instanceof h) && ((h) this.amM).sE();
    }

    public long sF() throws IOException {
        if (this.amM instanceof h) {
            return ((h) this.amM).getFilePointer();
        }
        return this.amN;
    }

    public boolean eZ(int i) throws ZipException {
        if (!sE()) {
            return false;
        }
        return ((h) this.amM).fa(i);
    }

    @Override // net.lingala.zip4j.b.b.g
    public long getFilePointer() throws IOException {
        if (this.amM instanceof h) {
            return ((h) this.amM).getFilePointer();
        }
        return this.amN;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.amM.close();
    }
}
