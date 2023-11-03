package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BusPath extends Path implements Parcelable {
    public static final Parcelable.Creator<BusPath> CREATOR = new Parcelable.Creator<BusPath>() { // from class: com.amap.api.services.route.BusPath.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: O */
        public BusPath createFromParcel(Parcel parcel) {
            return new BusPath(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ar */
        public BusPath[] newArray(int i) {
            return null;
        }
    };
    private boolean b;
    private List<BusStep> bo;
    private float d;
    private float eh;
    private float gV;

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.eh);
        parcel.writeBooleanArray(new boolean[]{this.b});
        parcel.writeFloat(this.gV);
        parcel.writeFloat(this.d);
        parcel.writeTypedList(this.bo);
    }

    public BusPath(Parcel parcel) {
        super(parcel);
        this.bo = new ArrayList();
        this.eh = parcel.readFloat();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.b = zArr[0];
        this.gV = parcel.readFloat();
        this.d = parcel.readFloat();
        this.bo = parcel.createTypedArrayList(BusStep.CREATOR);
    }

    public BusPath() {
        this.bo = new ArrayList();
    }
}
