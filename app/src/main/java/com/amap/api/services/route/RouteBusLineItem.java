package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class RouteBusLineItem extends BusLineItem implements Parcelable {
    public static final Parcelable.Creator<RouteBusLineItem> CREATOR = new Parcelable.Creator<RouteBusLineItem>() { // from class: com.amap.api.services.route.RouteBusLineItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ad */
        public RouteBusLineItem createFromParcel(Parcel parcel) {
            return new RouteBusLineItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aG */
        public RouteBusLineItem[] newArray(int i) {
            return null;
        }
    };
    private List<BusStationItem> bo;
    private List<LatLonPoint> c;
    private int d;
    private float hg;
    private BusStationItem hj;
    private BusStationItem hk;

    @Override // com.amap.api.services.busline.BusLineItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.busline.BusLineItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.hj, i);
        parcel.writeParcelable(this.hk, i);
        parcel.writeTypedList(this.c);
        parcel.writeInt(this.d);
        parcel.writeTypedList(this.bo);
        parcel.writeFloat(this.hg);
    }

    public RouteBusLineItem(Parcel parcel) {
        super(parcel);
        this.c = new ArrayList();
        this.bo = new ArrayList();
        this.hj = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.hk = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.d = parcel.readInt();
        this.bo = parcel.createTypedArrayList(BusStationItem.CREATOR);
        this.hg = parcel.readFloat();
    }

    public RouteBusLineItem() {
        this.c = new ArrayList();
        this.bo = new ArrayList();
    }

    @Override // com.amap.api.services.busline.BusLineItem
    public int hashCode() {
        return (31 * ((super.hashCode() * 31) + (this.hk == null ? 0 : this.hk.hashCode()))) + (this.hj != null ? this.hj.hashCode() : 0);
    }

    @Override // com.amap.api.services.busline.BusLineItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && getClass() == obj.getClass()) {
            RouteBusLineItem routeBusLineItem = (RouteBusLineItem) obj;
            if (this.hk == null) {
                if (routeBusLineItem.hk != null) {
                    return false;
                }
            } else if (!this.hk.equals(routeBusLineItem.hk)) {
                return false;
            }
            if (this.hj == null) {
                if (routeBusLineItem.hj != null) {
                    return false;
                }
            } else if (!this.hj.equals(routeBusLineItem.hj)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
