package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;

/* compiled from: ZipHeader.java */
/* loaded from: classes13.dex */
public abstract class o {
    private HeaderSignature anZ;

    public HeaderSignature tA() {
        return this.anZ;
    }

    public void a(HeaderSignature headerSignature) {
        this.anZ = headerSignature;
    }
}
