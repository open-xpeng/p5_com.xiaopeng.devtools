package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes11.dex */
public final class DistrictItem implements Parcelable {
    public static final Parcelable.Creator<DistrictItem> CREATOR = new Parcelable.Creator<DistrictItem>() { // from class: com.amap.api.services.district.DistrictItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: y */
        public DistrictItem createFromParcel(Parcel parcel) {
            return new DistrictItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: X */
        public DistrictItem[] newArray(int i) {
            return new DistrictItem[i];
        }
    };
    private String a;
    private String b;
    private List<DistrictItem> bU;
    private String c;
    private String e;
    private LatLonPoint gJ;
    private String[] gK;

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeParcelable(this.gJ, i);
        parcel.writeString(this.e);
        parcel.writeTypedList(this.bU);
        parcel.writeInt(this.gK.length);
        parcel.writeStringArray(this.gK);
    }

    public DistrictItem() {
        this.bU = new ArrayList();
        this.gK = new String[0];
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DistrictItem(Parcel parcel) {
        this.bU = new ArrayList();
        this.gK = new String[0];
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.gJ = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.e = parcel.readString();
        this.bU = parcel.createTypedArrayList(CREATOR);
        this.gK = new String[parcel.readInt()];
        parcel.readStringArray(this.gK);
    }

    public int hashCode() {
        return (31 * ((((((((((((this.b == null ? 0 : this.b.hashCode()) + 31) * 31) + (this.gJ == null ? 0 : this.gJ.hashCode())) * 31) + (this.a == null ? 0 : this.a.hashCode())) * 31) + Arrays.hashCode(this.gK)) * 31) + (this.bU == null ? 0 : this.bU.hashCode())) * 31) + (this.e == null ? 0 : this.e.hashCode()))) + (this.c != null ? this.c.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DistrictItem districtItem = (DistrictItem) obj;
        if (this.b == null) {
            if (districtItem.b != null) {
                return false;
            }
        } else if (!this.b.equals(districtItem.b)) {
            return false;
        }
        if (this.gJ == null) {
            if (districtItem.gJ != null) {
                return false;
            }
        } else if (!this.gJ.equals(districtItem.gJ)) {
            return false;
        }
        if (this.a == null) {
            if (districtItem.a != null) {
                return false;
            }
        } else if (!this.a.equals(districtItem.a)) {
            return false;
        }
        if (!Arrays.equals(this.gK, districtItem.gK)) {
            return false;
        }
        if (this.bU == null) {
            if (districtItem.bU != null) {
                return false;
            }
        } else if (!this.bU.equals(districtItem.bU)) {
            return false;
        }
        if (this.e == null) {
            if (districtItem.e != null) {
                return false;
            }
        } else if (!this.e.equals(districtItem.e)) {
            return false;
        }
        if (this.c == null) {
            if (districtItem.c != null) {
                return false;
            }
        } else if (!this.c.equals(districtItem.c)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "DistrictItem [mCitycode=" + this.a + ", mAdcode=" + this.b + ", mName=" + this.c + ", mCenter=" + this.gJ + ", mLevel=" + this.e + ", mDistricts=" + this.bU + "]";
    }
}
