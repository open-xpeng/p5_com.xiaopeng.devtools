package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.xiaopeng.vui.commons.b;
import com.xiaopeng.xui.vui.a;

/* loaded from: classes13.dex */
public class XSeekBar extends AppCompatSeekBar implements b, a {
    protected com.xiaopeng.xui.view.a abr;
    private SeekBar.OnSeekBarChangeListener aeu;

    public XSeekBar(Context context) {
        super(context);
        init();
    }

    public XSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.abr = com.xiaopeng.xui.view.a.b(this, attributeSet);
        init();
        c(this, attributeSet);
    }

    public XSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.abr = com.xiaopeng.xui.view.a.a(this, attributeSet, i, 0);
        init();
        c(this, attributeSet);
    }

    private void init() {
        super.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.xiaopeng.xui.widget.XSeekBar.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (XSeekBar.this.aeu != null) {
                    XSeekBar.this.aeu.onProgressChanged(seekBar, i, z);
                }
                XSeekBar.this.l(XSeekBar.this);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (XSeekBar.this.aeu != null) {
                    XSeekBar.this.aeu.onStartTrackingTouch(seekBar);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (XSeekBar.this.aeu != null) {
                    XSeekBar.this.aeu.onStopTrackingTouch(seekBar);
                }
            }
        });
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.aeu = onSeekBarChangeListener;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.abr != null) {
            this.abr.onConfigurationChanged(configuration);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.abr != null) {
            this.abr.onAttachedToWindow();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.abr != null) {
            this.abr.onDetachedFromWindow();
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        e(this, i);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        qX();
    }
}
