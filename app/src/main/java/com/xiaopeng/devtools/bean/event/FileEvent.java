package com.xiaopeng.devtools.bean.event;

/* loaded from: classes12.dex */
public class FileEvent {
    public static final int DELETE_SUCCESS = 1;
    private int status;

    public FileEvent(int i) {
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }
}
