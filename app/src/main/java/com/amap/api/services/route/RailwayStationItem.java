package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public class RailwayStationItem implements Parcelable {
    public static final Parcelable.Creator<RailwayStationItem> CREATOR = new Parcelable.Creator<RailwayStationItem>() { // from class: com.amap.api.services.route.RailwayStationItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: Z */
        public RailwayStationItem createFromParcel(Parcel parcel) {
            return new RailwayStationItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aC */
        public RailwayStationItem[] newArray(int i) {
            return new RailwayStationItem[i];
        }
    };
    private String a;
    private String b;
    private String d;
    private String e;
    private boolean f;
    private boolean g;
    private LatLonPoint gy;
    private float hh;

    public RailwayStationItem() {
        this.f = false;
        this.g = false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.gy, i);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeBooleanArray(new boolean[]{this.f, this.g});
        parcel.writeFloat(this.hh);
    }

    protected RailwayStationItem(Parcel parcel) {
        this.f = false;
        this.g = false;
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.gy = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readString();
        boolean[] zArr = new boolean[2];
        parcel.readBooleanArray(zArr);
        this.f = zArr[0];
        this.g = zArr[1];
        this.hh = parcel.readFloat();
    }
}
