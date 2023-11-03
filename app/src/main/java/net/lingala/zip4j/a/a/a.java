package net.lingala.zip4j.a.a;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: MacBasedPRF.java */
/* loaded from: classes13.dex */
public class a implements d {
    private int alB;
    private String alC;
    private Mac mac;

    public a(String str) {
        this.alC = str;
        try {
            this.mac = Mac.getInstance(str);
            this.alB = this.mac.getMacLength();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // net.lingala.zip4j.a.a.d
    public byte[] doFinal(byte[] bArr) {
        return this.mac.doFinal(bArr);
    }

    public byte[] doFinal() {
        return this.mac.doFinal();
    }

    @Override // net.lingala.zip4j.a.a.d
    public int ss() {
        return this.alB;
    }

    @Override // net.lingala.zip4j.a.a.d
    public void init(byte[] bArr) {
        try {
            this.mac.init(new SecretKeySpec(bArr, this.alC));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        try {
            this.mac.update(bArr, i, i2);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
}
