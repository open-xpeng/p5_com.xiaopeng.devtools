package net.lingala.zip4j.headers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import net.lingala.zip4j.d.f;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.i;
import net.lingala.zip4j.model.p;

/* compiled from: HeaderUtil.java */
/* loaded from: classes13.dex */
public class c {
    public static i a(p pVar, String str) throws ZipException {
        i b = b(pVar, str);
        if (b == null) {
            String replaceAll = str.replaceAll("\\\\", "/");
            i b2 = b(pVar, replaceAll);
            if (b2 == null) {
                return b(pVar, replaceAll.replaceAll("/", "\\\\"));
            }
            return b2;
        }
        return b;
    }

    public static String a(byte[] bArr, boolean z, Charset charset) {
        if (charset != null) {
            return new String(bArr, charset);
        }
        if (z) {
            return new String(bArr, net.lingala.zip4j.d.d.apQ);
        }
        try {
            return new String(bArr, "Cp437");
        } catch (UnsupportedEncodingException e) {
            return new String(bArr);
        }
    }

    public static byte[] b(String str, Charset charset) {
        if (charset == null) {
            return str.getBytes(net.lingala.zip4j.d.d.apR);
        }
        return str.getBytes(charset);
    }

    public static long b(p pVar) {
        if (pVar.tI()) {
            return pVar.tH().tz();
        }
        return pVar.tD().tf();
    }

    private static i b(p pVar, String str) throws ZipException {
        if (pVar == null) {
            throw new ZipException("zip model is null, cannot determine file header with exact match for fileName: " + str);
        } else if (!f.fy(str)) {
            throw new ZipException("file name is null, cannot determine file header with exact match for fileName: " + str);
        } else if (pVar.tC() == null) {
            throw new ZipException("central directory is null, cannot determine file header with exact match for fileName: " + str);
        } else if (pVar.tC().sZ() == null) {
            throw new ZipException("file Headers are null, cannot determine file header with exact match for fileName: " + str);
        } else if (pVar.tC().sZ().size() == 0) {
            return null;
        } else {
            for (i iVar : pVar.tC().sZ()) {
                String fileName = iVar.getFileName();
                if (f.fy(fileName) && str.equalsIgnoreCase(fileName)) {
                    return iVar;
                }
            }
            return null;
        }
    }
}
