package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.alibaba.mtl.log.d.i;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class DimensionSet implements Parcelable {
    public static final Parcelable.Creator<DimensionSet> CREATOR = new Parcelable.Creator<DimensionSet>() { // from class: com.alibaba.mtl.appmonitor.model.DimensionSet.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: f */
        public DimensionSet createFromParcel(Parcel parcel) {
            return DimensionSet.e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: w */
        public DimensionSet[] newArray(int i) {
            return new DimensionSet[i];
        }
    };
    private List<Dimension> c = new ArrayList(3);

    public static DimensionSet F() {
        return new DimensionSet();
    }

    private DimensionSet() {
    }

    public boolean b(DimensionValueSet dimensionValueSet) {
        if (this.c != null) {
            if (dimensionValueSet == null) {
                return false;
            }
            for (Dimension dimension : this.c) {
                if (!dimensionValueSet.m(dimension.getName())) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public void c(DimensionValueSet dimensionValueSet) {
        if (this.c != null && dimensionValueSet != null) {
            for (Dimension dimension : this.c) {
                if (dimension.E() != null && dimensionValueSet.getValue(dimension.getName()) == null) {
                    dimensionValueSet.o(dimension.getName(), dimension.E());
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
        if (this.c != null) {
            try {
                Object[] array = this.c.toArray();
                Dimension[] dimensionArr = null;
                if (array != null) {
                    dimensionArr = new Dimension[array.length];
                    for (int i2 = 0; i2 < array.length; i2++) {
                        dimensionArr[i2] = (Dimension) array[i2];
                    }
                }
                parcel.writeParcelableArray(dimensionArr, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static DimensionSet e(Parcel parcel) {
        DimensionSet F = F();
        try {
            Parcelable[] readParcelableArray = parcel.readParcelableArray(DimensionSet.class.getClassLoader());
            if (readParcelableArray != null) {
                if (F.c == null) {
                    F.c = new ArrayList();
                }
                for (int i = 0; i < readParcelableArray.length; i++) {
                    if (readParcelableArray[i] != null && (readParcelableArray[i] instanceof Dimension)) {
                        F.c.add((Dimension) readParcelableArray[i]);
                    } else {
                        i.a("DimensionSet", "parcelables[i]:", readParcelableArray[i]);
                    }
                }
            }
        } catch (Throwable th) {
            i.a("DimensionSet", "[readFromParcel]", th);
        }
        return F;
    }
}
