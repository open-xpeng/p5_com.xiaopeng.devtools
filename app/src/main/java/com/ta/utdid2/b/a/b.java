package com.ta.utdid2.b.a;

import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import java.io.UnsupportedEncodingException;

/* compiled from: Base64.java */
/* loaded from: classes11.dex */
public class b {
    static final /* synthetic */ boolean a = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Base64.java */
    /* loaded from: classes11.dex */
    public static abstract class a {
        public int a;
        public byte[] b;

        a() {
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        C0038b c0038b = new C0038b(i3, new byte[(i2 * 3) / 4]);
        if (!c0038b.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if (c0038b.a == c0038b.b.length) {
            return c0038b.b;
        }
        byte[] bArr2 = new byte[c0038b.a];
        System.arraycopy(c0038b.b, 0, bArr2, 0, c0038b.a);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Base64.java */
    /* renamed from: com.ta.utdid2.b.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0038b extends a {
        private static final int[] a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private final int[] c;
        private int state;
        private int value;

        public C0038b(int i, byte[] bArr) {
            this.b = bArr;
            this.c = (i & 8) == 0 ? a : b;
            this.state = 0;
            this.value = 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(byte[] r12, int r13, int r14, boolean r15) {
            /*
                Method dump skipped, instructions count: 316
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.b.C0038b.a(byte[], int, int, boolean):boolean");
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
        c cVar = new c(i3, null);
        int i4 = (i2 / 3) * 4;
        if (cVar.f121b) {
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
        if (cVar.f122c && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (cVar.f123d ? 2 : 1);
        }
        cVar.b = new byte[i4];
        cVar.a(bArr, i, i2, true);
        if (a || cVar.a == i4) {
            return cVar.b;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Base64.java */
    /* loaded from: classes11.dex */
    public static class c extends a {
        static final /* synthetic */ boolean a = true;
        private static final byte[] c = {65, 66, 67, 68, TestResultItem.RESULT_ENTER, TestResultItem.RESULT_FAIL, 71, 72, 73, 74, 75, 76, 77, TestResultItem.RESULT_NOTEST, 79, TestResultItem.RESULT_PASS, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] d = {65, 66, 67, 68, TestResultItem.RESULT_ENTER, TestResultItem.RESULT_FAIL, 71, 72, 73, 74, 75, 76, 77, TestResultItem.RESULT_NOTEST, 79, TestResultItem.RESULT_PASS, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        int b;

        /* renamed from: b  reason: collision with other field name */
        public final boolean f121b;

        /* renamed from: c  reason: collision with other field name */
        public final boolean f122c;
        private int count;

        /* renamed from: d  reason: collision with other field name */
        public final boolean f123d;
        private final byte[] e;
        private final byte[] f;

        public c(int i, byte[] bArr) {
            this.b = bArr;
            this.f121b = (i & 1) == 0;
            this.f122c = (i & 2) == 0;
            this.f123d = (i & 4) != 0;
            this.f = (i & 8) == 0 ? c : d;
            this.e = new byte[2];
            this.b = 0;
            this.count = this.f122c ? 19 : -1;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Type inference failed for: r10v0 */
        /* JADX WARN: Type inference failed for: r10v1, types: [int, boolean] */
        /* JADX WARN: Type inference failed for: r10v11 */
        public boolean a(byte[] bArr, int i, int i2, boolean z) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            byte b;
            int i10;
            byte b2;
            int i11;
            byte b3;
            int i12;
            int i13;
            int i14;
            byte[] bArr2 = this.f;
            byte[] bArr3 = this.b;
            int i15 = this.count;
            int i16 = i2 + i;
            int i17 = 0;
            ?? r10 = 1;
            switch (this.b) {
                case 0:
                default:
                    i3 = i;
                    i4 = -1;
                    break;
                case 1:
                    if (i + 2 <= i16) {
                        int i18 = i + 1;
                        i3 = i18 + 1;
                        i4 = ((bArr[i] & 255) << 8) | ((this.e[0] & 255) << 16) | (bArr[i18] & 255);
                        this.b = 0;
                        break;
                    }
                    i3 = i;
                    i4 = -1;
                    break;
                case 2:
                    i3 = i + 1;
                    if (i3 <= i16) {
                        i4 = (bArr[i] & 255) | ((this.e[0] & 255) << 16) | ((this.e[1] & 255) << 8);
                        this.b = 0;
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
                int i19 = i15 - 1;
                if (i19 == 0) {
                    if (this.f123d) {
                        i14 = 5;
                        bArr3[4] = 13;
                    } else {
                        i14 = 4;
                    }
                    i6 = i14 + 1;
                    bArr3[i14] = 10;
                    i5 = 19;
                } else {
                    i5 = i19;
                    i6 = 4;
                }
            } else {
                i5 = i15;
                i6 = 0;
            }
            while (true) {
                int i20 = i3 + 3;
                if (i20 <= i16) {
                    int i21 = (bArr[i3 + 2] & 255) | ((bArr[i3] & 255) << 16) | ((bArr[i3 + 1] & 255) << 8);
                    bArr3[i6] = bArr2[(i21 >> 18) & 63];
                    bArr3[i6 + 1] = bArr2[(i21 >> 12) & 63];
                    bArr3[i6 + 2] = bArr2[(i21 >> 6) & 63];
                    bArr3[i6 + 3] = bArr2[i21 & 63];
                    i6 += 4;
                    i5--;
                    if (i5 == 0) {
                        if (this.f123d) {
                            i13 = i6 + 1;
                            bArr3[i6] = 13;
                        } else {
                            i13 = i6;
                        }
                        i6 = i13 + 1;
                        bArr3[i13] = 10;
                        i3 = i20;
                        i5 = 19;
                    } else {
                        i3 = i20;
                    }
                    i17 = 0;
                    r10 = 1;
                } else {
                    if (z) {
                        if (i3 - this.b == i16 - 1) {
                            if (this.b > 0) {
                                b3 = this.e[i17];
                                i17 = r10;
                            } else {
                                b3 = bArr[i3];
                                i3++;
                            }
                            int i22 = (b3 & 255) << 4;
                            this.b -= i17;
                            int i23 = i6 + 1;
                            bArr3[i6] = bArr2[(i22 >> 6) & 63];
                            i6 = i23 + 1;
                            bArr3[i23] = bArr2[i22 & 63];
                            if (this.f121b) {
                                int i24 = i6 + 1;
                                bArr3[i6] = 61;
                                i6 = i24 + 1;
                                bArr3[i24] = 61;
                            }
                            if (this.f122c) {
                                if (this.f123d) {
                                    i12 = i6 + 1;
                                    bArr3[i6] = 13;
                                } else {
                                    i12 = i6;
                                }
                                i8 = i12 + 1;
                                bArr3[i12] = 10;
                                i6 = i8;
                            }
                            if (a && this.b != 0) {
                                throw new AssertionError();
                            }
                            if (!a && i3 != i16) {
                                throw new AssertionError();
                            }
                        } else {
                            if (i3 - this.b == i16 - 2) {
                                if (this.b > r10) {
                                    byte b4 = this.e[i17];
                                    i17 = r10;
                                    i9 = i3;
                                    b = b4;
                                } else {
                                    i9 = i3 + 1;
                                    b = bArr[i3];
                                }
                                int i25 = (b & 255) << 10;
                                if (this.b > 0) {
                                    b2 = this.e[i17];
                                    i17++;
                                    i10 = i9;
                                } else {
                                    i10 = i9 + 1;
                                    b2 = bArr[i9];
                                }
                                int i26 = ((b2 & 255) << 2) | i25;
                                this.b -= i17;
                                int i27 = i6 + 1;
                                bArr3[i6] = bArr2[(i26 >> 12) & 63];
                                int i28 = i27 + 1;
                                bArr3[i27] = bArr2[(i26 >> 6) & 63];
                                int i29 = i28 + 1;
                                bArr3[i28] = bArr2[i26 & 63];
                                if (this.f121b) {
                                    i11 = i29 + 1;
                                    bArr3[i29] = 61;
                                } else {
                                    i11 = i29;
                                }
                                if (this.f122c) {
                                    if (this.f123d) {
                                        bArr3[i11] = 13;
                                        i11++;
                                    }
                                    bArr3[i11] = 10;
                                    i6 = i11 + 1;
                                } else {
                                    i6 = i11;
                                }
                                i3 = i10;
                            } else if (this.f122c && i6 > 0 && i5 != 19) {
                                if (this.f123d) {
                                    i7 = i6 + 1;
                                    bArr3[i6] = 13;
                                } else {
                                    i7 = i6;
                                }
                                i8 = i7 + 1;
                                bArr3[i7] = 10;
                                i6 = i8;
                            }
                            if (a) {
                            }
                            if (!a) {
                                throw new AssertionError();
                            }
                        }
                    } else if (i3 == i16 - 1) {
                        byte[] bArr4 = this.e;
                        int i30 = this.b;
                        this.b = i30 + 1;
                        bArr4[i30] = bArr[i3];
                    } else if (i3 == i16 - 2) {
                        byte[] bArr5 = this.e;
                        int i31 = this.b;
                        this.b = i31 + 1;
                        bArr5[i31] = bArr[i3];
                        byte[] bArr6 = this.e;
                        int i32 = this.b;
                        this.b = i32 + 1;
                        bArr6[i32] = bArr[i3 + r10];
                    }
                    this.a = i6;
                    this.count = i5;
                    return r10;
                }
            }
        }
    }

    private b() {
    }
}
