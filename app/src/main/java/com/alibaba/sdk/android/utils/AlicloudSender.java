package com.alibaba.sdk.android.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.sdk.android.man.util.MANConfig;
import com.alibaba.sdk.android.tbrest.SendService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class AlicloudSender {
    private static SendService a;

    /* renamed from: a  reason: collision with other field name */
    private static final String f100a = null;

    /* renamed from: a  reason: collision with other field name */
    private static AtomicBoolean f101a = new AtomicBoolean(false);
    private static Map<String, SdkInfo> b;

    /* renamed from: b  reason: collision with other field name */
    private static ExecutorService f102b;
    private static Map<String, a> c;

    public static void asyncSend(Application application, SdkInfo sdkInfo) {
        String b2 = sdkInfo.b();
        if (application == null || TextUtils.isEmpty(b2)) {
            return;
        }
        a(application, sdkInfo);
        b.put(b2, sdkInfo);
        a(application.getApplicationContext(), sdkInfo);
    }

    private static void a(Application application, SdkInfo sdkInfo) {
        if (f101a.compareAndSet(false, true)) {
            b = new ConcurrentHashMap();
            c = m61a(application.getApplicationContext());
            a = new SendService();
            a.openHttp = true;
            a.init(application.getApplicationContext(), "24527540@android", "24527540", a(application.getApplicationContext()), (String) null, (String) null);
            a.appSecret = "56fc10fbe8c6ae7d0d895f49c4fb6838";
            f102b = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new SynchronousQueue());
            if (Build.VERSION.SDK_INT >= 14) {
                application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.alibaba.sdk.android.utils.AlicloudSender.1
                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityCreated(Activity activity, Bundle bundle) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStarted(Activity activity) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityResumed(Activity activity) {
                        if (!AlicloudSender.b.isEmpty()) {
                            for (SdkInfo sdkInfo2 : AlicloudSender.b.values()) {
                                AlicloudSender.a(activity, sdkInfo2);
                            }
                        }
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityPaused(Activity activity) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStopped(Activity activity) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityDestroyed(Activity activity) {
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final Context context, final SdkInfo sdkInfo) {
        f102b.execute(new Runnable() { // from class: com.alibaba.sdk.android.utils.AlicloudSender.2
            @Override // java.lang.Runnable
            public void run() {
                AlicloudSender.b(context, sdkInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void b(Context context, SdkInfo sdkInfo) {
        synchronized (AlicloudSender.class) {
            String format = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
            a aVar = c.get(sdkInfo.b());
            if (aVar == null) {
                aVar = new a();
                c.put(sdkInfo.b(), aVar);
            } else if (TextUtils.equals(format, aVar.b) && aVar.a == 0) {
                return;
            }
            aVar.b = format;
            aVar.a = -1;
            HashMap hashMap = new HashMap();
            hashMap.put("sdkId", sdkInfo.c);
            hashMap.put("packageName", context.getPackageName());
            hashMap.put("sdkVersion", sdkInfo.d);
            hashMap.put("kVersion", "2.0.0");
            if (!TextUtils.isEmpty(sdkInfo.e)) {
                hashMap.put("appKey", sdkInfo.e);
            }
            if (sdkInfo.g != null) {
                hashMap.putAll(sdkInfo.g);
            }
            hashMap.put("_aliyun_biz_id", "emas-active");
            SendService sendService = a;
            long currentTimeMillis = System.currentTimeMillis();
            String str = f100a;
            StringBuilder sb = new StringBuilder();
            sb.append(sdkInfo.c);
            sb.append("_biz_active");
            aVar.a = sendService.sendRequest("adash-emas.cn-hangzhou.aliyuncs.com", currentTimeMillis, str, (int) MANConfig.GENERIC_EVENT_ID, sb.toString(), (Object) null, (Object) null, hashMap).booleanValue() ? 0 : -1;
            a(context, c);
        }
    }

    private static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    private static Map<String, a> m61a(Context context) {
        HashMap hashMap = new HashMap();
        String string = context.getSharedPreferences("emas_info", 0).getString("emas_sdk_info", "");
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String string2 = jSONObject.getString("id");
                        a aVar = new a();
                        aVar.b = jSONObject.getString("time");
                        aVar.a = jSONObject.getInt("statu");
                        hashMap.put(string2, aVar);
                    }
                }
            } catch (Exception e) {
            }
        }
        return hashMap;
    }

    private static void a(Context context, Map<String, a> map) {
        if (map == null || map.isEmpty()) {
            context.getSharedPreferences("emas_info", 0).edit().remove("emas_sdk_info").apply();
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (String str : map.keySet()) {
            a aVar = map.get(str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", str);
                jSONObject.put("time", aVar.b);
                jSONObject.put("statu", aVar.a);
                jSONArray.put(jSONObject);
            } catch (Exception e) {
            }
        }
        context.getSharedPreferences("emas_info", 0).edit().putString("emas_sdk_info", jSONArray.toString()).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class a {
        private int a;
        private String b;

        private a() {
            this.a = -1;
            this.b = "";
        }
    }
}
