package androidx.core.view;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.internal.view.SupportMenu;
import androidx.core.os.BuildCompat;

/* loaded from: classes8.dex */
public final class MenuCompat {
    @Deprecated
    public static void setShowAsAction(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }

    @SuppressLint({"NewApi"})
    public static void setGroupDividerEnabled(Menu menu, boolean z) {
        if (menu instanceof SupportMenu) {
            ((SupportMenu) menu).setGroupDividerEnabled(z);
        } else if (BuildCompat.isAtLeastP()) {
            menu.setGroupDividerEnabled(z);
        }
    }

    private MenuCompat() {
    }
}
