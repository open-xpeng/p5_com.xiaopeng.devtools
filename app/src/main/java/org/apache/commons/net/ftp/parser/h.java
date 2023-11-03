package org.apache.commons.net.ftp.parser;

/* compiled from: MacOsPeterFTPEntryParser.java */
/* loaded from: classes13.dex */
public class h extends b {
    public h() {
        this(null);
    }

    public h(org.apache.commons.net.ftp.d dVar) {
        super("([bcdelfmpSs-])(((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-]))((r|-)(w|-)([xsStTL-])))\\+?\\s+((folder\\s+)|((\\d+)\\s+(\\d+)\\s+))(\\d+)\\s+((?:\\d+[-/]\\d+[-/]\\d+)|(?:\\S{3}\\s+\\d{1,2})|(?:\\d{1,2}\\s+\\S{3}))\\s+(\\d+(?::\\d+)?)\\s+(\\S*)(\\s*.*)");
        a(dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:3|4|5|6|(12:8|(10:10|12|(3:14|(2:21|22)(2:18|19)|20)|23|(2:41|42)|25|26|27|(1:29)(2:32|(2:34|(1:36)(1:37))(1:38))|30)|47|12|(0)|23|(0)|25|26|27|(0)(0)|30)|48|12|(0)|23|(0)|25|26|27|(0)(0)|30) */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.apache.commons.net.ftp.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.commons.net.ftp.FTPFile fV(java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.ftp.parser.h.fV(java.lang.String):org.apache.commons.net.ftp.FTPFile");
    }

    @Override // org.apache.commons.net.ftp.parser.b
    protected org.apache.commons.net.ftp.d vd() {
        return new org.apache.commons.net.ftp.d("UNIX", "MMM d yyyy", "MMM d HH:mm");
    }
}
