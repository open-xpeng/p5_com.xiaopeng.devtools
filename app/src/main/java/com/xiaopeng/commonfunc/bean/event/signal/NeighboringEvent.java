package com.xiaopeng.commonfunc.bean.event.signal;

/* loaded from: classes11.dex */
public class NeighboringEvent {
    private final String neighboringCellInfo;

    public NeighboringEvent(String str) {
        this.neighboringCellInfo = str;
    }

    public String getNeighboringCellInfo() {
        return this.neighboringCellInfo;
    }
}
