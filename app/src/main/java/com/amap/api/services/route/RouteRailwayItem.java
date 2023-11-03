package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class RouteRailwayItem extends Railway implements Parcelable {
    public static final Parcelable.Creator<RouteRailwayItem> CREATOR = new Parcelable.Creator<RouteRailwayItem>() { // from class: com.amap.api.services.route.RouteRailwayItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: af */
        public RouteRailwayItem createFromParcel(Parcel parcel) {
            return new RouteRailwayItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aI */
        public RouteRailwayItem[] newArray(int i) {
            return new RouteRailwayItem[i];
        }
    };
    private String a;
    private String b;
    private List<RailwayStationItem> bV;
    private List<Railway> bW;
    private List<RailwaySpace> bX;
    private String d;
    private float gV;
    private RailwayStationItem hl;
    private RailwayStationItem hm;

    public RouteRailwayItem() {
        this.bV = new ArrayList();
        this.bW = new ArrayList();
        this.bX = new ArrayList();
    }

    protected RouteRailwayItem(Parcel parcel) {
        super(parcel);
        this.bV = new ArrayList();
        this.bW = new ArrayList();
        this.bX = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.gV = parcel.readFloat();
        this.d = parcel.readString();
        this.hl = (RailwayStationItem) parcel.readParcelable(RailwayStationItem.class.getClassLoader());
        this.hm = (RailwayStationItem) parcel.readParcelable(RailwayStationItem.class.getClassLoader());
        this.bV = parcel.createTypedArrayList(RailwayStationItem.CREATOR);
        this.bW = parcel.createTypedArrayList(Railway.CREATOR);
        this.bX = parcel.createTypedArrayList(RailwaySpace.CREATOR);
    }

    @Override // com.amap.api.services.route.Railway, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeFloat(this.gV);
        parcel.writeString(this.d);
        parcel.writeParcelable(this.hl, i);
        parcel.writeParcelable(this.hm, i);
        parcel.writeTypedList(this.bV);
        parcel.writeTypedList(this.bW);
        parcel.writeTypedList(this.bX);
    }

    @Override // com.amap.api.services.route.Railway, android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
