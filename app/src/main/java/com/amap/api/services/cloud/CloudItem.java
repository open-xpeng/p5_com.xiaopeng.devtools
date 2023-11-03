package com.amap.api.services.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes11.dex */
public class CloudItem implements Parcelable {
    public static final Parcelable.Creator<CloudItem> CREATOR = new Parcelable.Creator<CloudItem>() { // from class: com.amap.api.services.cloud.CloudItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: t */
        public CloudItem createFromParcel(Parcel parcel) {
            return new CloudItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: R */
        public CloudItem[] newArray(int i) {
            return new CloudItem[i];
        }
    };
    private String a;
    private int b;
    private List<CloudImage> bU;
    private String c;
    private String d;
    protected final String gA;
    private HashMap<String, String> gB = new HashMap<>();
    protected final LatLonPoint gz;
    protected final String mTitle;

    /* JADX INFO: Access modifiers changed from: protected */
    public CloudItem(Parcel parcel) {
        this.b = -1;
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.gz = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.gA = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        parcel.readMap(this.gB, HashMap.class.getClassLoader());
        this.bU = new ArrayList();
        parcel.readList(this.bU, getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeValue(this.gz);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.gA);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeMap(this.gB);
        parcel.writeList(this.bU);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass() && CloudItem.class != obj.getClass() && CloudItemDetail.class != obj.getClass()) {
            return false;
        }
        CloudItem cloudItem = (CloudItem) obj;
        if (this.a == null) {
            if (cloudItem.a != null) {
                return false;
            }
        } else if (!this.a.equals(cloudItem.a)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 31 + (this.a == null ? 0 : this.a.hashCode());
    }

    public String toString() {
        return this.mTitle;
    }
}
