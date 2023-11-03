package com.google.zxing.qrcode.a;

import java.lang.reflect.Array;

/* compiled from: ByteMatrix.java */
/* loaded from: classes11.dex */
public final class b {
    private final int height;
    private final byte[][] kg;
    private final int width;

    public b(int i, int i2) {
        this.kg = (byte[][]) Array.newInstance(byte.class, i2, i);
        this.width = i;
        this.height = i2;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public byte k(int i, int i2) {
        return this.kg[i2][i];
    }

    public byte[][] cB() {
        return this.kg;
    }

    public void set(int i, int i2, int i3) {
        this.kg[i2][i] = (byte) i3;
    }

    public void set(int i, int i2, boolean z) {
        this.kg[i2][i] = z ? (byte) 1 : (byte) 0;
    }

    public void a(byte b) {
        for (int i = 0; i < this.height; i++) {
            for (int i2 = 0; i2 < this.width; i2++) {
                this.kg[i][i2] = b;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.width * 2 * this.height) + 2);
        for (int i = 0; i < this.height; i++) {
            for (int i2 = 0; i2 < this.width; i2++) {
                switch (this.kg[i][i2]) {
                    case 0:
                        sb.append(" 0");
                        break;
                    case 1:
                        sb.append(" 1");
                        break;
                    default:
                        sb.append("  ");
                        break;
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
