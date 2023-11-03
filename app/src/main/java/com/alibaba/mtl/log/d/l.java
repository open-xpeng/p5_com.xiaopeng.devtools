package com.alibaba.mtl.log.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* compiled from: NetworkUtil.java */
/* loaded from: classes11.dex */
public class l {
    private static String[] a = {"Unknown", "Unknown"};
    private static b dh = new b();
    private static a di = new a();

    public static String aS() {
        NetworkInfo activeNetworkInfo;
        Context context = com.alibaba.mtl.log.a.getContext();
        if (context == null) {
            return "Unknown";
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0 && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 1) {
                    return "wifi";
                }
                if (activeNetworkInfo.getType() == 0) {
                    return D(activeNetworkInfo.getSubtype());
                }
                return "Unknown";
            }
            return "Unknown";
        } catch (Throwable th) {
            return "Unknown";
        }
    }

    public static boolean isConnected() {
        Context context = com.alibaba.mtl.log.a.getContext();
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        return activeNetworkInfo.isConnected();
                    }
                    return false;
                }
                return true;
            } catch (Exception e) {
                return true;
            }
        }
        return true;
    }

    private static String D(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "Unknown";
        }
    }

    public static String[] j(Context context) {
        return a;
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        context.registerReceiver(dh, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void c(Context context) {
        if (context == null || dh == null) {
            return;
        }
        context.unregisterReceiver(dh);
    }

    /* compiled from: NetworkUtil.java */
    /* loaded from: classes11.dex */
    private static class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            s.aW().b(l.di.k(context));
        }
    }

    /* compiled from: NetworkUtil.java */
    /* loaded from: classes11.dex */
    private static class a implements Runnable {
        private Context a;

        private a() {
        }

        public a k(Context context) {
            this.a = context;
            return this;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a == null) {
                return;
            }
            try {
                if (this.a.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.a.getPackageName()) != 0) {
                    l.a[0] = "Unknown";
                    return;
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
                if (connectivityManager == null) {
                    l.a[0] = "Unknown";
                    return;
                }
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    if (1 == activeNetworkInfo.getType()) {
                        l.a[0] = "Wi-Fi";
                    } else if (activeNetworkInfo.getType() == 0) {
                        l.a[0] = "2G/3G";
                        l.a[1] = activeNetworkInfo.getSubtypeName();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
