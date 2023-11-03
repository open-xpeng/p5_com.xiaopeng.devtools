package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class AndroidDugReq {
    public int dugMcuPrintReq;
    public int dugMcuPrintStateReq;
    public int dugSt;
    public int dugUpReq;

    public int[] packToIntArray() {
        return new int[]{this.dugUpReq, this.dugMcuPrintReq, this.dugMcuPrintStateReq, this.dugSt};
    }

    public String toString() {
        return "dugUpReq=" + this.dugUpReq + "\n dugMcuPrintReq=" + this.dugMcuPrintReq + "\n dugMcuPrintStateReq=" + this.dugMcuPrintStateReq + "\n dugSt=" + this.dugSt;
    }
}
