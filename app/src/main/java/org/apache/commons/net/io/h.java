package org.apache.commons.net.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* compiled from: Util.java */
/* loaded from: classes13.dex */
public final class h {
    public static final long a(InputStream inputStream, OutputStream outputStream, int i, long j, c cVar, boolean z) throws CopyStreamException {
        long j2;
        byte[] bArr = new byte[i > 0 ? i : 1024];
        long j3 = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                if (read == 0) {
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        break;
                    }
                    outputStream.write(read2);
                    if (z) {
                        outputStream.flush();
                    }
                    j2 = j3 + 1;
                    if (cVar != null) {
                        try {
                            cVar.a(j2, 1, j);
                        } catch (IOException e) {
                            e = e;
                            j3 = j2;
                            throw new CopyStreamException("IOException caught while copying.", j3, e);
                        }
                    } else {
                        continue;
                    }
                } else {
                    outputStream.write(bArr, 0, read);
                    if (z) {
                        outputStream.flush();
                    }
                    j2 = j3 + read;
                    if (cVar != null) {
                        cVar.a(j2, read, j);
                    }
                }
                j3 = j2;
            } catch (IOException e2) {
                e = e2;
            }
        }
        return j3;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
