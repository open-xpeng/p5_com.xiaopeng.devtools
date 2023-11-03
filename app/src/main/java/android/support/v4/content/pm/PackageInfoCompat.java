package android.support.v4.content.pm;

import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;
import android.support.v4.os.BuildCompat;

/* loaded from: classes7.dex */
public final class PackageInfoCompat {
    public static long getLongVersionCode(@NonNull PackageInfo packageInfo) {
        if (BuildCompat.isAtLeastP()) {
            return packageInfo.getLongVersionCode();
        }
        return packageInfo.versionCode;
    }

    private PackageInfoCompat() {
    }
}
