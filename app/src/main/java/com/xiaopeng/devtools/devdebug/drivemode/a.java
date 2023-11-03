package com.xiaopeng.devtools.devdebug.drivemode;

import com.xiaopeng.devtools.utils.q;

/* compiled from: DriveModeModel.java */
/* loaded from: classes12.dex */
public class a {
    public void bQ(int i) {
        com.xiaopeng.lib.utils.c.f("DriveModeModel", "controlMode mode = " + i);
        if (i > 4 || i < 1) {
        }
    }

    public int getMode() {
        int i = q.lg().getInt("pref_select_drive_mode");
        if (i > 4 || i < 1) {
            return 1;
        }
        return i;
    }

    public void onDestroy() {
    }

    public static void bR(int i) {
        com.xiaopeng.lib.utils.c.f("DriveModeModel", "setModePreference mode = " + i);
        q.lg().putInt("pref_select_drive_mode", i);
    }
}
