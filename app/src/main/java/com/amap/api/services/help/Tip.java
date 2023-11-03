package com.amap.api.services.help;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public class Tip implements Parcelable {
    public static final Parcelable.Creator<Tip> CREATOR = new Parcelable.Creator<Tip>() { // from class: com.amap.api.services.help.Tip.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: H */
        public Tip createFromParcel(Parcel parcel) {
            return new Tip(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ai */
        public Tip[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String c;
    private String d;
    private String e;
    private String f;
    private LatLonPoint gX;

    public Tip() {
    }

    public String toString() {
        return "name:" + this.c + " district:" + this.d + " adcode:" + this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeString(this.d);
        parcel.writeString(this.a);
        parcel.writeValue(this.gX);
        parcel.writeString(this.f);
    }

    private Tip(Parcel parcel) {
        this.c = parcel.readString();
        this.e = parcel.readString();
        this.d = parcel.readString();
        this.a = parcel.readString();
        this.gX = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f = parcel.readString();
    }
}
