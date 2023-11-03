package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public final class RegeocodeRoad implements Parcelable {
    public static final Parcelable.Creator<RegeocodeRoad> CREATOR = new Parcelable.Creator<RegeocodeRoad>() { // from class: com.amap.api.services.geocoder.RegeocodeRoad.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: F */
        public RegeocodeRoad createFromParcel(Parcel parcel) {
            return new RegeocodeRoad(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ag */
        public RegeocodeRoad[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private String d;
    private float gV;
    private LatLonPoint gW;

    public RegeocodeRoad() {
    }

    public void setId(String str) {
        this.a = str;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void a(float f) {
        this.gV = f;
    }

    public void ad(String str) {
        this.d = str;
    }

    public void e(LatLonPoint latLonPoint) {
        this.gW = latLonPoint;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeFloat(this.gV);
        parcel.writeString(this.d);
        parcel.writeValue(this.gW);
    }

    private RegeocodeRoad(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.gV = parcel.readFloat();
        this.d = parcel.readString();
        this.gW = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
    }
}
