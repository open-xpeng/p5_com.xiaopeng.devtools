package com.xiaopeng.devtools.bean.factorytest;

/* loaded from: classes12.dex */
public class AndTempInfo {
    private double cpuTemp = -1.0d;
    private double socTemp = -1.0d;
    private double ufsTemp = -1.0d;
    private double ddrTemp = -1.0d;
    private double ampTemp = -1.0d;
    private double pmicTemp = -1.0d;

    public double getCpuTemp() {
        return this.cpuTemp;
    }

    public void setCpuTemp(double d) {
        this.cpuTemp = d;
    }

    public double getSocTemp() {
        return this.socTemp;
    }

    public void setSocTemp(double d) {
        this.socTemp = d;
    }

    public double getUfsTemp() {
        return this.ufsTemp;
    }

    public void setUfsTemp(double d) {
        this.ufsTemp = d;
    }

    public double getDdrTemp() {
        return this.ddrTemp;
    }

    public void setDdrTemp(double d) {
        this.ddrTemp = d;
    }

    public double getAmpTemp() {
        return this.ampTemp;
    }

    public void setAmpTemp(double d) {
        this.ampTemp = d;
    }

    public double getPmicTemp() {
        return this.pmicTemp;
    }

    public void setPmicTemp(double d) {
        this.pmicTemp = d;
    }

    public String toString() {
        return "AndTempInfo{cpuTemp=" + this.cpuTemp + ", socTemp=" + this.socTemp + ", ufsTemp=" + this.ufsTemp + ", ddrTemp=" + this.ddrTemp + ", ampTemp=" + this.ampTemp + ", pmicTemp=" + this.pmicTemp + '}';
    }
}
