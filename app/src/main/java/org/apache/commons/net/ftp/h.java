package org.apache.commons.net.ftp;

/* compiled from: FTPFileFilters.java */
/* loaded from: classes13.dex */
public class h {
    public static final g asN = new g() { // from class: org.apache.commons.net.ftp.h.1
        @Override // org.apache.commons.net.ftp.g
        public boolean a(FTPFile fTPFile) {
            return true;
        }
    };
    public static final g asO = new g() { // from class: org.apache.commons.net.ftp.h.2
        @Override // org.apache.commons.net.ftp.g
        public boolean a(FTPFile fTPFile) {
            return fTPFile != null;
        }
    };
    public static final g asP = new g() { // from class: org.apache.commons.net.ftp.h.3
        @Override // org.apache.commons.net.ftp.g
        public boolean a(FTPFile fTPFile) {
            return fTPFile != null && fTPFile.isDirectory();
        }
    };
}
