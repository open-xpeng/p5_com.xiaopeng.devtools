package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class MeasureValueSet implements Parcelable, com.alibaba.mtl.appmonitor.c.b {
    public static final Parcelable.Creator<MeasureValueSet> CREATOR = new Parcelable.Creator<MeasureValueSet>() { // from class: com.alibaba.mtl.appmonitor.model.MeasureValueSet.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: p */
        public MeasureValueSet createFromParcel(Parcel parcel) {
            return MeasureValueSet.o(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: B */
        public MeasureValueSet[] newArray(int i) {
            return new MeasureValueSet[i];
        }
    };
    private Map<String, MeasureValue> map = new LinkedHashMap();

    public static MeasureValueSet R() {
        return (MeasureValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValueSet.class, new Object[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MeasureValueSet a(String str, double d) {
        this.map.put(str, com.alibaba.mtl.appmonitor.c.a.y().a(MeasureValue.class, Double.valueOf(d)));
        return this;
    }

    public void a(String str, MeasureValue measureValue) {
        this.map.put(str, measureValue);
    }

    public MeasureValue o(String str) {
        return this.map.get(str);
    }

    public Map<String, MeasureValue> getMap() {
        return this.map;
    }

    public boolean m(String str) {
        return this.map.containsKey(str);
    }

    public void e(MeasureValueSet measureValueSet) {
        for (String str : this.map.keySet()) {
            this.map.get(str).b(measureValueSet.o(str));
        }
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        for (MeasureValue measureValue : this.map.values()) {
            com.alibaba.mtl.appmonitor.c.a.y().a((com.alibaba.mtl.appmonitor.c.a) measureValue);
        }
        this.map.clear();
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
        if (this.map == null) {
            this.map = new LinkedHashMap();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.map);
    }

    static MeasureValueSet o(Parcel parcel) {
        try {
            MeasureValueSet R = R();
            try {
                R.map = parcel.readHashMap(DimensionValueSet.class.getClassLoader());
                return R;
            } catch (Throwable th) {
                return R;
            }
        } catch (Throwable th2) {
            return null;
        }
    }
}
