package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public class TaxiItem implements Parcelable {
    public static final Parcelable.Creator<TaxiItem> CREATOR = new Parcelable.Creator<TaxiItem>() { // from class: com.amap.api.services.route.TaxiItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ap */
        public TaxiItem createFromParcel(Parcel parcel) {
            return new TaxiItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aS */
        public TaxiItem[] newArray(int i) {
            return new TaxiItem[i];
        }
    };
    private float d;
    private String e;
    private String f;
    private LatLonPoint gP;
    private float gV;
    private LatLonPoint gX;

    public TaxiItem() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.gP, i);
        parcel.writeParcelable(this.gX, i);
        parcel.writeFloat(this.gV);
        parcel.writeFloat(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
    }

    protected TaxiItem(Parcel parcel) {
        this.gP = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.gX = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.gV = parcel.readFloat();
        this.d = parcel.readFloat();
        this.e = parcel.readString();
        this.f = parcel.readString();
    }
}
