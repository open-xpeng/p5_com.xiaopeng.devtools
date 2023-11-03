package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.log.d.i;

/* compiled from: EventType.java */
/* loaded from: classes11.dex */
public enum f {
    ALARM(65501, 30, "alarmData", 5000),
    COUNTER(65502, 30, "counterData", 5000),
    OFFLINE_COUNTER(65133, 30, "counterData", 5000),
    STAT(65503, 30, "statData", 5000);
    
    static String TAG = "EventType";
    private int e;
    private int h;
    private int k;
    private String t;
    private int i = 25;
    private int j = 180;
    private boolean m = true;

    f(int i, int i2, String str, int i3) {
        this.e = i;
        this.h = i2;
        this.t = str;
        this.k = i3;
    }

    public int a() {
        return this.e;
    }

    public boolean isOpen() {
        return this.m;
    }

    public void b(boolean z) {
        this.m = z;
    }

    public int b() {
        return this.h;
    }

    public void b(int i) {
        String str = TAG;
        i.a(str, "[setTriggerCount]", this.t, i + "");
        this.h = i;
    }

    public static f r(int i) {
        f[] values;
        for (f fVar : values()) {
            if (fVar != null && fVar.a() == i) {
                return fVar;
            }
        }
        return null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m15a() {
        return this.t;
    }

    public int v() {
        return this.i;
    }

    public int w() {
        return this.j;
    }

    public void f(int i) {
        this.i = i;
        this.j = i;
    }

    public int x() {
        return this.k;
    }

    public void c(int i) {
        this.k = i;
    }
}
