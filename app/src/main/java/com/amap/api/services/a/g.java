package com.amap.api.services.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaopeng.lib.apirouter.ClientConstants;
import java.io.File;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: DeviceInfo.java */
/* loaded from: classes11.dex */
public class g {
    private static String a = "";
    private static boolean b = false;
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static String f = "";

    public static String a(Context context) {
        try {
            return A(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return D(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int n(Context context) {
        try {
            return E(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static int o(Context context) {
        try {
            return B(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String e(Context context) {
        try {
            return z(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static void a() {
        try {
            if (Build.VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", Integer.TYPE).invoke(null, 40964);
            }
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "setTraficTag");
        }
    }

    /* compiled from: DeviceInfo.java */
    /* loaded from: classes11.dex */
    static class a extends DefaultHandler {
        a() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue(ClientConstants.ALIAS.P_NAME))) {
                boolean unused = g.b = true;
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (g.b) {
                String unused = g.a = new String(cArr, i, i2);
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            boolean unused = g.b = false;
        }
    }

    public static String f(Context context) {
        try {
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getUTDID");
        }
        if (a != null && !"".equals(a)) {
            return a;
        }
        if (c(context, "android.permission.WRITE_SETTINGS")) {
            a = Settings.System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
        }
        if (a != null && !"".equals(a)) {
            return a;
        }
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(absolutePath + "/.UTSystemConfig/Global/Alvin2.xml");
                if (file.exists()) {
                    SAXParserFactory.newInstance().newSAXParser().parse(file, new a());
                }
            }
        } catch (Throwable th2) {
            o.a(th2, "DeviceInfo", "getUTDID");
        }
        return a == null ? "" : a;
    }

    private static boolean c(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g(Context context) {
        WifiManager wifiManager;
        if (context != null) {
            try {
                if (!c(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null || !wifiManager.isWifiEnabled()) {
                    return "";
                }
                return wifiManager.getConnectionInfo().getBSSID();
            } catch (Throwable th) {
                o.a(th, "DeviceInfo", "getWifiMacs");
                return "";
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String h(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            try {
            } catch (Throwable th) {
                o.a(th, "DeviceInfo", "getWifiMacs");
            }
            if (c(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                if (wifiManager.isWifiEnabled()) {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() != 0) {
                        List<ScanResult> g = g(scanResults);
                        boolean z = true;
                        for (int i = 0; i < g.size() && i < 7; i++) {
                            ScanResult scanResult = g.get(i);
                            if (!z) {
                                sb.append(";");
                            } else {
                                z = false;
                            }
                            sb.append(scanResult.BSSID);
                        }
                    }
                    return sb.toString();
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public static String i(Context context) {
        try {
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getDeviceMac");
        }
        if (c != null && !"".equals(c)) {
            return c;
        }
        if (!c(context, "android.permission.ACCESS_WIFI_STATE")) {
            return c;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        c = wifiManager.getConnectionInfo().getMacAddress();
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] p(Context context) {
        try {
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "cellInfo");
        }
        if (c(context, "android.permission.READ_PHONE_STATE") && c(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return new String[]{"", ""};
            }
            CellLocation cellLocation = telephonyManager.getCellLocation();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                int cid = gsmCellLocation.getCid();
                int lac = gsmCellLocation.getLac();
                return new String[]{lac + "||" + cid, "gsm"};
            } else if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                int systemId = cdmaCellLocation.getSystemId();
                int networkId = cdmaCellLocation.getNetworkId();
                int baseStationId = cdmaCellLocation.getBaseStationId();
                if (systemId >= 0) {
                }
                return new String[]{systemId + "||" + networkId + "||" + baseStationId, "cdma"};
            } else {
                return new String[]{"", ""};
            }
        }
        return new String[]{"", ""};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String q(Context context) {
        TelephonyManager F;
        try {
            if (c(context, "android.permission.READ_PHONE_STATE") && (F = F(context)) != null) {
                String networkOperator = F.getNetworkOperator();
                if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                    return networkOperator.substring(3);
                }
                return "";
            }
            return "";
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getMNC");
            return "";
        }
    }

    public static int r(Context context) {
        try {
            return E(context);
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getNetWorkType");
            return -1;
        }
    }

    public static int s(Context context) {
        try {
            return B(context);
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getActiveNetWorkType");
            return -1;
        }
    }

    public static NetworkInfo t(Context context) {
        ConnectivityManager C;
        if (c(context, "android.permission.ACCESS_NETWORK_STATE") && (C = C(context)) != null) {
            return C.getActiveNetworkInfo();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String u(Context context) {
        try {
            NetworkInfo t = t(context);
            if (t == null) {
                return null;
            }
            return t.getExtraInfo();
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getNetworkExtraInfo");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String v(Context context) {
        StringBuilder sb;
        try {
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getReslution");
        }
        if (d != null && !"".equals(d)) {
            return d;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            sb = new StringBuilder();
            sb.append(i);
            sb.append("*");
            sb.append(i2);
        } else {
            sb = new StringBuilder();
            sb.append(i2);
            sb.append("*");
            sb.append(i);
        }
        d = sb.toString();
        return d;
    }

    public static String w(Context context) {
        try {
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getDeviceID");
        }
        if (e != null && !"".equals(e)) {
            return e;
        }
        if (!c(context, "android.permission.READ_PHONE_STATE")) {
            return e;
        }
        TelephonyManager F = F(context);
        if (F == null) {
            return "";
        }
        e = F.getDeviceId();
        if (e == null) {
            e = "";
        }
        return e;
    }

    public static String x(Context context) {
        try {
            return z(context);
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getSubscriberId");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String y(Context context) {
        try {
            return A(context);
        } catch (Throwable th) {
            o.a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    private static String z(Context context) {
        if (f != null && !"".equals(f)) {
            return f;
        }
        if (!c(context, "android.permission.READ_PHONE_STATE")) {
            return f;
        }
        TelephonyManager F = F(context);
        if (F == null) {
            return "";
        }
        f = F.getSubscriberId();
        if (f == null) {
            f = "";
        }
        return f;
    }

    private static String A(Context context) {
        if (!c(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        TelephonyManager F = F(context);
        if (F == null) {
            return "";
        }
        String simOperatorName = F.getSimOperatorName();
        if (TextUtils.isEmpty(simOperatorName)) {
            return F.getNetworkOperatorName();
        }
        return simOperatorName;
    }

    private static int B(Context context) {
        ConnectivityManager C;
        NetworkInfo activeNetworkInfo;
        if (context == null || !c(context, "android.permission.ACCESS_NETWORK_STATE") || (C = C(context)) == null || (activeNetworkInfo = C.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    private static ConnectivityManager C(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static String D(Context context) {
        String x = x(context);
        if (x == null || x.length() < 5) {
            return "";
        }
        return x.substring(3, 5);
    }

    private static int E(Context context) {
        TelephonyManager F;
        if (c(context, "android.permission.READ_PHONE_STATE") && (F = F(context)) != null) {
            return F.getNetworkType();
        }
        return -1;
    }

    private static TelephonyManager F(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static List<ScanResult> g(List<ScanResult> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int i2 = 1; i2 < size - i; i2++) {
                int i3 = i2 - 1;
                if (list.get(i3).level > list.get(i2).level) {
                    list.set(i3, list.get(i2));
                    list.set(i2, list.get(i3));
                }
            }
        }
        return list;
    }
}
