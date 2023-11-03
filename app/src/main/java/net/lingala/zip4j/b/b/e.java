package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.util.zip.Deflater;
import net.lingala.zip4j.model.enums.CompressionLevel;

/* compiled from: DeflaterOutputStream.java */
/* loaded from: classes13.dex */
class e extends c {
    private byte[] amO;
    protected Deflater deflater;

    public e(b bVar, CompressionLevel compressionLevel, int i) {
        super(bVar);
        this.deflater = new Deflater(compressionLevel.getLevel(), true);
        this.amO = new byte[i];
    }

    @Override // net.lingala.zip4j.b.b.c, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.b.b.c, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override // net.lingala.zip4j.b.b.c, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.deflater.setInput(bArr, i, i2);
        while (!this.deflater.needsInput()) {
            deflate();
        }
    }

    private void deflate() throws IOException {
        int deflate = this.deflater.deflate(this.amO, 0, this.amO.length);
        if (deflate > 0) {
            super.write(this.amO, 0, deflate);
        }
    }

    @Override // net.lingala.zip4j.b.b.c
    public void closeEntry() throws IOException {
        if (!this.deflater.finished()) {
            this.deflater.finish();
            while (!this.deflater.finished()) {
                deflate();
            }
        }
        this.deflater.end();
        super.closeEntry();
    }
}
