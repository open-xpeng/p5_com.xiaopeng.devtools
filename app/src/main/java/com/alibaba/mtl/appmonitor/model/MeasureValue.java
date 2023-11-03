package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class MeasureValue implements Parcelable, com.alibaba.mtl.appmonitor.c.b {
    public static final Parcelable.Creator<MeasureValue> CREATOR = new Parcelable.Creator<MeasureValue>() { // from class: com.alibaba.mtl.appmonitor.model.MeasureValue.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: n */
        public MeasureValue createFromParcel(Parcel parcel) {
            return MeasureValue.m(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: A */
        public MeasureValue[] newArray(int i) {
            return new MeasureValue[i];
        }
    };
    private Double bA;
    private boolean bz;
    private double value;

    public static MeasureValue N() {
        return (MeasureValue) com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValue.class, new Object[0]);
    }

    public Double O() {
        return this.bA;
    }

    public boolean P() {
        return this.bz;
    }

    public void g(boolean z) {
        this.bz = z;
    }

    public void b(double d) {
        this.bA = Double.valueOf(d);
    }

    public double Q() {
        return this.value;
    }

    public void c(double d) {
        this.value = d;
    }

    public synchronized void b(MeasureValue measureValue) {
        if (measureValue == null) {
            return;
        }
        try {
            this.value += measureValue.Q();
            if (measureValue.O() != null) {
                if (this.bA == null) {
                    this.bA = Double.valueOf(0.0d);
                }
                this.bA = Double.valueOf(this.bA.doubleValue() + measureValue.O().doubleValue());
            }
        } catch (Throwable th) {
        }
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public synchronized void clean() {
        this.value = 0.0d;
        this.bA = null;
        this.bz = false;
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public synchronized void a(Object... objArr) {
        if (objArr == null) {
            return;
        }
        if (objArr.length > 0) {
            this.value = ((Double) objArr[0]).doubleValue();
        }
        if (objArr.length > 1) {
            this.bA = (Double) objArr[1];
            this.bz = false;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeInt(this.bz ? 1 : 0);
            parcel.writeDouble(this.bA == null ? 0.0d : this.bA.doubleValue());
            parcel.writeDouble(this.value);
        } catch (Throwable th) {
        }
    }

    static MeasureValue m(Parcel parcel) {
        boolean z;
        Double valueOf;
        double readDouble;
        MeasureValue N;
        MeasureValue measureValue = null;
        try {
            z = parcel.readInt() != 0;
            valueOf = Double.valueOf(parcel.readDouble());
            readDouble = parcel.readDouble();
            N = N();
        } catch (Throwable th) {
            th = th;
        }
        try {
            N.bz = z;
            N.bA = valueOf;
            N.value = readDouble;
            return N;
        } catch (Throwable th2) {
            measureValue = N;
            th = th2;
            th.printStackTrace();
            return measureValue;
        }
    }
}
