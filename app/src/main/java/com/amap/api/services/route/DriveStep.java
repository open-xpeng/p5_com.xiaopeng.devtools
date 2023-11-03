package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class DriveStep implements Parcelable {
    public static final Parcelable.Creator<DriveStep> CREATOR = new Parcelable.Creator<DriveStep>() { // from class: com.amap.api.services.route.DriveStep.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: V */
        public DriveStep createFromParcel(Parcel parcel) {
            return new DriveStep(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ay */
        public DriveStep[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private List<LatLonPoint> bX;
    private String c;
    private List<RouteSearchCity> cN;
    private float d;
    private float e;
    private String g;
    private float hg;
    private float hh;
    private String j;
    private String k;
    private List<TMC> m;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeFloat(this.hg);
        parcel.writeString(this.g);
        parcel.writeFloat(this.hh);
        parcel.writeTypedList(this.bX);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeTypedList(this.cN);
        parcel.writeTypedList(this.m);
    }

    public DriveStep(Parcel parcel) {
        this.bX = new ArrayList();
        this.cN = new ArrayList();
        this.m = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.hg = parcel.readFloat();
        this.g = parcel.readString();
        this.hh = parcel.readFloat();
        this.bX = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.cN = parcel.createTypedArrayList(RouteSearchCity.CREATOR);
        this.m = parcel.createTypedArrayList(TMC.CREATOR);
    }

    public DriveStep() {
        this.bX = new ArrayList();
        this.cN = new ArrayList();
        this.m = new ArrayList();
    }
}
