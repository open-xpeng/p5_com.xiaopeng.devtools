package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/* compiled from: FTPFileEntryParserImpl.java */
/* loaded from: classes13.dex */
public abstract class f implements e {
    @Override // org.apache.commons.net.ftp.e
    public String a(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }

    @Override // org.apache.commons.net.ftp.e
    public List<String> G(List<String> list) {
        return list;
    }
}
