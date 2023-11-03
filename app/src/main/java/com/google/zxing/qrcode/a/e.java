package com.google.zxing.qrcode.a;

import android.support.v4.media.subtitle.Cea708CCParser;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MatrixUtil.java */
/* loaded from: classes11.dex */
public final class e {
    private static final int[][] kj = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] kk = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] kl = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, Cea708CCParser.Const.CODE_C1_CW2, -1}, new int[]{6, 30, 56, 82, 108, Cea708CCParser.Const.CODE_C1_CW6, -1}, new int[]{6, 34, 60, 86, 112, Cea708CCParser.Const.CODE_C1_HDW, -1}, new int[]{6, 30, 58, 86, 114, Cea708CCParser.Const.CODE_C1_DLC, -1}, new int[]{6, 34, 62, 90, 118, Cea708CCParser.Const.CODE_C1_SPL, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, Cea708CCParser.Const.CODE_C1_DF2}, new int[]{6, 28, 54, 80, 106, Cea708CCParser.Const.CODE_C1_CW4, Cea708CCParser.Const.CODE_C1_DF6}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, Cea708CCParser.Const.CODE_C1_HDW, 166}, new int[]{6, 30, 58, 86, 114, Cea708CCParser.Const.CODE_C1_DLC, 170}};
    private static final int[][] km = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    static void f(b bVar) {
        bVar.a((byte) -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(com.google.zxing.common.a aVar, ErrorCorrectionLevel errorCorrectionLevel, com.google.zxing.qrcode.decoder.a aVar2, int i, b bVar) throws WriterException {
        f(bVar);
        a(aVar2, bVar);
        a(errorCorrectionLevel, i, bVar);
        b(aVar2, bVar);
        a(aVar, i, bVar);
    }

    static void a(com.google.zxing.qrcode.decoder.a aVar, b bVar) throws WriterException {
        i(bVar);
        h(bVar);
        c(aVar, bVar);
        g(bVar);
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, b bVar) throws WriterException {
        com.google.zxing.common.a aVar = new com.google.zxing.common.a();
        a(errorCorrectionLevel, i, aVar);
        for (int i2 = 0; i2 < aVar.getSize(); i2++) {
            boolean z = aVar.get((aVar.getSize() - 1) - i2);
            bVar.set(km[i2][0], km[i2][1], z);
            if (i2 < 8) {
                bVar.set((bVar.getWidth() - i2) - 1, 8, z);
            } else {
                bVar.set(8, (bVar.getHeight() - 7) + (i2 - 8), z);
            }
        }
    }

    static void b(com.google.zxing.qrcode.decoder.a aVar, b bVar) throws WriterException {
        if (aVar.cq() < 7) {
            return;
        }
        com.google.zxing.common.a aVar2 = new com.google.zxing.common.a();
        a(aVar, aVar2);
        int i = 17;
        int i2 = 0;
        while (i2 < 6) {
            int i3 = i;
            for (int i4 = 0; i4 < 3; i4++) {
                boolean z = aVar2.get(i3);
                i3--;
                bVar.set(i2, (bVar.getHeight() - 11) + i4, z);
                bVar.set((bVar.getHeight() - 11) + i4, i2, z);
            }
            i2++;
            i = i3;
        }
    }

    static void a(com.google.zxing.common.a aVar, int i, b bVar) throws WriterException {
        boolean z;
        int width = bVar.getWidth() - 1;
        int height = bVar.getHeight() - 1;
        int i2 = -1;
        int i3 = 0;
        while (width > 0) {
            if (width == 6) {
                width--;
            }
            while (height >= 0 && height < bVar.getHeight()) {
                int i4 = i3;
                for (int i5 = 0; i5 < 2; i5++) {
                    int i6 = width - i5;
                    if (isEmpty(bVar.k(i6, height))) {
                        if (i4 < aVar.getSize()) {
                            z = aVar.get(i4);
                            i4++;
                        } else {
                            z = false;
                        }
                        if (i != -1 && d.d(i, i6, height)) {
                            z = !z;
                        }
                        bVar.set(i6, height, z);
                    }
                }
                height += i2;
                i3 = i4;
            }
            i2 = -i2;
            height += i2;
            width -= 2;
        }
        if (i3 != aVar.getSize()) {
            throw new WriterException("Not all bits consumed: " + i3 + '/' + aVar.getSize());
        }
    }

    static int bx(int i) {
        int i2 = 0;
        while (i != 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    static int l(int i, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int bx = bx(i2);
        int i3 = i << (bx - 1);
        while (bx(i3) >= bx) {
            i3 ^= i2 << (bx(i3) - bx);
        }
        return i3;
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, com.google.zxing.common.a aVar) throws WriterException {
        if (!f.bz(i)) {
            throw new WriterException("Invalid mask pattern");
        }
        int cp = (errorCorrectionLevel.cp() << 3) | i;
        aVar.e(cp, 5);
        aVar.e(l(cp, 1335), 10);
        com.google.zxing.common.a aVar2 = new com.google.zxing.common.a();
        aVar2.e(21522, 15);
        aVar.b(aVar2);
        if (aVar.getSize() != 15) {
            throw new WriterException("should not happen but we got: " + aVar.getSize());
        }
    }

    static void a(com.google.zxing.qrcode.decoder.a aVar, com.google.zxing.common.a aVar2) throws WriterException {
        aVar2.e(aVar.cq(), 6);
        aVar2.e(l(aVar.cq(), 7973), 12);
        if (aVar2.getSize() != 18) {
            throw new WriterException("should not happen but we got: " + aVar2.getSize());
        }
    }

    private static boolean isEmpty(int i) {
        return i == -1;
    }

    private static void g(b bVar) {
        int i = 8;
        while (i < bVar.getWidth() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (isEmpty(bVar.k(i, 6))) {
                bVar.set(i, 6, i3);
            }
            if (isEmpty(bVar.k(6, i))) {
                bVar.set(6, i, i3);
            }
            i = i2;
        }
    }

    private static void h(b bVar) throws WriterException {
        if (bVar.k(8, bVar.getHeight() - 8) == 0) {
            throw new WriterException();
        }
        bVar.set(8, bVar.getHeight() - 8, 1);
    }

    private static void a(int i, int i2, b bVar) throws WriterException {
        for (int i3 = 0; i3 < 8; i3++) {
            int i4 = i + i3;
            if (!isEmpty(bVar.k(i4, i2))) {
                throw new WriterException();
            }
            bVar.set(i4, i2, 0);
        }
    }

    private static void b(int i, int i2, b bVar) throws WriterException {
        for (int i3 = 0; i3 < 7; i3++) {
            int i4 = i2 + i3;
            if (!isEmpty(bVar.k(i, i4))) {
                throw new WriterException();
            }
            bVar.set(i, i4, 0);
        }
    }

    private static void c(int i, int i2, b bVar) {
        for (int i3 = 0; i3 < 5; i3++) {
            for (int i4 = 0; i4 < 5; i4++) {
                bVar.set(i + i4, i2 + i3, kk[i3][i4]);
            }
        }
    }

    private static void d(int i, int i2, b bVar) {
        for (int i3 = 0; i3 < 7; i3++) {
            for (int i4 = 0; i4 < 7; i4++) {
                bVar.set(i + i4, i2 + i3, kj[i3][i4]);
            }
        }
    }

    private static void i(b bVar) throws WriterException {
        int length = kj[0].length;
        d(0, 0, bVar);
        d(bVar.getWidth() - length, 0, bVar);
        d(0, bVar.getWidth() - length, bVar);
        a(0, 7, bVar);
        a(bVar.getWidth() - 8, 7, bVar);
        a(0, bVar.getWidth() - 8, bVar);
        b(7, 0, bVar);
        b((bVar.getHeight() - 7) - 1, 0, bVar);
        b(7, bVar.getHeight() - 7, bVar);
    }

    private static void c(com.google.zxing.qrcode.decoder.a aVar, b bVar) {
        if (aVar.cq() < 2) {
            return;
        }
        int cq = aVar.cq() - 1;
        int[] iArr = kl[cq];
        int length = kl[cq].length;
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = iArr[i];
                int i4 = iArr[i2];
                if (i4 != -1 && i3 != -1 && isEmpty(bVar.k(i4, i3))) {
                    c(i4 - 2, i3 - 2, bVar);
                }
            }
        }
    }
}
