package org.apache.commons.net.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: FromNetASCIIOutputStream.java */
/* loaded from: classes13.dex */
public final class e extends FilterOutputStream {
    private boolean ato;

    public e(OutputStream outputStream) {
        super(outputStream);
        this.ato = false;
    }

    private void fD(int i) throws IOException {
        if (i == 10) {
            if (this.ato) {
                this.out.write(d.atm);
                this.ato = false;
                return;
            }
            this.ato = false;
            this.out.write(10);
        } else if (i == 13) {
            this.ato = true;
        } else {
            if (this.ato) {
                this.out.write(13);
                this.ato = false;
            }
            this.out.write(i);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(int i) throws IOException {
        if (d.atk) {
            this.out.write(i);
        } else {
            fD(i);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        if (d.atk) {
            this.out.write(bArr, i, i2);
            return;
        }
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            int i4 = i + 1;
            fD(bArr[i]);
            i = i4;
            i2 = i3;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (d.atk) {
            super.close();
            return;
        }
        if (this.ato) {
            this.out.write(13);
        }
        super.close();
    }
}
