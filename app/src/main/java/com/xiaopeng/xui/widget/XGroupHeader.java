package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.view.a;
import com.xiaopeng.xui.view.b.b;

/* loaded from: classes13.dex */
public class XGroupHeader extends XLinearLayout {
    private TextView adh;
    private View adi;
    private int adj;

    public XGroupHeader(Context context) {
        this(context, null);
    }

    public XGroupHeader(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XGroupHeader(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XGroupHeader(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XGroupHeader);
        if (obtainStyledAttributes.getInt(R.styleable.XGroupHeader_group_header_action_location, 0) == 1) {
            LayoutInflater.from(context).inflate(R.layout.x_groupheader_right, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.x_groupheader, this);
        }
        nN();
        setText(obtainStyledAttributes.getString(R.styleable.XGroupHeader_group_header_text));
        this.adj = obtainStyledAttributes.getInt(R.styleable.XGroupHeader_group_header_action_mode, 0);
        setView(obtainStyledAttributes.getResourceId(R.styleable.XGroupHeader_group_header_action, -1));
        this.adh.setTextSize(0, obtainStyledAttributes.getDimensionPixelSize(R.styleable.XGroupHeader_group_header_text_size, 0));
        final b a = b.a(obtainStyledAttributes, R.styleable.XGroupHeader_group_header_text_size);
        if (a != null && this.abr != null) {
            this.abr.a(new a.InterfaceC0085a() { // from class: com.xiaopeng.xui.widget.-$$Lambda$XGroupHeader$4FdQAyWh3K0kRdpWjyeGqf6KTFI
                @Override // com.xiaopeng.xui.view.a.InterfaceC0085a
                public final void onFontScaleChanged() {
                    XGroupHeader.this.a(a);
                }
            });
        }
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(b bVar) {
        bVar.a(this.adh);
    }

    private void setView(int i) {
        switch (this.adj) {
            case 0:
            default:
                return;
            case 1:
                this.adi = LayoutInflater.from(getContext()).inflate(R.layout.x_groupheader_action_icon, (ViewGroup) this, false);
                addView(this.adi);
                if (i > 0) {
                    setIcon(i);
                    return;
                }
                return;
            case 2:
                this.adi = LayoutInflater.from(getContext()).inflate(R.layout.x_groupheader_action_button, (ViewGroup) this, false);
                addView(this.adi);
                if (i > 0) {
                    ((TextView) this.adi).setText(i);
                    return;
                }
                return;
            case 3:
                this.adi = LayoutInflater.from(getContext()).inflate(R.layout.x_groupheader_action_loading, (ViewGroup) this, false);
                addView(this.adi);
                return;
            case 4:
                if (i > 0) {
                    this.adi = LayoutInflater.from(getContext()).inflate(i, (ViewGroup) this, false);
                    addView(this.adi);
                    return;
                }
                return;
        }
    }

    private void nN() {
        this.adh = (TextView) findViewById(R.id.x_groupheader_tv);
    }

    public void setText(@Nullable CharSequence charSequence) {
        this.adh.setText(charSequence);
    }

    public void setCustom(@LayoutRes int i) {
        this.adj = 4;
        removeView(this.adi);
        setView(i);
    }

    public void setIcon(@DrawableRes int i) {
        if (this.adj == 1) {
            ((ImageButton) this.adi).setImageResource(i);
            return;
        }
        this.adj = 1;
        removeView(this.adi);
        setView(i);
    }

    public void setButtonText(@StringRes int i) {
        if (this.adj == 2) {
            ((TextView) this.adi).setText(i);
            return;
        }
        this.adj = 2;
        removeView(this.adi);
        setView(i);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view != null) {
            view.setVisibility(4);
        }
        super.removeView(view);
    }

    public View getRightView() {
        return this.adi;
    }
}
