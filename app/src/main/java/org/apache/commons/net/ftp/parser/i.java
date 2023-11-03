package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPFile;

/* compiled from: NTFTPEntryParser.java */
/* loaded from: classes13.dex */
public class i extends b {
    private final e asV;

    public i() {
        this(null);
    }

    public i(org.apache.commons.net.ftp.d dVar) {
        super("(\\S+)\\s+(\\S+)\\s+(?:(<DIR>)|([0-9]+))\\s+(\\S.*)", 32);
        a(dVar);
        org.apache.commons.net.ftp.d dVar2 = new org.apache.commons.net.ftp.d("WINDOWS", "MM-dd-yy kk:mm", null);
        dVar2.fN("MM-dd-yy kk:mm");
        this.asV = new f();
        ((org.apache.commons.net.ftp.a) this.asV).a(dVar2);
    }

    @Override // org.apache.commons.net.ftp.e
    public FTPFile fV(String str) {
        FTPFile fTPFile = new FTPFile();
        fTPFile.fT(str);
        if (matches(str)) {
            String str2 = group(1) + " " + group(2);
            String group = group(3);
            String group2 = group(4);
            String group3 = group(5);
            try {
                fTPFile.a(super.fW(str2));
            } catch (ParseException e) {
                try {
                    fTPFile.a(this.asV.fW(str2));
                } catch (ParseException e2) {
                }
            }
            if (group3 == null || group3.equals(".") || group3.equals("..")) {
                return null;
            }
            fTPFile.setName(group3);
            if ("<DIR>".equals(group)) {
                fTPFile.setType(1);
                fTPFile.setSize(0L);
            } else {
                fTPFile.setType(0);
                if (group2 != null) {
                    fTPFile.setSize(Long.parseLong(group2));
                }
            }
            return fTPFile;
        }
        return null;
    }

    @Override // org.apache.commons.net.ftp.parser.b
    public org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("WINDOWS", "MM-dd-yy hh:mma", null);
    }
}
