package org.apache.commons.net.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/* compiled from: CRLFLineReader.java */
/* loaded from: classes13.dex */
public final class a extends BufferedReader {
    public a(Reader reader) {
        super(reader);
    }

    @Override // java.io.BufferedReader
    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        synchronized (this.lock) {
            boolean z = false;
            while (true) {
                int read = read();
                if (read == -1) {
                    String sb2 = sb.toString();
                    if (sb2.length() == 0) {
                        return null;
                    }
                    return sb2;
                } else if (z && read == 10) {
                    return sb.substring(0, sb.length() - 1);
                } else {
                    z = read == 13;
                    sb.append((char) read);
                }
            }
        }
    }
}
