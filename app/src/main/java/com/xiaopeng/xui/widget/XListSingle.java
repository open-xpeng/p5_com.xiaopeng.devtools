package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.g;
import com.xiaopeng.xui.d.h;
import java.util.List;

/* loaded from: classes13.dex */
public class XListSingle extends XRelativeLayout {
    private TextView adh;
    private ViewStub adm;
    private TextView adn;
    private ViewGroup ado;
    private ViewStub adp;
    private ImageView adq;
    private ViewGroup adr;
    private ViewGroup ads;
    private int adt;

    public XListSingle(Context context) {
        this(context, null);
    }

    public XListSingle(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XListSingle(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XListSingle(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(context).inflate(R.layout.x_list_single, this);
        nN();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XListSingle);
        setText(obtainStyledAttributes.getString(R.styleable.XListSingle_list_single_text));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XListSingle_list_single_left, -1);
        if (resourceId != -1) {
            this.adp.setLayoutResource(resourceId);
            View inflate = this.adp.inflate();
            if (inflate instanceof ViewGroup) {
                this.adr = (ViewGroup) inflate;
            }
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.XListSingle_list_single_right, -1);
        if (resourceId2 != -1) {
            this.adm.setLayoutResource(resourceId2);
            View inflate2 = this.adm.inflate();
            if (inflate2 instanceof ViewGroup) {
                this.ads = (ViewGroup) inflate2;
            }
        }
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.XListSingle_list_single_tag_icon, -1);
        if (resourceId3 != -1) {
            setTagIcon(resourceId3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.XListSingle_list_single_tag, false);
        bg(z);
        this.adt = obtainStyledAttributes.getInt(R.styleable.XListSingle_list_single_left_right_touch_full, 0);
        obtainStyledAttributes.recycle();
        if (resourceId2 != -1) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.adh.getLayoutParams();
            marginLayoutParams.setMarginEnd((int) getResources().getDimension(z ? R.dimen.x_list_tv_has_tag_margin_end : R.dimen.x_list_tv_margin_end));
            this.adh.setLayoutParams(marginLayoutParams);
        } else if (z) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.adh.getLayoutParams();
            marginLayoutParams2.setMarginEnd((int) getResources().getDimension(R.dimen.x_list_tv_has_tag_margin_end));
            this.adh.setLayoutParams(marginLayoutParams2);
        }
    }

    private void nN() {
        this.adh = (TextView) findViewById(R.id.x_list_tv);
        this.adn = (TextView) findViewById(R.id.x_list_num);
        this.ado = (ViewGroup) findViewById(R.id.x_list_left_lay);
        this.adp = (ViewStub) findViewById(R.id.x_list_left);
        this.adm = (ViewStub) findViewById(R.id.x_list_right);
        this.adq = (ImageView) findViewById(R.id.x_list_tag);
    }

    public void setNum(int i) {
        this.adn.setText(String.valueOf(i));
    }

    public void setText(@Nullable CharSequence charSequence) {
        this.adh.setText(charSequence);
    }

    public void setTagIcon(@DrawableRes int i) {
        this.adq.setImageResource(i);
    }

    public void bg(boolean z) {
        this.adq.setVisibility(z ? 0 : 4);
    }

    public void setTagOnClickListener(View.OnClickListener onClickListener) {
        this.adq.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XRelativeLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        rl();
        rm();
    }

    private void rl() {
        if (this.adr != null) {
            if (this.adt == 1) {
                for (Class cls : g.abq) {
                    List<View> a = h.a(this.adr, cls);
                    if (a.size() > 0) {
                        View[] viewArr = new View[a.size()];
                        a.toArray(viewArr);
                        g.a(viewArr, this);
                    }
                }
            } else {
                g.a(this.ado);
            }
        }
        if (this.ads != null) {
            if (this.adt == 2) {
                for (Class cls2 : g.abq) {
                    List<View> a2 = h.a(this.ads, cls2);
                    if (a2.size() > 0) {
                        View[] viewArr2 = new View[a2.size()];
                        a2.toArray(viewArr2);
                        g.a(viewArr2, this);
                    }
                }
                return;
            }
            g.a(this.ads);
        }
    }

    private void rm() {
        if (this.ads != null) {
            if (this.adt == 2) {
                for (Class cls : g.abq) {
                    List<View> a = h.a(this.ads, cls);
                    if (a.size() > 0) {
                        View[] viewArr = new View[a.size()];
                        a.toArray(viewArr);
                        g.a(viewArr, this);
                    }
                }
                return;
            }
            g.a(this.ads);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        b(z, true);
    }

    public void b(boolean z, boolean z2) {
        super.setEnabled(z);
        if (z2) {
            a(this, z);
        }
    }

    private void a(ViewGroup viewGroup, boolean z) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, z);
            }
            childAt.setEnabled(z);
        }
    }
}
