package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.model.ZipParameters;

/* compiled from: ZipStandardCipherOutputStream.java */
/* loaded from: classes13.dex */
class l extends b<net.lingala.zip4j.a.d> {
    public l(j jVar, ZipParameters zipParameters, char[] cArr) throws IOException {
        super(jVar, zipParameters, cArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.b.b.b
    /* renamed from: d */
    public net.lingala.zip4j.a.d b(OutputStream outputStream, ZipParameters zipParameters, char[] cArr) throws IOException {
        net.lingala.zip4j.a.d dVar = new net.lingala.zip4j.a.d(cArr, f(zipParameters));
        L(dVar.sv());
        return dVar;
    }

    @Override // net.lingala.zip4j.b.b.b, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // net.lingala.zip4j.b.b.b, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.b.b.b, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        super.write(bArr, i, i2);
    }

    private long f(ZipParameters zipParameters) {
        if (zipParameters.tT()) {
            return (net.lingala.zip4j.d.f.M(zipParameters.tR()) & 65535) << 16;
        }
        return zipParameters.tO();
    }
}
