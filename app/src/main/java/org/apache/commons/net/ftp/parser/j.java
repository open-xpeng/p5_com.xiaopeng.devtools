package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import org.apache.commons.net.ftp.FTPFile;

/* compiled from: NetwareFTPEntryParser.java */
/* loaded from: classes13.dex */
public class j extends b {
    public j() {
        this(null);
    }

    public j(org.apache.commons.net.ftp.d dVar) {
        super("(d|-){1}\\s+\\[([-A-Z]+)\\]\\s+(\\S+)\\s+(\\d+)\\s+(\\S+\\s+\\S+\\s+((\\d+:\\d+)|(\\d{4})))\\s+(.*)");
        a(dVar);
    }

    @Override // org.apache.commons.net.ftp.e
    public FTPFile fV(String str) {
        FTPFile fTPFile = new FTPFile();
        if (matches(str)) {
            String group = group(1);
            String group2 = group(2);
            String group3 = group(3);
            String group4 = group(4);
            String group5 = group(5);
            String group6 = group(9);
            try {
                fTPFile.a(super.fW(group5));
            } catch (ParseException e) {
            }
            if (group.trim().equals("d")) {
                fTPFile.setType(1);
            } else {
                fTPFile.setType(0);
            }
            fTPFile.setUser(group3);
            fTPFile.setName(group6.trim());
            fTPFile.setSize(Long.parseLong(group4.trim()));
            if (group2.indexOf("R") != -1) {
                fTPFile.b(0, 0, true);
            }
            if (group2.indexOf("W") != -1) {
                fTPFile.b(0, 1, true);
            }
            return fTPFile;
        }
        return null;
    }

    @Override // org.apache.commons.net.ftp.parser.b
    protected org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("NETWARE", "MMM dd yyyy", "MMM dd HH:mm");
    }
}
