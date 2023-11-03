package com.xiaopeng.commonfunc.bean.can;

/* loaded from: classes11.dex */
public class CanDataFeature {
    private int minLength = 0;
    private int minEachCanLength = 0;
    private int startpoint = 0;
    private int channelbitoffset = 0;
    private byte canidbit = 0;
    private boolean withValidbit = false;
    private boolean withTimestamp = false;
    private boolean withTriggerEvent = false;
    private boolean isDLC = false;

    public boolean isDLC() {
        return this.isDLC;
    }

    public void setDLC(boolean z) {
        this.isDLC = z;
    }

    public boolean isWithTriggerEvent() {
        return this.withTriggerEvent;
    }

    public void setWithTriggerEvent(boolean z) {
        this.withTriggerEvent = z;
    }

    public boolean isWithTimestamp() {
        return this.withTimestamp;
    }

    public void setWithTimestamp(boolean z) {
        this.withTimestamp = z;
    }

    public int getMinLength() {
        return this.minLength;
    }

    public void setMinLength(int i) {
        this.minLength = i;
    }

    public int getMinEachCanLength() {
        return this.minEachCanLength;
    }

    public void setMinEachCanLength(int i) {
        this.minEachCanLength = i;
    }

    public int getStartpoint() {
        return this.startpoint;
    }

    public void setStartpoint(int i) {
        this.startpoint = i;
    }

    public int getChannelbitoffset() {
        return this.channelbitoffset;
    }

    public void setChannelbitoffset(int i) {
        this.channelbitoffset = i;
    }

    public byte getCanidbit() {
        return this.canidbit;
    }

    public void setCanidbit(byte b) {
        this.canidbit = b;
    }

    public boolean isWithValidbit() {
        return this.withValidbit;
    }

    public void setWithValidbit(boolean z) {
        this.withValidbit = z;
    }
}
