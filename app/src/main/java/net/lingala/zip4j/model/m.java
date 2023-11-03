package net.lingala.zip4j.model;

/* compiled from: Zip64EndOfCentralDirectoryRecord.java */
/* loaded from: classes13.dex */
public class m extends o {
    private int anH;
    private long anS;
    private int anT;
    private long anU;
    private long anV;
    private long anW;
    private long anX = -1;
    private byte[] anY;
    private int anh;
    private int anz;

    public long tu() {
        return this.anS;
    }

    public void A(long j) {
        this.anS = j;
    }

    public int ti() {
        return this.anH;
    }

    public void fl(int i) {
        this.anH = i;
    }

    public int sO() {
        return this.anh;
    }

    public void fc(int i) {
        this.anh = i;
    }

    public int tb() {
        return this.anz;
    }

    public void fg(int i) {
        this.anz = i;
    }

    public int tv() {
        return this.anT;
    }

    public void fq(int i) {
        this.anT = i;
    }

    public long tw() {
        return this.anU;
    }

    public void B(long j) {
        this.anU = j;
    }

    public long tx() {
        return this.anV;
    }

    public void C(long j) {
        this.anV = j;
    }

    public long ty() {
        return this.anW;
    }

    public void D(long j) {
        this.anW = j;
    }

    public long tz() {
        return this.anX;
    }

    public void E(long j) {
        this.anX = j;
    }

    public void Q(byte[] bArr) {
        this.anY = bArr;
    }
}
