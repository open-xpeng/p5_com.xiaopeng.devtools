package com.alibaba.sdk.android.beacon;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.beacon.Beacon;
import com.ta.utdid2.device.UTDevice;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class b {
    private static final String a;
    private static final String b;

    /* renamed from: a  reason: collision with other field name */
    private final Beacon f15a;
    private final List<Beacon.Config> c = new ArrayList();

    /* renamed from: a  reason: collision with other field name */
    private final a f16a = new a();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public final class a {
        private a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:54:0x00c6 A[Catch: IOException -> 0x00c2, TRY_LEAVE, TryCatch #5 {IOException -> 0x00c2, blocks: (B:50:0x00be, B:54:0x00c6), top: B:58:0x00be }] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00be A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        java.lang.String a(java.lang.String r7, byte[] r8) {
            /*
                r6 = this;
                r0 = 0
                java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                r1.<init>(r7)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                java.net.URLConnection r7 = r1.openConnection()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                r1 = 10000(0x2710, float:1.4013E-41)
                r7.setReadTimeout(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                r7.setConnectTimeout(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                java.lang.String r1 = "POST"
                r7.setRequestMethod(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                r1 = 1
                r7.setDoOutput(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                r7.setDoInput(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                r1 = 0
                r7.setUseCaches(r1)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                boolean r1 = com.alibaba.sdk.android.beacon.a.a     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                if (r1 == 0) goto L2f
                java.lang.String r1 = "Host"
                java.lang.String r2 = "beacon-api.aliyuncs.com"
                r7.setRequestProperty(r1, r2)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            L2f:
                java.io.OutputStream r1 = r7.getOutputStream()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
                r1.write(r8)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                r1.flush()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                int r8 = r7.getResponseCode()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                boolean r2 = r6.a(r8)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                if (r2 == 0) goto L48
                java.io.InputStream r7 = r7.getInputStream()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                goto L4c
            L48:
                java.io.InputStream r7 = r7.getErrorStream()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            L4c:
                java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                java.lang.String r5 = "UTF-8"
                r4.<init>(r7, r5)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
                r7.<init>()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            L5d:
                java.lang.String r0 = r3.readLine()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
                if (r0 == 0) goto L67
                r7.append(r0)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
                goto L5d
            L67:
                if (r2 != 0) goto L76
                com.alibaba.sdk.android.beacon.b r0 = com.alibaba.sdk.android.beacon.b.this     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
                java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
                java.lang.String r2 = r7.toString()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
                com.alibaba.sdk.android.beacon.b.a(r0, r8, r2)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
            L76:
                java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L88
                if (r1 == 0) goto L82
                r1.close()     // Catch: java.io.IOException -> L80
                goto L82
            L80:
                r8 = move-exception
                return r7
            L82:
                r3.close()     // Catch: java.io.IOException -> L80
                return r7
            L86:
                r7 = move-exception
                goto L8c
            L88:
                r7 = move-exception
                goto L90
            L8a:
                r7 = move-exception
                r3 = r0
            L8c:
                r0 = r1
                goto Lbc
            L8e:
                r7 = move-exception
                r3 = r0
            L90:
                r0 = r1
                goto L97
            L92:
                r7 = move-exception
                r3 = r0
                goto Lbc
            L95:
                r7 = move-exception
                r3 = r0
            L97:
                java.lang.String r8 = "beacon"
                java.lang.String r1 = r7.getMessage()     // Catch: java.lang.Throwable -> Lbb
                android.util.Log.i(r8, r1, r7)     // Catch: java.lang.Throwable -> Lbb
                com.alibaba.sdk.android.beacon.b r8 = com.alibaba.sdk.android.beacon.b.this     // Catch: java.lang.Throwable -> Lbb
                java.lang.String r1 = "-100"
                java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> Lbb
                com.alibaba.sdk.android.beacon.b.a(r8, r1, r7)     // Catch: java.lang.Throwable -> Lbb
                if (r0 == 0) goto Lb3
                r0.close()     // Catch: java.io.IOException -> Lb1
                goto Lb3
            Lb1:
                r7 = move-exception
                goto Lb8
            Lb3:
                if (r3 == 0) goto Lb8
                r3.close()     // Catch: java.io.IOException -> Lb1
            Lb8:
                java.lang.String r7 = ""
                return r7
            Lbb:
                r7 = move-exception
            Lbc:
                if (r0 == 0) goto Lc4
                r0.close()     // Catch: java.io.IOException -> Lc2
                goto Lc4
            Lc2:
                r8 = move-exception
                goto Lc9
            Lc4:
                if (r3 == 0) goto Lc9
                r3.close()     // Catch: java.io.IOException -> Lc2
            Lc9:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.beacon.b.a.a(java.lang.String, byte[]):java.lang.String");
        }

        boolean a(int i) {
            return i >= 200 && i < 300;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.alibaba.sdk.android.beacon.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0016b {
        final Map<String, String> a;
        final String c;
        final String d;
        final String e;
        final String f;
        final String g;
        final String h;
        final String i;
        final String mAppKey;
        final Map<String, String> mExtras;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.alibaba.sdk.android.beacon.b$b$a */
        /* loaded from: classes11.dex */
        public static final class a {
            Map<String, String> b = new HashMap();
            String j;
            String k;
            String l;
            String m;
            String n;
            String o;
            String p;

            a() {
            }

            a a(String str) {
                this.j = str;
                return this;
            }

            a a(Map<String, String> map) {
                this.b.putAll(map);
                return this;
            }

            public C0016b a() {
                return new C0016b(this);
            }

            a b(String str) {
                this.k = str;
                return this;
            }

            a c(String str) {
                this.l = str;
                return this;
            }

            a d(String str) {
                this.m = str;
                return this;
            }

            a e(String str) {
                this.n = str;
                return this;
            }

            a f(String str) {
                this.o = str;
                return this;
            }

            a g(String str) {
                this.p = str;
                return this;
            }
        }

        private C0016b(a aVar) {
            this.a = new TreeMap();
            this.mAppKey = aVar.j;
            this.c = aVar.k;
            this.d = aVar.l;
            this.e = aVar.m;
            this.f = aVar.n;
            this.g = aVar.o;
            this.h = aVar.p;
            this.mExtras = aVar.b;
            this.i = a();
        }

        private String a() {
            this.a.put("appKey", this.mAppKey);
            this.a.put("appVer", this.d);
            this.a.put("osType", this.e);
            this.a.put("osVer", this.f);
            this.a.put("deviceId", this.g);
            this.a.put("beaconVer", this.h);
            for (String str : this.mExtras.keySet()) {
                this.a.put(str, this.mExtras.get(str));
            }
            StringBuilder sb = new StringBuilder();
            for (String str2 : this.a.keySet()) {
                sb.append(str2);
                sb.append(this.a.get(str2));
            }
            String a2 = c.a(this.c, sb.toString());
            this.a.put("sign", a2);
            return a2;
        }
    }

    static {
        a = com.alibaba.sdk.android.beacon.a.a ? "100.67.64.54" : "beacon-api.aliyuncs.com";
        b = "https://" + a + "/beacon/fetch/config";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Beacon beacon) {
        this.f15a = beacon;
    }

    private C0016b a(Context context, String str, String str2, Map<String, String> map) {
        return new C0016b.a().a(str).b(str2).c(c.a(context)).d("Android").e(String.valueOf(Build.VERSION.SDK_INT)).f(UTDevice.getUtdid(context)).g("1.0.7").a(map).a();
    }

    private String a(C0016b c0016b) {
        Map<String, String> map = c0016b.a;
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            sb.append(encode(str));
            sb.append("=");
            sb.append(encode(map.get(str)));
            sb.append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private void a(String str) {
        b(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        this.f15a.a(new Beacon.Error(str, str2));
    }

    private void b(String str) {
        JSONArray optJSONArray;
        try {
            if (TextUtils.isEmpty(str) || (optJSONArray = new JSONObject(str).optJSONArray("result")) == null || optJSONArray.length() <= 0) {
                return;
            }
            this.c.clear();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) optJSONArray.get(i);
                this.c.add(new Beacon.Config(jSONObject.optString("key"), jSONObject.optString("value")));
            }
        } catch (Exception e) {
        }
    }

    private String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Beacon.Config> a() {
        return Collections.unmodifiableList(this.c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public void m18a(Context context, String str, String str2, Map<String, String> map) {
        C0016b a2 = a(context, str, str2, map);
        String str3 = b + "/byappkey";
        Log.i("beacon", "url=" + str3);
        String a3 = this.f16a.a(str3, a(a2).getBytes());
        Log.i("beacon", "[fetchByAppKey] result: " + a3);
        a(a3);
    }
}
