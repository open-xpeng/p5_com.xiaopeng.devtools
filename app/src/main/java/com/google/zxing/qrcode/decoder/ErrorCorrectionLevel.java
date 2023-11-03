package com.google.zxing.qrcode.decoder;

/* loaded from: classes11.dex */
public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);
    
    private final int bits;
    private static final ErrorCorrectionLevel[] jJ = {M, L, H, Q};

    ErrorCorrectionLevel(int i) {
        this.bits = i;
    }

    public int cp() {
        return this.bits;
    }
}
