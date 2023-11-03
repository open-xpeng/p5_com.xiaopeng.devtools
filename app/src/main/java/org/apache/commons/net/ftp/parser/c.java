package org.apache.commons.net.ftp.parser;

import java.util.regex.Pattern;

/* compiled from: DefaultFTPFileEntryParserFactory.java */
/* loaded from: classes13.dex */
public class c implements d {
    private static final Pattern asW = Pattern.compile("(\\p{javaJavaIdentifierStart}(\\p{javaJavaIdentifierPart})*\\.)+\\p{javaJavaIdentifierStart}(\\p{javaJavaIdentifierPart})*");

    @Override // org.apache.commons.net.ftp.parser.d
    public org.apache.commons.net.ftp.e fX(String str) {
        if (str == null) {
            throw new ParserInitializationException("Parser key cannot be null");
        }
        return a(str, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.apache.commons.net.ftp.e a(java.lang.String r5, org.apache.commons.net.ftp.d r6) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.ftp.parser.c.a(java.lang.String, org.apache.commons.net.ftp.d):org.apache.commons.net.ftp.e");
    }

    @Override // org.apache.commons.net.ftp.parser.d
    public org.apache.commons.net.ftp.e b(org.apache.commons.net.ftp.d dVar) throws ParserInitializationException {
        return a(dVar.uS(), dVar);
    }

    private org.apache.commons.net.ftp.e c(org.apache.commons.net.ftp.d dVar) {
        if (dVar != null && "WINDOWS".equals(dVar.uS())) {
            return new i(dVar);
        }
        org.apache.commons.net.ftp.d dVar2 = dVar != null ? new org.apache.commons.net.ftp.d(dVar) : null;
        org.apache.commons.net.ftp.e[] eVarArr = new org.apache.commons.net.ftp.e[2];
        i iVar = new i(dVar);
        boolean z = false;
        eVarArr[0] = iVar;
        if (dVar2 != null && "UNIX_LTRIM".equals(dVar2.uS())) {
            z = true;
        }
        eVarArr[1] = new n(dVar2, z);
        return new a(eVarArr);
    }

    private org.apache.commons.net.ftp.e d(org.apache.commons.net.ftp.d dVar) {
        if (dVar != null && "OS/400".equals(dVar.uS())) {
            return new l(dVar);
        }
        org.apache.commons.net.ftp.d dVar2 = dVar != null ? new org.apache.commons.net.ftp.d(dVar) : null;
        org.apache.commons.net.ftp.e[] eVarArr = new org.apache.commons.net.ftp.e[2];
        l lVar = new l(dVar);
        boolean z = false;
        eVarArr[0] = lVar;
        if (dVar2 != null && "UNIX_LTRIM".equals(dVar2.uS())) {
            z = true;
        }
        eVarArr[1] = new n(dVar2, z);
        return new a(eVarArr);
    }
}
