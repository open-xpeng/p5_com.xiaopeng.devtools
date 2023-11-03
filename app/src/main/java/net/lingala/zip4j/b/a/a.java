package net.lingala.zip4j.b.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.d.c;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;

/* compiled from: NumberedSplitRandomAccessFile.java */
/* loaded from: classes13.dex */
public class a extends RandomAccessFile {
    private long amB;
    private File[] amC;
    private RandomAccessFile amD;
    private byte[] amE;
    private int amF;
    private String amG;

    public a(File file, String str, File[] fileArr) throws IOException {
        super(file, str);
        this.amE = new byte[1];
        this.amF = 0;
        super.close();
        if (RandomAccessFileMode.WRITE.getValue().equals(str)) {
            throw new IllegalArgumentException("write mode is not allowed for NumberedSplitRandomAccessFile");
        }
        b(fileArr);
        this.amD = new RandomAccessFile(file, str);
        this.amC = fileArr;
        this.amB = file.length();
        this.amG = str;
    }

    @Override // java.io.RandomAccessFile
    public int read() throws IOException {
        if (read(this.amE) == -1) {
            return -1;
        }
        return this.amE[0] & 255;
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.amD.read(bArr, i, i2);
        if (read == -1) {
            if (this.amF == this.amC.length - 1) {
                return -1;
            }
            eY(this.amF + 1);
            return read(bArr, i, i2);
        }
        return read;
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j) throws IOException {
        int i = (int) (j / this.amB);
        if (i != this.amF) {
            eY(i);
        }
        this.amD.seek(j - (i * this.amB));
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() throws IOException {
        return this.amD.getFilePointer();
    }

    @Override // java.io.RandomAccessFile
    public long length() throws IOException {
        return this.amD.length();
    }

    public void s(long j) throws IOException {
        this.amD.seek(j);
    }

    public void sy() throws IOException {
        eY(this.amC.length - 1);
    }

    private void eY(int i) throws IOException {
        if (this.amF == i) {
            return;
        }
        if (i > this.amC.length - 1) {
            throw new IOException("split counter greater than number of split files");
        }
        if (this.amD != null) {
            this.amD.close();
        }
        this.amD = new RandomAccessFile(this.amC[i], this.amG);
        this.amF = i;
    }

    private void b(File[] fileArr) throws IOException {
        int i = 1;
        for (File file : fileArr) {
            String m = c.m(file);
            try {
                if (i != Integer.parseInt(m)) {
                    throw new IOException("Split file number " + i + " does not exist");
                }
                i++;
            } catch (NumberFormatException e) {
                throw new IOException("Split file extension not in expected format. Found: " + m + " expected of format: .001, .002, etc");
            }
        }
    }
}
