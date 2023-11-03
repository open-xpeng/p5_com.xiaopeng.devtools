package com.xiaopeng.xui.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.dialogview.e;

/* loaded from: classes13.dex */
public abstract class XDialogFragment extends DialogFragment {
    protected com.xiaopeng.xui.widget.dialogview.b ZN;

    protected abstract com.xiaopeng.xui.widget.dialogview.b ax(Context context);

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        com.xiaopeng.xui.widget.dialogview.b ax = ax(getContext());
        if (ax != null) {
            this.ZN = ax;
            this.ZN.a(new e.d() { // from class: com.xiaopeng.xui.app.-$$Lambda$XDialogFragment$dRsz5-BGceKsoyNYuNbQEqV4450
                @Override // com.xiaopeng.xui.widget.dialogview.e.d
                public final void onDismiss(com.xiaopeng.xui.widget.dialogview.b bVar) {
                    XDialogFragment.this.c(bVar);
                }
            });
            if (isFullScreen()) {
                com.xiaopeng.xui.d.d.a(getDialog());
            }
            return this.ZN.qu();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(com.xiaopeng.xui.widget.dialogview.b bVar) {
        dismiss();
    }

    protected boolean isFullScreen() {
        return com.xiaopeng.xui.a.qp();
    }

    @Override // androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.XAppTheme_XDialog;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getDialog() != null && getDialog().getWindow() != null) {
            com.xiaopeng.xui.c.a.setWindowBackgroundResource(configuration, getDialog().getWindow(), R.drawable.x_bg_dialog);
        }
    }
}
