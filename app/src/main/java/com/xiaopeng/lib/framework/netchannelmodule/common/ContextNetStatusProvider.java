package com.xiaopeng.lib.framework.netchannelmodule.common;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.pso.PsoCrypto;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.e;

/* loaded from: classes12.dex */
public class ContextNetStatusProvider extends ContentProvider {
    public static final String TAG = "NetChannel-MqttChannel";
    private static Context mContext;
    private static MyPhoneStateListener mPhoneStateListener;

    public static Context getApplicationContext() {
        return mContext;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        if (context != null) {
            mContext = context.getApplicationContext();
            if (mPhoneStateListener == null) {
                mPhoneStateListener = new MyPhoneStateListener();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    telephonyManager.listen(mPhoneStateListener, 256);
                }
            }
        }
        SharePrefsANRFix.fix();
        return true;
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class MyPhoneStateListener extends PhoneStateListener {
        public int signalStrengthValue;

        private MyPhoneStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (signalStrength.isGsm()) {
                if (signalStrength.getGsmSignalStrength() != 99) {
                    this.signalStrengthValue = (signalStrength.getGsmSignalStrength() * 2) + PsoCrypto.Desc.SaveAesCbcKeyFailed;
                    return;
                } else {
                    this.signalStrengthValue = signalStrength.getGsmSignalStrength();
                    return;
                }
            }
            this.signalStrengthValue = signalStrength.getCdmaDbm();
        }
    }

    private static int getWifiRssi(Context context) {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
            return connectionInfo.getRssi();
        }
        return 0;
    }

    public static int getNetStrength(int i) {
        if (mContext == null) {
            return 0;
        }
        if (i == 10) {
            int wifiRssi = getWifiRssi(mContext);
            c.f(TAG, "current net type is wifi, rssi is " + wifiRssi);
            return wifiRssi;
        } else if (mPhoneStateListener != null) {
            int i2 = mPhoneStateListener.signalStrengthValue;
            c.f(TAG, "current net type is mobile, dbm is " + i2);
            return i2;
        } else {
            c.f(TAG, "not init or not wifi, signal strength is 0");
            return 0;
        }
    }

    public static int getNetType() {
        if (mContext != null) {
            return e.as(mContext);
        }
        return 0;
    }

    public static int getNetStrength() {
        return getNetStrength(getNetType());
    }
}
