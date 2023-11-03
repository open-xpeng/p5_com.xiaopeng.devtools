package org.apache.commons.net.ftp.parser;

import org.apache.commons.net.ftp.FTPFile;

/* compiled from: CompositeFileEntryParser.java */
/* loaded from: classes13.dex */
public class a extends org.apache.commons.net.ftp.f {
    private final org.apache.commons.net.ftp.e[] asT;
    private org.apache.commons.net.ftp.e asU = null;

    public a(org.apache.commons.net.ftp.e[] eVarArr) {
        this.asT = eVarArr;
    }

    @Override // org.apache.commons.net.ftp.e
    public FTPFile fV(String str) {
        org.apache.commons.net.ftp.e[] eVarArr;
        if (this.asU != null) {
            FTPFile fV = this.asU.fV(str);
            if (fV != null) {
                return fV;
            }
            return null;
        }
        for (org.apache.commons.net.ftp.e eVar : this.asT) {
            FTPFile fV2 = eVar.fV(str);
            if (fV2 != null) {
                this.asU = eVar;
                return fV2;
            }
        }
        return null;
    }
}
