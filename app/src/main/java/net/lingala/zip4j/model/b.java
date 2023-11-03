package net.lingala.zip4j.model;

import java.util.List;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* compiled from: AbstractFileHeader.java */
/* loaded from: classes13.dex */
public abstract class b extends o {
    private CompressionMethod ang;
    private int anh;
    private byte[] ani;
    private long anj;
    private int anm;
    private int ann;
    private boolean ano;
    private boolean anq;
    private n anr;
    private a ans;
    private boolean ant;
    private List<h> anu;
    private String fileName;
    private boolean isDirectory;
    private long crc = 0;
    private long ank = 0;
    private long anl = 0;
    private EncryptionMethod anp = EncryptionMethod.NONE;

    public int sO() {
        return this.anh;
    }

    public void fc(int i) {
        this.anh = i;
    }

    public byte[] sP() {
        return this.ani;
    }

    public void N(byte[] bArr) {
        this.ani = bArr;
    }

    public CompressionMethod sN() {
        return this.ang;
    }

    public void a(CompressionMethod compressionMethod) {
        this.ang = compressionMethod;
    }

    public long getLastModifiedTime() {
        return this.anj;
    }

    public void t(long j) {
        this.anj = j;
    }

    public long getCrc() {
        return this.crc;
    }

    public void setCrc(long j) {
        this.crc = j;
    }

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

    public int sR() {
        return this.anm;
    }

    public void fd(int i) {
        this.anm = i;
    }

    public int sS() {
        return this.ann;
    }

    public void fe(int i) {
        this.ann = i;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public boolean isEncrypted() {
        return this.ano;
    }

    public void bt(boolean z) {
        this.ano = z;
    }

    public EncryptionMethod sT() {
        return this.anp;
    }

    public void a(EncryptionMethod encryptionMethod) {
        this.anp = encryptionMethod;
    }

    public boolean sU() {
        return this.anq;
    }

    public void bu(boolean z) {
        this.anq = z;
    }

    public n sV() {
        return this.anr;
    }

    public void a(n nVar) {
        this.anr = nVar;
    }

    public a sW() {
        return this.ans;
    }

    public void a(a aVar) {
        this.ans = aVar;
    }

    public boolean sX() {
        return this.ant;
    }

    public void bv(boolean z) {
        this.ant = z;
    }

    public List<h> sY() {
        return this.anu;
    }

    public void C(List<h> list) {
        this.anu = list;
    }

    public void bw(boolean z) {
        this.isDirectory = z;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        return getFileName().equals(((b) obj).getFileName());
    }
}
