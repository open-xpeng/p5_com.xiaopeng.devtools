package androidx.core.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.NonNull;

/* loaded from: classes8.dex */
public final class BitmapCompat {
    public static boolean hasMipMap(@NonNull Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 18) {
            return bitmap.hasMipMap();
        }
        return false;
    }

    public static void setHasMipMap(@NonNull Bitmap bitmap, boolean z) {
        if (Build.VERSION.SDK_INT >= 18) {
            bitmap.setHasMipMap(z);
        }
    }

    public static int getAllocationByteCount(@NonNull Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount();
        }
        return bitmap.getByteCount();
    }

    private BitmapCompat() {
    }
}
