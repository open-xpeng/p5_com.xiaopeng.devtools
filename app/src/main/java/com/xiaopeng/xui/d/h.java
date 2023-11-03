package com.xiaopeng.xui.d;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.DimenRes;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: XuiUtils.java */
/* loaded from: classes13.dex */
public class h {
    public static int g(float f) {
        return (int) ((f * com.xiaopeng.xui.a.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int e(Context context, @DimenRes int i) {
        return (int) ((context.getResources().getDimension(i) * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static List<View> a(ViewGroup viewGroup, Class cls) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (cls.isInstance(childAt)) {
                arrayList.add(childAt);
            } else if (childAt instanceof ViewGroup) {
                arrayList.addAll(a((ViewGroup) childAt, cls));
            }
        }
        return arrayList;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static String ew(int i) {
        if (i != 0) {
            if (i != 4) {
                if (i == 8) {
                    return "gone";
                }
                return null;
            }
            return "invisible";
        }
        return "visible";
    }
}
