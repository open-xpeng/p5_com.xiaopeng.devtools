package org.apache.commons.net.util;

import java.nio.charset.Charset;

/* compiled from: Charsets.java */
/* loaded from: classes13.dex */
public class a {
    public static Charset ga(String str) {
        return str == null ? Charset.defaultCharset() : Charset.forName(str);
    }
}
