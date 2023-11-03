package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/* compiled from: AesCipherOutputStream.java */
/* loaded from: classes13.dex */
class a extends b<net.lingala.zip4j.a.a> {
    private byte[] amH;
    private int amI;

    public a(j jVar, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException {
        super(jVar, zipParameters, cArr);
        this.amH = new byte[16];
        this.amI = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.b.b.b
    /* renamed from: a */
    public net.lingala.zip4j.a.a b(OutputStream outputStream, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException {
        net.lingala.zip4j.a.a aVar = new net.lingala.zip4j.a.a(cArr, zipParameters.sM());
        a(aVar);
        return aVar;
    }

    private void a(net.lingala.zip4j.a.a aVar) throws IOException {
        L(aVar.sr());
        L(aVar.sq());
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
        int i3;
        if (i2 >= 16 - this.amI) {
            System.arraycopy(bArr, i, this.amH, this.amI, 16 - this.amI);
            super.write(this.amH, 0, this.amH.length);
            int i4 = 16 - this.amI;
            int i5 = i2 - i4;
            this.amI = 0;
            if (i5 != 0 && (i3 = i5 % 16) != 0) {
                System.arraycopy(bArr, (i5 + i4) - i3, this.amH, 0, i3);
                this.amI = i3;
                i5 -= this.amI;
            }
            super.write(bArr, i4, i5);
            return;
        }
        System.arraycopy(bArr, i, this.amH, this.amI, i2);
        this.amI += i2;
    }

    @Override // net.lingala.zip4j.b.b.b
    public void closeEntry() throws IOException {
        if (this.amI != 0) {
            super.write(this.amH, 0, this.amI);
            this.amI = 0;
        }
        L(sA().sp());
        super.closeEntry();
    }
}
