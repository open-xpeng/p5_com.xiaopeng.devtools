package com.xiaopeng.xui.a;

import com.xiaopeng.xui.d.f;
import java.util.Locale;

/* compiled from: XFpsMonitor.java */
/* loaded from: classes13.dex */
public class a {
    private final long aaN;
    private long aaO;
    private long aaP;
    private int aaQ;
    private int aaR;
    private float aaS;
    private String aaT;
    private long start;
    private String tag;

    public a() {
        this("XFpsMonitor");
    }

    public a(String str) {
        this.tag = str;
        this.aaO = -1L;
        this.aaN = 1000L;
        this.start = -1L;
        this.aaT = "";
    }

    public final void qK() {
        if (this.start != -1) {
            throw new RuntimeException("Do you forget to call frameEnd? ");
        }
        this.start = System.currentTimeMillis();
        if (this.aaO == -1) {
            this.aaO = this.start;
        }
    }

    public final String qL() {
        if (this.start == -1) {
            throw new RuntimeException("Do you forget to call frameStart? ");
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.aaP += currentTimeMillis - this.start;
        this.aaQ++;
        if (currentTimeMillis - this.aaO > this.aaN) {
            this.aaR = this.aaQ;
            StringBuilder sb = new StringBuilder();
            sb.append(this.aaQ);
            sb.append(" FPS\t\t");
            this.aaS = (((float) this.aaP) * 1.0f) / this.aaQ;
            sb.append(String.format(Locale.getDefault(), "%.2f", Float.valueOf(this.aaS)));
            sb.append(" ms/f");
            this.aaT = sb.toString();
            f.f(this.tag, this.aaT);
            this.aaP = 0L;
            this.aaQ = 0;
            this.aaO = currentTimeMillis;
        }
        this.start = -1L;
        return this.aaT;
    }
}
