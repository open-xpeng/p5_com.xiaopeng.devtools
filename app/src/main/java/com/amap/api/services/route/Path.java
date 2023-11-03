package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class Path implements Parcelable {
    public static final Parcelable.Creator<Path> CREATOR = new Parcelable.Creator<Path>() { // from class: com.amap.api.services.route.Path.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: W */
        public Path createFromParcel(Parcel parcel) {
            return new Path(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: az */
        public Path[] newArray(int i) {
            return null;
        }
    };
    private long b;
    private float eh;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.eh);
        parcel.writeLong(this.b);
    }

    public Path(Parcel parcel) {
        this.eh = parcel.readFloat();
        this.b = parcel.readLong();
    }

    public Path() {
    }
}
