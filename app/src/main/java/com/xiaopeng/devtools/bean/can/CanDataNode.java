package com.xiaopeng.devtools.bean.can;

import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes12.dex */
public class CanDataNode {
    private byte[] data;
    private int dataLength;
    private String parsingString;
    private int queque;
    private String triggerEvent;
    private byte[] type;
    private ReentrantLock mReentrantLock = new ReentrantLock();
    private boolean isWithTriggerEvent = false;
    private int triggerFlag = 0;
    private boolean isDataNormal = false;
    private boolean isParsed = false;

    public CanDataNode(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.data = bArr;
        this.dataLength = i;
        this.type = bArr2;
        this.queque = i2;
    }

    public void lockData() {
        this.mReentrantLock.lock();
    }

    public void unlockData() {
        this.mReentrantLock.unlock();
    }

    public int getTriggerFlag() {
        return this.triggerFlag;
    }

    public void setTriggerFlag(int i) {
        this.triggerFlag = i;
    }

    public boolean isParsed() {
        return this.isParsed;
    }

    public void setParsed(boolean z) {
        this.isParsed = z;
    }

    public boolean isDataNormal() {
        return this.isDataNormal;
    }

    public void setDataNormal(boolean z) {
        this.isDataNormal = z;
    }

    public boolean isWithTriggerEvent() {
        return this.isWithTriggerEvent;
    }

    public void setWithTriggerEvent(boolean z) {
        this.isWithTriggerEvent = z;
    }

    public String getTriggerEvent() {
        return this.triggerEvent;
    }

    public void setTriggerEvent(String str) {
        this.triggerEvent = str;
    }

    public String getParsingString() {
        return this.parsingString;
    }

    public void setParsingString(String str) {
        this.parsingString = str;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getDataLength() {
        return this.dataLength;
    }

    public byte[] getType() {
        return this.type;
    }

    public int getQueque() {
        return this.queque;
    }
}
