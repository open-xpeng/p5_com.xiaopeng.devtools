package net.lingala.zip4j.a;

import java.security.SecureRandom;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.AesKeyStrength;

/* compiled from: AESEncrypter.java */
/* loaded from: classes13.dex */
public class a implements c {
    private byte[] alA;
    private net.lingala.zip4j.a.b.a alu;
    private net.lingala.zip4j.a.a.a alv;
    private final byte[] aly;
    private byte[] alz;
    private boolean finished;
    private final byte[] iv;
    private final SecureRandom random = new SecureRandom();
    private int alw = 1;
    private int alx = 0;

    public a(char[] cArr, AesKeyStrength aesKeyStrength) throws ZipException {
        if (cArr == null || cArr.length == 0) {
            throw new ZipException("input password is empty or null");
        }
        if (aesKeyStrength != AesKeyStrength.KEY_STRENGTH_128 && aesKeyStrength != AesKeyStrength.KEY_STRENGTH_256) {
            throw new ZipException("Invalid AES key strength");
        }
        this.finished = false;
        this.aly = new byte[16];
        this.iv = new byte[16];
        a(cArr, aesKeyStrength);
    }

    private void a(char[] cArr, AesKeyStrength aesKeyStrength) throws ZipException {
        this.alA = eW(aesKeyStrength.getSaltLength());
        byte[] a = b.a(this.alA, cArr, aesKeyStrength);
        this.alz = b.a(a, aesKeyStrength);
        this.alu = b.c(a, aesKeyStrength);
        this.alv = b.b(a, aesKeyStrength);
    }

    @Override // net.lingala.zip4j.a.c
    public int d(byte[] bArr, int i, int i2) throws ZipException {
        if (this.finished) {
            throw new ZipException("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
        }
        if (i2 % 16 != 0) {
            this.finished = true;
        }
        int i3 = i;
        while (true) {
            int i4 = i + i2;
            if (i3 < i4) {
                int i5 = i3 + 16;
                this.alx = i5 <= i4 ? 16 : i4 - i3;
                b.h(this.iv, this.alw);
                this.alu.f(this.iv, this.aly);
                for (int i6 = 0; i6 < this.alx; i6++) {
                    int i7 = i3 + i6;
                    bArr[i7] = (byte) (bArr[i7] ^ this.aly[i6]);
                }
                this.alv.update(bArr, i3, this.alx);
                this.alw++;
                i3 = i5;
            } else {
                return i2;
            }
        }
    }

    private byte[] eW(int i) throws ZipException {
        if (i != 8 && i != 16) {
            throw new ZipException("invalid salt size, cannot generate salt");
        }
        int i2 = i == 8 ? 2 : 4;
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i2; i3++) {
            int nextInt = this.random.nextInt();
            int i4 = i3 * 4;
            bArr[i4] = (byte) (nextInt >> 24);
            bArr[1 + i4] = (byte) (nextInt >> 16);
            bArr[2 + i4] = (byte) (nextInt >> 8);
            bArr[3 + i4] = (byte) nextInt;
        }
        return bArr;
    }

    public byte[] sp() {
        byte[] bArr = new byte[10];
        System.arraycopy(this.alv.doFinal(), 0, bArr, 0, 10);
        return bArr;
    }

    public byte[] sq() {
        return this.alz;
    }

    public byte[] sr() {
        return this.alA;
    }
}
