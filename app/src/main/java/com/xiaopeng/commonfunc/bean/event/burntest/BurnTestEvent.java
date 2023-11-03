package com.xiaopeng.commonfunc.bean.event.burntest;

/* loaded from: classes11.dex */
public class BurnTestEvent {
    public int position;
    public boolean testPass;

    public BurnTestEvent(int i, boolean z) {
        this.position = i;
        this.testPass = z;
    }

    public String toString() {
        return "BurnTestEvent{position=" + this.position + ", testPass=" + this.testPass + '}';
    }
}
