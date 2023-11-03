package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.a.c;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/* compiled from: CipherOutputStream.java */
/* loaded from: classes13.dex */
abstract class b<T extends net.lingala.zip4j.a.c> extends OutputStream {
    private j amJ;
    private T amK;

    protected abstract T b(OutputStream outputStream, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException;

    public b(j jVar, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException {
        this.amJ = jVar;
        this.amK = b(jVar, zipParameters, cArr);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.amJ.write(i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.amJ.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.amK.d(bArr, i, i2);
        this.amJ.write(bArr, i, i2);
    }

    public void L(byte[] bArr) throws IOException {
        this.amJ.write(bArr);
    }

    public void closeEntry() throws IOException {
        this.amJ.closeEntry();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.amJ.close();
    }

    public long sz() {
        return this.amJ.sz();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T sA() {
        return this.amK;
    }
}
