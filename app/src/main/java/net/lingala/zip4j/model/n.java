package net.lingala.zip4j.model;

/* compiled from: Zip64ExtendedInfo.java */
/* loaded from: classes13.dex */
public class n extends o {
    private long ank = -1;
    private long anl = -1;
    private long anM = -1;
    private int anJ = -1;

    public long getCompressedSize() {
        return this.ank;
    }

    public void setCompressedSize(long j) {
        this.ank = j;
    }

    public long sQ() {
        return this.anl;
    }

    public void u(long j) {
        this.anl = j;
    }

    public long tl() {
        return this.anM;
    }

    public void y(long j) {
        this.anM = j;
    }

    public int tj() {
        return this.anJ;
    }

    public void fn(int i) {
        this.anJ = i;
    }
}
