package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class Dimension implements Parcelable {
    public static final Parcelable.Creator<Dimension> CREATOR = new Parcelable.Creator<Dimension>() { // from class: com.alibaba.mtl.appmonitor.model.Dimension.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: d */
        public Dimension createFromParcel(Parcel parcel) {
            return Dimension.c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: v */
        public Dimension[] newArray(int i) {
            return new Dimension[i];
        }
    };
    protected String bu;
    protected String name;

    public Dimension() {
        this.bu = "null";
    }

    public Dimension(String str, String str2) {
        this.bu = "null";
        this.name = str;
        this.bu = str2 == null ? "null" : str2;
    }

    public String getName() {
        return this.name;
    }

    public String E() {
        return this.bu;
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
        Dimension dimension = (Dimension) obj;
        if (this.name == null) {
            if (dimension.name != null) {
                return false;
            }
        } else if (!this.name.equals(dimension.name)) {
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
        parcel.writeString(this.bu);
        parcel.writeString(this.name);
    }

    static Dimension c(Parcel parcel) {
        try {
            return new Dimension(parcel.readString(), parcel.readString());
        } catch (Throwable th) {
            return null;
        }
    }
}
