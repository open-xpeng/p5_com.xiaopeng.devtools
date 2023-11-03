package net.lingala.zip4j.headers;

import java.nio.charset.Charset;
import net.lingala.zip4j.d.e;
import net.lingala.zip4j.d.f;
import net.lingala.zip4j.d.g;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.model.i;
import net.lingala.zip4j.model.j;

/* compiled from: FileHeaderFactory.java */
/* loaded from: classes13.dex */
public class a {
    public i a(ZipParameters zipParameters, boolean z, int i, Charset charset, e eVar) throws ZipException {
        i iVar = new i();
        iVar.a(HeaderSignature.CENTRAL_DIRECTORY);
        iVar.fl(g.a(zipParameters, eVar));
        iVar.fc(g.h(zipParameters).getCode());
        if (zipParameters.tJ() && zipParameters.sT() == EncryptionMethod.AES) {
            iVar.a(CompressionMethod.AES_INTERNAL_ONLY);
            iVar.a(a(zipParameters));
            iVar.fe(iVar.sS() + 11);
        } else {
            iVar.a(zipParameters.sN());
        }
        if (zipParameters.tJ()) {
            if (zipParameters.sT() == null || zipParameters.sT() == EncryptionMethod.NONE) {
                throw new ZipException("Encryption method has to be set when encryptFiles flag is set in zip parameters");
            }
            iVar.bt(true);
            iVar.a(zipParameters.sT());
        }
        String fo = fo(zipParameters.tQ());
        iVar.setFileName(fo);
        iVar.fd(a(fo, charset));
        if (!z) {
            i = 0;
        }
        iVar.fn(i);
        if (zipParameters.tR() > 0) {
            iVar.t(f.M(zipParameters.tR()));
        } else {
            iVar.t(f.M(System.currentTimeMillis()));
        }
        boolean fx = net.lingala.zip4j.d.c.fx(fo);
        iVar.bw(fx);
        iVar.P(net.lingala.zip4j.d.c.bC(fx));
        if (zipParameters.tT() && zipParameters.tS() == -1) {
            iVar.u(0L);
        } else {
            iVar.u(zipParameters.tS());
        }
        if (zipParameters.tJ() && zipParameters.sT() == EncryptionMethod.ZIP_STANDARD) {
            iVar.setCrc(zipParameters.tO());
        }
        iVar.N(a(iVar.isEncrypted(), zipParameters, charset));
        iVar.bu(zipParameters.tT());
        iVar.fr(zipParameters.tm());
        return iVar;
    }

    public j a(i iVar) {
        j jVar = new j();
        jVar.a(HeaderSignature.LOCAL_FILE_HEADER);
        jVar.fc(iVar.sO());
        jVar.a(iVar.sN());
        jVar.t(iVar.getLastModifiedTime());
        jVar.u(iVar.sQ());
        jVar.fd(iVar.sR());
        jVar.setFileName(iVar.getFileName());
        jVar.bt(iVar.isEncrypted());
        jVar.a(iVar.sT());
        jVar.a(iVar.sW());
        jVar.setCrc(iVar.getCrc());
        jVar.setCompressedSize(iVar.getCompressedSize());
        jVar.N((byte[]) iVar.sP().clone());
        jVar.bu(iVar.sU());
        jVar.fe(iVar.sS());
        return jVar;
    }

    private byte[] a(boolean z, ZipParameters zipParameters, Charset charset) {
        byte[] bArr = new byte[2];
        bArr[0] = a(z, zipParameters);
        if (charset == null || net.lingala.zip4j.d.d.apQ.equals(charset)) {
            bArr[1] = net.lingala.zip4j.d.a.b(bArr[1], 3);
        }
        return bArr;
    }

    private byte a(boolean z, ZipParameters zipParameters) {
        byte b = 0;
        if (z) {
            b = net.lingala.zip4j.d.a.b((byte) 0, 0);
        }
        if (CompressionMethod.DEFLATE.equals(zipParameters.sN())) {
            if (CompressionLevel.NORMAL.equals(zipParameters.tK())) {
                b = net.lingala.zip4j.d.a.c(net.lingala.zip4j.d.a.c(b, 1), 2);
            } else if (CompressionLevel.MAXIMUM.equals(zipParameters.tK())) {
                b = net.lingala.zip4j.d.a.c(net.lingala.zip4j.d.a.b(b, 1), 2);
            } else if (CompressionLevel.FAST.equals(zipParameters.tK())) {
                b = net.lingala.zip4j.d.a.b(net.lingala.zip4j.d.a.c(b, 1), 2);
            } else if (CompressionLevel.FASTEST.equals(zipParameters.tK()) || CompressionLevel.ULTRA.equals(zipParameters.tK())) {
                b = net.lingala.zip4j.d.a.b(net.lingala.zip4j.d.a.b(b, 1), 2);
            }
        }
        if (zipParameters.tT()) {
            return net.lingala.zip4j.d.a.b(b, 3);
        }
        return b;
    }

    private String fo(String str) throws ZipException {
        if (!f.fy(str)) {
            throw new ZipException("fileNameInZip is null or empty");
        }
        return str;
    }

    private net.lingala.zip4j.model.a a(ZipParameters zipParameters) throws ZipException {
        net.lingala.zip4j.model.a aVar = new net.lingala.zip4j.model.a();
        if (zipParameters.sK() != null) {
            aVar.a(zipParameters.sK());
        }
        if (zipParameters.sM() == AesKeyStrength.KEY_STRENGTH_128) {
            aVar.a(AesKeyStrength.KEY_STRENGTH_128);
        } else if (zipParameters.sM() == AesKeyStrength.KEY_STRENGTH_192) {
            aVar.a(AesKeyStrength.KEY_STRENGTH_192);
        } else if (zipParameters.sM() == AesKeyStrength.KEY_STRENGTH_256) {
            aVar.a(AesKeyStrength.KEY_STRENGTH_256);
        } else {
            throw new ZipException("invalid AES key strength");
        }
        aVar.a(zipParameters.sN());
        return aVar;
    }

    private int a(String str, Charset charset) {
        return c.b(str, charset).length;
    }
}
