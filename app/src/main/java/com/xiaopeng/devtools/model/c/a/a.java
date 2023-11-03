package com.xiaopeng.devtools.model.c.a;

import android.content.Context;
import android.os.IPowerManager;
import android.os.PowerManager;
import android.os.ServiceManager;
import android.provider.Settings;

/* compiled from: BrightnessModel.java */
/* loaded from: classes12.dex */
public class a {
    private Context mContext;
    private PowerManager so;
    private IPowerManager sp = IPowerManager.Stub.asInterface(ServiceManager.getService("power"));

    public a(Context context) {
        this.mContext = context;
        this.so = (PowerManager) this.mContext.getSystemService("power");
    }

    public int fM() {
        int maximumScreenBrightnessSetting = this.so.getMaximumScreenBrightnessSetting();
        com.xiaopeng.lib.utils.c.f("BrightnessModel", "getMaxBrightness = " + maximumScreenBrightnessSetting);
        return maximumScreenBrightnessSetting;
    }

    public int fN() {
        int minimumScreenBrightnessSetting = this.so.getMinimumScreenBrightnessSetting();
        com.xiaopeng.lib.utils.c.f("BrightnessModel", "getMinBrightness = " + minimumScreenBrightnessSetting);
        return minimumScreenBrightnessSetting;
    }

    public int fO() {
        int i;
        try {
            i = Settings.System.getInt(this.mContext.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            i = -1;
        }
        com.xiaopeng.lib.utils.c.f("BrightnessModel", "getCurrentBrightness = " + i);
        return i;
    }

    public void bZ(int i) {
        com.xiaopeng.lib.utils.c.f("BrightnessModel", "setBrightness : " + i);
        Settings.System.putInt(this.mContext.getContentResolver(), "screen_brightness", i);
    }
}
