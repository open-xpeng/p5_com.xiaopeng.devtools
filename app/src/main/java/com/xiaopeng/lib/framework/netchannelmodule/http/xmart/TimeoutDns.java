package com.xiaopeng.lib.framework.netchannelmodule.http.xmart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alibaba.mtl.log.d.b;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a;
import com.xiaopeng.lib.framework.netchannelmodule.common.GlobalConfig;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.h;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import okhttp3.Dns;

/* loaded from: classes12.dex */
public class TimeoutDns implements Dns {
    private static final String ACCOUNT_ID = "133084";
    private static final int MEANINGFUL_MIN_TIMEOUT = 1000;
    private static final String SECRET_KEY = "2bfcc6860a1eaa5ccfc9e54c020ba66e";
    private static final String TAG = "TimeoutDns";
    private static final String XMART_HOST = "xmart.xiaopeng.com";
    private static HttpDnsService sHttpDns;
    private LookupService mLookupService;
    private static int sTimeout = a.r;
    private static int sHttpDnsTimeout = 5000;
    private static boolean sInitialized = false;

    private TimeoutDns() {
        this.mLookupService = LookupService.create();
    }

    private void checkAndInitHttpDns(Context context) {
        if (sInitialized) {
            return;
        }
        synchronized (TimeoutDns.class) {
            if (sInitialized) {
                return;
            }
            if (context == null) {
                throw new IllegalStateException("Not init GlobalConfig with applicationContext");
            }
            sHttpDns = HttpDns.getService(context, ACCOUNT_ID, SECRET_KEY);
            sHttpDns.setExpiredIPEnabled(true);
            sHttpDns.setCachedIPEnabled(true);
            sHttpDns.setLogEnabled(true);
            sHttpDns.setHTTPSRequestEnabled(true);
            sHttpDns.setPreResolveAfterNetworkChanged(true);
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(XMART_HOST);
            sHttpDns.setPreResolveHosts(arrayList);
            sHttpDns.setTimeoutInterval(sHttpDnsTimeout);
            hookHttpDns();
            sInitialized = true;
        }
    }

    private void hookHttpDns() {
        for (String str : new String[]{b.class.getName(), com.alibaba.mtl.log.a.class.getName()}) {
            try {
                Field[] declaredFields = Class.forName(str).getDeclaredFields();
                int i = 0;
                while (true) {
                    if (i < declaredFields.length) {
                        Field field = declaredFields[i];
                        if (field.getType().equals(Boolean.TYPE)) {
                            field.setAccessible(true);
                            field.set(null, true);
                        } else if (field.getType().equals(Context.class)) {
                            field.setAccessible(true);
                            field.set(null, null);
                            break;
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                c.f(TAG, "Try to disable httpdns log:" + e);
            }
        }
    }

    public static TimeoutDns getInstance() {
        return Holder.INSTANCE;
    }

    public static void timeout(int i) {
        if (i < 1000) {
            i = 1000;
        }
        sTimeout = i;
        sHttpDnsTimeout = (i * 2) / 3;
    }

    public static int timeout() {
        return sTimeout;
    }

    public List<InetAddress> lookupAsync(@NonNull String str) throws UnknownHostException {
        try {
            checkAndInitHttpDns(GlobalConfig.getApplicationContext());
            String ipByHostAsync = sHttpDns.getIpByHostAsync(str);
            c.f(TAG, "async get the ip getByHostAsync is " + ipByHostAsync);
            if (!TextUtils.isEmpty(ipByHostAsync)) {
                return Arrays.asList(InetAddress.getAllByName(ipByHostAsync));
            }
            return new ArrayList();
        } catch (Exception e) {
            c.b(TAG, "exception occurs when getIpsByHttpDns, and details: ", e);
            throw new UnknownHostException(e.toString());
        }
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(@NonNull String str) throws UnknownHostException {
        checkAndInitHttpDns(GlobalConfig.getApplicationContext());
        try {
            return Arrays.asList(this.mLookupService.getAllByName(str).get(sTimeout, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            c.f(TAG, "get ip timeout");
            InetAddress[] cacheAddress = this.mLookupService.getCacheAddress(str);
            if (cacheAddress != null && cacheAddress.length > 0) {
                return Arrays.asList(cacheAddress);
            }
            throw new UnknownHostException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class LookupService {
        private static final String IP_CACHE = "ipCache";
        ExecutorService executor = Executors.newSingleThreadExecutor();
        h sSharePrefs;

        private LookupService() {
        }

        static LookupService create() {
            return new LookupService();
        }

        Future<InetAddress[]> getAllByName(final String str) {
            FutureTask futureTask = new FutureTask(new Callable<InetAddress[]>() { // from class: com.xiaopeng.lib.framework.netchannelmodule.http.xmart.TimeoutDns.LookupService.1
                @Override // java.util.concurrent.Callable
                public InetAddress[] call() {
                    try {
                        return LookupService.this.getAddressByHttpDns(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            this.executor.execute(futureTask);
            return futureTask;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Nullable
        public InetAddress[] getAddressByHttpDns(String str) {
            String ipByHostAsync = TimeoutDns.sHttpDns.getIpByHostAsync(str);
            c.f(TimeoutDns.TAG, "the ip getByHostAsync is " + ipByHostAsync);
            if (!TextUtils.isEmpty(ipByHostAsync)) {
                try {
                    c.f(TimeoutDns.TAG, "the inetAddress getByHttpDns is " + Arrays.toString(InetAddress.getAllByName(ipByHostAsync)));
                    InetAddress[] allByName = InetAddress.getAllByName(ipByHostAsync);
                    saveValidIp(allByName, str);
                    return allByName;
                } catch (UnknownHostException e) {
                    c.b(TimeoutDns.TAG, "exception occurs when getIpsByHttpDns, and details: ", e);
                }
            }
            try {
                InetAddress[] allByName2 = InetAddress.getAllByName(str);
                c.f(TimeoutDns.TAG, "the inetAddress getByLocalDns is " + Arrays.toString(allByName2));
                saveValidIp(allByName2, str);
                return allByName2;
            } catch (UnknownHostException e2) {
                c.b(TimeoutDns.TAG, "exception occurs when getIpsByLocalDns, and details: ", e2);
                return getCacheAddress(str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public InetAddress[] getCacheAddress(String str) {
            if (this.sSharePrefs == null) {
                this.sSharePrefs = h.av(GlobalConfig.getApplicationContext());
            }
            h hVar = this.sSharePrefs;
            String string = hVar.getString(IP_CACHE + str, null);
            c.f(TimeoutDns.TAG, "get ip from cache " + string);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            try {
                return InetAddress.getAllByName(string);
            } catch (UnknownHostException e) {
                c.b(TimeoutDns.TAG, "exception occurs when get ip from cache, and details: ", e);
                return null;
            }
        }

        private void saveValidIp(InetAddress[] inetAddressArr, String str) {
            if (inetAddressArr == null || inetAddressArr.length == 0) {
                return;
            }
            if (this.sSharePrefs == null) {
                this.sSharePrefs = h.av(GlobalConfig.getApplicationContext());
            }
            for (InetAddress inetAddress : inetAddressArr) {
                if ((!inetAddress.isSiteLocalAddress() && !inetAddress.isLoopbackAddress()) || inetAddress.getHostAddress().startsWith("10.")) {
                    String hostAddress = inetAddress.getHostAddress();
                    c.f(TimeoutDns.TAG, "save valid ip " + hostAddress);
                    this.sSharePrefs.putString(IP_CACHE + str, hostAddress);
                    return;
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    private static final class Holder {
        private static final TimeoutDns INSTANCE = new TimeoutDns();

        private Holder() {
        }
    }
}
