package net.lingala.zip4j.d;

import net.lingala.zip4j.headers.VersionMadeBy;
import net.lingala.zip4j.headers.VersionNeededToExtract;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* compiled from: ZipVersionUtils.java */
/* loaded from: classes13.dex */
public class g {
    public static int a(ZipParameters zipParameters, e eVar) {
        byte[] bArr = {VersionMadeBy.SPECIFICATION_VERSION.sx(), VersionMadeBy.UNIX.sx()};
        if (c.ui() && !zipParameters.tY()) {
            bArr[1] = VersionMadeBy.WINDOWS.sx();
        }
        return eVar.n(bArr, 0);
    }

    public static VersionNeededToExtract h(ZipParameters zipParameters) {
        VersionNeededToExtract versionNeededToExtract = VersionNeededToExtract.DEFAULT;
        if (zipParameters.sN() == CompressionMethod.DEFLATE) {
            versionNeededToExtract = VersionNeededToExtract.DEFLATE_COMPRESSED;
        }
        if (zipParameters.tS() > 4294967295L) {
            versionNeededToExtract = VersionNeededToExtract.ZIP_64_FORMAT;
        }
        if (zipParameters.tJ() && zipParameters.sT().equals(EncryptionMethod.AES)) {
            return VersionNeededToExtract.AES_ENCRYPTED;
        }
        return versionNeededToExtract;
    }
}
