package com.xiaopeng.commonfunc.bean;

/* loaded from: classes11.dex */
public class ButtonData {
    private String mTextString;
    private Class mTriggerClass;

    public ButtonData(String str, Class cls) {
        this.mTextString = str;
        this.mTriggerClass = cls;
    }

    public String getTextString() {
        return this.mTextString;
    }

    public void setTextString(String str) {
        this.mTextString = str;
    }

    public Class getTriggerClass() {
        return this.mTriggerClass;
    }

    public void setTriggerClass(Class cls) {
        this.mTriggerClass = cls;
    }

    public String toString() {
        return "ButtonData{mTextString='" + this.mTextString + "', mTriggerClass=" + this.mTriggerClass + '}';
    }
}
