package net.lingala.zip4j.model;

import java.util.Objects;
import net.lingala.zip4j.headers.HeaderSignature;

/* compiled from: FileHeader.java */
/* loaded from: classes13.dex */
public class i extends b {
    private int anH;
    private int anI = 0;
    private int anJ;
    private byte[] anK;
    private byte[] anL;
    private long anM;
    private String anN;

    public i() {
        a(HeaderSignature.CENTRAL_DIRECTORY);
    }

    public int ti() {
        return this.anH;
    }

    public void fl(int i) {
        this.anH = i;
    }

    public void fm(int i) {
        this.anI = i;
    }

    public int tj() {
        return this.anJ;
    }

    public void fn(int i) {
        this.anJ = i;
    }

    public void O(byte[] bArr) {
        this.anK = bArr;
    }

    public byte[] tk() {
        return this.anL;
    }

    public void P(byte[] bArr) {
        this.anL = bArr;
    }

    public long tl() {
        return this.anM;
    }

    public void y(long j) {
        this.anM = j;
    }

    public String tm() {
        return this.anN;
    }

    public void fr(String str) {
        this.anN = str;
    }

    public String toString() {
        return getFileName();
    }

    @Override // net.lingala.zip4j.model.b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && super.equals(obj) && d(this) == d((i) obj)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getFileName(), Long.valueOf(d(this)));
    }

    private long d(i iVar) {
        if (iVar.sV() != null) {
            return iVar.sV().tl();
        }
        return iVar.tl();
    }
}
