package org.apache.commons.net.ftp.parser;

import com.activeandroid.annotation.Table;
import java.text.ParseException;
import java.util.List;
import org.apache.commons.net.ftp.FTPFile;

/* compiled from: MVSFTPEntryParser.java */
/* loaded from: classes13.dex */
public class g extends b {
    private int atc;
    private n atd;

    public g() {
        super("");
        this.atc = -1;
        super.a((org.apache.commons.net.ftp.d) null);
    }

    @Override // org.apache.commons.net.ftp.e
    public FTPFile fV(String str) {
        boolean d;
        FTPFile fTPFile = new FTPFile();
        if (this.atc == 0) {
            d = a(fTPFile, str);
        } else if (this.atc == 1) {
            boolean b = b(fTPFile, str);
            d = !b ? c(fTPFile, str) : b;
        } else {
            d = this.atc == 2 ? d(fTPFile, str) : this.atc == 3 ? e(fTPFile, str) : this.atc == 4 ? f(fTPFile, str) : false;
        }
        if (!d) {
            return null;
        }
        return fTPFile;
    }

    private boolean a(FTPFile fTPFile, String str) {
        if (matches(str)) {
            fTPFile.fT(str);
            String group = group(2);
            String group2 = group(1);
            fTPFile.setName(group);
            if ("PS".equals(group2)) {
                fTPFile.setType(0);
            } else if (!"PO".equals(group2) && !"PO-E".equals(group2)) {
                return false;
            } else {
                fTPFile.setType(1);
            }
            return true;
        }
        return false;
    }

    private boolean b(FTPFile fTPFile, String str) {
        if (matches(str)) {
            fTPFile.fT(str);
            String str2 = group(2) + " " + group(3);
            fTPFile.setName(group(1));
            fTPFile.setType(0);
            try {
                fTPFile.a(super.fW(str2));
                return true;
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean c(FTPFile fTPFile, String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        fTPFile.fT(str);
        fTPFile.setName(str.split(" ")[0]);
        fTPFile.setType(0);
        return true;
    }

    private boolean d(FTPFile fTPFile, String str) {
        if (this.atd.fV(str) == null) {
            return false;
        }
        return true;
    }

    private boolean e(FTPFile fTPFile, String str) {
        if (matches(str) && group(3).equalsIgnoreCase("OUTPUT")) {
            fTPFile.fT(str);
            fTPFile.setName(group(2));
            fTPFile.setType(0);
            return true;
        }
        return false;
    }

    private boolean f(FTPFile fTPFile, String str) {
        if (matches(str) && group(4).equalsIgnoreCase("OUTPUT")) {
            fTPFile.fT(str);
            fTPFile.setName(group(2));
            fTPFile.setType(0);
            return true;
        }
        return false;
    }

    @Override // org.apache.commons.net.ftp.f, org.apache.commons.net.ftp.e
    public List<String> G(List<String> list) {
        if (list != null && list.size() > 0) {
            String str = list.get(0);
            if (str.indexOf("Volume") >= 0 && str.indexOf("Dsname") >= 0) {
                setType(0);
                super.fZ("\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+[FV]\\S*\\s+\\S+\\s+\\S+\\s+(PS|PO|PO-E)\\s+(\\S+)\\s*");
            } else if (str.indexOf("Name") >= 0 && str.indexOf(Table.DEFAULT_ID_NAME) >= 0) {
                setType(1);
                super.fZ("(\\S+)\\s+\\S+\\s+\\S+\\s+(\\S+)\\s+(\\S+)\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s*");
            } else if (str.indexOf("total") == 0) {
                setType(2);
                this.atd = new n();
            } else if (str.indexOf("Spool Files") >= 30) {
                setType(3);
                super.fZ("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*");
            } else if (str.indexOf("JOBNAME") == 0 && str.indexOf("JOBID") > 8) {
                setType(4);
                super.fZ("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+).*");
            } else {
                setType(-1);
            }
            if (this.atc != 3) {
                list.remove(0);
            }
        }
        return list;
    }

    void setType(int i) {
        this.atc = i;
    }

    @Override // org.apache.commons.net.ftp.parser.b
    protected org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("MVS", "yyyy/MM/dd HH:mm", null);
    }
}
