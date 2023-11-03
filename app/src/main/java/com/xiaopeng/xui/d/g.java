package com.xiaopeng.xui.d;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/* compiled from: XTouchAreaUtils.java */
/* loaded from: classes13.dex */
public class g {
    public static final Class[] abq = {TextView.class, ImageView.class};

    public static void a(View view, ViewGroup viewGroup, int[] iArr) {
        if (view == null) {
            return;
        }
        if (iArr == null) {
            iArr = new int[]{20, 20, 20, 20};
        }
        com.xiaopeng.xui.view.c.c.a(view, viewGroup, iArr[0], iArr[1], iArr[2], iArr[3]);
    }

    public static void a(ViewGroup viewGroup) {
        a(abq, viewGroup);
    }

    public static void a(Class<?>[] clsArr, ViewGroup viewGroup) {
        for (Class<?> cls : clsArr) {
            List<View> a = h.a(viewGroup, cls);
            if (a.size() > 0) {
                View[] viewArr = new View[a.size()];
                a.toArray(viewArr);
                a(viewArr, viewGroup);
            }
        }
    }

    public static void a(View[] viewArr, ViewGroup viewGroup) {
        for (View view : viewArr) {
            a(view, viewGroup);
        }
    }

    public static void a(View view, ViewGroup viewGroup) {
        if (view == null || viewGroup == null) {
            return;
        }
        com.xiaopeng.xui.view.c.c.a(view, viewGroup);
    }
}
