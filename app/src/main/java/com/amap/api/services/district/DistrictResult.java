package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public final class DistrictResult implements Parcelable {
    public Parcelable.Creator<DistrictResult> CREATOR;
    private DistrictSearchQuery gL;
    private ArrayList<DistrictItem> gM;

    public DistrictResult() {
        this.gM = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: z */
            public DistrictResult createFromParcel(Parcel parcel) {
                return new DistrictResult(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: Y */
            public DistrictResult[] newArray(int i) {
                return new DistrictResult[i];
            }
        };
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.gL, i);
        parcel.writeTypedList(this.gM);
    }

    protected DistrictResult(Parcel parcel) {
        this.gM = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: z */
            public DistrictResult createFromParcel(Parcel parcel2) {
                return new DistrictResult(parcel2);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: Y */
            public DistrictResult[] newArray(int i) {
                return new DistrictResult[i];
            }
        };
        this.gL = (DistrictSearchQuery) parcel.readParcelable(DistrictSearchQuery.class.getClassLoader());
        this.gM = parcel.createTypedArrayList(DistrictItem.CREATOR);
    }

    public int hashCode() {
        return (31 * ((this.gL == null ? 0 : this.gL.hashCode()) + 31)) + (this.gM != null ? this.gM.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DistrictResult districtResult = (DistrictResult) obj;
        if (this.gL == null) {
            if (districtResult.gL != null) {
                return false;
            }
        } else if (!this.gL.equals(districtResult.gL)) {
            return false;
        }
        if (this.gM == null) {
            if (districtResult.gM != null) {
                return false;
            }
        } else if (!this.gM.equals(districtResult.gM)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "DistrictResult [mDisQuery=" + this.gL + ", mDistricts=" + this.gM + "]";
    }
}
