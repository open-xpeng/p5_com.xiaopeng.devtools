package com.amap.api.services.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthConfigManager.java */
/* loaded from: classes11.dex */
public class e {
    public static int a = -1;
    public static String b = "";

    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes11.dex */
    public static class a {
        public String a;
        public int b = -1;
        public d eA;
        public c eB;
        public b eC;
        public b eD;
        public b eE;
        public b eF;
        public JSONObject en;
        public JSONObject eo;
        public JSONObject ep;
        public JSONObject eq;
        public JSONObject er;
        public JSONObject es;
        public JSONObject et;
        public JSONObject eu;
        public JSONObject ev;
        public JSONObject ew;
        public JSONObject ex;
        public JSONObject ey;
        public C0028a ez;

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.amap.api.services.a.e$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public static class C0028a {
            public boolean a;
            public boolean b;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes11.dex */
        public static class b {
            public boolean a;
            public String b;
            public String c;
            public String d;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes11.dex */
        public static class c {
            public String a;
            public String b;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes11.dex */
        public static class d {
            public String a;
            public String b;
            public String c;
        }
    }

    public static boolean b(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split("/");
            return split[split.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable th) {
            return z;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x008e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amap.api.services.a.e.a a(android.content.Context r8, com.amap.api.services.a.l r9, java.lang.String r10, java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
            Method dump skipped, instructions count: 683
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.e.a(android.content.Context, com.amap.api.services.a.l, java.lang.String, java.util.Map):com.amap.api.services.a.e$a");
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str) || jSONObject.getString(str).equals("[]")) {
            return "";
        }
        return jSONObject.optString(str);
    }

    private static void a(JSONObject jSONObject, a.b bVar) {
        if (bVar != null) {
            try {
                String a2 = a(jSONObject, "m");
                String a3 = a(jSONObject, "u");
                String a4 = a(jSONObject, "v");
                String a5 = a(jSONObject, "able");
                bVar.c = a2;
                bVar.b = a3;
                bVar.d = a4;
                bVar.a = b(a5, false);
            } catch (Throwable th) {
                o.a(th, "ConfigManager", "parsePluginEntity");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.c cVar) {
        if (jSONObject != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "url");
                cVar.b = a2;
                cVar.a = a3;
            } catch (Throwable th) {
                o.a(th, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.d dVar) {
        if (jSONObject != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "url");
                String a4 = a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4)) {
                    dVar.a = a3;
                    dVar.b = a2;
                    dVar.c = a4;
                }
            } catch (Throwable th) {
                o.a(th, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes11.dex */
    static class b extends bc {
        private String c;
        private Map<String, String> d;

        b(Context context, l lVar, String str, Map<String, String> map) {
            super(context, lVar);
            this.c = str;
            this.d = map;
        }

        @Override // com.amap.api.services.a.bh
        public Map<String, String> c() {
            return null;
        }

        @Override // com.amap.api.services.a.bh
        public String g() {
            return "https://restapi.amap.com/v3/iasdkauth";
        }

        @Override // com.amap.api.services.a.bc
        public byte[] a() {
            return null;
        }

        @Override // com.amap.api.services.a.bc
        public byte[] bi() {
            return m.a(m.a(bj()));
        }

        @Override // com.amap.api.services.a.bc
        protected String e() {
            return "3.0";
        }

        private Map<String, String> bj() {
            String w = g.w(this.a);
            if (!TextUtils.isEmpty(w)) {
                w = j.b(new StringBuilder(w).reverse().toString());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("authkey", this.c);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.eL.a());
            hashMap.put("version", this.eL.b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", Build.VERSION.SDK_INT + "");
            hashMap.put("deviceId", w);
            if (this.d != null && !this.d.isEmpty()) {
                hashMap.putAll(this.d);
            }
            String str = null;
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    ApplicationInfo applicationInfo = this.a.getApplicationInfo();
                    Field declaredField = Class.forName(ApplicationInfo.class.getName()).getDeclaredField("primaryCpuAbi");
                    declaredField.setAccessible(true);
                    str = (String) declaredField.get(applicationInfo);
                } catch (Throwable th) {
                    o.a(th, "ConfigManager", "getcpu");
                }
            }
            if (TextUtils.isEmpty(str)) {
                str = Build.CPU_ABI;
            }
            hashMap.put("abitype", str);
            hashMap.put("ext", this.eL.c());
            return hashMap;
        }
    }
}
