package org.apache.commons.net.ftp.parser;

import java.io.File;
import java.text.ParseException;
import org.apache.commons.net.ftp.FTPFile;

/* compiled from: OS400FTPEntryParser.java */
/* loaded from: classes13.dex */
public class l extends b {
    public l() {
        this(null);
    }

    public l(org.apache.commons.net.ftp.d dVar) {
        super("(\\S+)\\s+(?:(\\d+)\\s+)?(?:(\\S+)\\s+(\\S+)\\s+)?(\\*STMF|\\*DIR|\\*FILE|\\*MEM)\\s+(?:(\\S+)\\s*)?");
        a(dVar);
    }

    @Override // org.apache.commons.net.ftp.e
    public FTPFile fV(String str) {
        int i;
        int lastIndexOf;
        FTPFile fTPFile = new FTPFile();
        fTPFile.fT(str);
        if (matches(str)) {
            String group = group(1);
            String group2 = group(2);
            String str2 = "";
            int i2 = 3;
            if (!isNullOrEmpty(group(3)) || !isNullOrEmpty(group(4))) {
                str2 = group(3) + " " + group(4);
            }
            String group3 = group(5);
            String group4 = group(6);
            try {
                fTPFile.a(super.fW(str2));
            } catch (ParseException e) {
            }
            if (!group3.equalsIgnoreCase("*STMF")) {
                if (!group3.equalsIgnoreCase("*DIR")) {
                    if (group3.equalsIgnoreCase("*FILE")) {
                        if (group4 == null || !group4.toUpperCase().endsWith(".SAVF")) {
                            return null;
                        }
                    } else if (!group3.equalsIgnoreCase("*MEM")) {
                        i = 1;
                    } else if (isNullOrEmpty(group4) || !isNullOrEmpty(group2) || !isNullOrEmpty(str2)) {
                        return null;
                    } else {
                        group4 = group4.replace('/', File.separatorChar);
                    }
                    i = 0;
                } else if (isNullOrEmpty(group2) || isNullOrEmpty(group4)) {
                    return null;
                } else {
                    i = 1;
                }
                i2 = i;
            } else if (isNullOrEmpty(group2) || isNullOrEmpty(group4)) {
                return null;
            } else {
                i = 1;
                i2 = 0;
            }
            fTPFile.setType(i2);
            fTPFile.setUser(group);
            try {
                fTPFile.setSize(Long.parseLong(group2));
            } catch (NumberFormatException e2) {
            }
            if (group4.endsWith("/")) {
                group4 = group4.substring(0, group4.length() - 1);
            }
            if (i != 0 && (lastIndexOf = group4.lastIndexOf(47)) > -1) {
                group4 = group4.substring(lastIndexOf + 1);
            }
            fTPFile.setName(group4);
            return fTPFile;
        }
        return null;
    }

    private boolean isNullOrEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    @Override // org.apache.commons.net.ftp.parser.b
    protected org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("OS/400", "yy/MM/dd HH:mm:ss", null);
    }
}
