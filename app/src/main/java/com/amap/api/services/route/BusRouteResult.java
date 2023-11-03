package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BusRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<BusRouteResult> CREATOR = new Parcelable.Creator<BusRouteResult>() { // from class: com.amap.api.services.route.BusRouteResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: P */
        public BusRouteResult createFromParcel(Parcel parcel) {
            return new BusRouteResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: as */
        public BusRouteResult[] newArray(int i) {
            return new BusRouteResult[i];
        }
    };
    private List<BusPath> b;
    private float eh;
    private RouteSearch.BusRouteQuery gZ;

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.eh);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.gZ, i);
    }

    public BusRouteResult(Parcel parcel) {
        super(parcel);
        this.b = new ArrayList();
        this.eh = parcel.readFloat();
        this.b = parcel.createTypedArrayList(BusPath.CREATOR);
        this.gZ = (RouteSearch.BusRouteQuery) parcel.readParcelable(RouteSearch.BusRouteQuery.class.getClassLoader());
    }

    public BusRouteResult() {
        this.b = new ArrayList();
    }
}
