package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.a.br;

/* loaded from: classes11.dex */
public class DistrictSearchQuery implements Parcelable, Cloneable {
    public static final Parcelable.Creator<DistrictSearchQuery> CREATOR = new Parcelable.Creator<DistrictSearchQuery>() { // from class: com.amap.api.services.district.DistrictSearchQuery.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: A */
        public DistrictSearchQuery createFromParcel(Parcel parcel) {
            DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
            districtSearchQuery.ag(parcel.readString());
            districtSearchQuery.ah(parcel.readString());
            districtSearchQuery.Z(parcel.readInt());
            districtSearchQuery.aa(parcel.readInt());
            districtSearchQuery.o(parcel.readByte() == 1);
            districtSearchQuery.n(parcel.readByte() == 1);
            districtSearchQuery.p(parcel.readByte() == 1);
            return districtSearchQuery;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ab */
        public DistrictSearchQuery[] newArray(int i) {
            return new DistrictSearchQuery[i];
        }
    };
    private int a;
    private int b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;

    public void n(boolean z) {
        this.g = z;
    }

    public DistrictSearchQuery() {
        this.a = 0;
        this.b = 20;
        this.e = true;
        this.f = false;
        this.g = false;
    }

    public DistrictSearchQuery(String str, String str2, int i) {
        this.a = 0;
        this.b = 20;
        this.e = true;
        this.f = false;
        this.g = false;
        this.c = str;
        this.d = str2;
        this.a = i;
    }

    public DistrictSearchQuery(String str, String str2, int i, boolean z, int i2) {
        this(str, str2, i);
        this.e = z;
        this.b = i2;
    }

    public void Z(int i) {
        this.a = i;
    }

    public void aa(int i) {
        this.b = i;
    }

    public void ag(String str) {
        this.c = str;
    }

    public void ah(String str) {
        this.d = str;
    }

    public void o(boolean z) {
        this.e = z;
    }

    public void p(boolean z) {
        this.f = z;
    }

    public int hashCode() {
        return (31 * ((((((((((this.g ? 1231 : 1237) + 31) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + this.a) * 31) + this.b)) + (this.e ? 1231 : 1237);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DistrictSearchQuery districtSearchQuery = (DistrictSearchQuery) obj;
        if (this.g != districtSearchQuery.g) {
            return false;
        }
        if (this.c == null) {
            if (districtSearchQuery.c != null) {
                return false;
            }
        } else if (!this.c.equals(districtSearchQuery.c)) {
            return false;
        }
        if (this.d == null) {
            if (districtSearchQuery.d != null) {
                return false;
            }
        } else if (!this.d.equals(districtSearchQuery.d)) {
            return false;
        }
        if (this.a == districtSearchQuery.a && this.b == districtSearchQuery.b && this.e == districtSearchQuery.e) {
            return true;
        }
        return false;
    }

    /* renamed from: bI */
    public DistrictSearchQuery clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            br.a(e, "DistrictSearchQuery", "clone");
        }
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery(this.c, this.d, this.a, this.e, this.b);
        districtSearchQuery.n(this.g);
        districtSearchQuery.p(this.f);
        return districtSearchQuery;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f ? (byte) 1 : (byte) 0);
    }
}
