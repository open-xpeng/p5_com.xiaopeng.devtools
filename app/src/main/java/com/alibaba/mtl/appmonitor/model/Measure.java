package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class Measure implements Parcelable {
    public static final Parcelable.Creator<Measure> CREATOR = new Parcelable.Creator<Measure>() { // from class: com.alibaba.mtl.appmonitor.model.Measure.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: j */
        public Measure createFromParcel(Parcel parcel) {
            return Measure.i(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: y */
        public Measure[] newArray(int i) {
            return new Measure[i];
        }
    };
    protected Double bv;
    protected Double bw;
    protected Double bx;
    protected String name;

    public Measure(String str, Double d, Double d2, Double d3) {
        this.bv = Double.valueOf(0.0d);
        this.bw = Double.valueOf(0.0d);
        this.bx = Double.valueOf(0.0d);
        this.bv = d2;
        this.bw = d3;
        this.name = str;
        this.bx = Double.valueOf(d != null ? d.doubleValue() : 0.0d);
    }

    public Double H() {
        return this.bw;
    }

    public String getName() {
        return this.name;
    }

    public Double I() {
        return this.bx;
    }

    public boolean a(MeasureValue measureValue) {
        Double valueOf = Double.valueOf(measureValue.Q());
        return valueOf != null && (this.bv == null || valueOf.doubleValue() >= this.bv.doubleValue()) && (this.bw == null || valueOf.doubleValue() <= this.bw.doubleValue());
    }

    public int hashCode() {
        return 31 + (this.name == null ? 0 : this.name.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Measure measure = (Measure) obj;
        if (this.name == null) {
            if (measure.name != null) {
                return false;
            }
        } else if (!this.name.equals(measure.name)) {
            return false;
        }
        return true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeInt(this.bw == null ? 0 : 1);
            if (this.bw != null) {
                parcel.writeDouble(this.bw.doubleValue());
            }
            parcel.writeInt(this.bv == null ? 0 : 1);
            if (this.bv != null) {
                parcel.writeDouble(this.bv.doubleValue());
            }
            parcel.writeString(this.name);
            parcel.writeInt(this.bx == null ? 0 : 1);
            if (this.bx != null) {
                parcel.writeDouble(this.bx.doubleValue());
            }
        } catch (Throwable th) {
        }
    }

    static Measure i(Parcel parcel) {
        Double d;
        Double d2;
        Double d3;
        try {
            if (!(parcel.readInt() == 0)) {
                d = Double.valueOf(parcel.readDouble());
            } else {
                d = null;
            }
            if (!(parcel.readInt() == 0)) {
                d2 = Double.valueOf(parcel.readDouble());
            } else {
                d2 = null;
            }
            String readString = parcel.readString();
            if (!(parcel.readInt() == 0)) {
                d3 = Double.valueOf(parcel.readDouble());
            } else {
                d3 = null;
            }
            return new Measure(readString, d3, d2, d);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
