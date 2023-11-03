package org.apache.commons.net.ftp.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.StringTokenizer;
import org.apache.commons.net.ftp.FTPFile;

/* compiled from: VMSFTPEntryParser.java */
/* loaded from: classes13.dex */
public class o extends b {
    public o() {
        this(null);
    }

    public o(org.apache.commons.net.ftp.d dVar) {
        super("(.*?;[0-9]+)\\s*(\\d+)/\\d+\\s*(\\S+)\\s+(\\S+)\\s+\\[(([0-9$A-Za-z_]+)|([0-9$A-Za-z_]+),([0-9$a-zA-Z_]+))\\]?\\s*\\([a-zA-Z]*,([a-zA-Z]*),([a-zA-Z]*),([a-zA-Z]*)\\)");
        a(dVar);
    }

    @Override // org.apache.commons.net.ftp.e
    public FTPFile fV(String str) {
        String nextToken;
        String str2 = null;
        if (matches(str)) {
            FTPFile fTPFile = new FTPFile();
            fTPFile.fT(str);
            String group = group(1);
            String group2 = group(2);
            String str3 = group(3) + " " + group(4);
            String group3 = group(5);
            String[] strArr = {group(9), group(10), group(11)};
            try {
                fTPFile.a(super.fW(str3));
            } catch (ParseException e) {
            }
            StringTokenizer stringTokenizer = new StringTokenizer(group3, ",");
            switch (stringTokenizer.countTokens()) {
                case 1:
                    nextToken = stringTokenizer.nextToken();
                    break;
                case 2:
                    str2 = stringTokenizer.nextToken();
                    nextToken = stringTokenizer.nextToken();
                    break;
                default:
                    nextToken = null;
                    break;
            }
            if (group.lastIndexOf(".DIR") != -1) {
                fTPFile.setType(1);
            } else {
                fTPFile.setType(0);
            }
            if (vf()) {
                fTPFile.setName(group);
            } else {
                fTPFile.setName(group.substring(0, group.lastIndexOf(";")));
            }
            fTPFile.setSize(Long.parseLong(group2) * 512);
            fTPFile.setGroup(str2);
            fTPFile.setUser(nextToken);
            for (int i = 0; i < 3; i++) {
                String str4 = strArr[i];
                fTPFile.b(i, 0, str4.indexOf(82) >= 0);
                fTPFile.b(i, 1, str4.indexOf(87) >= 0);
                fTPFile.b(i, 2, str4.indexOf(69) >= 0);
            }
            return fTPFile;
        }
        return null;
    }

    @Override // org.apache.commons.net.ftp.f, org.apache.commons.net.ftp.e
    public String a(BufferedReader bufferedReader) throws IOException {
        String readLine = bufferedReader.readLine();
        StringBuilder sb = new StringBuilder();
        while (readLine != null) {
            if (readLine.startsWith("Directory") || readLine.startsWith("Total")) {
                readLine = bufferedReader.readLine();
            } else {
                sb.append(readLine);
                if (readLine.trim().endsWith(")")) {
                    break;
                }
                readLine = bufferedReader.readLine();
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    protected boolean vf() {
        return false;
    }

    @Override // org.apache.commons.net.ftp.parser.b
    protected org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("VMS", "d-MMM-yyyy HH:mm:ss", null);
    }
}
