package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class RailwaySpace implements Parcelable {
    public static final Parcelable.Creator<RailwaySpace> CREATOR = new Parcelable.Creator<RailwaySpace>() { // from class: com.amap.api.services.route.RailwaySpace.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: Y */
        public RailwaySpace createFromParcel(Parcel parcel) {
            return new RailwaySpace(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aB */
        public RailwaySpace[] newArray(int i) {
            return new RailwaySpace[i];
        }
    };
    private String a;
    private float gT;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeFloat(this.gT);
    }

    protected RailwaySpace(Parcel parcel) {
        this.a = parcel.readString();
        this.gT = parcel.readFloat();
    }
}
