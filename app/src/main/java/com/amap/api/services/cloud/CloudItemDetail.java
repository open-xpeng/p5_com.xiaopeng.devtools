package com.amap.api.services.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes11.dex */
public class CloudItemDetail extends CloudItem implements Parcelable {
    public static final Parcelable.Creator<CloudItemDetail> CREATOR = new Parcelable.Creator<CloudItemDetail>() { // from class: com.amap.api.services.cloud.CloudItemDetail.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: u */
        public CloudItemDetail createFromParcel(Parcel parcel) {
            return new CloudItemDetail(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: S */
        public CloudItemDetail[] newArray(int i) {
            return new CloudItemDetail[i];
        }
    };

    protected CloudItemDetail(Parcel parcel) {
        super(parcel);
    }

    @Override // com.amap.api.services.cloud.CloudItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.cloud.CloudItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
