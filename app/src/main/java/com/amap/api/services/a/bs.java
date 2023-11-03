package com.amap.api.services.a;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.AoiItem;
import com.amap.api.services.geocoder.BusinessArea;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemExtension;
import com.amap.api.services.poisearch.SubPoiItem;
import com.amap.api.services.road.Crossroad;
import com.xiaopeng.lib.apirouter.ClientConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSONHelper.java */
/* loaded from: classes11.dex */
public class bs {
    public static ArrayList<PoiItem> d(JSONObject jSONObject) throws JSONException {
        ArrayList<PoiItem> arrayList = new ArrayList<>();
        if (jSONObject == null) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("pois");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(e(optJSONObject));
            }
        }
        return arrayList;
    }

    public static PoiItem e(JSONObject jSONObject) throws JSONException {
        PoiItem poiItem = new PoiItem(a(jSONObject, "id"), c(jSONObject, RequestParameters.SUBRESOURCE_LOCATION), a(jSONObject, ClientConstants.ALIAS.P_NAME), a(jSONObject, "address"));
        poiItem.Y(a(jSONObject, "adcode"));
        poiItem.V(a(jSONObject, "pname"));
        poiItem.U(a(jSONObject, "cityname"));
        poiItem.T(a(jSONObject, "adname"));
        poiItem.Z(a(jSONObject, "citycode"));
        poiItem.ae(a(jSONObject, "pcode"));
        poiItem.ad(a(jSONObject, "direction"));
        if (jSONObject.has("distance")) {
            String a = a(jSONObject, "distance");
            if (!P(a)) {
                try {
                    poiItem.V((int) Float.parseFloat(a));
                } catch (NumberFormatException e) {
                    br.a(e, "JSONHelper", "parseBasePoi");
                } catch (Exception e2) {
                    br.a(e2, "JSONHelper", "parseBasePoi");
                }
            }
        }
        poiItem.X(a(jSONObject, "tel"));
        poiItem.W(a(jSONObject, "type"));
        poiItem.a(c(jSONObject, "entr_location"));
        poiItem.b(c(jSONObject, "exit_location"));
        poiItem.aa(a(jSONObject, RequestParameters.SUBRESOURCE_WEBSITE));
        poiItem.ab(a(jSONObject, "postcode"));
        poiItem.S(a(jSONObject, "business_area"));
        poiItem.ac(a(jSONObject, "email"));
        if (O(a(jSONObject, "indoor_map"))) {
            poiItem.m(false);
        } else {
            poiItem.m(true);
        }
        poiItem.af(a(jSONObject, "parking_type"));
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("children")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("children");
            if (optJSONArray == null) {
                poiItem.i(arrayList);
            } else {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        arrayList.add(f(optJSONObject));
                    }
                }
                poiItem.i(arrayList);
            }
        }
        poiItem.a(d(jSONObject, "indoor_data"));
        poiItem.a(e(jSONObject, "biz_ext"));
        a(poiItem, jSONObject);
        return poiItem;
    }

    private static SubPoiItem f(JSONObject jSONObject) throws JSONException {
        SubPoiItem subPoiItem = new SubPoiItem(a(jSONObject, "id"), c(jSONObject, RequestParameters.SUBRESOURCE_LOCATION), a(jSONObject, ClientConstants.ALIAS.P_NAME), a(jSONObject, "address"));
        subPoiItem.au(a(jSONObject, "sname"));
        subPoiItem.av(a(jSONObject, "subtype"));
        if (jSONObject.has("distance")) {
            String a = a(jSONObject, "distance");
            if (!P(a)) {
                try {
                    subPoiItem.V((int) Float.parseFloat(a));
                } catch (NumberFormatException e) {
                    br.a(e, "JSONHelper", "parseSubPoiItem");
                } catch (Exception e2) {
                    br.a(e2, "JSONHelper", "parseSubPoiItem");
                }
            }
        }
        return subPoiItem;
    }

    public static void a(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Crossroad crossroad = new Crossroad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                crossroad.setId(a(optJSONObject, "id"));
                crossroad.ad(a(optJSONObject, "direction"));
                crossroad.a(R(a(optJSONObject, "distance")));
                crossroad.d(c(optJSONObject, RequestParameters.SUBRESOURCE_LOCATION));
                crossroad.aw(a(optJSONObject, "first_id"));
                crossroad.ax(a(optJSONObject, "first_name"));
                crossroad.ay(a(optJSONObject, "second_id"));
                crossroad.az(a(optJSONObject, "second_name"));
                arrayList.add(crossroad);
            }
        }
        regeocodeAddress.m(arrayList);
    }

    public static void b(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            RegeocodeRoad regeocodeRoad = new RegeocodeRoad();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                regeocodeRoad.setId(a(optJSONObject, "id"));
                regeocodeRoad.setName(a(optJSONObject, ClientConstants.ALIAS.P_NAME));
                regeocodeRoad.e(c(optJSONObject, RequestParameters.SUBRESOURCE_LOCATION));
                regeocodeRoad.ad(a(optJSONObject, "direction"));
                regeocodeRoad.a(R(a(optJSONObject, "distance")));
                arrayList.add(regeocodeRoad);
            }
        }
        regeocodeAddress.k(arrayList);
    }

    public static void a(JSONObject jSONObject, RegeocodeAddress regeocodeAddress) throws JSONException {
        regeocodeAddress.ak(a(jSONObject, "province"));
        regeocodeAddress.al(a(jSONObject, "city"));
        regeocodeAddress.Z(a(jSONObject, "citycode"));
        regeocodeAddress.Y(a(jSONObject, "adcode"));
        regeocodeAddress.am(a(jSONObject, "district"));
        regeocodeAddress.an(a(jSONObject, "township"));
        regeocodeAddress.ao(a(jSONObject.optJSONObject("neighborhood"), ClientConstants.ALIAS.P_NAME));
        regeocodeAddress.ap(a(jSONObject.optJSONObject("building"), ClientConstants.ALIAS.P_NAME));
        StreetNumber streetNumber = new StreetNumber();
        JSONObject optJSONObject = jSONObject.optJSONObject("streetNumber");
        streetNumber.at(a(optJSONObject, "street"));
        streetNumber.setNumber(a(optJSONObject, "number"));
        streetNumber.f(c(optJSONObject, RequestParameters.SUBRESOURCE_LOCATION));
        streetNumber.ad(a(optJSONObject, "direction"));
        streetNumber.a(R(a(optJSONObject, "distance")));
        regeocodeAddress.a(streetNumber);
        regeocodeAddress.n(g(jSONObject));
        regeocodeAddress.aq(a(jSONObject, "towncode"));
    }

    public static List<BusinessArea> g(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("businessAreas");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            BusinessArea businessArea = new BusinessArea();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                businessArea.d(c(optJSONObject, RequestParameters.SUBRESOURCE_LOCATION));
                businessArea.setName(a(optJSONObject, ClientConstants.ALIAS.P_NAME));
                arrayList.add(businessArea);
            }
        }
        return arrayList;
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str) || jSONObject.getString(str).equals("[]")) {
            return "";
        }
        return jSONObject.optString(str).trim();
    }

    public static LatLonPoint c(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str)) {
            return null;
        }
        return N(jSONObject.optString(str));
    }

    public static LatLonPoint N(String str) {
        if (str == null || str.equals("") || str.equals("[]")) {
            return null;
        }
        String[] split = str.split(",| ");
        if (split.length != 2) {
            return null;
        }
        return new LatLonPoint(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
    }

    public static boolean O(String str) {
        if (str == null || str.equals("") || str.equals("0")) {
            return true;
        }
        return false;
    }

    public static boolean P(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static int Q(String str) {
        if (str == null || str.equals("") || str.equals("[]")) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            br.a(e, "JSONHelper", "str2int");
            return 0;
        }
    }

    public static float R(String str) {
        if (str == null || str.equals("") || str.equals("[]")) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            br.a(e, "JSONHelper", "str2float");
            return 0.0f;
        }
    }

    private static IndoorData d(JSONObject jSONObject, String str) throws JSONException {
        int i;
        JSONObject optJSONObject;
        String str2 = "";
        String str3 = "";
        if (jSONObject.has(str) && (optJSONObject = jSONObject.optJSONObject(str)) != null && optJSONObject.has("cpid") && optJSONObject.has("floor")) {
            str2 = a(optJSONObject, "cpid");
            i = Q(a(optJSONObject, "floor"));
            str3 = a(optJSONObject, "truefloor");
        } else {
            i = 0;
        }
        return new IndoorData(str2, i, str3);
    }

    public static void c(JSONArray jSONArray, RegeocodeAddress regeocodeAddress) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            AoiItem aoiItem = new AoiItem();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                aoiItem.setId(a(optJSONObject, "id"));
                aoiItem.setName(a(optJSONObject, ClientConstants.ALIAS.P_NAME));
                aoiItem.ai(a(optJSONObject, "adcode"));
                aoiItem.c(c(optJSONObject, RequestParameters.SUBRESOURCE_LOCATION));
                aoiItem.a(Float.valueOf(R(a(optJSONObject, "area"))));
                arrayList.add(aoiItem);
            }
        }
        regeocodeAddress.o(arrayList);
    }

    public static void a(PoiItem poiItem, JSONObject jSONObject) throws JSONException {
        List<Photo> h = h(jSONObject.optJSONObject("deep_info"));
        if (h.size() == 0) {
            h = h(jSONObject);
        }
        poiItem.j(h);
    }

    private static List<Photo> h(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        if (!jSONObject.has("photos")) {
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("photos");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            Photo photo = new Photo();
            photo.setTitle(a(optJSONObject, "title"));
            photo.setUrl(a(optJSONObject, "url"));
            arrayList.add(photo);
        }
        return arrayList;
    }

    private static PoiItemExtension e(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject;
        String str2 = "";
        String str3 = "";
        if (jSONObject.has(str) && (optJSONObject = jSONObject.optJSONObject(str)) != null) {
            str2 = a(optJSONObject, "open_time");
            str3 = a(optJSONObject, "rating");
        }
        return new PoiItemExtension(str2, str3);
    }
}
