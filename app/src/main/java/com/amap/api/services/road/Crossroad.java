package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public final class Crossroad extends Road implements Parcelable {
    public static final Parcelable.Creator<Crossroad> CREATOR = new Parcelable.Creator<Crossroad>() { // from class: com.amap.api.services.road.Crossroad.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ap */
        public Crossroad[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: M */
        public Crossroad createFromParcel(Parcel parcel) {
            return new Crossroad(parcel);
        }
    };
    private String b;
    private String c;
    private String d;
    private String e;
    private float eh;
    private String f;

    public Crossroad() {
    }

    public void a(float f) {
        this.eh = f;
    }

    public void ad(String str) {
        this.b = str;
    }

    public void aw(String str) {
        this.c = str;
    }

    public void ax(String str) {
        this.d = str;
    }

    public void ay(String str) {
        this.e = str;
    }

    public void az(String str) {
        this.f = str;
    }

    @Override // com.amap.api.services.road.Road, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.road.Road, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.eh);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
    }

    private Crossroad(Parcel parcel) {
        super(parcel);
        this.eh = parcel.readFloat();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
    }
}
