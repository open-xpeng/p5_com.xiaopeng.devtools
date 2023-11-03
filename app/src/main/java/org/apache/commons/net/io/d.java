package org.apache.commons.net.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;

/* compiled from: FromNetASCIIInputStream.java */
/* loaded from: classes13.dex */
public final class d extends PushbackInputStream {
    static final byte[] atm;
    private int atn;
    static final String atl = System.getProperty("line.separator");
    static final boolean atk = atl.equals("\r\n");

    static {
        try {
            atm = atl.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Broken JVM - cannot find US-ASCII charset!", e);
        }
    }

    public d(InputStream inputStream) {
        super(inputStream, atm.length + 1);
        this.atn = 0;
    }

    private int vg() throws IOException {
        int read = super.read();
        if (read == 13) {
            int read2 = super.read();
            if (read2 == 10) {
                unread(atm);
                this.atn--;
                return super.read();
            }
            if (read2 != -1) {
                unread(read2);
            }
            return 13;
        }
        return read;
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (atk) {
            return super.read();
        }
        return vg();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (atk) {
            return super.read(bArr, i, i2);
        }
        if (i2 < 1) {
            return 0;
        }
        int available = available();
        if (i2 > available) {
            i2 = available;
        }
        this.atn = i2;
        if (this.atn < 1) {
            this.atn = 1;
        }
        int vg = vg();
        if (vg == -1) {
            return -1;
        }
        int i4 = vg;
        int i5 = i;
        while (true) {
            i3 = i5 + 1;
            bArr[i5] = (byte) i4;
            int i6 = this.atn - 1;
            this.atn = i6;
            if (i6 <= 0 || (i4 = vg()) == -1) {
                break;
            }
            i5 = i3;
        }
        return i3 - i;
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new IOException("Stream closed");
        }
        return (this.buf.length - this.pos) + this.in.available();
    }
}
