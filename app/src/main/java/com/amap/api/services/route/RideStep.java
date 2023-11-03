package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class RideStep implements Parcelable {
    public static final Parcelable.Creator<RideStep> CREATOR = new Parcelable.Creator<RideStep>() { // from class: com.amap.api.services.route.RideStep.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ac */
        public RideStep createFromParcel(Parcel parcel) {
            return new RideStep(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aF */
        public RideStep[] newArray(int i) {
            return new RideStep[i];
        }
    };
    private String a;
    private String b;
    private List<LatLonPoint> bU;
    private String c;
    private float d;
    private float e;
    private String g;

    public RideStep() {
        this.bU = new ArrayList();
    }

    protected RideStep(Parcel parcel) {
        this.bU = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.bU = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.g = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeTypedList(this.bU);
        parcel.writeString(this.g);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
