package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import androidx.annotation.NonNull;
import androidx.core.os.BuildCompat;

/* loaded from: classes8.dex */
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
