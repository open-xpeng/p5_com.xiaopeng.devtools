package net.lingala.zip4j.a;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;

/* compiled from: AesCipherUtil.java */
/* loaded from: classes13.dex */
public class b {
    public static byte[] a(byte[] bArr, char[] cArr, AesKeyStrength aesKeyStrength) throws ZipException {
        net.lingala.zip4j.a.a.b bVar = new net.lingala.zip4j.a.a.b(new net.lingala.zip4j.a.a.c("HmacSHA1", "ISO-8859-1", bArr, 1000));
        int keyLength = aesKeyStrength.getKeyLength();
        int macLength = aesKeyStrength.getMacLength();
        int i = keyLength + macLength + 2;
        byte[] a = bVar.a(cArr, i);
        if (a != null && a.length == i) {
            return a;
        }
        throw new ZipException(String.format("Derived Key invalid for Key Length [%d] MAC Length [%d]", Integer.valueOf(keyLength), Integer.valueOf(macLength)));
    }

    public static byte[] a(byte[] bArr, AesKeyStrength aesKeyStrength) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, aesKeyStrength.getKeyLength() + aesKeyStrength.getMacLength(), bArr2, 0, 2);
        return bArr2;
    }

    public static net.lingala.zip4j.a.a.a b(byte[] bArr, AesKeyStrength aesKeyStrength) {
        int macLength = aesKeyStrength.getMacLength();
        byte[] bArr2 = new byte[macLength];
        System.arraycopy(bArr, aesKeyStrength.getKeyLength(), bArr2, 0, macLength);
        net.lingala.zip4j.a.a.a aVar = new net.lingala.zip4j.a.a.a("HmacSHA1");
        aVar.init(bArr2);
        return aVar;
    }

    public static net.lingala.zip4j.a.b.a c(byte[] bArr, AesKeyStrength aesKeyStrength) throws ZipException {
        int keyLength = aesKeyStrength.getKeyLength();
        byte[] bArr2 = new byte[keyLength];
        System.arraycopy(bArr, 0, bArr2, 0, keyLength);
        return new net.lingala.zip4j.a.b.a(bArr2);
    }

    public static void h(byte[] bArr, int i) {
        bArr[0] = (byte) i;
        bArr[1] = (byte) (i >> 8);
        bArr[2] = (byte) (i >> 16);
        bArr[3] = (byte) (i >> 24);
        for (int i2 = 4; i2 <= 15; i2++) {
            bArr[i2] = 0;
        }
    }
}
