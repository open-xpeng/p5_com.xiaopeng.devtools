package com.xiaopeng.devtools.bean.car;

/* loaded from: classes12.dex */
public class McuTempInfo {
    private float mcuTemp = -1.0f;
    private float battTemp = -1.0f;
    private float pcbTemp = -1.0f;

    public float getMcuTemp() {
        return this.mcuTemp;
    }

    public void setMcuTemp(float f) {
        this.mcuTemp = f;
    }

    public float getBattTemp() {
        return this.battTemp;
    }

    public void setBattTemp(float f) {
        this.battTemp = f;
    }

    public float getPcbTemp() {
        return this.pcbTemp;
    }

    public void setPcbTemp(float f) {
        this.pcbTemp = f;
    }

    public String toString() {
        return "McuTempInfo{mcuTemp=" + this.mcuTemp + ", battTemp=" + this.battTemp + ", pcbTemp=" + this.pcbTemp + '}';
    }
}
