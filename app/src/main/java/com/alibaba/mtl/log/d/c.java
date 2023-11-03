package com.alibaba.mtl.log.d;

import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import java.io.UnsupportedEncodingException;

/* compiled from: Base64.java */
/* loaded from: classes11.dex */
public class c {
    static final /* synthetic */ boolean cU = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Base64.java */
    /* loaded from: classes11.dex */
    public static abstract class a {
        public int op;
        public byte[] output;

        a() {
        }
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, new byte[(i2 * 3) / 4]);
        if (!bVar.process(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (bVar.op == bVar.output.length) {
            return bVar.output;
        }
        byte[] bArr2 = new byte[bVar.op];
        System.arraycopy(bVar.output, 0, bArr2, 0, bVar.op);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Base64.java */
    /* loaded from: classes11.dex */
    public static class b extends a {
        private static final int[] a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private final int[] c;
        private int state;
        private int value;

        public b(int i, byte[] bArr) {
            this.output = bArr;
            this.c = (i & 8) == 0 ? a : b;
            this.state = 0;
            this.value = 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:56:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00f3  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean process(byte[] r12, int r13, int r14, boolean r15) {
            /*
                Method dump skipped, instructions count: 314
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.d.c.b.process(byte[], int, int, boolean):boolean");
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        C0015c c0015c = new C0015c(i3, null);
        int i4 = (i2 / 3) * 4;
        if (c0015c.do_padding) {
            if (i2 % 3 > 0) {
                i4 += 4;
            }
        } else {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        }
        if (c0015c.do_newline && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (c0015c.do_cr ? 2 : 1);
        }
        c0015c.output = new byte[i4];
        c0015c.process(bArr, i, i2, true);
        if (cU || c0015c.op == i4) {
            return c0015c.output;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Base64.java */
    /* renamed from: com.alibaba.mtl.log.d.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0015c extends a {
        int E;
        private final byte[] c;
        private int count;
        private final byte[] d;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        static final /* synthetic */ boolean cU = true;
        private static final byte[] a = {65, 66, 67, 68, TestResultItem.RESULT_ENTER, TestResultItem.RESULT_FAIL, 71, 72, 73, 74, 75, 76, 77, TestResultItem.RESULT_NOTEST, 79, TestResultItem.RESULT_PASS, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] b = {65, 66, 67, 68, TestResultItem.RESULT_ENTER, TestResultItem.RESULT_FAIL, 71, 72, 73, 74, 75, 76, 77, TestResultItem.RESULT_NOTEST, 79, TestResultItem.RESULT_PASS, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

        public C0015c(int i, byte[] bArr) {
            this.output = bArr;
            this.do_padding = (i & 1) == 0;
            this.do_newline = (i & 2) == 0;
            this.do_cr = (i & 4) != 0;
            this.d = (i & 8) == 0 ? a : b;
            this.c = new byte[2];
            this.E = 0;
            this.count = this.do_newline ? 19 : -1;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            byte b2;
            int i9;
            byte b3;
            int i10;
            byte b4;
            int i11;
            int i12;
            int i13;
            byte[] bArr2 = this.d;
            byte[] bArr3 = this.output;
            int i14 = this.count;
            int i15 = i2 + i;
            int i16 = 0;
            switch (this.E) {
                case 0:
                default:
                    i3 = i;
                    i4 = -1;
                    break;
                case 1:
                    if (i + 2 <= i15) {
                        int i17 = i + 1;
                        i3 = i17 + 1;
                        i4 = ((bArr[i] & 255) << 8) | ((this.c[0] & 255) << 16) | (bArr[i17] & 255);
                        this.E = 0;
                        break;
                    }
                    i3 = i;
                    i4 = -1;
                    break;
                case 2:
                    i3 = i + 1;
                    if (i3 <= i15) {
                        i4 = (bArr[i] & 255) | ((this.c[0] & 255) << 16) | ((this.c[1] & 255) << 8);
                        this.E = 0;
                        break;
                    }
                    i3 = i;
                    i4 = -1;
                    break;
            }
            if (i4 != -1) {
                bArr3[0] = bArr2[(i4 >> 18) & 63];
                bArr3[1] = bArr2[(i4 >> 12) & 63];
                bArr3[2] = bArr2[(i4 >> 6) & 63];
                bArr3[3] = bArr2[i4 & 63];
                int i18 = i14 - 1;
                if (i18 == 0) {
                    if (this.do_cr) {
                        i13 = 5;
                        bArr3[4] = 13;
                    } else {
                        i13 = 4;
                    }
                    i6 = i13 + 1;
                    bArr3[i13] = 10;
                    i5 = 19;
                } else {
                    i5 = i18;
                    i6 = 4;
                }
            } else {
                i5 = i14;
                i6 = 0;
            }
            while (true) {
                int i19 = i3 + 3;
                if (i19 <= i15) {
                    int i20 = (bArr[i3 + 2] & 255) | ((bArr[i3] & 255) << 16) | ((bArr[i3 + 1] & 255) << 8);
                    bArr3[i6] = bArr2[(i20 >> 18) & 63];
                    bArr3[i6 + 1] = bArr2[(i20 >> 12) & 63];
                    bArr3[i6 + 2] = bArr2[(i20 >> 6) & 63];
                    bArr3[i6 + 3] = bArr2[i20 & 63];
                    i6 += 4;
                    i5--;
                    if (i5 == 0) {
                        if (this.do_cr) {
                            i12 = i6 + 1;
                            bArr3[i6] = 13;
                        } else {
                            i12 = i6;
                        }
                        i6 = i12 + 1;
                        bArr3[i12] = 10;
                        i3 = i19;
                        i5 = 19;
                    } else {
                        i3 = i19;
                    }
                } else {
                    if (z) {
                        if (i3 - this.E == i15 - 1) {
                            if (this.E > 0) {
                                b4 = this.c[0];
                                i16 = 1;
                            } else {
                                b4 = bArr[i3];
                                i3++;
                            }
                            int i21 = (b4 & 255) << 4;
                            this.E -= i16;
                            int i22 = i6 + 1;
                            bArr3[i6] = bArr2[(i21 >> 6) & 63];
                            i6 = i22 + 1;
                            bArr3[i22] = bArr2[i21 & 63];
                            if (this.do_padding) {
                                int i23 = i6 + 1;
                                bArr3[i6] = 61;
                                i6 = i23 + 1;
                                bArr3[i23] = 61;
                            }
                            if (this.do_newline) {
                                if (this.do_cr) {
                                    i11 = i6 + 1;
                                    bArr3[i6] = 13;
                                } else {
                                    i11 = i6;
                                }
                                i6 = i11 + 1;
                                bArr3[i11] = 10;
                            }
                        } else if (i3 - this.E == i15 - 2) {
                            if (this.E > 1) {
                                i16 = 1;
                                i8 = i3;
                                b2 = this.c[0];
                            } else {
                                i8 = i3 + 1;
                                b2 = bArr[i3];
                            }
                            int i24 = (b2 & 255) << 10;
                            if (this.E > 0) {
                                b3 = this.c[i16];
                                i16++;
                                i9 = i8;
                            } else {
                                i9 = i8 + 1;
                                b3 = bArr[i8];
                            }
                            int i25 = ((b3 & 255) << 2) | i24;
                            this.E -= i16;
                            int i26 = i6 + 1;
                            bArr3[i6] = bArr2[(i25 >> 12) & 63];
                            int i27 = i26 + 1;
                            bArr3[i26] = bArr2[(i25 >> 6) & 63];
                            int i28 = i27 + 1;
                            bArr3[i27] = bArr2[i25 & 63];
                            if (this.do_padding) {
                                i10 = i28 + 1;
                                bArr3[i28] = 61;
                            } else {
                                i10 = i28;
                            }
                            if (this.do_newline) {
                                if (this.do_cr) {
                                    bArr3[i10] = 13;
                                    i10++;
                                }
                                bArr3[i10] = 10;
                                i10++;
                            }
                            i6 = i10;
                            i3 = i9;
                        } else if (this.do_newline && i6 > 0 && i5 != 19) {
                            if (this.do_cr) {
                                i7 = i6 + 1;
                                bArr3[i6] = 13;
                            } else {
                                i7 = i6;
                            }
                            bArr3[i7] = 10;
                            i6 = i7 + 1;
                        }
                        if (!cU && this.E != 0) {
                            throw new AssertionError();
                        }
                        if (!cU && i3 != i15) {
                            throw new AssertionError();
                        }
                    } else if (i3 == i15 - 1) {
                        byte[] bArr4 = this.c;
                        int i29 = this.E;
                        this.E = i29 + 1;
                        bArr4[i29] = bArr[i3];
                    } else if (i3 == i15 - 2) {
                        byte[] bArr5 = this.c;
                        int i30 = this.E;
                        this.E = i30 + 1;
                        bArr5[i30] = bArr[i3];
                        byte[] bArr6 = this.c;
                        int i31 = this.E;
                        this.E = i31 + 1;
                        bArr6[i31] = bArr[i3 + 1];
                    }
                    this.op = i6;
                    this.count = i5;
                    return true;
                }
            }
        }
    }

    private c() {
    }
}
