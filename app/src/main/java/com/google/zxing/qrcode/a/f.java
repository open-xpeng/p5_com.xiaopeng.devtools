package com.google.zxing.qrcode.a;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;

/* compiled from: QRCode.java */
/* loaded from: classes11.dex */
public final class f {
    private Mode kn;
    private ErrorCorrectionLevel ko;
    private com.google.zxing.qrcode.decoder.a kp;
    private int kq = -1;
    private b kr;

    public b cC() {
        return this.kr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.kn);
        sb.append("\n ecLevel: ");
        sb.append(this.ko);
        sb.append("\n version: ");
        sb.append(this.kp);
        sb.append("\n maskPattern: ");
        sb.append(this.kq);
        if (this.kr == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.kr);
        }
        sb.append(">>\n");
        return sb.toString();
    }

    public void a(Mode mode) {
        this.kn = mode;
    }

    public void b(ErrorCorrectionLevel errorCorrectionLevel) {
        this.ko = errorCorrectionLevel;
    }

    public void b(com.google.zxing.qrcode.decoder.a aVar) {
        this.kp = aVar;
    }

    public void by(int i) {
        this.kq = i;
    }

    public void j(b bVar) {
        this.kr = bVar;
    }

    public static boolean bz(int i) {
        return i >= 0 && i < 8;
    }
}
