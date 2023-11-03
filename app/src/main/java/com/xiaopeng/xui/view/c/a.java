package com.xiaopeng.xui.view.c;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

/* compiled from: XTouchDelegate.java */
/* loaded from: classes13.dex */
public class a extends TouchDelegate {
    private View abw;

    public a(Rect rect, View view) {
        super(rect, view);
        this.abw = view;
    }

    public View qQ() {
        return this.abw;
    }
}
