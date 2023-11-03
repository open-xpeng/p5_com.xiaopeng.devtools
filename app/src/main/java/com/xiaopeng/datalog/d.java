package com.xiaopeng.datalog;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent;
import com.xiaopeng.lib.utils.i;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: StatEvent.java */
/* loaded from: classes11.dex */
public class d implements IStatEvent {
    private static String pY;
    private static String pZ;
    private String pX;
    private Map<String, Object> properties = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Context context) {
        this.properties.put(IStatEvent.CUSTOM_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
        this.properties.put(IStatEvent.CUSTOM_MODULE_VERSION, getVersion(context));
        this.properties.put(IStatEvent.CUSTOM_NETWORK, Z(context));
        this.properties.put(IStatEvent.CUSTOM_STARTUP, Long.valueOf(SystemClock.uptimeMillis() / 1000));
        this.properties.put(IStatEvent.CUSTOM_DEVICE_MCUVER, eR());
        this.properties.put(IStatEvent.CUSTOM_UID, Long.valueOf(i.oR()));
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent
    public String getEventName() {
        return this.pX;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent
    public void setEventName(String str) {
        this.pX = str;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent
    public String toJson() {
        this.properties.put(IStatEvent.CUSTOM_MODULE, bg(this.pX));
        this.properties.put(IStatEvent.CUSTOM_EVENT, this.pX);
        return com.xiaopeng.datalog.b.b.eX().eY().toJson(this.properties, new TypeToken<Map<String, Object>>() { // from class: com.xiaopeng.datalog.d.1
        }.getType());
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent
    public void put(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.properties.put(str, str2);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent
    public void put(String str, Number number) {
        if (str == null || number == null) {
            return;
        }
        this.properties.put(str, number);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent
    public void put(String str, Boolean bool) {
        if (str == null || bool == null) {
            return;
        }
        this.properties.put(str, bool);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent
    public void put(String str, Character ch) {
        if (str == null || ch == null) {
            return;
        }
        this.properties.put(str, ch);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String bg(String str) {
        String[] split;
        if (!TextUtils.isEmpty(str) && (split = str.split("_")) != null && split.length > 0) {
            return split[0];
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getVersion(Context context) {
        if (TextUtils.isEmpty(pZ)) {
            try {
                pZ = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                Log.w("StatEvent", "getVersion fail!", e);
                return "Unknown";
            }
        }
        return pZ;
    }

    public String toString() {
        return "StatEvent{eventName='" + this.pX + "'}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String eR() {
        if (TextUtils.isEmpty(pY)) {
            pY = SystemProperties.get("persist.sys.mcu.version");
        }
        if (TextUtils.isEmpty(pY)) {
            pY = SystemProperties.get("sys.mcu.version");
        }
        return pY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String Z(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
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
                    case 11:
                    default:
                        return "";
                    case 13:
                        return "4G";
                }
            } else if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            } else {
                return "";
            }
        }
        return "";
    }
}
