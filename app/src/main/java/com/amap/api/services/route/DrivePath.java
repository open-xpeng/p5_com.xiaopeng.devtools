package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class DrivePath extends Path implements Parcelable {
    public static final Parcelable.Creator<DrivePath> CREATOR = new Parcelable.Creator<DrivePath>() { // from class: com.amap.api.services.route.DrivePath.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: T */
        public DrivePath createFromParcel(Parcel parcel) {
            return new DrivePath(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aw */
        public DrivePath[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private List<DriveStep> bo;
    private int d;
    private float gT;
    private float gV;

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeFloat(this.gT);
        parcel.writeFloat(this.gV);
        parcel.writeTypedList(this.bo);
        parcel.writeInt(this.d);
    }

    public DrivePath(Parcel parcel) {
        super(parcel);
        this.bo = new ArrayList();
        this.a = parcel.readString();
        this.gT = parcel.readFloat();
        this.gV = parcel.readFloat();
        this.bo = parcel.createTypedArrayList(DriveStep.CREATOR);
        this.d = parcel.readInt();
    }

    public DrivePath() {
        this.bo = new ArrayList();
    }
}
