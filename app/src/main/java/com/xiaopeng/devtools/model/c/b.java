package com.xiaopeng.devtools.model.c;

import java.util.Map;

/* compiled from: FactoryCodeModel.java */
/* loaded from: classes12.dex */
public class b {
    private String sd;
    private String se;
    private boolean sf;
    private boolean sg;
    private boolean sh;
    private boolean si;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.sf = true;
        this.sg = false;
        this.sh = true;
        this.si = true;
        this.sd = str;
        this.se = str2;
        this.sf = z;
        this.sg = z2;
        this.sh = z3;
        this.si = z4;
    }

    public String fF() {
        return this.sd;
    }

    public boolean fG() {
        return this.sg;
    }

    public boolean fH() {
        return this.sh;
    }

    public boolean fI() {
        return this.si;
    }

    public static b a(Map<String, a> map, String str) {
        if (map == null) {
            com.xiaopeng.lib.utils.c.f("FactoryCodeModel", " codes is null .");
            return null;
        }
        String bq = bq(str);
        a aVar = map.get(bq);
        if (aVar == null || aVar.fB().isEmpty()) {
            com.xiaopeng.lib.utils.c.f("FactoryCodeModel", " cannot find this factory code type:" + bq);
            return null;
        }
        b bp = aVar.bp(str);
        if (bp == null) {
            com.xiaopeng.lib.utils.c.f("FactoryCodeModel", " cannot find this factory code:" + str);
        }
        return bp;
    }

    public static String bq(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (!c.bs(str)) {
            return "";
        }
        if ("*#1224#*".equals(str) || "*#1225#*".equals(str) || "*#0101#*".equals(str)) {
            return "9444";
        }
        if ("*#8#*".equals(str) || "*#400#*".equals(str)) {
            return a.rX[4];
        }
        int indexOf = str.indexOf("*", 2);
        if (indexOf == -1) {
            return "";
        }
        return str.substring(2, indexOf);
    }

    public static String br(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        try {
            return a.rX[Integer.valueOf(str.substring(2, 4)).intValue()];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
