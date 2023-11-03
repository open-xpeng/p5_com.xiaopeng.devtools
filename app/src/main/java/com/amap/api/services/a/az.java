package com.amap.api.services.a;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: StrictLineReader.java */
/* loaded from: classes11.dex */
public class az implements Closeable {
    private byte[] c;
    private int d;
    private int e;
    private final InputStream fB;
    private final Charset fC;

    public az(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public az(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        }
        if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(ba.fE)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.fB = inputStream;
        this.fC = charset;
        this.c = new byte[i];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.fB) {
            if (this.c != null) {
                this.c = null;
                this.fB.close();
            }
        }
    }

    public String a() throws IOException {
        int i;
        int i2;
        synchronized (this.fB) {
            if (this.c == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.d >= this.e) {
                b();
            }
            for (int i3 = this.d; i3 != this.e; i3++) {
                if (this.c[i3] == 10) {
                    if (i3 != this.d) {
                        i2 = i3 - 1;
                        if (this.c[i2] == 13) {
                            String str = new String(this.c, this.d, i2 - this.d, this.fC.name());
                            this.d = i3 + 1;
                            return str;
                        }
                    }
                    i2 = i3;
                    String str2 = new String(this.c, this.d, i2 - this.d, this.fC.name());
                    this.d = i3 + 1;
                    return str2;
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((this.e - this.d) + 80) { // from class: com.amap.api.services.a.az.1
                @Override // java.io.ByteArrayOutputStream
                public String toString() {
                    try {
                        return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1, az.this.fC.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1: while (true) {
                byteArrayOutputStream.write(this.c, this.d, this.e - this.d);
                this.e = -1;
                b();
                i = this.d;
                while (i != this.e) {
                    if (this.c[i] == 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.d) {
                byteArrayOutputStream.write(this.c, this.d, i - this.d);
            }
            this.d = i + 1;
            return byteArrayOutputStream.toString();
        }
    }

    private void b() throws IOException {
        int read = this.fB.read(this.c, 0, this.c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.d = 0;
        this.e = read;
    }
}
