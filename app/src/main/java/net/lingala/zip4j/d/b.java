package net.lingala.zip4j.d;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: CrcUtil.java */
/* loaded from: classes13.dex */
public class b {
    public static long a(File file, ProgressMonitor progressMonitor) throws IOException {
        if (file == null || !file.exists() || !file.canRead()) {
            throw new ZipException("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
        }
        byte[] bArr = new byte[16384];
        CRC32 crc32 = new CRC32();
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    long value = crc32.getValue();
                    fileInputStream.close();
                    return value;
                }
                crc32.update(bArr, 0, read);
                if (progressMonitor != null) {
                    progressMonitor.J(read);
                    if (progressMonitor.ud()) {
                        progressMonitor.a(ProgressMonitor.Result.CANCELLED);
                        progressMonitor.a(ProgressMonitor.State.READY);
                        fileInputStream.close();
                        return 0L;
                    }
                }
            } finally {
            }
        }
    }
}
