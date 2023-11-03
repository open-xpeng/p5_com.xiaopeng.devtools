package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.RegeocodeAddress;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReverseGeocodingHandler.java */
/* loaded from: classes11.dex */
public class b extends h<com.amap.api.services.geocoder.c, RegeocodeAddress> {
    public b(Context context, com.amap.api.services.geocoder.c cVar) {
        super(context, cVar);
    }

    @Override // com.amap.api.services.a.bh
    public String g() {
        return bq.a() + "/geocode/regeo?";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.services.a.a
    /* renamed from: D */
    public RegeocodeAddress a(String str) throws AMapException {
        JSONObject optJSONObject;
        RegeocodeAddress regeocodeAddress = new RegeocodeAddress();
        try {
            optJSONObject = new JSONObject(str).optJSONObject("regeocode");
        } catch (JSONException e) {
            br.a(e, "ReverseGeocodingHandler", "paseJSON");
        }
        if (optJSONObject == null) {
            return regeocodeAddress;
        }
        regeocodeAddress.aj(bs.a(optJSONObject, "formatted_address"));
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
        if (optJSONObject2 != null) {
            bs.a(optJSONObject2, regeocodeAddress);
        }
        regeocodeAddress.l(bs.d(optJSONObject));
        JSONArray optJSONArray = optJSONObject.optJSONArray("roads");
        if (optJSONArray != null) {
            bs.b(optJSONArray, regeocodeAddress);
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("roadinters");
        if (optJSONArray2 != null) {
            bs.a(optJSONArray2, regeocodeAddress);
        }
        JSONArray optJSONArray3 = optJSONObject.optJSONArray("aois");
        if (optJSONArray3 != null) {
            bs.c(optJSONArray3, regeocodeAddress);
        }
        return regeocodeAddress;
    }

    @Override // com.amap.api.services.a.h
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json");
        stringBuffer.append("&extensions=all");
        stringBuffer.append("&location=");
        stringBuffer.append(((com.amap.api.services.geocoder.c) this.a).bK().getLongitude());
        stringBuffer.append(",");
        stringBuffer.append(((com.amap.api.services.geocoder.c) this.a).bK().getLatitude());
        stringBuffer.append("&radius=");
        stringBuffer.append(((com.amap.api.services.geocoder.c) this.a).getRadius());
        stringBuffer.append("&coordsys=");
        stringBuffer.append(((com.amap.api.services.geocoder.c) this.a).bL());
        stringBuffer.append("&key=" + d.f(this.ei));
        stringBuffer.append("&language=");
        stringBuffer.append(bq.c());
        return stringBuffer.toString();
    }
}
