package com.xiaopeng.logictree;

/* compiled from: IssueInfo.java */
/* loaded from: classes12.dex */
public class a {
    private static a XN;
    private c XJ;
    private String XK;
    private int XL;
    private String XM;
    private long mEndTime;
    private long mStartTime;

    public a(long j, long j2, c cVar, String str, int i, String str2) {
        this.mStartTime = j;
        this.mEndTime = j2;
        this.XJ = cVar;
        this.XK = str;
        this.XL = i;
        this.XM = str2;
    }

    public static a pC() {
        if (XN == null) {
            XN = new a(0L, 0L, null, null, 0, null);
        }
        return XN;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long pD() {
        return this.mEndTime;
    }

    public void r(long j) {
        this.mEndTime = j;
    }

    public c pE() {
        return this.XJ;
    }

    public void a(c cVar) {
        this.XJ = cVar;
    }

    public String pF() {
        return this.XK;
    }

    public void dP(String str) {
        this.XK = str;
    }

    public int pG() {
        return this.XL;
    }

    public void dT(int i) {
        this.XL = i;
    }

    public String pH() {
        return this.XM;
    }

    public void dQ(String str) {
        this.XM = str;
    }

    public String toString() {
        return "IssueInfo{mStartTime=" + this.mStartTime + ", mEndTime=" + this.mEndTime + ", mLogicTree=" + this.XJ + ", mIssueName='" + this.XK + "'}";
    }
}
