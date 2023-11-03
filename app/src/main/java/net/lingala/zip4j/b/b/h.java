package net.lingala.zip4j.b.b;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;

/* compiled from: SplitOutputStream.java */
/* loaded from: classes13.dex */
public class h extends OutputStream implements g {
    private File alk;
    private long amB;
    private RandomAccessFile amP;
    private int amQ;
    private long amR;
    private net.lingala.zip4j.d.e amc;

    public h(File file) throws FileNotFoundException, ZipException {
        this(file, -1L);
    }

    public h(File file, long j) throws FileNotFoundException, ZipException {
        this.amc = new net.lingala.zip4j.d.e();
        if (j >= 0 && j < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            throw new ZipException("split length less than minimum allowed split length of 65536 Bytes");
        }
        this.amP = new RandomAccessFile(file, RandomAccessFileMode.WRITE.getValue());
        this.amB = j;
        this.alk = file;
        this.amQ = 0;
        this.amR = 0L;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 <= 0) {
            return;
        }
        if (this.amB == -1) {
            this.amP.write(bArr, i, i2);
            this.amR += i2;
        } else if (this.amR >= this.amB) {
            sG();
            this.amP.write(bArr, i, i2);
            this.amR = i2;
        } else {
            long j = i2;
            if (this.amR + j > this.amB) {
                if (M(bArr)) {
                    sG();
                    this.amP.write(bArr, i, i2);
                    this.amR = j;
                    return;
                }
                this.amP.write(bArr, i, (int) (this.amB - this.amR));
                sG();
                this.amP.write(bArr, i + ((int) (this.amB - this.amR)), (int) (j - (this.amB - this.amR)));
                this.amR = j - (this.amB - this.amR);
                return;
            }
            this.amP.write(bArr, i, i2);
            this.amR += j;
        }
    }

    private void sG() throws IOException {
        String str;
        String fw = net.lingala.zip4j.d.c.fw(this.alk.getName());
        String absolutePath = this.alk.getAbsolutePath();
        if (this.alk.getParent() == null) {
            str = "";
        } else {
            str = this.alk.getParent() + System.getProperty("file.separator");
        }
        String str2 = ".z0" + (this.amQ + 1);
        if (this.amQ >= 9) {
            str2 = ".z" + (this.amQ + 1);
        }
        File file = new File(str + fw + str2);
        this.amP.close();
        if (file.exists()) {
            throw new IOException("split file: " + file.getName() + " already exists in the current directory, cannot rename this file");
        } else if (!this.alk.renameTo(file)) {
            throw new IOException("cannot rename newly created split file");
        } else {
            this.alk = new File(absolutePath);
            this.amP = new RandomAccessFile(this.alk, RandomAccessFileMode.WRITE.getValue());
            this.amQ++;
        }
    }

    private boolean M(byte[] bArr) {
        HeaderSignature[] values;
        int R = this.amc.R(bArr);
        for (HeaderSignature headerSignature : HeaderSignature.values()) {
            if (headerSignature != HeaderSignature.SPLIT_ZIP && headerSignature.getValue() == R) {
                return true;
            }
        }
        return false;
    }

    public boolean fa(int i) throws ZipException {
        if (i < 0) {
            throw new ZipException("negative buffersize for checkBufferSizeAndStartNextSplitFile");
        }
        if (!fb(i)) {
            try {
                sG();
                this.amR = 0L;
                return true;
            } catch (IOException e) {
                throw new ZipException(e);
            }
        }
        return false;
    }

    private boolean fb(int i) {
        return this.amB < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH || this.amR + ((long) i) <= this.amB;
    }

    public void seek(long j) throws IOException {
        this.amP.seek(j);
    }

    public int skipBytes(int i) throws IOException {
        return this.amP.skipBytes(i);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.amP.close();
    }

    @Override // net.lingala.zip4j.b.b.g
    public long getFilePointer() throws IOException {
        return this.amP.getFilePointer();
    }

    public boolean sE() {
        return this.amB != -1;
    }

    public long sD() {
        return this.amB;
    }

    @Override // net.lingala.zip4j.b.b.g
    public int sB() {
        return this.amQ;
    }
}
