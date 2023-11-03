package com.alibaba.mtl.log.model;

import android.text.TextUtils;
import com.alibaba.mtl.log.d.c;
import com.alibaba.mtl.log.d.h;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.n;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/* compiled from: Log.java */
/* loaded from: classes11.dex */
public class a {
    public String X;
    private String aU;
    private Map<String, String> aV;
    public String dZ;
    private String ea;
    public String eb;
    public String ec;
    public int id;
    private String u;
    private String v;
    private String w;

    public a() {
        this.dZ = "3";
        this.eb = null;
        this.ec = "";
    }

    public a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        this.dZ = "3";
        this.eb = null;
        this.ec = "";
        this.X = str2;
        this.u = str;
        this.v = str3;
        this.w = str4;
        this.aU = str5;
        this.aV = map;
        this.eb = String.valueOf(System.currentTimeMillis());
        aZ();
    }

    public String i() {
        try {
            byte[] decode = c.decode(this.ea.getBytes("UTF-8"), 2);
            if (decode == null) {
                return null;
            }
            return new String(n.b(decode, "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"));
        } catch (Exception e) {
            return null;
        }
    }

    public String j() {
        return this.ea;
    }

    public void k(String str) {
        if (str != null) {
            try {
                this.ea = new String(c.encode(n.b(str.getBytes(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK"), 2), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void C(String str) {
        this.ea = str;
    }

    public String toString() {
        return "Log [id=" + this.id + ", eventId=" + this.X + ", index=" + this.ec + "]";
    }

    public void aZ() {
        if (TextUtils.isEmpty(this.eb)) {
            this.eb = String.valueOf(System.currentTimeMillis());
        }
        String a = h.a(this.u, this.X, this.v, this.w, this.aU, this.aV, this.ec, this.eb);
        i.a("UTLog", this, a);
        k(a);
    }
}
