package com.xiaopeng.xui.widget.dialogview;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xui.widget.dialogview.e;

/* compiled from: XDialogView.java */
/* loaded from: classes13.dex */
public class b extends com.xiaopeng.xui.vui.b {
    private c aiw;

    public b(@NonNull Context context, int i) {
        this.aiw = c.a(this, context, i);
        m(this.aiw.qu());
    }

    public b n(@Nullable CharSequence charSequence) {
        this.aiw.setTitle(charSequence);
        return this;
    }

    public b eQ(@StringRes int i) {
        this.aiw.setTitle(i);
        return this;
    }

    public b o(@Nullable CharSequence charSequence) {
        this.aiw.setMessage(charSequence);
        return this;
    }

    public b eR(@StringRes int i) {
        this.aiw.eP(i);
        return this;
    }

    public b g(@NonNull View view, boolean z) {
        this.aiw.h(view, z);
        return this;
    }

    public b bk(boolean z) {
        this.aiw.bn(z);
        return this;
    }

    public b p(@Nullable CharSequence charSequence) {
        this.aiw.r(charSequence);
        return this;
    }

    public b a(e.a aVar) {
        this.aiw.c(aVar);
        return this;
    }

    public b q(@Nullable CharSequence charSequence) {
        this.aiw.s(charSequence);
        return this;
    }

    public b b(e.a aVar) {
        this.aiw.d(aVar);
        return this;
    }

    public b bl(boolean z) {
        this.aiw.bo(z);
        return this;
    }

    public b bm(boolean z) {
        this.aiw.ab(z);
        return this;
    }

    public void eS(int i) {
        this.aiw.eS(i);
    }

    public void eT(int i) {
        this.aiw.eT(i);
    }

    public ViewGroup qu() {
        return this.aiw.qu();
    }

    public b a(e.b bVar) {
        this.aiw.b(bVar);
        return this;
    }

    public b a(e.d dVar) {
        this.aiw.b(dVar);
        return this;
    }

    public void a(ThemeViewModel.OnCallback onCallback) {
        this.aiw.a(onCallback);
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return this.aiw.a(i, keyEvent);
    }
}
