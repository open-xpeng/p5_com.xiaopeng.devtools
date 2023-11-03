package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionMethod;

/* compiled from: AESExtraDataRecord.java */
/* loaded from: classes13.dex */
public class a extends o {
    private AesVersion and;
    private String ane;
    private AesKeyStrength anf;
    private CompressionMethod ang;
    private int dataSize;

    public a() {
        a(HeaderSignature.AES_EXTRA_DATA_RECORD);
        this.dataSize = 7;
        this.and = AesVersion.TWO;
        this.ane = "AE";
        this.anf = AesKeyStrength.KEY_STRENGTH_256;
        this.ang = CompressionMethod.DEFLATE;
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public void setDataSize(int i) {
        this.dataSize = i;
    }

    public AesVersion sK() {
        return this.and;
    }

    public void a(AesVersion aesVersion) {
        this.and = aesVersion;
    }

    public String sL() {
        return this.ane;
    }

    public void fp(String str) {
        this.ane = str;
    }

    public AesKeyStrength sM() {
        return this.anf;
    }

    public void a(AesKeyStrength aesKeyStrength) {
        this.anf = aesKeyStrength;
    }

    public CompressionMethod sN() {
        return this.ang;
    }

    public void a(CompressionMethod compressionMethod) {
        this.ang = compressionMethod;
    }
}
