package com.xiaopeng.lib.http;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.http.c.e;
import com.xiaopeng.lib.http.logging.HttpLoggingInterceptor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/* compiled from: HttpsUtils.java */
/* loaded from: classes12.dex */
public class b {
    private static boolean sIsInitialized = false;

    public static SSLSocketFactory T(Context context) {
        return e.T(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static OkHttpClient U(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor("HttpsUtils");
        httpLoggingInterceptor.a((com.xiaopeng.lib.utils.c.b.pl() || Build.TYPE.equals("userdebug")) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);
        httpLoggingInterceptor.a(Level.INFO);
        return new OkHttpClient.Builder().sslSocketFactory(e.T(context), e.aj(context)).connectionSpecs(e.getConnectionSpecs()).connectionPool(new ConnectionPool()).addInterceptor(httpLoggingInterceptor).readTimeout(30L, TimeUnit.SECONDS).hostnameVerifier(com.xiaopeng.lib.http.c.b.Vh).build();
    }

    public static synchronized boolean a(Application application, boolean z) {
        synchronized (b.class) {
            if (sIsInitialized) {
                return true;
            }
            com.xiaopeng.lib.security.b.am(application);
            if (z && !Security.et()) {
                Security.init(application);
                if (!Security.od()) {
                    com.xiaopeng.lib.utils.c.h("HttpsUtils", "!Security.isInitSuccessWithoutIndiv, return false!");
                    return sIsInitialized;
                }
            }
            Module.register(com.xiaopeng.system.delegate.module.a.class, new com.xiaopeng.system.delegate.module.a(application));
            c(application);
            if (e.aj(application) == null) {
                com.xiaopeng.lib.utils.c.h("HttpsUtils", "KeyManager init fail, return false!");
                return sIsInitialized;
            }
            sIsInitialized = true;
            return sIsInitialized;
        }
    }

    private static void c(Application application) {
        com.xiaopeng.lib.utils.c.f("HttpsUtils", "start to initOkHttpClient!!!");
        ag(application);
        com.lzy.okgo.a.cE().b(application).a(U(application));
        com.xiaopeng.lib.utils.c.f("HttpsUtils", "finish initOkHttpClient!!!");
    }

    private static void ag(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaopeng.action.CLIENT_SSL_UPDATE");
        intentFilter.setPriority(Integer.MAX_VALUE);
        context.registerReceiver(new BroadcastReceiver() { // from class: com.xiaopeng.lib.http.b.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Log.e("HttpsUtils", "ACTION_BROADCAST_CLIENT_SSL_UPDATE");
                try {
                    com.lzy.okgo.a.cE().a(b.U(context2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, intentFilter);
    }

    public static synchronized void destroy() {
        synchronized (b.class) {
            Security.destroy();
        }
    }

    public static void a(@NonNull e.a aVar) {
        e.a(aVar);
    }
}
