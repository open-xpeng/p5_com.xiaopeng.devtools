package org.apache.commons.net.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: ToNetASCIIOutputStream.java */
/* loaded from: classes13.dex */
public final class g extends FilterOutputStream {
    private boolean ato;

    public g(OutputStream outputStream) {
        super(outputStream);
        this.ato = false;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(int i) throws IOException {
        if (i != 10) {
            if (i == 13) {
                this.ato = true;
                this.out.write(13);
                return;
            }
        } else if (!this.ato) {
            this.out.write(13);
        }
        this.ato = false;
        this.out.write(i);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                int i4 = i + 1;
                write(bArr[i]);
                i = i4;
                i2 = i3;
            }
        }
    }
}
