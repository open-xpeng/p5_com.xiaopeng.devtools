package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.a.br;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes11.dex */
public class BusLineItem implements Parcelable {
    public static final Parcelable.Creator<BusLineItem> CREATOR = new Parcelable.Creator<BusLineItem>() { // from class: com.amap.api.services.busline.BusLineItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: q */
        public BusLineItem createFromParcel(Parcel parcel) {
            return new BusLineItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: O */
        public BusLineItem[] newArray(int i) {
            return null;
        }
    };
    private String b;
    private List<LatLonPoint> bU;
    private List<LatLonPoint> bo;
    private String c;
    private String d;
    private float eh;
    private String g;
    private Date gu;
    private Date gv;
    private float gw;
    private float gx;
    private String h;
    private String i;
    private String l;
    private List<BusStationItem> o;

    public BusLineItem() {
        this.bo = new ArrayList();
        this.bU = new ArrayList();
        this.o = new ArrayList();
    }

    public String bE() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BusLineItem busLineItem = (BusLineItem) obj;
        if (this.g == null) {
            if (busLineItem.g != null) {
                return false;
            }
        } else if (!this.g.equals(busLineItem.g)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 31 + (this.g == null ? 0 : this.g.hashCode());
    }

    public String toString() {
        return this.b + " " + br.a(this.gu) + "-" + br.a(this.gv);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.eh);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeList(this.bo);
        parcel.writeList(this.bU);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(br.a(this.gu));
        parcel.writeString(br.a(this.gv));
        parcel.writeString(this.l);
        parcel.writeFloat(this.gw);
        parcel.writeFloat(this.gx);
        parcel.writeList(this.o);
    }

    public BusLineItem(Parcel parcel) {
        this.bo = new ArrayList();
        this.bU = new ArrayList();
        this.o = new ArrayList();
        this.eh = parcel.readFloat();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.bo = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.bU = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.gu = br.M(parcel.readString());
        this.gv = br.M(parcel.readString());
        this.l = parcel.readString();
        this.gw = parcel.readFloat();
        this.gx = parcel.readFloat();
        this.o = parcel.readArrayList(BusStationItem.class.getClassLoader());
    }
}
