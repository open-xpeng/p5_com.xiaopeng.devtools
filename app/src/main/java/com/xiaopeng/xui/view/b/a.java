package com.xiaopeng.xui.view.b;

import android.content.Context;
import android.content.res.Configuration;
import com.xiaopeng.xui.view.a;

/* compiled from: XFontChangeMonitor.java */
/* loaded from: classes13.dex */
public class a {
    private a.InterfaceC0085a abv;
    private Context mContext;
    private float mFontScale;

    public a(Context context) {
        this.mContext = context;
        this.mFontScale = context.getResources().getConfiguration().fontScale;
    }

    public void b(a.InterfaceC0085a interfaceC0085a) {
        this.abv = interfaceC0085a;
    }

    public void onConfigurationChanged(Configuration configuration) {
        b(configuration);
    }

    private void b(Configuration configuration) {
        if (this.mFontScale != configuration.fontScale) {
            this.mFontScale = configuration.fontScale;
            if (this.abv != null) {
                this.abv.onFontScaleChanged();
            }
        }
    }

    public void onAttachedToWindow() {
        b(this.mContext.getResources().getConfiguration());
    }
}
