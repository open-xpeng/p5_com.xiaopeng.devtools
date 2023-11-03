package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.content.Context;
import android.net.NetworkInfo;
import com.xiaopeng.devtools.R;

/* compiled from: Summary.java */
/* loaded from: classes12.dex */
class d {
    static String a(Context context, String str, NetworkInfo.DetailedState detailedState) {
        String[] stringArray = context.getResources().getStringArray(str == null ? R.array.wifi_status : R.array.wifi_status_with_ssid);
        int ordinal = detailedState.ordinal();
        if (ordinal >= stringArray.length || stringArray[ordinal].length() == 0) {
            return null;
        }
        return String.format(stringArray[ordinal], str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context, NetworkInfo.DetailedState detailedState) {
        return a(context, null, detailedState);
    }
}
