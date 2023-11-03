package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes11.dex */
public class RouteResult implements Parcelable {
    public static final Parcelable.Creator<RouteResult> CREATOR = new Parcelable.Creator<RouteResult>() { // from class: com.amap.api.services.route.RouteResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ag */
        public RouteResult createFromParcel(Parcel parcel) {
            return new RouteResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aJ */
        public RouteResult[] newArray(int i) {
            return new RouteResult[i];
        }
    };
    private LatLonPoint gP;
    private LatLonPoint gX;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.gP, i);
        parcel.writeParcelable(this.gX, i);
    }

    public RouteResult(Parcel parcel) {
        this.gP = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.gX = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public RouteResult() {
    }
}
