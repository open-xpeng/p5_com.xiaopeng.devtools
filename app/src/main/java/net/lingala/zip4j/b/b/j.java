package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: ZipEntryOutputStream.java */
/* loaded from: classes13.dex */
class j extends OutputStream {
    private OutputStream amM;
    private long amS = 0;
    private boolean amT = false;

    public j(OutputStream outputStream) {
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
        if (this.amT) {
            throw new IllegalStateException("ZipEntryOutputStream is closed");
        }
        this.amM.write(bArr, i, i2);
        this.amS += i2;
    }

    public void closeEntry() throws IOException {
        this.amT = true;
    }

    public long sz() {
        return this.amS;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }
}
