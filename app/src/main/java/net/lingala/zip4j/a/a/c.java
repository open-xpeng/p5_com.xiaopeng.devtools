package net.lingala.zip4j.a.a;

/* compiled from: PBKDF2Parameters.java */
/* loaded from: classes13.dex */
public class c {
    protected int alF;
    protected String alG;
    protected byte[] alH;
    protected String hashAlgorithm;
    protected byte[] salt;

    public c() {
        this.hashAlgorithm = null;
        this.alG = "UTF-8";
        this.salt = null;
        this.alF = 1000;
        this.alH = null;
    }

    public c(String str, String str2, byte[] bArr, int i) {
        this(str, str2, bArr, i, null);
    }

    public c(String str, String str2, byte[] bArr, int i, byte[] bArr2) {
        this.hashAlgorithm = str;
        this.alG = str2;
        this.salt = bArr;
        this.alF = i;
        this.alH = bArr2;
    }

    public int getIterationCount() {
        return this.alF;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public String st() {
        return this.hashAlgorithm;
    }
}
