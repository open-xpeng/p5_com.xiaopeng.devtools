package com.alibaba.sdk.android.man.crashreporter.e;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/* loaded from: classes11.dex */
public class a {
    public static final String k() {
        try {
            int nextInt = new Random().nextInt();
            int nextInt2 = new Random().nextInt();
            byte[] d = c.d((int) (System.currentTimeMillis() / 1000));
            byte[] d2 = c.d((int) System.nanoTime());
            byte[] d3 = c.d(nextInt);
            byte[] d4 = c.d(nextInt2);
            byte[] bArr = new byte[16];
            System.arraycopy(d, 0, bArr, 0, 4);
            System.arraycopy(d2, 0, bArr, 4, 4);
            System.arraycopy(d3, 0, bArr, 8, 4);
            System.arraycopy(d4, 0, bArr, 12, 4);
            return b.a(bArr, 2);
        } catch (IOException e) {
            return "";
        }
    }

    public static String e(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Exception e) {
            }
        }
        if (i.b(str)) {
            return k();
        }
        return str;
    }

    public static String f(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Exception e) {
            }
        }
        if (i.b(str)) {
            return k();
        }
        return str;
    }

    public static String a(Context context) {
        if (context != null) {
            try {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null) {
                    for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                        if (runningAppProcessInfo.pid == myPid) {
                            return runningAppProcessInfo.processName;
                        }
                    }
                    return null;
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static int a() {
        try {
            return Build.VERSION.class.getField("SDK_INT").getInt(null);
        } catch (IllegalAccessException e) {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (IllegalArgumentException e2) {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (NoSuchFieldException e3) {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (SecurityException e4) {
            return Integer.parseInt(Build.VERSION.SDK);
        }
    }

    public static String g(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            if (packageManager != null && packageName != null) {
                return packageManager.getApplicationLabel(packageManager.getPackageInfo(packageName, 1).applicationInfo).toString();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean d(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public static String[] m52a(Context context) {
        ConnectivityManager connectivityManager;
        String[] strArr = {"Unknown", "Unknown"};
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0 && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "Wi-Fi";
                    return strArr;
                }
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
            }
        } catch (Exception e) {
        }
        return strArr;
    }

    /* renamed from: e  reason: collision with other method in class */
    public static boolean m53e(Context context) {
        return m52a(context)[0].equals("Wi-Fi");
    }

    public static String h(Context context) {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                String macAddress = connectionInfo.getMacAddress();
                if (i.b((CharSequence) macAddress)) {
                    return macAddress;
                }
                return "00-00-00-00-00-00";
            }
            return "00-00-00-00-00-00";
        } catch (Exception e) {
            return "00-00-00-00-00-00";
        }
    }

    public static String c(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                int ipAddress = connectionInfo.getIpAddress();
                return (ipAddress & 255) + "" + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
            }
            return "127.0.0.1";
        } catch (Exception e) {
            return "127.0.0.1";
        }
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            if (i > i2) {
                int i3 = i ^ i2;
                i2 ^= i3;
                i = i3 ^ i2;
            }
            return String.format("%s*%s", Integer.valueOf(i2), Integer.valueOf(i));
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static String f() {
        String str;
        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                str = bufferedReader.readLine();
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e) {
                }
            } catch (IOException e2) {
                str = null;
            }
        } catch (FileNotFoundException e3) {
            str = null;
        }
        if (str != null) {
            return str.substring(str.indexOf(58) + 1).trim();
        }
        return "";
    }
}
