package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: CompressedOutputStream.java */
/* loaded from: classes13.dex */
abstract class c extends OutputStream {
    private b amL;

    public c(b bVar) {
        this.amL = bVar;
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
        this.amL.write(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void closeEntry() throws IOException {
        this.amL.closeEntry();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.amL.close();
    }

    public long getCompressedSize() {
        return this.amL.sz();
    }
}
