package net.lingala.zip4j.headers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.b.b.g;
import net.lingala.zip4j.b.b.h;
import net.lingala.zip4j.d.e;
import net.lingala.zip4j.d.f;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.i;
import net.lingala.zip4j.model.j;
import net.lingala.zip4j.model.l;
import net.lingala.zip4j.model.m;
import net.lingala.zip4j.model.p;

/* compiled from: HeaderWriter.java */
/* loaded from: classes13.dex */
public class d {
    private final e amc = new e();
    private final byte[] amr = new byte[8];
    private final byte[] amd = new byte[4];

    /* JADX WARN: Removed duplicated region for block: B:11:0x0070 A[Catch: Throwable -> 0x0169, TryCatch #2 {Throwable -> 0x0169, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x0070, B:13:0x00ab, B:15:0x00b7, B:16:0x00bf, B:20:0x00cc, B:22:0x00d2, B:23:0x00d4, B:25:0x00dc, B:27:0x00e1, B:28:0x0106, B:30:0x010c, B:31:0x015c, B:12:0x0088), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0088 A[Catch: Throwable -> 0x0169, TryCatch #2 {Throwable -> 0x0169, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x0070, B:13:0x00ab, B:15:0x00b7, B:16:0x00bf, B:20:0x00cc, B:22:0x00d2, B:23:0x00d4, B:25:0x00dc, B:27:0x00e1, B:28:0x0106, B:30:0x010c, B:31:0x015c, B:12:0x0088), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00b7 A[Catch: Throwable -> 0x0169, TryCatch #2 {Throwable -> 0x0169, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x0070, B:13:0x00ab, B:15:0x00b7, B:16:0x00bf, B:20:0x00cc, B:22:0x00d2, B:23:0x00d4, B:25:0x00dc, B:27:0x00e1, B:28:0x0106, B:30:0x010c, B:31:0x015c, B:12:0x0088), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d2 A[Catch: Throwable -> 0x0169, TryCatch #2 {Throwable -> 0x0169, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x0070, B:13:0x00ab, B:15:0x00b7, B:16:0x00bf, B:20:0x00cc, B:22:0x00d2, B:23:0x00d4, B:25:0x00dc, B:27:0x00e1, B:28:0x0106, B:30:0x010c, B:31:0x015c, B:12:0x0088), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00dc A[Catch: Throwable -> 0x0169, TryCatch #2 {Throwable -> 0x0169, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x0070, B:13:0x00ab, B:15:0x00b7, B:16:0x00bf, B:20:0x00cc, B:22:0x00d2, B:23:0x00d4, B:25:0x00dc, B:27:0x00e1, B:28:0x0106, B:30:0x010c, B:31:0x015c, B:12:0x0088), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e1 A[Catch: Throwable -> 0x0169, TryCatch #2 {Throwable -> 0x0169, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x0070, B:13:0x00ab, B:15:0x00b7, B:16:0x00bf, B:20:0x00cc, B:22:0x00d2, B:23:0x00d4, B:25:0x00dc, B:27:0x00e1, B:28:0x0106, B:30:0x010c, B:31:0x015c, B:12:0x0088), top: B:50:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x010c A[Catch: Throwable -> 0x0169, TryCatch #2 {Throwable -> 0x0169, blocks: (B:3:0x0006, B:5:0x0061, B:11:0x0070, B:13:0x00ab, B:15:0x00b7, B:16:0x00bf, B:20:0x00cc, B:22:0x00d2, B:23:0x00d4, B:25:0x00dc, B:27:0x00e1, B:28:0x0106, B:30:0x010c, B:31:0x015c, B:12:0x0088), top: B:50:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(net.lingala.zip4j.model.p r12, net.lingala.zip4j.model.j r13, java.io.OutputStream r14, java.nio.charset.Charset r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.headers.d.a(net.lingala.zip4j.model.p, net.lingala.zip4j.model.j, java.io.OutputStream, java.nio.charset.Charset):void");
    }

    public void a(j jVar, OutputStream outputStream) throws IOException {
        if (jVar == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot write extended local header");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.amc.b(byteArrayOutputStream, (int) HeaderSignature.EXTRA_DATA_RECORD.getValue());
            this.amc.a(this.amr, 0, jVar.getCrc());
            byteArrayOutputStream.write(this.amr, 0, 4);
            if (jVar.tn()) {
                this.amc.a(byteArrayOutputStream, jVar.getCompressedSize());
                this.amc.a(byteArrayOutputStream, jVar.sQ());
            } else {
                this.amc.a(this.amr, 0, jVar.getCompressedSize());
                byteArrayOutputStream.write(this.amr, 0, 4);
                this.amc.a(this.amr, 0, jVar.sQ());
                byteArrayOutputStream.write(this.amr, 0, 4);
            }
            outputStream.write(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
    }

    public void a(p pVar, OutputStream outputStream, Charset charset) throws IOException {
        if (pVar == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(pVar, outputStream);
            long c = c(pVar);
            a(pVar, byteArrayOutputStream, this.amc, charset);
            int size = byteArrayOutputStream.size();
            if (pVar.tI() || c >= 4294967295L || pVar.tC().sZ().size() >= 65535) {
                if (pVar.tH() == null) {
                    pVar.a(new m());
                }
                if (pVar.tG() == null) {
                    pVar.a(new l());
                }
                pVar.tG().z(size + c);
                if (b(outputStream)) {
                    int c2 = c(outputStream);
                    pVar.tG().fo(c2);
                    pVar.tG().fp(c2 + 1);
                } else {
                    pVar.tG().fo(0);
                    pVar.tG().fp(1);
                }
                m a = a(pVar, size, c);
                pVar.a(a);
                a(a, byteArrayOutputStream, this.amc);
                a(pVar.tG(), byteArrayOutputStream, this.amc);
            }
            a(pVar, size, c, byteArrayOutputStream, this.amc, charset);
            a(pVar, outputStream, byteArrayOutputStream.toByteArray(), charset);
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        }
    }

    public void a(i iVar, p pVar, h hVar) throws IOException {
        h hVar2;
        String str;
        if (iVar == null || pVar == null) {
            throw new ZipException("invalid input parameters, cannot update local file header");
        }
        boolean z = true;
        if (iVar.tj() != hVar.sB()) {
            String parent = pVar.tF().getParent();
            String fw = net.lingala.zip4j.d.c.fw(pVar.tF().getName());
            String str2 = "";
            if (parent != null) {
                str2 = parent + System.getProperty("file.separator");
            }
            if (iVar.tj() < 9) {
                str = str2 + fw + ".z0" + (iVar.tj() + 1);
            } else {
                str = str2 + fw + ".z" + (iVar.tj() + 1);
            }
            hVar2 = new h(new File(str));
        } else {
            hVar2 = hVar;
            z = false;
        }
        long filePointer = hVar2.getFilePointer();
        hVar2.seek(iVar.tl() + 14);
        this.amc.a(this.amr, 0, iVar.getCrc());
        hVar2.write(this.amr, 0, 4);
        a(hVar2, iVar);
        if (z) {
            hVar2.close();
        } else {
            hVar.seek(filePointer);
        }
    }

    private void a(h hVar, i iVar) throws IOException {
        if (iVar.sQ() >= 4294967295L) {
            this.amc.a(this.amr, 0, 4294967295L);
            hVar.write(this.amr, 0, 4);
            hVar.write(this.amr, 0, 4);
            int sR = 4 + iVar.sR() + 2 + 2;
            if (hVar.skipBytes(sR) != sR) {
                throw new ZipException("Unable to skip " + sR + " bytes to update LFH");
            }
            this.amc.a(hVar, iVar.sQ());
            this.amc.a(hVar, iVar.getCompressedSize());
            return;
        }
        this.amc.a(this.amr, 0, iVar.getCompressedSize());
        hVar.write(this.amr, 0, 4);
        this.amc.a(this.amr, 0, iVar.sQ());
        hVar.write(this.amr, 0, 4);
    }

    private boolean b(OutputStream outputStream) {
        if (outputStream instanceof h) {
            return ((h) outputStream).sE();
        }
        if (outputStream instanceof net.lingala.zip4j.b.b.d) {
            return ((net.lingala.zip4j.b.b.d) outputStream).sE();
        }
        return false;
    }

    private int c(OutputStream outputStream) {
        if (outputStream instanceof h) {
            return ((h) outputStream).sB();
        }
        return ((net.lingala.zip4j.b.b.d) outputStream).sB();
    }

    private void a(p pVar, OutputStream outputStream, byte[] bArr, Charset charset) throws IOException {
        if (bArr == null) {
            throw new ZipException("invalid buff to write as zip headers");
        }
        if ((outputStream instanceof net.lingala.zip4j.b.b.d) && ((net.lingala.zip4j.b.b.d) outputStream).eZ(bArr.length)) {
            a(pVar, outputStream, charset);
        } else {
            outputStream.write(bArr);
        }
    }

    private void a(p pVar, OutputStream outputStream) throws IOException {
        int i;
        if (outputStream instanceof g) {
            g gVar = (g) outputStream;
            pVar.tD().v(gVar.getFilePointer());
            i = gVar.sB();
        } else {
            i = 0;
        }
        if (pVar.tI()) {
            if (pVar.tH() == null) {
                pVar.a(new m());
            }
            if (pVar.tG() == null) {
                pVar.a(new l());
            }
            pVar.tH().E(pVar.tD().tf());
            pVar.tG().fo(i);
            pVar.tG().fp(i + 1);
        }
        pVar.tD().fg(i);
        pVar.tD().fh(i);
    }

    private void a(p pVar, ByteArrayOutputStream byteArrayOutputStream, e eVar, Charset charset) throws ZipException {
        if (pVar.tC() == null || pVar.tC().sZ() == null || pVar.tC().sZ().size() <= 0) {
            return;
        }
        for (i iVar : pVar.tC().sZ()) {
            a(pVar, iVar, byteArrayOutputStream, eVar, charset);
        }
    }

    private void a(p pVar, i iVar, ByteArrayOutputStream byteArrayOutputStream, e eVar, Charset charset) throws ZipException {
        byte[] bArr;
        if (iVar == null) {
            throw new ZipException("input parameters is null, cannot write local file header");
        }
        try {
            byte[] bArr2 = {0, 0};
            boolean b = b(iVar);
            eVar.b(byteArrayOutputStream, (int) iVar.tA().getValue());
            eVar.a((OutputStream) byteArrayOutputStream, iVar.ti());
            eVar.a((OutputStream) byteArrayOutputStream, iVar.sO());
            byteArrayOutputStream.write(iVar.sP());
            eVar.a((OutputStream) byteArrayOutputStream, iVar.sN().getCode());
            eVar.a(this.amr, 0, iVar.getLastModifiedTime());
            byteArrayOutputStream.write(this.amr, 0, 4);
            eVar.a(this.amr, 0, iVar.getCrc());
            byteArrayOutputStream.write(this.amr, 0, 4);
            if (b) {
                eVar.a(this.amr, 0, 4294967295L);
                byteArrayOutputStream.write(this.amr, 0, 4);
                byteArrayOutputStream.write(this.amr, 0, 4);
                pVar.bz(true);
                bArr = bArr2;
            } else {
                bArr = bArr2;
                eVar.a(this.amr, 0, iVar.getCompressedSize());
                byteArrayOutputStream.write(this.amr, 0, 4);
                eVar.a(this.amr, 0, iVar.sQ());
                byteArrayOutputStream.write(this.amr, 0, 4);
            }
            byte[] bArr3 = new byte[0];
            if (f.fy(iVar.getFileName())) {
                bArr3 = c.b(iVar.getFileName(), charset);
            }
            eVar.a((OutputStream) byteArrayOutputStream, bArr3.length);
            byte[] bArr4 = new byte[4];
            if (b) {
                eVar.a(this.amr, 0, 4294967295L);
                System.arraycopy(this.amr, 0, bArr4, 0, 4);
            } else {
                eVar.a(this.amr, 0, iVar.tl());
                System.arraycopy(this.amr, 0, bArr4, 0, 4);
            }
            eVar.a((OutputStream) byteArrayOutputStream, a(iVar, b));
            String tm = iVar.tm();
            byte[] bArr5 = new byte[0];
            if (f.fy(tm)) {
                bArr5 = c.b(tm, charset);
            }
            eVar.a((OutputStream) byteArrayOutputStream, bArr5.length);
            if (b) {
                eVar.g(this.amd, 0, 65535);
                byteArrayOutputStream.write(this.amd, 0, 2);
            } else {
                eVar.a((OutputStream) byteArrayOutputStream, iVar.tj());
            }
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(iVar.tk());
            byteArrayOutputStream.write(bArr4);
            if (bArr3.length > 0) {
                byteArrayOutputStream.write(bArr3);
            }
            if (b) {
                pVar.bz(true);
                eVar.a((OutputStream) byteArrayOutputStream, (int) HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue());
                eVar.a((OutputStream) byteArrayOutputStream, 28);
                eVar.a(byteArrayOutputStream, iVar.sQ());
                eVar.a(byteArrayOutputStream, iVar.getCompressedSize());
                eVar.a(byteArrayOutputStream, iVar.tl());
                eVar.b(byteArrayOutputStream, iVar.tj());
            }
            if (iVar.sW() != null) {
                net.lingala.zip4j.model.a sW = iVar.sW();
                eVar.a((OutputStream) byteArrayOutputStream, (int) sW.tA().getValue());
                eVar.a((OutputStream) byteArrayOutputStream, sW.getDataSize());
                eVar.a((OutputStream) byteArrayOutputStream, sW.sK().cq());
                byteArrayOutputStream.write(sW.sL().getBytes());
                byteArrayOutputStream.write(new byte[]{(byte) sW.sM().tZ()});
                eVar.a((OutputStream) byteArrayOutputStream, sW.sN().getCode());
            }
            a(iVar, byteArrayOutputStream);
            if (bArr5.length > 0) {
                byteArrayOutputStream.write(bArr5);
            }
        } catch (Exception e) {
            throw new ZipException(e);
        }
    }

    private int a(i iVar, boolean z) {
        int i;
        if (z) {
            i = 32;
        } else {
            i = 0;
        }
        if (iVar.sW() != null) {
            i += 11;
        }
        if (iVar.sY() != null) {
            for (net.lingala.zip4j.model.h hVar : iVar.sY()) {
                if (hVar.th() != HeaderSignature.AES_EXTRA_DATA_RECORD.getValue() && hVar.th() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                    i += 4 + hVar.ta();
                }
            }
        }
        return i;
    }

    private void a(i iVar, OutputStream outputStream) throws IOException {
        if (iVar.sY() == null || iVar.sY().size() == 0) {
            return;
        }
        for (net.lingala.zip4j.model.h hVar : iVar.sY()) {
            if (hVar.th() != HeaderSignature.AES_EXTRA_DATA_RECORD.getValue() && hVar.th() != HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue()) {
                this.amc.a(outputStream, (int) hVar.th());
                this.amc.a(outputStream, hVar.ta());
                if (hVar.ta() > 0 && hVar.getData() != null) {
                    outputStream.write(hVar.getData());
                }
            }
        }
    }

    private void a(m mVar, ByteArrayOutputStream byteArrayOutputStream, e eVar) throws IOException {
        eVar.b(byteArrayOutputStream, (int) mVar.tA().getValue());
        eVar.a(byteArrayOutputStream, mVar.tu());
        eVar.a((OutputStream) byteArrayOutputStream, mVar.ti());
        eVar.a((OutputStream) byteArrayOutputStream, mVar.sO());
        eVar.b(byteArrayOutputStream, mVar.tb());
        eVar.b(byteArrayOutputStream, mVar.tv());
        eVar.a(byteArrayOutputStream, mVar.tw());
        eVar.a(byteArrayOutputStream, mVar.tx());
        eVar.a(byteArrayOutputStream, mVar.ty());
        eVar.a(byteArrayOutputStream, mVar.tz());
    }

    private void a(l lVar, ByteArrayOutputStream byteArrayOutputStream, e eVar) throws IOException {
        eVar.b(byteArrayOutputStream, (int) HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR.getValue());
        eVar.b(byteArrayOutputStream, lVar.tr());
        eVar.a(byteArrayOutputStream, lVar.ts());
        eVar.b(byteArrayOutputStream, lVar.tt());
    }

    private void a(p pVar, int i, long j, ByteArrayOutputStream byteArrayOutputStream, e eVar, Charset charset) throws IOException {
        long j2;
        byte[] bArr = new byte[8];
        eVar.b(byteArrayOutputStream, (int) HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue());
        eVar.a((OutputStream) byteArrayOutputStream, pVar.tD().tb());
        eVar.a((OutputStream) byteArrayOutputStream, pVar.tD().tc());
        long size = pVar.tC().sZ().size();
        if (pVar.tE()) {
            j2 = d(pVar.tC().sZ(), pVar.tD().tb());
        } else {
            j2 = size;
        }
        if (j2 > 65535) {
            j2 = 65535;
        }
        eVar.a((OutputStream) byteArrayOutputStream, (int) j2);
        if (size > 65535) {
            size = 65535;
        }
        eVar.a((OutputStream) byteArrayOutputStream, (int) size);
        eVar.b(byteArrayOutputStream, i);
        if (j > 4294967295L) {
            eVar.a(bArr, 0, 4294967295L);
            byteArrayOutputStream.write(bArr, 0, 4);
        } else {
            eVar.a(bArr, 0, j);
            byteArrayOutputStream.write(bArr, 0, 4);
        }
        String comment = pVar.tD().getComment();
        if (f.fy(comment)) {
            byte[] b = c.b(comment, charset);
            eVar.a((OutputStream) byteArrayOutputStream, b.length);
            byteArrayOutputStream.write(b);
            return;
        }
        eVar.a((OutputStream) byteArrayOutputStream, 0);
    }

    private long d(List<i> list, int i) throws ZipException {
        if (list == null) {
            throw new ZipException("file headers are null, cannot calculate number of entries on this disk");
        }
        int i2 = 0;
        for (i iVar : list) {
            if (iVar.tj() == i) {
                i2++;
            }
        }
        return i2;
    }

    private boolean b(i iVar) {
        return iVar.getCompressedSize() >= 4294967295L || iVar.sQ() >= 4294967295L || iVar.tl() >= 4294967295L || iVar.tj() >= 65535;
    }

    private long c(p pVar) {
        if (pVar.tI() && pVar.tH() != null && pVar.tH().tz() != -1) {
            return pVar.tH().tz();
        }
        return pVar.tD().tf();
    }

    private m a(p pVar, int i, long j) throws ZipException {
        long j2;
        m mVar = new m();
        mVar.a(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD);
        mVar.A(44L);
        if (pVar.tC() != null && pVar.tC().sZ() != null && pVar.tC().sZ().size() > 0) {
            i iVar = pVar.tC().sZ().get(0);
            mVar.fl(iVar.ti());
            mVar.fc(iVar.sO());
        }
        mVar.fg(pVar.tD().tb());
        mVar.fq(pVar.tD().tc());
        long size = pVar.tC().sZ().size();
        if (pVar.tE()) {
            j2 = d(pVar.tC().sZ(), pVar.tD().tb());
        } else {
            j2 = size;
        }
        mVar.B(j2);
        mVar.C(size);
        mVar.D(i);
        mVar.E(j);
        return mVar;
    }
}
