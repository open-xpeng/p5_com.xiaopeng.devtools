package com.xiaopeng.xui.view;

import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xui.view.a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: XViewDelegateImpl.java */
/* loaded from: classes13.dex */
class b extends a {
    private com.xiaopeng.xui.view.b.a abs;
    private ArrayList<com.xiaopeng.xui.view.a.b> abt = new ArrayList<>();
    private ThemeViewModel mThemeViewModel;
    private View mView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(@NonNull View view, AttributeSet attributeSet, int i, int i2, Object obj) {
        com.xiaopeng.xui.view.a.b b;
        this.mView = view;
        if (com.xiaopeng.xui.a.qo() && (view instanceof TextView) && (b = com.xiaopeng.xui.view.a.b.b((TextView) view, attributeSet, i, i2)) != null) {
            this.abt.add(b);
        }
        if (!view.isInEditMode()) {
            this.mThemeViewModel = ThemeViewModel.create(view.getContext(), attributeSet, i, i2, obj);
        }
    }

    @Override // com.xiaopeng.xui.view.a
    public void onConfigurationChanged(Configuration configuration) {
        Iterator<com.xiaopeng.xui.view.a.b> it = this.abt.iterator();
        while (it.hasNext()) {
            it.next().onConfigurationChanged(configuration);
        }
        if (this.mThemeViewModel != null) {
            this.mThemeViewModel.onConfigurationChanged(this.mView, configuration);
        }
        if (this.abs != null) {
            this.abs.onConfigurationChanged(configuration);
        }
    }

    @Override // com.xiaopeng.xui.view.a
    public void onAttachedToWindow() {
        Iterator<com.xiaopeng.xui.view.a.b> it = this.abt.iterator();
        while (it.hasNext()) {
            it.next().onAttachedToWindow();
        }
        if (this.mThemeViewModel != null) {
            this.mThemeViewModel.onAttachedToWindow(this.mView);
        }
        if (this.abs != null) {
            this.abs.onAttachedToWindow();
        }
    }

    @Override // com.xiaopeng.xui.view.a
    public void onDetachedFromWindow() {
        Iterator<com.xiaopeng.xui.view.a.b> it = this.abt.iterator();
        while (it.hasNext()) {
            it.next().onDetachedFromWindow();
        }
        if (this.mThemeViewModel != null) {
            this.mThemeViewModel.onDetachedFromWindow(this.mView);
        }
    }

    @Override // com.xiaopeng.xui.view.a
    public void onWindowFocusChanged(boolean z) {
        if (this.mThemeViewModel != null) {
            this.mThemeViewModel.onWindowFocusChanged(this.mView, z);
        }
    }

    @Override // com.xiaopeng.xui.view.a
    public void a(a.InterfaceC0085a interfaceC0085a) {
        if (interfaceC0085a != null && this.abs == null) {
            this.abs = new com.xiaopeng.xui.view.b.a(this.mView.getContext());
        }
        this.abs.b(interfaceC0085a);
    }

    @Override // com.xiaopeng.xui.view.a
    public ThemeViewModel qP() {
        return this.mThemeViewModel;
    }
}
