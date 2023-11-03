package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;

/* compiled from: LocalFileHeader.java */
/* loaded from: classes13.dex */
public class j extends b {
    private boolean anO;

    public j() {
        a(HeaderSignature.LOCAL_FILE_HEADER);
    }

    public boolean tn() {
        return this.anO;
    }

    public void bx(boolean z) {
        this.anO = z;
    }
}
