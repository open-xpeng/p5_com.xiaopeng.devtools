package com.google.zxing.qrcode.a;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.a;
import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import com.xiaopeng.libtheme.ThemeManager;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: Encoder.java */
/* loaded from: classes11.dex */
public final class c {
    private static final int[] kh = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    private static int a(b bVar) {
        return d.b(bVar) + d.c(bVar) + d.d(bVar) + d.e(bVar);
    }

    public static f a(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException {
        CharacterSetECI aD;
        String str2 = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        Mode r = r(str, str2);
        com.google.zxing.common.a aVar = new com.google.zxing.common.a();
        if (r == Mode.BYTE && !"ISO-8859-1".equals(str2) && (aD = CharacterSetECI.aD(str2)) != null) {
            a(aD, aVar);
        }
        a(r, aVar);
        com.google.zxing.common.a aVar2 = new com.google.zxing.common.a();
        a(str, r, aVar2, str2);
        com.google.zxing.qrcode.decoder.a a = a(aVar.getSize() + r.a(a(aVar.getSize() + r.a(com.google.zxing.qrcode.decoder.a.bv(1)) + aVar2.getSize(), errorCorrectionLevel)) + aVar2.getSize(), errorCorrectionLevel);
        com.google.zxing.common.a aVar3 = new com.google.zxing.common.a();
        aVar3.a(aVar);
        a(r == Mode.BYTE ? aVar2.ci() : str.length(), a, r, aVar3);
        aVar3.a(aVar2);
        a.b a2 = a.a(errorCorrectionLevel);
        int cs = a.cs() - a2.cy();
        a(cs, aVar3);
        com.google.zxing.common.a a3 = a(aVar3, a.cs(), cs, a2.cx());
        f fVar = new f();
        fVar.b(errorCorrectionLevel);
        fVar.a(r);
        fVar.b(a);
        int ct = a.ct();
        b bVar = new b(ct, ct);
        int a4 = a(a3, errorCorrectionLevel, a, bVar);
        fVar.by(a4);
        e.a(a3, errorCorrectionLevel, a, a4, bVar);
        fVar.j(bVar);
        return fVar;
    }

    static int bw(int i) {
        if (i < kh.length) {
            return kh[i];
        }
        return -1;
    }

    private static Mode r(String str, String str2) {
        if ("Shift_JIS".equals(str2)) {
            return aE(str) ? Mode.KANJI : Mode.BYTE;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else if (bw(charAt) != -1) {
                z = true;
            } else {
                return Mode.BYTE;
            }
        }
        if (z) {
            return Mode.ALPHANUMERIC;
        }
        if (z2) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean aE(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    private static int a(com.google.zxing.common.a aVar, ErrorCorrectionLevel errorCorrectionLevel, com.google.zxing.qrcode.decoder.a aVar2, b bVar) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            e.a(aVar, errorCorrectionLevel, aVar2, i3, bVar);
            int a = a(bVar);
            if (a < i) {
                i2 = i3;
                i = a;
            }
        }
        return i2;
    }

    private static com.google.zxing.qrcode.decoder.a a(int i, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            com.google.zxing.qrcode.decoder.a bv = com.google.zxing.qrcode.decoder.a.bv(i2);
            if (bv.cs() - bv.a(errorCorrectionLevel).cy() >= (i + 7) / 8) {
                return bv;
            }
        }
        throw new WriterException("Data too big");
    }

    static void a(int i, com.google.zxing.common.a aVar) throws WriterException {
        int i2 = i * 8;
        if (aVar.getSize() > i2) {
            throw new WriterException("data bits cannot fit in the QR Code" + aVar.getSize() + " > " + i2);
        }
        for (int i3 = 0; i3 < 4 && aVar.getSize() < i2; i3++) {
            aVar.u(false);
        }
        int size = aVar.getSize() & 7;
        if (size > 0) {
            while (size < 8) {
                aVar.u(false);
                size++;
            }
        }
        int ci = i - aVar.ci();
        for (int i4 = 0; i4 < ci; i4++) {
            aVar.e((i4 & 1) == 0 ? TestResultItem.INDEX_V18_CDU_CERT : 17, 8);
        }
        if (aVar.getSize() != i2) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    static void a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 >= i3) {
            throw new WriterException("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i7 + 1;
        int i9 = i2 / i3;
        int i10 = i9 + 1;
        int i11 = i7 - i9;
        int i12 = i8 - i10;
        if (i11 != i12) {
            throw new WriterException("EC bytes mismatch");
        }
        if (i3 != i6 + i5) {
            throw new WriterException("RS blocks mismatch");
        }
        if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
            throw new WriterException("Total bytes mismatch");
        }
        if (i4 < i6) {
            iArr[0] = i9;
            iArr2[0] = i11;
            return;
        }
        iArr[0] = i10;
        iArr2[0] = i12;
    }

    static com.google.zxing.common.a a(com.google.zxing.common.a aVar, int i, int i2, int i3) throws WriterException {
        if (aVar.ci() == i2) {
            ArrayList<a> arrayList = new ArrayList(i3);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                a(i, i2, i3, i7, iArr, iArr2);
                int i8 = iArr[0];
                byte[] bArr = new byte[i8];
                aVar.a(8 * i4, bArr, 0, i8);
                byte[] c = c(bArr, iArr2[0]);
                arrayList.add(new a(bArr, c));
                i5 = Math.max(i5, i8);
                i6 = Math.max(i6, c.length);
                i4 += iArr[0];
            }
            if (i2 != i4) {
                throw new WriterException("Data bytes does not match offset");
            }
            com.google.zxing.common.a aVar2 = new com.google.zxing.common.a();
            for (int i9 = 0; i9 < i5; i9++) {
                for (a aVar3 : arrayList) {
                    byte[] dataBytes = aVar3.getDataBytes();
                    if (i9 < dataBytes.length) {
                        aVar2.e(dataBytes[i9], 8);
                    }
                }
            }
            for (int i10 = 0; i10 < i6; i10++) {
                for (a aVar4 : arrayList) {
                    byte[] cA = aVar4.cA();
                    if (i10 < cA.length) {
                        aVar2.e(cA[i10], 8);
                    }
                }
            }
            if (i != aVar2.ci()) {
                throw new WriterException("Interleaving error: " + i + " and " + aVar2.ci() + " differ.");
            }
            return aVar2;
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    static byte[] c(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length + i];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new com.google.zxing.common.a.c(com.google.zxing.common.a.a.js).a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    static void a(Mode mode, com.google.zxing.common.a aVar) {
        aVar.e(mode.cp(), 4);
    }

    static void a(int i, com.google.zxing.qrcode.decoder.a aVar, Mode mode, com.google.zxing.common.a aVar2) throws WriterException {
        int a = mode.a(aVar);
        int i2 = 1 << a;
        if (i >= i2) {
            throw new WriterException(i + " is bigger than " + (i2 - 1));
        }
        aVar2.e(i, a);
    }

    static void a(String str, Mode mode, com.google.zxing.common.a aVar, String str2) throws WriterException {
        switch (mode) {
            case NUMERIC:
                a((CharSequence) str, aVar);
                return;
            case ALPHANUMERIC:
                b(str, aVar);
                return;
            case BYTE:
                a(str, aVar, str2);
                return;
            case KANJI:
                a(str, aVar);
                return;
            default:
                throw new WriterException("Invalid mode: " + mode);
        }
    }

    static void a(CharSequence charSequence, com.google.zxing.common.a aVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                aVar.e((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    aVar.e((charAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    aVar.e(charAt, 4);
                }
            }
        }
    }

    static void b(CharSequence charSequence, com.google.zxing.common.a aVar) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int bw = bw(charSequence.charAt(i));
            if (bw == -1) {
                throw new WriterException();
            }
            int i2 = i + 1;
            if (i2 < length) {
                int bw2 = bw(charSequence.charAt(i2));
                if (bw2 == -1) {
                    throw new WriterException();
                }
                aVar.e((bw * 45) + bw2, 11);
                i += 2;
            } else {
                aVar.e(bw, 6);
                i = i2;
            }
        }
    }

    static void a(String str, com.google.zxing.common.a aVar, String str2) throws WriterException {
        try {
            for (byte b : str.getBytes(str2)) {
                aVar.e(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    static void a(String str, com.google.zxing.common.a aVar) throws WriterException {
        int i;
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i2 = 0; i2 < length; i2 += 2) {
                int i3 = ((bytes[i2] & 255) << 8) | (bytes[i2 + 1] & 255);
                if (i3 >= 33088 && i3 <= 40956) {
                    i = i3 - 33088;
                } else if (i3 >= 57408 && i3 <= 60351) {
                    i = i3 - 49472;
                } else {
                    i = -1;
                }
                if (i == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                aVar.e(((i >> 8) * ThemeManager.UI_MODE_THEME_MASK) + (i & 255), 13);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    private static void a(CharacterSetECI characterSetECI, com.google.zxing.common.a aVar) {
        aVar.e(Mode.ECI.cp(), 4);
        aVar.e(characterSetECI.getValue(), 8);
    }
}
