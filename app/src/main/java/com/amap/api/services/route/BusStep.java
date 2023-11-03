package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BusStep implements Parcelable {
    public static final Parcelable.Creator<BusStep> CREATOR = new Parcelable.Creator<BusStep>() { // from class: com.amap.api.services.route.BusStep.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: Q */
        public BusStep createFromParcel(Parcel parcel) {
            return new BusStep(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: at */
        public BusStep[] newArray(int i) {
            return null;
        }
    };
    private List<RouteBusLineItem> b;
    private RouteBusWalkItem ha;
    private Doorway hb;
    private Doorway hc;
    private RouteRailwayItem hd;
    private TaxiItem he;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.ha, i);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.hb, i);
        parcel.writeParcelable(this.hc, i);
        parcel.writeParcelable(this.hd, i);
        parcel.writeParcelable(this.he, i);
    }

    public BusStep(Parcel parcel) {
        this.b = new ArrayList();
        this.ha = (RouteBusWalkItem) parcel.readParcelable(RouteBusWalkItem.class.getClassLoader());
        this.b = parcel.createTypedArrayList(RouteBusLineItem.CREATOR);
        this.hb = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
        this.hc = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
        this.hd = (RouteRailwayItem) parcel.readParcelable(RouteRailwayItem.class.getClassLoader());
        this.he = (TaxiItem) parcel.readParcelable(TaxiItem.class.getClassLoader());
    }

    public BusStep() {
        this.b = new ArrayList();
    }
}
