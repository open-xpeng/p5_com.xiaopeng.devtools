package com.amap.api.services.a;

import com.xiaopeng.libtheme.ThemeManager;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Encrypt.java */
/* loaded from: classes11.dex */
public class i {
    private static final char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] b = new byte[128];

    static {
        for (int i = 0; i < 128; i++) {
            b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            b[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            b[i4] = (byte) ((i4 - 48) + 52);
        }
        b[43] = 62;
        b[47] = 63;
    }

    public static String a(byte[] bArr) {
        try {
            return c(bArr);
        } catch (Throwable th) {
            o.a(th, "Encrypt", "encodeBase64");
            return null;
        }
    }

    public static String a(String str) {
        return m.a(b(str));
    }

    public static String b(byte[] bArr) {
        try {
            return c(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            return b(bArr, bArr2);
        } catch (Throwable th) {
            o.a(th, "Encrypt", "aesEncrypt");
            return null;
        }
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(m.a());
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        try {
            cipher.init(1, secretKeySpec, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return cipher.doFinal(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(byte[] bArr, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, key);
        return cipher.doFinal(bArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x005b, code lost:
        if (r5 != (-1)) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005e, code lost:
        r2.write(((r4 & 15) << 4) | ((r5 & 60) >>> 2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
        r4 = r0 + 1;
        r0 = r8[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006e, code lost:
        if (r0 != 61) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
        return r2.toByteArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0075, code lost:
        r0 = com.amap.api.services.a.i.b[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0079, code lost:
        if (r4 >= r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007b, code lost:
        if (r0 == (-1)) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007e, code lost:
        r0 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0080, code lost:
        if (r0 != (-1)) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0083, code lost:
        r2.write(r0 | ((r5 & 3) << 6));
        r0 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] b(java.lang.String r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L6
            byte[] r8 = new byte[r0]
            return r8
        L6:
            byte[] r8 = com.amap.api.services.a.m.a(r8)
            int r1 = r8.length
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r2.<init>(r1)
        L11:
            if (r0 >= r1) goto L8d
        L13:
            byte[] r3 = com.amap.api.services.a.i.b
            int r4 = r0 + 1
            r0 = r8[r0]
            r0 = r3[r0]
            r3 = -1
            if (r4 >= r1) goto L23
            if (r0 == r3) goto L21
            goto L23
        L21:
            r0 = r4
            goto L13
        L23:
            if (r0 != r3) goto L27
            goto L8d
        L27:
            byte[] r5 = com.amap.api.services.a.i.b
            int r6 = r4 + 1
            r4 = r8[r4]
            r4 = r5[r4]
            if (r6 >= r1) goto L36
            if (r4 == r3) goto L34
            goto L36
        L34:
            r4 = r6
            goto L27
        L36:
            if (r4 != r3) goto L39
            goto L8d
        L39:
            int r0 = r0 << 2
            r5 = r4 & 48
            int r5 = r5 >>> 4
            r0 = r0 | r5
            r2.write(r0)
        L43:
            int r0 = r6 + 1
            r5 = r8[r6]
            r6 = 61
            if (r5 != r6) goto L50
            byte[] r8 = r2.toByteArray()
            return r8
        L50:
            byte[] r7 = com.amap.api.services.a.i.b
            r5 = r7[r5]
            if (r0 >= r1) goto L5b
            if (r5 == r3) goto L59
            goto L5b
        L59:
            r6 = r0
            goto L43
        L5b:
            if (r5 != r3) goto L5e
            goto L8d
        L5e:
            r4 = r4 & 15
            int r4 = r4 << 4
            r7 = r5 & 60
            int r7 = r7 >>> 2
            r4 = r4 | r7
            r2.write(r4)
        L6a:
            int r4 = r0 + 1
            r0 = r8[r0]
            if (r0 != r6) goto L75
            byte[] r8 = r2.toByteArray()
            return r8
        L75:
            byte[] r7 = com.amap.api.services.a.i.b
            r0 = r7[r0]
            if (r4 >= r1) goto L80
            if (r0 == r3) goto L7e
            goto L80
        L7e:
            r0 = r4
            goto L6a
        L80:
            if (r0 != r3) goto L83
            goto L8d
        L83:
            r3 = r5 & 3
            int r3 = r3 << 6
            r0 = r0 | r3
            r2.write(r0)
            r0 = r4
            goto L11
        L8d:
            byte[] r8 = r2.toByteArray()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.i.b(java.lang.String):byte[]");
    }

    private static String c(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            int i5 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
                stringBuffer.append(a[(i5 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] & 255;
            stringBuffer.append(a[i3 >>> 2]);
            stringBuffer.append(a[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
            stringBuffer.append(a[((i5 & 15) << 2) | ((i7 & ThemeManager.UI_MODE_THEME_MASK) >>> 6)]);
            stringBuffer.append(a[i7 & 63]);
            i = i6;
        }
        return stringBuffer.toString();
    }
}
