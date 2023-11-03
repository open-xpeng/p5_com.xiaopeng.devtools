package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.a.br;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class RouteSearch {

    /* loaded from: classes11.dex */
    public interface a {
        void a(BusRouteResult busRouteResult, int i);

        void a(DriveRouteResult driveRouteResult, int i);

        void a(RideRouteResult rideRouteResult, int i);

        void a(WalkRouteResult walkRouteResult, int i);
    }

    /* loaded from: classes11.dex */
    public static class FromAndTo implements Parcelable, Cloneable {
        public static final Parcelable.Creator<FromAndTo> CREATOR = new Parcelable.Creator<FromAndTo>() { // from class: com.amap.api.services.route.RouteSearch.FromAndTo.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: aj */
            public FromAndTo createFromParcel(Parcel parcel) {
                return new FromAndTo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: aM */
            public FromAndTo[] newArray(int i) {
                return new FromAndTo[i];
            }
        };
        private String c;
        private String d;
        private LatLonPoint gP;
        private LatLonPoint gX;

        public FromAndTo(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.gP = latLonPoint;
            this.gX = latLonPoint2;
        }

        public void aB(String str) {
            this.c = str;
        }

        public void aC(String str) {
            this.d = str;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.gP, i);
            parcel.writeParcelable(this.gX, i);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
        }

        public FromAndTo(Parcel parcel) {
            this.gP = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.gX = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.c = parcel.readString();
            this.d = parcel.readString();
        }

        public FromAndTo() {
        }

        public int hashCode() {
            return (31 * ((((((this.d == null ? 0 : this.d.hashCode()) + 31) * 31) + (this.gP == null ? 0 : this.gP.hashCode())) * 31) + (this.c == null ? 0 : this.c.hashCode()))) + (this.gX != null ? this.gX.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            FromAndTo fromAndTo = (FromAndTo) obj;
            if (this.d == null) {
                if (fromAndTo.d != null) {
                    return false;
                }
            } else if (!this.d.equals(fromAndTo.d)) {
                return false;
            }
            if (this.gP == null) {
                if (fromAndTo.gP != null) {
                    return false;
                }
            } else if (!this.gP.equals(fromAndTo.gP)) {
                return false;
            }
            if (this.c == null) {
                if (fromAndTo.c != null) {
                    return false;
                }
            } else if (!this.c.equals(fromAndTo.c)) {
                return false;
            }
            if (this.gX == null) {
                if (fromAndTo.gX != null) {
                    return false;
                }
            } else if (!this.gX.equals(fromAndTo.gX)) {
                return false;
            }
            return true;
        }

        /* renamed from: bP */
        public FromAndTo clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                br.a(e, "RouteSearch", "FromAndToclone");
            }
            FromAndTo fromAndTo = new FromAndTo(this.gP, this.gX);
            fromAndTo.aB(this.c);
            fromAndTo.aC(this.d);
            return fromAndTo;
        }
    }

    /* loaded from: classes11.dex */
    public static class BusRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<BusRouteQuery> CREATOR = new Parcelable.Creator<BusRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.BusRouteQuery.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: ah */
            public BusRouteQuery createFromParcel(Parcel parcel) {
                return new BusRouteQuery(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: aK */
            public BusRouteQuery[] newArray(int i) {
                return new BusRouteQuery[i];
            }
        };
        private int b;
        private String c;
        private String d;
        private int e;
        private FromAndTo hn;

        public BusRouteQuery(FromAndTo fromAndTo, int i, String str, int i2) {
            this.hn = fromAndTo;
            this.b = i;
            this.c = str;
            this.e = i2;
        }

        public void aA(String str) {
            this.d = str;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.hn, i);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
            parcel.writeInt(this.e);
            parcel.writeString(this.d);
        }

        public BusRouteQuery(Parcel parcel) {
            this.hn = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.readString();
            this.e = parcel.readInt();
            this.d = parcel.readString();
        }

        public BusRouteQuery() {
        }

        public int hashCode() {
            return (31 * ((((((((this.c == null ? 0 : this.c.hashCode()) + 31) * 31) + (this.hn == null ? 0 : this.hn.hashCode())) * 31) + this.b) * 31) + this.e)) + (this.d != null ? this.d.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BusRouteQuery busRouteQuery = (BusRouteQuery) obj;
            if (this.c == null) {
                if (busRouteQuery.c != null) {
                    return false;
                }
            } else if (!this.c.equals(busRouteQuery.c)) {
                return false;
            }
            if (this.d == null) {
                if (busRouteQuery.d != null) {
                    return false;
                }
            } else if (!this.d.equals(busRouteQuery.d)) {
                return false;
            }
            if (this.hn == null) {
                if (busRouteQuery.hn != null) {
                    return false;
                }
            } else if (!this.hn.equals(busRouteQuery.hn)) {
                return false;
            }
            if (this.b == busRouteQuery.b && this.e == busRouteQuery.e) {
                return true;
            }
            return false;
        }

        /* renamed from: bN */
        public BusRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                br.a(e, "RouteSearch", "BusRouteQueryclone");
            }
            BusRouteQuery busRouteQuery = new BusRouteQuery(this.hn, this.b, this.c, this.e);
            busRouteQuery.aA(this.d);
            return busRouteQuery;
        }
    }

    /* loaded from: classes11.dex */
    public static class WalkRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<WalkRouteQuery> CREATOR = new Parcelable.Creator<WalkRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.WalkRouteQuery.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: al */
            public WalkRouteQuery createFromParcel(Parcel parcel) {
                return new WalkRouteQuery(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: aO */
            public WalkRouteQuery[] newArray(int i) {
                return new WalkRouteQuery[i];
            }
        };
        private int b;
        private FromAndTo hn;

        public WalkRouteQuery(FromAndTo fromAndTo, int i) {
            this.hn = fromAndTo;
            this.b = i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.hn, i);
            parcel.writeInt(this.b);
        }

        public WalkRouteQuery(Parcel parcel) {
            this.hn = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public WalkRouteQuery() {
        }

        public int hashCode() {
            return (31 * ((this.hn == null ? 0 : this.hn.hashCode()) + 31)) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.hn == null) {
                if (walkRouteQuery.hn != null) {
                    return false;
                }
            } else if (!this.hn.equals(walkRouteQuery.hn)) {
                return false;
            }
            if (this.b == walkRouteQuery.b) {
                return true;
            }
            return false;
        }

        /* renamed from: bR */
        public WalkRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                br.a(e, "RouteSearch", "WalkRouteQueryclone");
            }
            return new WalkRouteQuery(this.hn, this.b);
        }
    }

    /* loaded from: classes11.dex */
    public static class DriveRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<DriveRouteQuery> CREATOR = new Parcelable.Creator<DriveRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.DriveRouteQuery.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: ai */
            public DriveRouteQuery createFromParcel(Parcel parcel) {
                return new DriveRouteQuery(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: aL */
            public DriveRouteQuery[] newArray(int i) {
                return new DriveRouteQuery[i];
            }
        };
        private int b;
        private List<List<LatLonPoint>> by;
        private List<LatLonPoint> c;
        private String e;
        private FromAndTo hn;

        public DriveRouteQuery(FromAndTo fromAndTo, int i, List<LatLonPoint> list, List<List<LatLonPoint>> list2, String str) {
            this.hn = fromAndTo;
            this.b = i;
            this.c = list;
            this.by = list2;
            this.e = str;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.hn, i);
            parcel.writeInt(this.b);
            parcel.writeTypedList(this.c);
            if (this.by == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(this.by.size());
                for (List<LatLonPoint> list : this.by) {
                    parcel.writeTypedList(list);
                }
            }
            parcel.writeString(this.e);
        }

        public DriveRouteQuery(Parcel parcel) {
            this.hn = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            int readInt = parcel.readInt();
            if (readInt == 0) {
                this.by = null;
            } else {
                this.by = new ArrayList();
            }
            for (int i = 0; i < readInt; i++) {
                this.by.add(parcel.createTypedArrayList(LatLonPoint.CREATOR));
            }
            this.e = parcel.readString();
        }

        public DriveRouteQuery() {
        }

        public int hashCode() {
            return (31 * ((((((((this.e == null ? 0 : this.e.hashCode()) + 31) * 31) + (this.by == null ? 0 : this.by.hashCode())) * 31) + (this.hn == null ? 0 : this.hn.hashCode())) * 31) + this.b)) + (this.c != null ? this.c.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DriveRouteQuery driveRouteQuery = (DriveRouteQuery) obj;
            if (this.e == null) {
                if (driveRouteQuery.e != null) {
                    return false;
                }
            } else if (!this.e.equals(driveRouteQuery.e)) {
                return false;
            }
            if (this.by == null) {
                if (driveRouteQuery.by != null) {
                    return false;
                }
            } else if (!this.by.equals(driveRouteQuery.by)) {
                return false;
            }
            if (this.hn == null) {
                if (driveRouteQuery.hn != null) {
                    return false;
                }
            } else if (!this.hn.equals(driveRouteQuery.hn)) {
                return false;
            }
            if (this.b != driveRouteQuery.b) {
                return false;
            }
            if (this.c == null) {
                if (driveRouteQuery.c != null) {
                    return false;
                }
            } else if (!this.c.equals(driveRouteQuery.c)) {
                return false;
            }
            return true;
        }

        /* renamed from: bO */
        public DriveRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                br.a(e, "RouteSearch", "DriveRouteQueryclone");
            }
            return new DriveRouteQuery(this.hn, this.b, this.c, this.by, this.e);
        }
    }

    /* loaded from: classes11.dex */
    public static class RideRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<RideRouteQuery> CREATOR = new Parcelable.Creator<RideRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.RideRouteQuery.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: ak */
            public RideRouteQuery createFromParcel(Parcel parcel) {
                return new RideRouteQuery(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: aN */
            public RideRouteQuery[] newArray(int i) {
                return new RideRouteQuery[i];
            }
        };
        private int b;
        private FromAndTo hn;

        public RideRouteQuery(FromAndTo fromAndTo, int i) {
            this.hn = fromAndTo;
            this.b = i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.hn, i);
            parcel.writeInt(this.b);
        }

        public RideRouteQuery(Parcel parcel) {
            this.hn = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public RideRouteQuery() {
        }

        public int hashCode() {
            return (31 * ((this.hn == null ? 0 : this.hn.hashCode()) + 31)) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.hn == null) {
                if (walkRouteQuery.hn != null) {
                    return false;
                }
            } else if (!this.hn.equals(walkRouteQuery.hn)) {
                return false;
            }
            if (this.b == walkRouteQuery.b) {
                return true;
            }
            return false;
        }

        /* renamed from: bQ */
        public RideRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                br.a(e, "RouteSearch", "RideRouteQueryclone");
            }
            return new RideRouteQuery(this.hn, this.b);
        }
    }
}
