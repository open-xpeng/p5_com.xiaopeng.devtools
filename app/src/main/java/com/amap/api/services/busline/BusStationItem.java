package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BusStationItem implements Parcelable {
    public static final Parcelable.Creator<BusStationItem> CREATOR = new Parcelable.Creator<BusStationItem>() { // from class: com.amap.api.services.busline.BusStationItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: r */
        public BusStationItem createFromParcel(Parcel parcel) {
            return new BusStationItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: P */
        public BusStationItem[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private List<BusLineItem> bU;
    private String d;
    private String e;
    private LatLonPoint gy;

    public BusStationItem() {
        this.bU = new ArrayList();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BusStationItem busStationItem = (BusStationItem) obj;
        if (this.a == null) {
            if (busStationItem.a != null) {
                return false;
            }
        } else if (!this.a.equals(busStationItem.a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 31 + (this.a == null ? 0 : this.a.hashCode());
    }

    public String toString() {
        return "BusStationName: " + this.b + " LatLonPoint: " + this.gy.toString() + " BusLines: " + h(this.bU) + " CityCode: " + this.d + " AdCode: " + this.e;
    }

    private String h(List<BusLineItem> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(list.get(i).bE());
                if (i < list.size() - 1) {
                    stringBuffer.append("|");
                }
            }
        }
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.a);
        parcel.writeValue(this.gy);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeList(this.bU);
    }

    private BusStationItem(Parcel parcel) {
        this.bU = new ArrayList();
        this.b = parcel.readString();
        this.a = parcel.readString();
        this.gy = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.bU = parcel.readArrayList(BusLineItem.class.getClassLoader());
    }
}
