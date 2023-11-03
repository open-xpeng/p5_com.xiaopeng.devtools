package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public class BusinessArea implements Parcelable {
    public static final Parcelable.Creator<BusinessArea> CREATOR = new Parcelable.Creator<BusinessArea>() { // from class: com.amap.api.services.geocoder.BusinessArea.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: C */
        public BusinessArea createFromParcel(Parcel parcel) {
            return new BusinessArea(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ad */
        public BusinessArea[] newArray(int i) {
            return new BusinessArea[i];
        }
    };
    private String b;
    private LatLonPoint gP;

    public BusinessArea() {
    }

    public void d(LatLonPoint latLonPoint) {
        this.gP = latLonPoint;
    }

    public void setName(String str) {
        this.b = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.gP, i);
        parcel.writeString(this.b);
    }

    public BusinessArea(Parcel parcel) {
        this.gP = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.b = parcel.readString();
    }
}
