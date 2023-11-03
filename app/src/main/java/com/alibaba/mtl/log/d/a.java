package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ApiResponseParse.java */
/* loaded from: classes11.dex */
public class a {
    public static C0014a y(String str) {
        C0014a c0014a = new C0014a();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(EcuUpdateResult.RESULT_SUCCESS)) {
                String string = jSONObject.getString(EcuUpdateResult.RESULT_SUCCESS);
                if (!TextUtils.isEmpty(string) && string.equals(EcuUpdateResult.RESULT_SUCCESS)) {
                    c0014a.cQ = true;
                }
            }
            if (jSONObject.has("ret")) {
                c0014a.cR = jSONObject.getString("ret");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return c0014a;
    }

    /* compiled from: ApiResponseParse.java */
    /* renamed from: com.alibaba.mtl.log.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0014a {
        public static C0014a cS = new C0014a();
        public boolean cQ = false;
        public String cR = null;

        public boolean g() {
            if ("E0102".equalsIgnoreCase(this.cR)) {
                return true;
            }
            return false;
        }

        public boolean aD() {
            if ("E0111".equalsIgnoreCase(this.cR) || "E0112".equalsIgnoreCase(this.cR)) {
                return true;
            }
            return false;
        }
    }
}
