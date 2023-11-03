package net.lingala.zip4j.headers;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.d.e;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.model.f;
import net.lingala.zip4j.model.h;
import net.lingala.zip4j.model.i;
import net.lingala.zip4j.model.k;
import net.lingala.zip4j.model.l;
import net.lingala.zip4j.model.m;
import net.lingala.zip4j.model.n;
import net.lingala.zip4j.model.p;

/* compiled from: HeaderReader.java */
/* loaded from: classes13.dex */
public class b {
    private p alm;
    private final e amc = new e();
    private final byte[] amd = new byte[4];

    public p a(RandomAccessFile randomAccessFile, k kVar) throws IOException {
        if (randomAccessFile.length() < 22) {
            throw new ZipException("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
        }
        this.alm = new p();
        try {
            this.alm.a(a(randomAccessFile, this.amc, kVar));
            if (this.alm.tD().te() == 0) {
                return this.alm;
            }
            this.alm.a(a(randomAccessFile, this.amc, this.alm.tD().tg()));
            if (this.alm.tI()) {
                this.alm.a(a(randomAccessFile, this.amc));
                if (this.alm.tH() != null && this.alm.tH().tb() > 0) {
                    this.alm.by(true);
                } else {
                    this.alm.by(false);
                }
            }
            this.alm.a(a(randomAccessFile, this.amc, kVar.tp()));
            return this.alm;
        } catch (ZipException e) {
            throw e;
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new ZipException("Zip headers not found. Probably not a zip file or a corrupted zip file", e2);
        }
    }

    private f a(RandomAccessFile randomAccessFile, e eVar, k kVar) throws IOException {
        long a = a(randomAccessFile);
        b(randomAccessFile, 4 + a);
        f fVar = new f();
        fVar.a(HeaderSignature.END_OF_CENTRAL_DIRECTORY);
        fVar.fg(eVar.e(randomAccessFile));
        fVar.fh(eVar.e(randomAccessFile));
        fVar.fi(eVar.e(randomAccessFile));
        fVar.fj(eVar.e(randomAccessFile));
        fVar.fk(eVar.d(randomAccessFile));
        fVar.w(a);
        randomAccessFile.readFully(this.amd);
        fVar.v(eVar.l(this.amd, 0));
        fVar.setComment(a(randomAccessFile, eVar.e(randomAccessFile), kVar.tp()));
        this.alm.by(fVar.tb() > 0);
        return fVar;
    }

    private net.lingala.zip4j.model.d a(RandomAccessFile randomAccessFile, e eVar, Charset charset) throws IOException {
        net.lingala.zip4j.model.d dVar = new net.lingala.zip4j.model.d();
        ArrayList arrayList = new ArrayList();
        long b = c.b(this.alm);
        long a = a(this.alm);
        randomAccessFile.seek(b);
        int i = 2;
        byte[] bArr = new byte[2];
        byte[] bArr2 = new byte[4];
        int i2 = 0;
        int i3 = 0;
        while (i3 < a) {
            i iVar = new i();
            byte[] bArr3 = bArr2;
            if (eVar.d(randomAccessFile) != HeaderSignature.CENTRAL_DIRECTORY.getValue()) {
                throw new ZipException("Expected central directory entry not found (#" + (i3 + 1) + ")");
            }
            iVar.a(HeaderSignature.CENTRAL_DIRECTORY);
            iVar.fl(eVar.e(randomAccessFile));
            iVar.fc(eVar.e(randomAccessFile));
            byte[] bArr4 = new byte[i];
            randomAccessFile.readFully(bArr4);
            iVar.bt(net.lingala.zip4j.d.a.a(bArr4[i2], i2));
            iVar.bu(net.lingala.zip4j.d.a.a(bArr4[i2], 3));
            iVar.bv(net.lingala.zip4j.d.a.a(bArr4[1], 3));
            iVar.N((byte[]) bArr4.clone());
            iVar.a(CompressionMethod.ft(eVar.e(randomAccessFile)));
            iVar.t(eVar.d(randomAccessFile));
            randomAccessFile.readFully(bArr3);
            iVar.setCrc(eVar.l(bArr3, i2));
            int i4 = i3;
            iVar.setCompressedSize(eVar.b(randomAccessFile, 4));
            iVar.u(eVar.b(randomAccessFile, 4));
            int e = eVar.e(randomAccessFile);
            iVar.fd(e);
            iVar.fe(eVar.e(randomAccessFile));
            int e2 = eVar.e(randomAccessFile);
            iVar.fm(e2);
            iVar.fn(eVar.e(randomAccessFile));
            randomAccessFile.readFully(bArr);
            iVar.O((byte[]) bArr.clone());
            randomAccessFile.readFully(bArr3);
            iVar.P((byte[]) bArr3.clone());
            randomAccessFile.readFully(bArr3);
            iVar.y(eVar.l(bArr3, 0));
            if (e > 0) {
                byte[] bArr5 = new byte[e];
                randomAccessFile.readFully(bArr5);
                iVar.setFileName(c.a(bArr5, iVar.sX(), charset));
            } else {
                iVar.setFileName(null);
            }
            iVar.bw(d(iVar.tk(), iVar.getFileName()));
            a(randomAccessFile, iVar);
            a(iVar, eVar);
            b(iVar, eVar);
            if (e2 > 0) {
                byte[] bArr6 = new byte[e2];
                randomAccessFile.readFully(bArr6);
                iVar.fr(c.a(bArr6, iVar.sX(), charset));
            }
            if (iVar.isEncrypted()) {
                if (iVar.sW() != null) {
                    iVar.a(EncryptionMethod.AES);
                } else {
                    iVar.a(EncryptionMethod.ZIP_STANDARD);
                }
            }
            arrayList.add(iVar);
            bArr2 = bArr3;
            i2 = 0;
            i = 2;
            i3 = i4 + 1;
        }
        dVar.D(arrayList);
        net.lingala.zip4j.model.e eVar2 = new net.lingala.zip4j.model.e();
        if (eVar.d(randomAccessFile) == HeaderSignature.DIGITAL_SIGNATURE.getValue()) {
            eVar2.a(HeaderSignature.DIGITAL_SIGNATURE);
            eVar2.ff(eVar.e(randomAccessFile));
            if (eVar2.ta() > 0) {
                byte[] bArr7 = new byte[eVar2.ta()];
                randomAccessFile.readFully(bArr7);
                eVar2.fq(new String(bArr7));
            }
        }
        return dVar;
    }

    private void a(RandomAccessFile randomAccessFile, i iVar) throws IOException {
        int sS = iVar.sS();
        if (sS <= 0) {
            return;
        }
        iVar.C(a(randomAccessFile, sS));
    }

    private List<h> a(RandomAccessFile randomAccessFile, int i) throws IOException {
        if (i < 4) {
            if (i > 0) {
                randomAccessFile.skipBytes(i);
                return null;
            }
            return null;
        }
        byte[] bArr = new byte[i];
        randomAccessFile.read(bArr);
        try {
            return k(bArr, i);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private List<h> k(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < i) {
            h hVar = new h();
            hVar.x(this.amc.n(bArr, i2));
            int i3 = i2 + 2;
            int n = this.amc.n(bArr, i3);
            hVar.ff(n);
            int i4 = i3 + 2;
            if (n > 0) {
                byte[] bArr2 = new byte[n];
                System.arraycopy(bArr, i4, bArr2, 0, n);
                hVar.setData(bArr2);
            }
            i2 = i4 + n;
            arrayList.add(hVar);
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    private l a(RandomAccessFile randomAccessFile, e eVar, long j) throws IOException {
        l lVar = new l();
        a(randomAccessFile, j);
        if (eVar.d(randomAccessFile) == HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR.getValue()) {
            this.alm.bz(true);
            lVar.a(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_LOCATOR);
            lVar.fo(eVar.d(randomAccessFile));
            lVar.z(eVar.c(randomAccessFile));
            lVar.fp(eVar.d(randomAccessFile));
            return lVar;
        }
        this.alm.bz(false);
        return null;
    }

    private m a(RandomAccessFile randomAccessFile, e eVar) throws IOException {
        if (this.alm.tG() == null) {
            throw new ZipException("invalid zip64 end of central directory locator");
        }
        long ts = this.alm.tG().ts();
        if (ts < 0) {
            throw new ZipException("invalid offset for start of end of central directory record");
        }
        randomAccessFile.seek(ts);
        m mVar = new m();
        if (eVar.d(randomAccessFile) != HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD.getValue()) {
            throw new ZipException("invalid signature for zip64 end of central directory record");
        }
        mVar.a(HeaderSignature.ZIP64_END_CENTRAL_DIRECTORY_RECORD);
        mVar.A(eVar.c(randomAccessFile));
        mVar.fl(eVar.e(randomAccessFile));
        mVar.fc(eVar.e(randomAccessFile));
        mVar.fg(eVar.d(randomAccessFile));
        mVar.fq(eVar.d(randomAccessFile));
        mVar.B(eVar.c(randomAccessFile));
        mVar.C(eVar.c(randomAccessFile));
        mVar.D(eVar.c(randomAccessFile));
        mVar.E(eVar.c(randomAccessFile));
        long tu = mVar.tu() - 44;
        if (tu > 0) {
            byte[] bArr = new byte[(int) tu];
            randomAccessFile.readFully(bArr);
            mVar.Q(bArr);
        }
        return mVar;
    }

    private void a(i iVar, e eVar) {
        n a;
        if (iVar.sY() == null || iVar.sY().size() <= 0 || (a = a(iVar.sY(), eVar, iVar.sQ(), iVar.getCompressedSize(), iVar.tl(), iVar.tj())) == null) {
            return;
        }
        iVar.a(a);
        if (a.sQ() != -1) {
            iVar.u(a.sQ());
        }
        if (a.getCompressedSize() != -1) {
            iVar.setCompressedSize(a.getCompressedSize());
        }
        if (a.tl() != -1) {
            iVar.y(a.tl());
        }
        if (a.tj() != -1) {
            iVar.fn(a.tj());
        }
    }

    private n a(List<h> list, e eVar, long j, long j2, long j3, int i) {
        for (h hVar : list) {
            if (hVar != null && HeaderSignature.ZIP64_EXTRA_FIELD_SIGNATURE.getValue() == hVar.th()) {
                n nVar = new n();
                byte[] data = hVar.getData();
                if (hVar.ta() <= 0) {
                    return null;
                }
                int i2 = 0;
                if (hVar.ta() > 0 && j == 4294967295L) {
                    nVar.u(eVar.l(data, 0));
                    i2 = 8;
                }
                if (i2 < hVar.ta() && j2 == 4294967295L) {
                    nVar.setCompressedSize(eVar.l(data, i2));
                    i2 += 8;
                }
                if (i2 < hVar.ta() && j3 == 4294967295L) {
                    nVar.y(eVar.l(data, i2));
                    i2 += 8;
                }
                if (i2 < hVar.ta() && i == 65535) {
                    nVar.fn(eVar.m(data, i2));
                }
                return nVar;
            }
        }
        return null;
    }

    private void a(RandomAccessFile randomAccessFile, long j) throws IOException {
        b(randomAccessFile, (((j - 4) - 8) - 4) - 4);
    }

    private void b(i iVar, e eVar) throws ZipException {
        net.lingala.zip4j.model.a a;
        if (iVar.sY() != null && iVar.sY().size() > 0 && (a = a(iVar.sY(), eVar)) != null) {
            iVar.a(a);
            iVar.a(EncryptionMethod.AES);
        }
    }

    private net.lingala.zip4j.model.a a(List<h> list, e eVar) throws ZipException {
        if (list == null) {
            return null;
        }
        for (h hVar : list) {
            if (hVar != null && hVar.th() == HeaderSignature.AES_EXTRA_DATA_RECORD.getValue()) {
                if (hVar.getData() == null) {
                    throw new ZipException("corrupt AES extra data records");
                }
                net.lingala.zip4j.model.a aVar = new net.lingala.zip4j.model.a();
                aVar.a(HeaderSignature.AES_EXTRA_DATA_RECORD);
                aVar.setDataSize(hVar.ta());
                byte[] data = hVar.getData();
                aVar.a(AesVersion.fs(eVar.n(data, 0)));
                byte[] bArr = new byte[2];
                System.arraycopy(data, 2, bArr, 0, 2);
                aVar.fp(new String(bArr));
                aVar.a(AesKeyStrength.fr(data[4] & 255));
                aVar.a(CompressionMethod.ft(eVar.n(data, 5)));
                return aVar;
            }
        }
        return null;
    }

    private long a(p pVar) {
        if (pVar.tI()) {
            return pVar.tH().tx();
        }
        return pVar.tD().te();
    }

    private long a(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length();
        if (length < 22) {
            throw new ZipException("Zip file size less than size of zip headers. Probably not a zip file.");
        }
        long j = length - 22;
        b(randomAccessFile, j);
        if (this.amc.d(randomAccessFile) == HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue()) {
            return j;
        }
        return b(randomAccessFile);
    }

    private long b(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length() - 22;
        long length2 = randomAccessFile.length();
        long j = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        if (length2 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            j = randomAccessFile.length();
        }
        while (j > 0 && length > 0) {
            length--;
            b(randomAccessFile, length);
            if (this.amc.d(randomAccessFile) == HeaderSignature.END_OF_CENTRAL_DIRECTORY.getValue()) {
                return length;
            }
            j--;
        }
        throw new ZipException("Zip headers not found. Probably not a zip file");
    }

    private void b(RandomAccessFile randomAccessFile, long j) throws IOException {
        if (randomAccessFile instanceof net.lingala.zip4j.b.a.a) {
            ((net.lingala.zip4j.b.a.a) randomAccessFile).s(j);
        } else {
            randomAccessFile.seek(j);
        }
    }

    private String a(RandomAccessFile randomAccessFile, int i, Charset charset) {
        if (i <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[i];
            randomAccessFile.readFully(bArr);
            if (charset == null) {
                charset = net.lingala.zip4j.d.d.apR;
            }
            return c.a(bArr, false, charset);
        } catch (IOException e) {
            return null;
        }
    }

    public boolean d(byte[] bArr, String str) {
        if (bArr[0] == 0 || !net.lingala.zip4j.d.a.a(bArr[0], 4)) {
            if (bArr[3] == 0 || !net.lingala.zip4j.d.a.a(bArr[3], 6)) {
                if (str != null) {
                    return str.endsWith("/") || str.endsWith("\\");
                }
                return false;
            }
            return true;
        }
        return true;
    }
}
