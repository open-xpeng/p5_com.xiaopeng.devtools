package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class MeasureSet implements Parcelable {
    public static final Parcelable.Creator<MeasureSet> CREATOR = new Parcelable.Creator<MeasureSet>() { // from class: com.alibaba.mtl.appmonitor.model.MeasureSet.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: l */
        public MeasureSet createFromParcel(Parcel parcel) {
            return MeasureSet.k(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: z */
        public MeasureSet[] newArray(int i) {
            return new MeasureSet[i];
        }
    };
    private List<Measure> by = new ArrayList(3);

    public static MeasureSet L() {
        return new MeasureSet();
    }

    private MeasureSet() {
    }

    public boolean c(MeasureValueSet measureValueSet) {
        if (this.by != null) {
            if (measureValueSet == null) {
                return false;
            }
            for (int i = 0; i < this.by.size(); i++) {
                Measure measure = this.by.get(i);
                if (measure != null) {
                    String name = measure.getName();
                    if (!measureValueSet.m(name) || !measure.a(measureValueSet.o(name))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }

    public MeasureSet a(Measure measure) {
        if (!this.by.contains(measure)) {
            this.by.add(measure);
        }
        return this;
    }

    public Measure n(String str) {
        for (Measure measure : this.by) {
            if (measure.getName().equals(str)) {
                return measure;
            }
        }
        return null;
    }

    public List<Measure> M() {
        return this.by;
    }

    public void d(MeasureValueSet measureValueSet) {
        if (this.by != null && measureValueSet != null) {
            for (Measure measure : this.by) {
                if (measure.I() != null && measureValueSet.o(measure.getName()) == null) {
                    measureValueSet.a(measure.getName(), measure.I().doubleValue());
                }
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.by != null) {
            try {
                Object[] array = this.by.toArray();
                Measure[] measureArr = null;
                if (array != null) {
                    measureArr = new Measure[array.length];
                    for (int i2 = 0; i2 < array.length; i2++) {
                        measureArr[i2] = (Measure) array[i2];
                    }
                }
                parcel.writeParcelableArray(measureArr, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static MeasureSet k(Parcel parcel) {
        MeasureSet L = L();
        try {
            Parcelable[] readParcelableArray = parcel.readParcelableArray(MeasureSet.class.getClassLoader());
            if (readParcelableArray != null) {
                ArrayList arrayList = new ArrayList(readParcelableArray.length);
                for (Parcelable parcelable : readParcelableArray) {
                    arrayList.add((Measure) parcelable);
                }
                L.by = arrayList;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return L;
    }
}
