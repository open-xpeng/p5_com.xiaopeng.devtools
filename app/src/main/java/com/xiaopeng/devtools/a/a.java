package com.xiaopeng.devtools.a;

import android.content.Context;
import com.xiaopeng.devtools.a.a.d;
import com.xiaopeng.lib.utils.c;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: CmdParser.java */
/* loaded from: classes12.dex */
public class a {
    private HashMap<String, com.xiaopeng.devtools.a.a.a> uU = new HashMap<>();

    public synchronized void a(Context context, b bVar) {
        c.g("CmdParser", "Register PC command handler");
        this.uU.put("XPENG+LOG", new com.xiaopeng.devtools.a.a.c(context, bVar));
        this.uU.put("XPENG+SETTINGS", new d(context, bVar));
        this.uU.put("XPENG+CAN", new com.xiaopeng.devtools.a.a.b(context, bVar));
    }

    public synchronized void hP() {
        if (!this.uU.isEmpty()) {
            c.g("CmdParser", "unregister PC command handler");
            for (String str : this.uU.keySet()) {
                com.xiaopeng.devtools.a.a.a aVar = this.uU.get(str);
                if (aVar != null) {
                    aVar.destroy();
                }
            }
            this.uU.clear();
        }
    }

    public boolean a(String str, b bVar) {
        c.f("CmdParser", "process " + str);
        if (str != null) {
            try {
                if (str.length() >= 1) {
                    if (b(str, bVar)) {
                        c.f("CmdParser", "process done successfully. cmd = " + str);
                    }
                    return true;
                }
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean b(String str, b bVar) {
        String str2;
        c.g("CmdParser", "runCmd " + str);
        if (str != null) {
            try {
                if (str.length() >= 1) {
                    String str3 = null;
                    if (str.contains("=") && str.indexOf("=") != 0) {
                        str2 = str.substring(0, str.indexOf("=")).toUpperCase(Locale.ENGLISH);
                    } else {
                        str2 = null;
                    }
                    c.g("CmdParser", "process command : " + str2);
                    if (str2 != null && this.uU.containsKey(str2)) {
                        bVar.hR();
                        str3 = this.uU.get(str2).f(bI(str));
                        c.g("CmdParser", "CMD : " + str + ", result : " + str3);
                    } else {
                        bVar.write("XPENG+CMD Error:NA\r\n");
                    }
                    if (str3 == null) {
                        return false;
                    }
                    bVar.write(str3);
                    return true;
                }
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public String[] bI(String str) {
        c.f("CmdParser", "splitArgu() cmd : " + str);
        String[] split = str.substring(str.indexOf("=") + 1, str.length()).split(",");
        if (split != null) {
            c.f("CmdParser", "splitArgu() args : " + split.length + " : " + Arrays.toString(split));
        }
        return split;
    }
}
