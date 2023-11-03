package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public class SubPoiItem implements Parcelable {
    public static final Parcelable.Creator<SubPoiItem> CREATOR = new Parcelable.Creator<SubPoiItem>() { // from class: com.amap.api.services.poisearch.SubPoiItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: L */
        public SubPoiItem createFromParcel(Parcel parcel) {
            return new SubPoiItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ao */
        public SubPoiItem[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private String c;
    private int d;
    private String f;
    private String g;
    private LatLonPoint gW;

    public SubPoiItem(String str, LatLonPoint latLonPoint, String str2, String str3) {
        this.a = str;
        this.gW = latLonPoint;
        this.b = str2;
        this.f = str3;
    }

    public SubPoiItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.gW = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f = parcel.readString();
        this.g = parcel.readString();
    }

    public void au(String str) {
        this.c = str;
    }

    public void V(int i) {
        this.d = i;
    }

    public void av(String str) {
        this.g = str;
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
        parcel.writeInt(this.d);
        parcel.writeValue(this.gW);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }
}
