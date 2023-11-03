package com.alibaba.mtl.log;

import java.util.Map;

/* compiled from: UTMCVariables.java */
/* loaded from: classes11.dex */
public class c {
    public static final c cv = new c();
    private boolean cw = false;
    private boolean cx = false;
    private String M = null;
    private Map<String, String> cy = null;
    private boolean cz = false;
    private boolean cA = false;
    private String cB = null;
    private String O = null;
    private String cC = null;
    private boolean cD = false;

    public static c ax() {
        return cv;
    }

    public synchronized void e(String str) {
        this.cB = str;
    }

    public synchronized void ay() {
        this.cA = true;
    }

    public synchronized boolean d() {
        return this.cA;
    }

    public synchronized void j(Map<String, String> map) {
        this.cy = map;
    }

    public synchronized Map<String, String> a() {
        return this.cy;
    }
}
