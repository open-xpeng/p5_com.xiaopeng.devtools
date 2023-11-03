package com.xiaopeng.xui.widget.dialogview;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.dialogview.e;

/* compiled from: XDialogViewDelegate.java */
/* loaded from: classes13.dex */
public abstract class c {
    protected b aix;
    protected Context mContext;

    public abstract void a(ThemeViewModel.OnCallback onCallback);

    public abstract boolean a(int i, KeyEvent keyEvent);

    public abstract void ab(boolean z);

    public abstract void b(e.b bVar);

    public abstract void b(e.d dVar);

    public abstract void bn(boolean z);

    public abstract void bo(boolean z);

    public abstract void c(e.a aVar);

    public abstract void d(e.a aVar);

    public abstract void eP(@StringRes int i);

    public abstract void eS(int i);

    public abstract void eT(int i);

    public abstract void h(@NonNull View view, boolean z);

    public abstract ViewGroup qu();

    public abstract void r(@Nullable CharSequence charSequence);

    public abstract void s(@Nullable CharSequence charSequence);

    public abstract void setMessage(@Nullable CharSequence charSequence);

    public abstract void setTitle(@StringRes int i);

    public abstract void setTitle(@Nullable CharSequence charSequence);

    public static c a(b bVar, @NonNull Context context, int i) {
        return new d(bVar, context, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar, @NonNull Context context, int i) {
        this.aix = bVar;
        this.mContext = new ContextThemeWrapper(context, i <= 0 ? R.style.XDialogView : i);
    }
}
