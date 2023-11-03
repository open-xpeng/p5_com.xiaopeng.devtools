package org.apache.commons.net.ftp.parser;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: VMSVersioningFTPEntryParser.java */
/* loaded from: classes13.dex */
public class p extends o {
    private final Pattern ati;

    public p() {
        this(null);
    }

    public p(org.apache.commons.net.ftp.d dVar) {
        a(dVar);
        try {
            this.ati = Pattern.compile("(.*?);([0-9]+)\\s*.*");
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Unparseable regex supplied:  (.*?);([0-9]+)\\s*.*");
        }
    }

    @Override // org.apache.commons.net.ftp.f, org.apache.commons.net.ftp.e
    public List<String> G(List<String> list) {
        HashMap hashMap = new HashMap();
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Matcher matcher = this.ati.matcher(listIterator.next().trim());
            if (matcher.matches()) {
                MatchResult matchResult = matcher.toMatchResult();
                String group = matchResult.group(1);
                Integer valueOf = Integer.valueOf(matchResult.group(2));
                Integer num = (Integer) hashMap.get(group);
                if (num != null && valueOf.intValue() < num.intValue()) {
                    listIterator.remove();
                } else {
                    hashMap.put(group, valueOf);
                }
            }
        }
        while (listIterator.hasPrevious()) {
            Matcher matcher2 = this.ati.matcher(listIterator.previous().trim());
            if (matcher2.matches()) {
                MatchResult matchResult2 = matcher2.toMatchResult();
                String group2 = matchResult2.group(1);
                Integer valueOf2 = Integer.valueOf(matchResult2.group(2));
                Integer num2 = (Integer) hashMap.get(group2);
                if (num2 != null && valueOf2.intValue() < num2.intValue()) {
                    listIterator.remove();
                }
            }
        }
        return list;
    }

    @Override // org.apache.commons.net.ftp.parser.o
    protected boolean vf() {
        return true;
    }
}
