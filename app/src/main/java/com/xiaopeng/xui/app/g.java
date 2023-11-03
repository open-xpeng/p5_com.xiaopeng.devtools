package com.xiaopeng.xui.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XTextView;

/* compiled from: XToast.java */
/* loaded from: classes13.dex */
public class g {
    private static Context getApplicationContext() {
        return com.xiaopeng.xui.a.getContext();
    }

    public static void show(@StringRes int i) {
        i(com.xiaopeng.xui.a.getContext().getText(i));
    }

    public static void i(CharSequence charSequence) {
        if (l(charSequence) > 8) {
            k(charSequence);
        } else {
            j(charSequence);
        }
    }

    public static void j(CharSequence charSequence) {
        a(charSequence, 0);
    }

    public static void ee(@StringRes int i) {
        k(com.xiaopeng.xui.a.getContext().getText(i));
    }

    public static void k(CharSequence charSequence) {
        a(charSequence, 1);
    }

    private static void a(CharSequence charSequence, int i) {
        Toast ef = ef(R.layout.x_toast);
        ef.setDuration(i);
        ((XTextView) ef.getView().findViewById(R.id.textView)).setText(charSequence);
        ef.show();
    }

    private static Toast ef(@LayoutRes int i) {
        Context applicationContext = getApplicationContext();
        View inflate = LayoutInflater.from(applicationContext).inflate(i, (ViewGroup) null);
        Toast toast = new Toast(applicationContext);
        toast.setGravity(8388661, 0, 0);
        toast.setView(inflate);
        return toast;
    }

    private static int l(CharSequence charSequence) {
        String[] split;
        if (charSequence == null) {
            return 0;
        }
        int i = 0;
        for (String str : charSequence.toString().trim().split(" ")) {
            if (str.trim().length() != 0) {
                int i2 = i;
                boolean z = true;
                boolean z2 = false;
                for (int i3 = 0; i3 < str.length(); i3++) {
                    if (!com.xiaopeng.xui.d.c.a(str.charAt(i3))) {
                        z = false;
                    } else {
                        if (!z) {
                            i2++;
                        }
                        i2++;
                        z2 = true;
                        z = true;
                    }
                }
                if (z2) {
                    if (!z) {
                        i2++;
                    }
                } else {
                    i2++;
                }
                i = i2;
            }
        }
        return i;
    }
}
