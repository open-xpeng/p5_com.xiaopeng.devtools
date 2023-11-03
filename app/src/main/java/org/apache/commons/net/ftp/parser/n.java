package org.apache.commons.net.ftp.parser;

import java.util.List;
import java.util.ListIterator;

/* compiled from: UnixFTPEntryParser.java */
/* loaded from: classes13.dex */
public class n extends b {
    public static final org.apache.commons.net.ftp.d atg = new org.apache.commons.net.ftp.d("UNIX", "yyyy-MM-dd HH:mm", null);
    final boolean ath;

    public n() {
        this(null);
    }

    public n(org.apache.commons.net.ftp.d dVar) {
        this(dVar, false);
    }

    public n(org.apache.commons.net.ftp.d dVar, boolean z) {
        super("([bcdelfmpSs-])(((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-])))\\+?\\s*(\\d+)\\s+(?:(\\S+(?:\\s\\S+)*?)\\s+)?(?:(\\S+(?:\\s\\S+)*)\\s+)?(\\d+(?:,\\s*\\d+)?)\\s+((?:\\d+[-/]\\d+[-/]\\d+)|(?:\\S{3}\\s+\\d{1,2})|(?:\\d{1,2}\\s+\\S{3})|(?:\\d{1,2}月\\s+\\d{1,2}日))\\s+((?:\\d+(?::\\d+)?)|(?:\\d{4}年))\\s(.*)");
        a(dVar);
        this.ath = z;
    }

    @Override // org.apache.commons.net.ftp.f, org.apache.commons.net.ftp.e
    public List<String> G(List<String> list) {
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().matches("^total \\d+$")) {
                listIterator.remove();
            }
        }
        return list;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:3|(1:5)|(3:6|7|(1:9)(1:50))|10|(12:12|(10:14|16|(3:18|(2:25|26)(2:22|23)|24)|27|(2:42|43)|29|30|31|(2:33|(1:35)(1:38))(1:39)|36)|48|16|(0)|27|(0)|29|30|31|(0)(0)|36)|49|16|(0)|27|(0)|29|30|31|(0)(0)|36) */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.commons.net.ftp.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.net.ftp.FTPFile fV(java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.ftp.parser.n.fV(java.lang.String):org.apache.commons.net.ftp.FTPFile");
    }

    @Override // org.apache.commons.net.ftp.parser.b
    protected org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("UNIX", "MMM d yyyy", "MMM d HH:mm");
    }
}
