package com.xiaopeng.lib.framework.netchannelmodule.http.traffic;

import android.support.annotation.NonNull;
import com.xiaopeng.lib.framework.netchannelmodule.http.statistic.BaseHttpCounter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes12.dex */
public class CountingInputStream extends FilterInputStream {
    protected ICollector mCollector;
    protected BaseHttpCounter mCounter;

    public CountingInputStream(@NonNull ICollector iCollector, @NonNull InputStream inputStream) throws IOException {
        super(inputStream);
        this.mCollector = iCollector;
    }

    public CountingInputStream setStatisticCounter(@NonNull BaseHttpCounter baseHttpCounter) {
        this.mCounter = baseHttpCounter;
        return this;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = this.in.read();
        if (read != -1 && this.mCounter != null) {
            this.mCounter.addReceivedSize(this.mCollector.getDomain(), 1L);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read > 0 && this.mCounter != null) {
            this.mCounter.addReceivedSize(this.mCollector.getDomain(), read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
    }
}
