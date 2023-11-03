package org.apache.commons.net.ftp.parser;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: RegexFTPFileEntryParserImpl.java */
/* loaded from: classes13.dex */
public abstract class m extends org.apache.commons.net.ftp.f {
    private Pattern pattern = null;
    private MatchResult ate = null;
    protected Matcher atf = null;

    public m(String str) {
        H(str, 0);
    }

    public m(String str, int i) {
        H(str, i);
    }

    public boolean matches(String str) {
        this.ate = null;
        this.atf = this.pattern.matcher(str);
        if (this.atf.matches()) {
            this.ate = this.atf.toMatchResult();
        }
        return this.ate != null;
    }

    public String group(int i) {
        if (this.ate == null) {
            return null;
        }
        return this.ate.group(i);
    }

    public boolean fZ(String str) {
        H(str, 0);
        return true;
    }

    private void H(String str, int i) {
        try {
            this.pattern = Pattern.compile(str, i);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Unparseable regex supplied: " + str);
        }
    }
}
