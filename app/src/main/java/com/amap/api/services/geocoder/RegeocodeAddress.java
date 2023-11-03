package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.road.Road;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public final class RegeocodeAddress implements Parcelable {
    public static final Parcelable.Creator<RegeocodeAddress> CREATOR = new Parcelable.Creator<RegeocodeAddress>() { // from class: com.amap.api.services.geocoder.RegeocodeAddress.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: E */
        public RegeocodeAddress createFromParcel(Parcel parcel) {
            return new RegeocodeAddress(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: af */
        public RegeocodeAddress[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private String c;
    private List<RegeocodeRoad> cN;
    private String d;
    private String e;
    private String f;
    private String g;
    private StreetNumber gS;
    private String i;
    private String j;
    private String k;
    private List<Crossroad> m;
    private List<PoiItem> n;
    private List<BusinessArea> o;
    private List<AoiItem> p;

    public RegeocodeAddress() {
        this.cN = new ArrayList();
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
    }

    public String bJ() {
        return this.a;
    }

    public void aj(String str) {
        this.a = str;
    }

    public void ak(String str) {
        this.b = str;
    }

    public void al(String str) {
        this.c = str;
    }

    public void Z(String str) {
        this.i = str;
    }

    public void Y(String str) {
        this.j = str;
    }

    public void am(String str) {
        this.d = str;
    }

    public void an(String str) {
        this.e = str;
    }

    public void ao(String str) {
        this.f = str;
    }

    public void ap(String str) {
        this.g = str;
    }

    public void a(StreetNumber streetNumber) {
        this.gS = streetNumber;
    }

    public void k(List<RegeocodeRoad> list) {
        this.cN = list;
    }

    public void l(List<PoiItem> list) {
        this.n = list;
    }

    public void m(List<Crossroad> list) {
        this.m = list;
    }

    public void n(List<BusinessArea> list) {
        this.o = list;
    }

    public void o(List<AoiItem> list) {
        this.p = list;
    }

    public void aq(String str) {
        this.k = str;
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
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeValue(this.gS);
        parcel.writeList(this.cN);
        parcel.writeList(this.m);
        parcel.writeList(this.n);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeList(this.o);
        parcel.writeList(this.p);
        parcel.writeString(this.k);
    }

    private RegeocodeAddress(Parcel parcel) {
        this.cN = new ArrayList();
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.gS = (StreetNumber) parcel.readValue(StreetNumber.class.getClassLoader());
        this.cN = parcel.readArrayList(Road.class.getClassLoader());
        this.m = parcel.readArrayList(Crossroad.class.getClassLoader());
        this.n = parcel.readArrayList(PoiItem.class.getClassLoader());
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.o = parcel.readArrayList(BusinessArea.class.getClassLoader());
        this.p = parcel.readArrayList(AoiItem.class.getClassLoader());
        this.k = parcel.readString();
    }
}
