package com.xiaopeng.commonfunc.bean.car;

import com.xiaopeng.commonfunc.utils.d;

/* loaded from: classes11.dex */
public class BatteryInfo {
    private int cell_connect;
    private int dc_impedance;
    private int discharge_current;
    private int incharging;
    private int indischarging;
    private int soc;
    private int soh;
    private int stepup_state;
    private int stepup_volt;
    private int voltage;

    public BatteryInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.incharging = i;
        this.soc = i2;
        this.voltage = i3;
        this.dc_impedance = i4;
        this.soh = i5;
        this.cell_connect = i6;
        this.stepup_state = i7;
        this.stepup_volt = i8;
        this.indischarging = i9;
        this.discharge_current = i10;
    }

    public BatteryInfo(byte[] bArr) {
        try {
            this.incharging = d.b(bArr[0]);
            this.soc = d.b(bArr[1]);
            this.soh = d.b(bArr[2]);
            this.cell_connect = d.b(bArr[3]);
            this.stepup_state = d.b(bArr[4]);
            this.indischarging = d.b(bArr[5]);
            this.discharge_current = d.t(d.c(bArr, 6, 2));
            this.voltage = d.t(d.c(bArr, 8, 2));
            this.dc_impedance = d.t(d.c(bArr, 10, 2));
            this.stepup_volt = d.t(d.c(bArr, 12, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIncharging() {
        return this.incharging;
    }

    public void setIncharging(int i) {
        this.incharging = i;
    }

    public int getSoc() {
        return this.soc;
    }

    public void setSoc(int i) {
        this.soc = i;
    }

    public int getVoltage() {
        return this.voltage;
    }

    public void setVoltage(int i) {
        this.voltage = i;
    }

    public int getDc_impedance() {
        return this.dc_impedance;
    }

    public void setDc_impedance(int i) {
        this.dc_impedance = i;
    }

    public int getSoh() {
        return this.soh;
    }

    public void setSoh(int i) {
        this.soh = i;
    }

    public int getCell_connect() {
        return this.cell_connect;
    }

    public void setCell_connect(int i) {
        this.cell_connect = i;
    }

    public int getStepup_state() {
        return this.stepup_state;
    }

    public void setStepup_state(int i) {
        this.stepup_state = i;
    }

    public int getStepup_volt() {
        return this.stepup_volt;
    }

    public void setStepup_volt(int i) {
        this.stepup_volt = i;
    }

    public int getIndischarging() {
        return this.indischarging;
    }

    public void setIndischarging(int i) {
        this.indischarging = i;
    }

    public int getDischarge_current() {
        return this.discharge_current;
    }

    public void setDischarge_current(int i) {
        this.discharge_current = i;
    }

    public String toString() {
        return "BatteryInfo{incharging=" + this.incharging + ", soc=" + this.soc + ", voltage=" + this.voltage + ", dc_impedance=" + this.dc_impedance + ", soh=" + this.soh + ", cell_connect=" + this.cell_connect + ", stepup_state=" + this.stepup_state + ", stepup_volt=" + this.stepup_volt + ", indischarging=" + this.indischarging + ", discharge_current=" + this.discharge_current + '}';
    }
}
