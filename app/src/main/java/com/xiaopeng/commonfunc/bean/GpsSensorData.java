package com.xiaopeng.commonfunc.bean;

/* loaded from: classes11.dex */
public class GpsSensorData {
    private double mAcc_X;
    private double mAcc_Y;
    private double mAcc_Z;
    private double mGyro_X;
    private double mGyro_Y;
    private double mGyro_Z;

    public GpsSensorData(double d, double d2, double d3, double d4, double d5, double d6) {
        this.mAcc_X = d;
        this.mAcc_Y = d2;
        this.mAcc_Z = d3;
        this.mGyro_X = d4;
        this.mGyro_Y = d5;
        this.mGyro_Z = d6;
    }

    public double getAcc_X() {
        return this.mAcc_X;
    }

    public void setAcc_X(double d) {
        this.mAcc_X = d;
    }

    public double getAcc_Y() {
        return this.mAcc_Y;
    }

    public void setAcc_Y(double d) {
        this.mAcc_Y = d;
    }

    public double getAcc_Z() {
        return this.mAcc_Z;
    }

    public void setAcc_Z(double d) {
        this.mAcc_Z = d;
    }

    public double getGyro_X() {
        return this.mGyro_X;
    }

    public void setGyro_X(double d) {
        this.mGyro_X = d;
    }

    public double getGyro_Y() {
        return this.mGyro_Y;
    }

    public void setGyro_Y(double d) {
        this.mGyro_Y = d;
    }

    public double getGyro_Z() {
        return this.mGyro_Z;
    }

    public void setGyro_Z(double d) {
        this.mGyro_Z = d;
    }

    public String toString() {
        return "GpsSensorData{mAcc_X=" + this.mAcc_X + ", mAcc_Y=" + this.mAcc_Y + ", mAcc_Z=" + this.mAcc_Z + ", mGyro_X=" + this.mGyro_X + ", mGyro_Y=" + this.mGyro_Y + ", mGyro_Z=" + this.mGyro_Z + '}';
    }
}
