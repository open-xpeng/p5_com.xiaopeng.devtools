package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.model.p;

/* compiled from: ZipOutputStream.java */
/* loaded from: classes13.dex */
public class k extends OutputStream {
    private p alm;
    private char[] alp;
    private d amU;
    private c amV;
    private net.lingala.zip4j.model.i amW;
    private net.lingala.zip4j.model.j amX;
    private net.lingala.zip4j.model.k anb;
    private boolean anc;
    private net.lingala.zip4j.headers.a amY = new net.lingala.zip4j.headers.a();
    private net.lingala.zip4j.headers.d alq = new net.lingala.zip4j.headers.d();
    private CRC32 amZ = new CRC32();
    private net.lingala.zip4j.d.e amc = new net.lingala.zip4j.d.e();
    private long ana = 0;
    private boolean amT = true;

    public k(OutputStream outputStream, char[] cArr, net.lingala.zip4j.model.k kVar, p pVar) throws IOException {
        if (kVar.tq() < 512) {
            throw new IllegalArgumentException("Buffer size cannot be less than 512 bytes");
        }
        this.amU = new d(outputStream);
        this.alp = cArr;
        this.anb = kVar;
        this.alm = a(pVar, this.amU);
        this.anc = false;
        sJ();
    }

    public void b(ZipParameters zipParameters) throws IOException {
        e(zipParameters);
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        if (net.lingala.zip4j.d.c.fx(zipParameters.tQ())) {
            zipParameters2.bB(false);
            zipParameters2.a(CompressionMethod.STORE);
            zipParameters2.bA(false);
        }
        c(zipParameters2);
        this.amV = d(zipParameters2);
        this.amT = false;
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
        sI();
        this.amZ.update(bArr, i, i2);
        this.amV.write(bArr, i, i2);
        this.ana += i2;
    }

    public net.lingala.zip4j.model.i sH() throws IOException {
        this.amV.closeEntry();
        long compressedSize = this.amV.getCompressedSize();
        this.amW.setCompressedSize(compressedSize);
        this.amX.setCompressedSize(compressedSize);
        this.amW.u(this.ana);
        this.amX.u(this.ana);
        if (c(this.amW)) {
            this.amW.setCrc(this.amZ.getValue());
            this.amX.setCrc(this.amZ.getValue());
        }
        this.alm.tB().add(this.amX);
        this.alm.tC().sZ().add(this.amW);
        if (this.amX.sU()) {
            this.alq.a(this.amX, this.amU);
        }
        reset();
        this.amT = true;
        return this.amW;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.amT) {
            sH();
        }
        this.alm.tD().v(this.amU.sF());
        this.alq.a(this.alm, this.amU, this.anb.tp());
        this.amU.close();
        this.anc = true;
    }

    private void sI() throws IOException {
        if (this.anc) {
            throw new IOException("Stream is closed");
        }
    }

    private p a(p pVar, d dVar) {
        if (pVar == null) {
            pVar = new p();
        }
        if (dVar.sE()) {
            pVar.by(true);
            pVar.F(dVar.sD());
        }
        return pVar;
    }

    private void c(ZipParameters zipParameters) throws IOException {
        this.amW = this.amY.a(zipParameters, this.amU.sE(), this.amU.sB(), this.anb.tp(), this.amc);
        this.amW.y(this.amU.sC());
        this.amX = this.amY.a(this.amW);
        this.alq.a(this.alm, this.amX, this.amU, this.anb.tp());
    }

    private void reset() throws IOException {
        this.ana = 0L;
        this.amZ.reset();
        this.amV.close();
    }

    private void sJ() throws IOException {
        if (!this.amU.sE()) {
            return;
        }
        this.amc.b(this.amU, (int) HeaderSignature.SPLIT_ZIP.getValue());
    }

    private c d(ZipParameters zipParameters) throws IOException {
        return a(a(new j(this.amU), zipParameters), zipParameters);
    }

    private b a(j jVar, ZipParameters zipParameters) throws IOException {
        if (!zipParameters.tJ()) {
            return new f(jVar, zipParameters, null);
        }
        if (this.alp == null || this.alp.length == 0) {
            throw new ZipException("password not set");
        }
        if (zipParameters.sT() == EncryptionMethod.AES) {
            return new a(jVar, zipParameters, this.alp);
        }
        if (zipParameters.sT() == EncryptionMethod.ZIP_STANDARD) {
            return new l(jVar, zipParameters, this.alp);
        }
        if (zipParameters.sT() == EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG) {
            throw new ZipException(EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG + " encryption method is not supported");
        }
        throw new ZipException("Invalid encryption method");
    }

    private c a(b bVar, ZipParameters zipParameters) {
        if (zipParameters.sN() == CompressionMethod.DEFLATE) {
            return new e(bVar, zipParameters.tK(), this.anb.tq());
        }
        return new i(bVar);
    }

    private void e(ZipParameters zipParameters) {
        if (zipParameters.sN() == CompressionMethod.STORE && zipParameters.tS() < 0 && !net.lingala.zip4j.d.c.fx(zipParameters.tQ()) && zipParameters.tT()) {
            throw new IllegalArgumentException("uncompressed size should be set for zip entries of compression type store");
        }
    }

    private boolean c(net.lingala.zip4j.model.i iVar) {
        boolean z;
        if (!iVar.isEncrypted() || !iVar.sT().equals(EncryptionMethod.AES)) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            return iVar.sW().sK().equals(AesVersion.ONE);
        }
        return true;
    }
}
