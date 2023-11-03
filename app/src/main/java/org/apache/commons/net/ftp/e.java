package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/* compiled from: FTPFileEntryParser.java */
/* loaded from: classes13.dex */
public interface e {
    List<String> G(List<String> list);

    String a(BufferedReader bufferedReader) throws IOException;

    FTPFile fV(String str);
}
