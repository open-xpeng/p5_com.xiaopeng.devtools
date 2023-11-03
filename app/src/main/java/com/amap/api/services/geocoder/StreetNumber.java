package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public final class StreetNumber implements Parcelable {
    public static final Parcelable.Creator<StreetNumber> CREATOR = new Parcelable.Creator<StreetNumber>() { // from class: com.amap.api.services.geocoder.StreetNumber.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: G */
        public StreetNumber createFromParcel(Parcel parcel) {
            return new StreetNumber(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ah */
        public StreetNumber[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private String d;
    private float e;
    private LatLonPoint gy;

    public StreetNumber() {
    }

    public void at(String str) {
        this.a = str;
    }

    public void setNumber(String str) {
        this.b = str;
    }

    public void f(LatLonPoint latLonPoint) {
        this.gy = latLonPoint;
    }

    public void ad(String str) {
        this.d = str;
    }

    public void a(float f) {
        this.e = f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeValue(this.gy);
        parcel.writeString(this.d);
        parcel.writeFloat(this.e);
    }

    private StreetNumber(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.gy = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readFloat();
    }
}
