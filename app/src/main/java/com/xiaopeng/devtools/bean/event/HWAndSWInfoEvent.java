package com.xiaopeng.devtools.bean.event;

/* loaded from: classes12.dex */
public class HWAndSWInfoEvent {
    public static final int GET_PART_CODE_ERROR = 2;
    public static final int REQUEST_PARAM_ERROR = 1;
    private int state;

    public HWAndSWInfoEvent(int i) {
        this.state = i;
    }

    public int getState() {
        return this.state;
    }
}
