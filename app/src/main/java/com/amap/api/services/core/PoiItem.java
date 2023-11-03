package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemExtension;
import com.amap.api.services.poisearch.SubPoiItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class PoiItem implements Parcelable {
    public static final Parcelable.Creator<PoiItem> CREATOR = new Parcelable.Creator<PoiItem>() { // from class: com.amap.api.services.core.PoiItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: x */
        public PoiItem createFromParcel(Parcel parcel) {
            return new PoiItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: W */
        public PoiItem[] newArray(int i) {
            return new PoiItem[i];
        }
    };
    private String a;
    private String b;
    private boolean bH;
    private String c;
    private String d;
    private String e;
    private int f;
    protected final String gA;
    private LatLonPoint gC;
    private LatLonPoint gD;
    private IndoorData gE;
    private List<SubPoiItem> gF;
    private List<Photo> gG;
    private PoiItemExtension gH;
    protected final LatLonPoint gz;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    protected final String mTitle;
    private String n;
    private String o;
    private String r;
    private String s;
    private String t;

    public PoiItem(String str, LatLonPoint latLonPoint, String str2, String str3) {
        this.e = "";
        this.f = -1;
        this.gF = new ArrayList();
        this.gG = new ArrayList();
        this.a = str;
        this.gz = latLonPoint;
        this.mTitle = str2;
        this.gA = str3;
    }

    public void S(String str) {
        this.s = str;
    }

    public void T(String str) {
        this.o = str;
    }

    public void U(String str) {
        this.n = str;
    }

    public void V(String str) {
        this.m = str;
    }

    public void W(String str) {
        this.e = str;
    }

    public void X(String str) {
        this.b = str;
    }

    public void Y(String str) {
        this.c = str;
    }

    public void V(int i) {
        this.f = i;
    }

    public void Z(String str) {
        this.d = str;
    }

    public void a(LatLonPoint latLonPoint) {
        this.gC = latLonPoint;
    }

    public void b(LatLonPoint latLonPoint) {
        this.gD = latLonPoint;
    }

    public void aa(String str) {
        this.i = str;
    }

    public void ab(String str) {
        this.j = str;
    }

    public void ac(String str) {
        this.k = str;
    }

    public void ad(String str) {
        this.l = str;
    }

    public void m(boolean z) {
        this.bH = z;
    }

    public void ae(String str) {
        this.r = str;
    }

    public void af(String str) {
        this.t = str;
    }

    public void i(List<SubPoiItem> list) {
        this.gF = list;
    }

    public void a(IndoorData indoorData) {
        this.gE = indoorData;
    }

    public void j(List<Photo> list) {
        this.gG = list;
    }

    public void a(PoiItemExtension poiItemExtension) {
        this.gH = poiItemExtension;
    }

    protected PoiItem(Parcel parcel) {
        this.e = "";
        this.f = -1;
        this.gF = new ArrayList();
        this.gG = new ArrayList();
        this.a = parcel.readString();
        this.c = parcel.readString();
        this.b = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
        this.gz = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.gA = parcel.readString();
        this.d = parcel.readString();
        this.gC = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.gD = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.bH = zArr[0];
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.gF = parcel.readArrayList(SubPoiItem.class.getClassLoader());
        this.gE = (IndoorData) parcel.readValue(IndoorData.class.getClassLoader());
        this.gG = parcel.createTypedArrayList(Photo.CREATOR);
        this.gH = (PoiItemExtension) parcel.readParcelable(PoiItemExtension.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.c);
        parcel.writeString(this.b);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeValue(this.gz);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.gA);
        parcel.writeString(this.d);
        parcel.writeValue(this.gC);
        parcel.writeValue(this.gD);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeBooleanArray(new boolean[]{this.bH});
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeList(this.gF);
        parcel.writeValue(this.gE);
        parcel.writeTypedList(this.gG);
        parcel.writeParcelable(this.gH, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PoiItem poiItem = (PoiItem) obj;
        if (this.a == null) {
            if (poiItem.a != null) {
                return false;
            }
        } else if (!this.a.equals(poiItem.a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 31 + (this.a == null ? 0 : this.a.hashCode());
    }

    public String toString() {
        return this.mTitle;
    }
}
