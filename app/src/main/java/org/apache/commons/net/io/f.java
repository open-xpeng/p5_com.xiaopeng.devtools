package org.apache.commons.net.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ToNetASCIIInputStream.java */
/* loaded from: classes13.dex */
public final class f extends FilterInputStream {
    private int atp;

    public f(InputStream inputStream) {
        super(inputStream);
        this.atp = 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.atp == 2) {
            this.atp = 0;
            return 10;
        }
        int read = this.in.read();
        if (read != 10) {
            if (read == 13) {
                this.atp = 1;
                return 13;
            }
        } else if (this.atp != 1) {
            this.atp = 2;
            return 13;
        }
        this.atp = 0;
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i2 < 1) {
            return 0;
        }
        int available = available();
        if (i2 > available) {
            i2 = available;
        }
        if (i2 < 1) {
            i2 = 1;
        }
        int read = read();
        if (read == -1) {
            return -1;
        }
        int i4 = i2;
        int i5 = i;
        while (true) {
            i3 = i5 + 1;
            bArr[i5] = (byte) read;
            i4--;
            if (i4 <= 0 || (read = read()) == -1) {
                break;
            }
            i5 = i3;
        }
        return i3 - i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int available = this.in.available();
        if (this.atp == 2) {
            return available + 1;
        }
        return available;
    }
}
