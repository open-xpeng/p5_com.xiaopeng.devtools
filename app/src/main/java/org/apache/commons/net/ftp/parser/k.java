package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPFile;

/* compiled from: OS2FTPEntryParser.java */
/* loaded from: classes13.dex */
public class k extends b {
    public k() {
        this(null);
    }

    public k(org.apache.commons.net.ftp.d dVar) {
        super("\\s*([0-9]+)\\s*(\\s+|[A-Z]+)\\s*(DIR|\\s+)\\s*(\\S+)\\s+(\\S+)\\s+(\\S.*)");
        a(dVar);
    }

    @Override // org.apache.commons.net.ftp.e
    public FTPFile fV(String str) {
        FTPFile fTPFile = new FTPFile();
        if (matches(str)) {
            String group = group(1);
            String group2 = group(2);
            String group3 = group(3);
            String str2 = group(4) + " " + group(5);
            String group4 = group(6);
            try {
                fTPFile.a(super.fW(str2));
            } catch (ParseException e) {
            }
            if (group3.trim().equals("DIR") || group2.trim().equals("DIR")) {
                fTPFile.setType(1);
            } else {
                fTPFile.setType(0);
            }
            fTPFile.setName(group4.trim());
            fTPFile.setSize(Long.parseLong(group.trim()));
            return fTPFile;
        }
        return null;
    }

    @Override // org.apache.commons.net.ftp.parser.b
    protected org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("OS/2", "MM-dd-yy HH:mm", null);
    }
}
