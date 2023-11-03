package com.ut.mini.internal;

/* loaded from: classes11.dex */
public class CustomDNS {
    private IDnsResolver a;

    /* loaded from: classes11.dex */
    public interface IDnsResolver {
        String[] resolveUrl(String str);
    }

    public static CustomDNS instance() {
        return a.a;
    }

    private CustomDNS() {
        this.a = null;
    }

    public void setDnsResolver(IDnsResolver iDnsResolver) {
        this.a = iDnsResolver;
    }

    public String[] resolveUrl(String str) {
        if (this.a != null) {
            return this.a.resolveUrl(str);
        }
        return null;
    }

    /* loaded from: classes11.dex */
    private static class a {
        private static final CustomDNS a = new CustomDNS();
    }
}
