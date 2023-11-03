package com.amap.api.services.a;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SDKInfo.java */
@y(a = "a")
/* loaded from: classes11.dex */
public class l {
    @z(a = "a1", b = 6)
    private String a;
    @z(a = "a2", b = 6)
    private String b;
    @z(a = "a6", b = 2)
    private int c;
    @z(a = "a3", b = 6)
    private String d;
    @z(a = "a4", b = 6)
    private String e;
    private String[] eG;
    @z(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;

    private l() {
        this.c = 1;
        this.eG = null;
    }

    private l(a aVar) {
        this.c = 1;
        this.eG = null;
        this.g = aVar.a;
        this.i = aVar.b;
        this.h = aVar.c;
        this.c = aVar.d ? 1 : 0;
        this.j = aVar.e;
        this.eG = aVar.f;
        this.b = m.b(this.g);
        this.a = m.b(this.i);
        this.d = m.b(this.h);
        this.e = m.b(a(this.eG));
        this.f = m.b(this.j);
    }

    /* compiled from: SDKInfo.java */
    /* loaded from: classes11.dex */
    public static class a {
        private String a;
        private String b;
        private String c;
        private boolean d = true;
        private String e = "standard";
        private String[] f = null;

        public a(String str, String str2, String str3) {
            this.a = str2;
            this.c = str3;
            this.b = str;
        }

        public a h(boolean z) {
            this.d = z;
            return this;
        }

        public a b(String[] strArr) {
            this.f = (String[]) strArr.clone();
            return this;
        }

        public l bm() throws au {
            if (this.f == null) {
                throw new au("sdk packages is null");
            }
            return new l(this);
        }
    }

    public String a() {
        if (TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.a)) {
            this.i = m.c(this.a);
        }
        return this.i;
    }

    public String b() {
        if (TextUtils.isEmpty(this.g) && !TextUtils.isEmpty(this.b)) {
            this.g = m.c(this.b);
        }
        return this.g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.f)) {
            this.j = m.c(this.f);
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "standard";
        }
        return this.j;
    }

    public String[] bl() {
        if ((this.eG == null || this.eG.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.eG = H(m.c(this.e));
        }
        return (String[]) this.eG.clone();
    }

    private String[] H(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
                sb.append(";");
            }
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("a1", m.b(str));
        return x.a((Map<String, String>) hashMap);
    }

    public static String e() {
        return "a6=1";
    }
}
