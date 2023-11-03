package org.apache.commons.net.ftp;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/* compiled from: FTPClientConfig.java */
/* loaded from: classes13.dex */
public class d {
    private static final Map<String, Object> arp = new TreeMap();
    private final String arh;
    private String ari;
    private String arj;
    private boolean ark;
    private String arl;
    private String arm;
    private String arn;
    private boolean aro;

    public d(String str) {
        this.ari = null;
        this.arj = null;
        this.ark = true;
        this.arl = null;
        this.arm = null;
        this.arn = null;
        this.aro = false;
        this.arh = str;
    }

    public d() {
        this("UNIX");
    }

    public d(String str, String str2, String str3) {
        this(str);
        this.ari = str2;
        this.arj = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(String str, d dVar) {
        this.ari = null;
        this.arj = null;
        this.ark = true;
        this.arl = null;
        this.arm = null;
        this.arn = null;
        this.aro = false;
        this.arh = str;
        this.ari = dVar.ari;
        this.ark = dVar.ark;
        this.arj = dVar.arj;
        this.aro = dVar.aro;
        this.arl = dVar.arl;
        this.arn = dVar.arn;
        this.arm = dVar.arm;
    }

    public d(d dVar) {
        this.ari = null;
        this.arj = null;
        this.ark = true;
        this.arl = null;
        this.arm = null;
        this.arn = null;
        this.aro = false;
        this.arh = dVar.arh;
        this.ari = dVar.ari;
        this.ark = dVar.ark;
        this.arj = dVar.arj;
        this.aro = dVar.aro;
        this.arl = dVar.arl;
        this.arn = dVar.arn;
        this.arm = dVar.arm;
    }

    static {
        arp.put("en", Locale.ENGLISH);
        arp.put("de", Locale.GERMAN);
        arp.put("it", Locale.ITALIAN);
        arp.put("es", new Locale("es", "", ""));
        arp.put("pt", new Locale("pt", "", ""));
        arp.put("da", new Locale("da", "", ""));
        arp.put("sv", new Locale("sv", "", ""));
        arp.put("no", new Locale("no", "", ""));
        arp.put("nl", new Locale("nl", "", ""));
        arp.put("ro", new Locale("ro", "", ""));
        arp.put("sq", new Locale("sq", "", ""));
        arp.put("sh", new Locale("sh", "", ""));
        arp.put("sk", new Locale("sk", "", ""));
        arp.put("sl", new Locale("sl", "", ""));
        arp.put("fr", "jan|fév|mar|avr|mai|jun|jui|aoû|sep|oct|nov|déc");
    }

    public String uS() {
        return this.arh;
    }

    public String uT() {
        return this.ari;
    }

    public String uU() {
        return this.arj;
    }

    public String uV() {
        return this.arn;
    }

    public String uW() {
        return this.arm;
    }

    public String uX() {
        return this.arl;
    }

    public boolean uY() {
        return this.ark;
    }

    public void fN(String str) {
        this.ari = str;
    }

    public void fO(String str) {
        this.arj = str;
    }

    public void fP(String str) {
        this.arn = str;
    }

    public static DateFormatSymbols fQ(String str) {
        Object obj = arp.get(str);
        if (obj != null) {
            if (obj instanceof Locale) {
                return new DateFormatSymbols((Locale) obj);
            }
            if (obj instanceof String) {
                return fR((String) obj);
            }
        }
        return new DateFormatSymbols(Locale.US);
    }

    public static DateFormatSymbols fR(String str) {
        String[] fS = fS(str);
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(Locale.US);
        dateFormatSymbols.setShortMonths(fS);
        return dateFormatSymbols;
    }

    private static String[] fS(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "|");
        if (12 != stringTokenizer.countTokens()) {
            throw new IllegalArgumentException("expecting a pipe-delimited string containing 12 tokens");
        }
        String[] strArr = new String[13];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            strArr[i] = stringTokenizer.nextToken();
            i++;
        }
        strArr[i] = "";
        return strArr;
    }

    public boolean uZ() {
        return this.aro;
    }
}
