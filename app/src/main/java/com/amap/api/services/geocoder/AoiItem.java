package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public class AoiItem implements Parcelable {
    public static final Parcelable.Creator<AoiItem> CREATOR = new Parcelable.Creator<AoiItem>() { // from class: com.amap.api.services.geocoder.AoiItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: B */
        public AoiItem createFromParcel(Parcel parcel) {
            return new AoiItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ac */
        public AoiItem[] newArray(int i) {
            return new AoiItem[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private LatLonPoint gJ;
    private Float gO;

    public AoiItem() {
    }

    public void setId(String str) {
        this.a = str;
    }

    public void setName(String str) {
        this.b = str;
    }

    public void ai(String str) {
        this.c = str;
    }

    public void c(LatLonPoint latLonPoint) {
        this.gJ = latLonPoint;
    }

    public void a(Float f) {
        this.gO = f;
    }

    public AoiItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.gJ = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.gO = Float.valueOf(parcel.readFloat());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeParcelable(this.gJ, i);
        parcel.writeFloat(this.gO.floatValue());
    }
}
