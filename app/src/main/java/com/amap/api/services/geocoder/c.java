package com.amap.api.services.geocoder;

import com.amap.api.services.core.LatLonPoint;

/* compiled from: RegeocodeQuery.java */
/* loaded from: classes11.dex */
public class c {
    private String c = "autonavi";
    private LatLonPoint gP;
    private float gT;

    public c(LatLonPoint latLonPoint, float f, String str) {
        this.gP = latLonPoint;
        this.gT = f;
        ar(str);
    }

    public LatLonPoint bK() {
        return this.gP;
    }

    public float getRadius() {
        return this.gT;
    }

    public String bL() {
        return this.c;
    }

    public void ar(String str) {
        if (str != null) {
            if (str.equals("autonavi") || str.equals("gps")) {
                this.c = str;
            }
        }
    }

    public int hashCode() {
        return (31 * ((((this.c == null ? 0 : this.c.hashCode()) + 31) * 31) + (this.gP != null ? this.gP.hashCode() : 0))) + Float.floatToIntBits(this.gT);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.c == null) {
            if (cVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(cVar.c)) {
            return false;
        }
        if (this.gP == null) {
            if (cVar.gP != null) {
                return false;
            }
        } else if (!this.gP.equals(cVar.gP)) {
            return false;
        }
        if (Float.floatToIntBits(this.gT) == Float.floatToIntBits(cVar.gT)) {
            return true;
        }
        return false;
    }
}
