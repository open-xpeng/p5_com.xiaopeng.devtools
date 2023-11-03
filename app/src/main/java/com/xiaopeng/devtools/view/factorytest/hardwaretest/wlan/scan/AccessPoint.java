package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class AccessPoint extends Preference {
    private static final int[] MX = {R.attr.state_encrypted};
    private static final int[] MY = new int[0];
    boolean MZ;
    PskType Na;
    private WifiConfiguration Nb;
    ScanResult Nc;
    private WifiInfo Nd;
    private NetworkInfo.DetailedState Ne;
    String bssid;
    private int mRssi;
    int networkId;
    int security;
    String ssid;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public enum PskType {
        UNKNOWN,
        WPA,
        WPA2,
        WPA_WPA2
    }

    static int a(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        return wifiConfiguration.wepKeys[0] != null ? 1 : 0;
    }

    private static int a(ScanResult scanResult) {
        if (scanResult.capabilities.contains("WEP")) {
            return 1;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return 2;
        }
        if (scanResult.capabilities.contains("EAP")) {
            return 3;
        }
        return 0;
    }

    public String aA(boolean z) {
        Context context = getContext();
        switch (this.security) {
            case 1:
                return z ? context.getString(R.string.wifi_security_short_wep) : context.getString(R.string.wifi_security_wep);
            case 2:
                switch (this.Na) {
                    case WPA:
                        return z ? context.getString(R.string.wifi_security_short_wpa) : context.getString(R.string.wifi_security_wpa);
                    case WPA2:
                        return z ? context.getString(R.string.wifi_security_short_wpa2) : context.getString(R.string.wifi_security_wpa2);
                    case WPA_WPA2:
                        return z ? context.getString(R.string.wifi_security_short_wpa_wpa2) : context.getString(R.string.wifi_security_wpa_wpa2);
                    default:
                        return z ? context.getString(R.string.wifi_security_short_psk_generic) : context.getString(R.string.wifi_security_psk_generic);
                }
            case 3:
                return z ? context.getString(R.string.wifi_security_short_eap) : context.getString(R.string.wifi_security_eap);
            default:
                return z ? "" : context.getString(R.string.wifi_security_none);
        }
    }

    private static PskType b(ScanResult scanResult) {
        boolean contains = scanResult.capabilities.contains("WPA-PSK");
        boolean contains2 = scanResult.capabilities.contains("WPA2-PSK");
        if (contains2 && contains) {
            return PskType.WPA_WPA2;
        }
        if (contains2) {
            return PskType.WPA2;
        }
        if (contains) {
            return PskType.WPA;
        }
        Log.w("Settings.AccessPoint", "Received abnormal flag string: " + scanResult.capabilities);
        return PskType.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessPoint(Context context, WifiConfiguration wifiConfiguration) {
        super(context);
        this.MZ = false;
        this.Na = PskType.UNKNOWN;
        setWidgetLayoutResource(R.layout.preference_widget_wifi_signal);
        b(wifiConfiguration);
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessPoint(Context context, ScanResult scanResult) {
        super(context);
        this.MZ = false;
        this.Na = PskType.UNKNOWN;
        setWidgetLayoutResource(R.layout.preference_widget_wifi_signal);
        c(scanResult);
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessPoint(Context context, Bundle bundle) {
        super(context);
        this.MZ = false;
        this.Na = PskType.UNKNOWN;
        setWidgetLayoutResource(R.layout.preference_widget_wifi_signal);
        this.Nb = (WifiConfiguration) bundle.getParcelable("key_config");
        if (this.Nb != null) {
            b(this.Nb);
        }
        this.Nc = (ScanResult) bundle.getParcelable("key_scanresult");
        if (this.Nc != null) {
            c(this.Nc);
        }
        this.Nd = (WifiInfo) bundle.getParcelable("key_wifiinfo");
        if (bundle.containsKey("key_detailedstate")) {
            this.Ne = NetworkInfo.DetailedState.valueOf(bundle.getString("key_detailedstate"));
        }
        a(this.Nd, this.Ne);
    }

    public void a(Bundle bundle) {
        bundle.putParcelable("key_config", this.Nb);
        bundle.putParcelable("key_scanresult", this.Nc);
        bundle.putParcelable("key_wifiinfo", this.Nd);
        if (this.Ne != null) {
            bundle.putString("key_detailedstate", this.Ne.toString());
        }
    }

    private void b(WifiConfiguration wifiConfiguration) {
        this.ssid = wifiConfiguration.SSID == null ? "" : removeDoubleQuotes(wifiConfiguration.SSID);
        this.bssid = wifiConfiguration.BSSID;
        this.security = a(wifiConfiguration);
        this.networkId = wifiConfiguration.networkId;
        this.mRssi = Integer.MAX_VALUE;
        this.Nb = wifiConfiguration;
    }

    private void c(ScanResult scanResult) {
        this.ssid = scanResult.SSID;
        this.bssid = scanResult.BSSID;
        this.security = a(scanResult);
        this.MZ = this.security != 3 && scanResult.capabilities.contains("WPS");
        if (this.security == 2) {
            this.Na = b(scanResult);
        }
        this.networkId = -1;
        this.mRssi = scanResult.level;
        this.Nc = scanResult;
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        com.xiaopeng.lib.utils.c.f("accessPoint", "onBindView");
        TextView textView = (TextView) view.findViewById(16908310);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.topMargin = 38;
        textView.setLayoutParams(layoutParams);
        textView.setGravity(16);
        textView.setTextSize(26.0f);
        TextView textView2 = (TextView) view.findViewById(16908304);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
        layoutParams2.topMargin = 8;
        textView2.setLayoutParams(layoutParams2);
        textView2.setGravity(16);
        textView2.setTextSize(18.0f);
        ImageView imageView = (ImageView) view.findViewById(R.id.signal);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams3.topMargin = 38;
        imageView.setLayoutParams(layoutParams3);
        if (this.mRssi == Integer.MAX_VALUE) {
            imageView.setImageDrawable(null);
            return;
        }
        imageView.setImageLevel(getLevel());
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.wifi_signal});
        imageView.setImageDrawable(obtainStyledAttributes.getDrawable(0));
        imageView.setImageState(this.security != 0 ? MX : MY, true);
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(Preference preference) {
        if (preference instanceof AccessPoint) {
            AccessPoint accessPoint = (AccessPoint) preference;
            if (this.Nd == null || accessPoint.Nd != null) {
                if (this.Nd != null || accessPoint.Nd == null) {
                    if (this.mRssi == Integer.MAX_VALUE || accessPoint.mRssi != Integer.MAX_VALUE) {
                        if (this.mRssi != Integer.MAX_VALUE || accessPoint.mRssi == Integer.MAX_VALUE) {
                            if (this.networkId == -1 || accessPoint.networkId != -1) {
                                if (this.networkId != -1 || accessPoint.networkId == -1) {
                                    int compareSignalLevel = WifiManager.compareSignalLevel(accessPoint.mRssi, this.mRssi);
                                    if (compareSignalLevel != 0) {
                                        return compareSignalLevel;
                                    }
                                    return this.ssid.compareToIgnoreCase(accessPoint.ssid);
                                }
                                return 1;
                            }
                            return -1;
                        }
                        return 1;
                    }
                    return -1;
                }
                return 1;
            }
            return -1;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        return (obj instanceof AccessPoint) && compareTo((Preference) ((AccessPoint) obj)) == 0;
    }

    public int hashCode() {
        return (this.Nd != null ? 0 + (13 * this.Nd.hashCode()) : 0) + (19 * this.mRssi) + (23 * this.networkId) + (29 * this.ssid.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(ScanResult scanResult) {
        if (this.ssid.equals(scanResult.SSID) && this.security == a(scanResult)) {
            if (WifiManager.compareSignalLevel(scanResult.level, this.mRssi) > 0) {
                int level = getLevel();
                this.mRssi = scanResult.level;
                if (getLevel() != level) {
                    notifyChanged();
                }
            }
            if (this.security == 2) {
                this.Na = b(scanResult);
            }
            refresh();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(WifiInfo wifiInfo, NetworkInfo.DetailedState detailedState) {
        boolean z = true;
        if (wifiInfo != null && this.networkId != -1 && this.networkId == wifiInfo.getNetworkId()) {
            if (this.Nd != null) {
                z = false;
            }
            this.mRssi = wifiInfo.getRssi();
            this.Nd = wifiInfo;
            this.Ne = detailedState;
            refresh();
        } else if (this.Nd != null) {
            this.Nd = null;
            this.Ne = null;
            refresh();
        } else {
            z = false;
        }
        if (z) {
            notifyHierarchyChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLevel() {
        if (this.mRssi == Integer.MAX_VALUE) {
            return -1;
        }
        return WifiManager.calculateSignalLevel(this.mRssi, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WifiConfiguration getConfig() {
        return this.Nb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WifiInfo mX() {
        return this.Nd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetworkInfo.DetailedState mY() {
        return this.Ne;
    }

    static String removeDoubleQuotes(String str) {
        int length = str.length();
        if (length > 1 && str.charAt(0) == '\"') {
            int i = length - 1;
            if (str.charAt(i) == '\"') {
                return str.substring(1, i);
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String dl(String str) {
        return "\"" + str + "\"";
    }

    private void refresh() {
        String string;
        setTitle(this.ssid);
        Context context = getContext();
        if (this.Nb == null || this.Nb.status != 1) {
            if (this.mRssi == Integer.MAX_VALUE) {
                setSummary(context.getString(R.string.wifi_not_in_range));
            } else if (this.Ne != null) {
                setSummary(d.a(context, this.Ne));
            } else {
                StringBuilder sb = new StringBuilder();
                if (this.Nb != null) {
                    sb.append(context.getString(R.string.wifi_remembered));
                }
                if (this.security != 0) {
                    if (sb.length() == 0) {
                        string = context.getString(R.string.wifi_secured_first_item);
                    } else {
                        string = context.getString(R.string.wifi_secured_second_item);
                    }
                    sb.append(String.format(string, aA(true)));
                }
                if (this.Nb == null && this.MZ) {
                    if (sb.length() == 0) {
                        sb.append(context.getString(R.string.wifi_wps_available_first_item));
                    } else {
                        sb.append(context.getString(R.string.wifi_wps_available_second_item));
                    }
                }
                setSummary(sb.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void mZ() {
        if (this.security != 0) {
            throw new IllegalStateException();
        }
        if (this.Nb != null) {
            return;
        }
        this.Nb = new WifiConfiguration();
        this.Nb.SSID = dl(this.ssid);
        this.Nb.allowedKeyManagement.set(0);
    }
}
