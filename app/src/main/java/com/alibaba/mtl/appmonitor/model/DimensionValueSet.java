package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class DimensionValueSet implements Parcelable, com.alibaba.mtl.appmonitor.c.b {
    public static final Parcelable.Creator<DimensionValueSet> CREATOR = new Parcelable.Creator<DimensionValueSet>() { // from class: com.alibaba.mtl.appmonitor.model.DimensionValueSet.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: h */
        public DimensionValueSet createFromParcel(Parcel parcel) {
            return DimensionValueSet.g(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: x */
        public DimensionValueSet[] newArray(int i) {
            return new DimensionValueSet[i];
        }
    };
    protected Map<String, String> map;

    public static DimensionValueSet G() {
        return (DimensionValueSet) com.alibaba.mtl.appmonitor.c.a.y().a(DimensionValueSet.class, new Object[0]);
    }

    @Deprecated
    public DimensionValueSet() {
        if (this.map == null) {
            this.map = new LinkedHashMap();
        }
    }

    public DimensionValueSet o(String str, String str2) {
        Map<String, String> map = this.map;
        if (str2 == null) {
            str2 = "null";
        }
        map.put(str, str2);
        return this;
    }

    public DimensionValueSet d(DimensionValueSet dimensionValueSet) {
        Map<String, String> map;
        if (dimensionValueSet != null && (map = dimensionValueSet.getMap()) != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.map.put(entry.getKey(), entry.getValue() != null ? entry.getValue() : "null");
            }
        }
        return this;
    }

    public boolean m(String str) {
        return this.map.containsKey(str);
    }

    public String getValue(String str) {
        return this.map.get(str);
    }

    public Map<String, String> getMap() {
        return this.map;
    }

    public void i(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.map.put(entry.getKey(), entry.getValue() != null ? entry.getValue() : "null");
        }
    }

    public int hashCode() {
        return 31 + (this.map == null ? 0 : this.map.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DimensionValueSet dimensionValueSet = (DimensionValueSet) obj;
        if (this.map == null) {
            if (dimensionValueSet.map != null) {
                return false;
            }
        } else if (!this.map.equals(dimensionValueSet.map)) {
            return false;
        }
        return true;
    }

    @Override // com.alibaba.mtl.appmonitor.c.b
    public void clean() {
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

    static DimensionValueSet g(Parcel parcel) {
        DimensionValueSet dimensionValueSet;
        try {
            dimensionValueSet = G();
        } catch (Throwable th) {
            th = th;
            dimensionValueSet = null;
        }
        try {
            dimensionValueSet.map = parcel.readHashMap(DimensionValueSet.class.getClassLoader());
        } catch (Throwable th2) {
            th = th2;
            th.printStackTrace();
            return dimensionValueSet;
        }
        return dimensionValueSet;
    }
}
