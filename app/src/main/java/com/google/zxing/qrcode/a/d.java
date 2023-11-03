package com.google.zxing.qrcode.a;

/* compiled from: MaskUtil.java */
/* loaded from: classes11.dex */
final class d {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(b bVar) {
        return a(bVar, true) + a(bVar, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(b bVar) {
        byte[][] cB = bVar.cB();
        int width = bVar.getWidth();
        int height = bVar.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height - 1) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < width - 1) {
                byte b = cB[i][i4];
                int i5 = i4 + 1;
                if (b == cB[i][i5]) {
                    int i6 = i + 1;
                    if (b == cB[i6][i4] && b == cB[i6][i5]) {
                        i3++;
                    }
                }
                i4 = i5;
            }
            i++;
            i2 = i3;
        }
        return 3 * i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(b bVar) {
        byte[][] cB = bVar.cB();
        int width = bVar.getWidth();
        int height = bVar.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height) {
            int i3 = i2;
            for (int i4 = 0; i4 < width; i4++) {
                byte[] bArr = cB[i];
                int i5 = i4 + 6;
                if (i5 < width && bArr[i4] == 1 && bArr[i4 + 1] == 0 && bArr[i4 + 2] == 1 && bArr[i4 + 3] == 1 && bArr[i4 + 4] == 1 && bArr[i4 + 5] == 0 && bArr[i5] == 1 && (b(bArr, i4 - 4, i4) || b(bArr, i4 + 7, i4 + 11))) {
                    i3++;
                }
                int i6 = i + 6;
                if (i6 < height && cB[i][i4] == 1 && cB[i + 1][i4] == 0 && cB[i + 2][i4] == 1 && cB[i + 3][i4] == 1 && cB[i + 4][i4] == 1 && cB[i + 5][i4] == 0 && cB[i6][i4] == 1 && (a(cB, i4, i - 4, i) || a(cB, i4, i + 7, i + 11))) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return i2 * 40;
    }

    private static boolean b(byte[] bArr, int i, int i2) {
        while (i < i2) {
            if (i < 0 || i >= bArr.length || bArr[i] != 1) {
                i++;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean a(byte[][] bArr, int i, int i2, int i3) {
        while (i2 < i3) {
            if (i2 < 0 || i2 >= bArr.length || bArr[i2][i] != 1) {
                i2++;
            } else {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(b bVar) {
        byte[][] cB = bVar.cB();
        int width = bVar.getWidth();
        int height = bVar.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height) {
            byte[] bArr = cB[i];
            int i3 = i2;
            for (int i4 = 0; i4 < width; i4++) {
                if (bArr[i4] == 1) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        int height2 = bVar.getHeight() * bVar.getWidth();
        return ((Math.abs((i2 * 2) - height2) * 10) / height2) * 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(int i, int i2, int i3) {
        int i4;
        switch (i) {
            case 0:
                i4 = (i3 + i2) & 1;
                break;
            case 1:
                i4 = i3 & 1;
                break;
            case 2:
                i4 = i2 % 3;
                break;
            case 3:
                i4 = (i3 + i2) % 3;
                break;
            case 4:
                i4 = ((i3 / 2) + (i2 / 3)) & 1;
                break;
            case 5:
                int i5 = i3 * i2;
                i4 = (i5 & 1) + (i5 % 3);
                break;
            case 6:
                int i6 = i3 * i2;
                i4 = ((i6 & 1) + (i6 % 3)) & 1;
                break;
            case 7:
                i4 = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + i);
        }
        return i4 == 0;
    }

    private static int a(b bVar, boolean z) {
        int height = z ? bVar.getHeight() : bVar.getWidth();
        int width = z ? bVar.getWidth() : bVar.getHeight();
        byte[][] cB = bVar.cB();
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            int i3 = i;
            byte b = -1;
            int i4 = 0;
            for (int i5 = 0; i5 < width; i5++) {
                byte b2 = z ? cB[i2][i5] : cB[i5][i2];
                if (b2 == b) {
                    i4++;
                } else {
                    if (i4 >= 5) {
                        i3 += 3 + (i4 - 5);
                    }
                    i4 = 1;
                    b = b2;
                }
            }
            if (i4 >= 5) {
                i3 += 3 + (i4 - 5);
            }
            i = i3;
        }
        return i;
    }
}
