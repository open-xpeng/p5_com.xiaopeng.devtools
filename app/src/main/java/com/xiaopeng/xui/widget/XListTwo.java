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
public class XListTwo extends XRelativeLayout {
    private TextView adh;
    private TextView adk;
    private ViewStub adm;
    private TextView adn;
    private ViewGroup ado;
    private ViewStub adp;
    private ImageView adq;
    private ViewGroup adr;
    private ViewGroup ads;
    private int adt;
    private ViewStub adu;
    private ViewGroup adv;

    public XListTwo(Context context) {
        this(context, null);
    }

    public XListTwo(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XListTwo(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XListTwo(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(context).inflate(R.layout.x_list_two, this);
        nN();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XListTwo);
        setText(obtainStyledAttributes.getString(R.styleable.XListTwo_list_two_text));
        setTextSub(obtainStyledAttributes.getString(R.styleable.XListTwo_list_two_text_sub));
        this.adk.setMaxLines(obtainStyledAttributes.getInt(R.styleable.XListTwo_list_text_sub_lines, 1));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XListTwo_list_two_left, -1);
        if (resourceId != -1) {
            this.adp.setLayoutResource(resourceId);
            View inflate = this.adp.inflate();
            if (inflate instanceof ViewGroup) {
                this.adr = (ViewGroup) inflate;
            }
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.XListTwo_list_two_right, -1);
        if (resourceId2 != -1) {
            this.adm.setLayoutResource(resourceId2);
            View inflate2 = this.adm.inflate();
            if (inflate2 instanceof ViewGroup) {
                this.ads = (ViewGroup) inflate2;
            }
        }
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.XListTwo_list_two_tag_icon, -1);
        if (resourceId3 != -1) {
            setTagIcon(resourceId3);
        }
        bg(obtainStyledAttributes.getBoolean(R.styleable.XListTwo_list_two_tag, false));
        int resourceId4 = obtainStyledAttributes.getResourceId(R.styleable.XListTwo_list_two_tag_custom, -1);
        if (resourceId4 != -1) {
            this.adu.setLayoutResource(resourceId4);
            this.adu.inflate();
        }
        this.adt = obtainStyledAttributes.getInt(R.styleable.XListTwo_list_two_left_right_touch_full, 0);
        obtainStyledAttributes.recycle();
        if (resourceId2 != -1) {
            View findViewById = findViewById(R.id.x_list_tv_lay);
            int dimension = (int) getResources().getDimension(R.dimen.x_list_tv_margin_end);
            findViewById.setPadding(findViewById.getPaddingLeft(), findViewById.getPaddingTop(), dimension, findViewById.getPaddingBottom());
            this.adk.setPadding(this.adk.getPaddingLeft(), this.adk.getPaddingTop(), dimension, this.adk.getPaddingBottom());
        }
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
                return;
            }
            g.a(this.ado);
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

    private void nN() {
        this.adh = (TextView) findViewById(R.id.x_list_tv);
        this.adn = (TextView) findViewById(R.id.x_list_num);
        this.ado = (ViewGroup) findViewById(R.id.x_list_left_lay);
        this.adv = (ViewGroup) findViewById(R.id.x_list_tv_lay);
        this.adk = (TextView) findViewById(R.id.x_list_tv_sub);
        this.adp = (ViewStub) findViewById(R.id.x_list_left);
        this.adm = (ViewStub) findViewById(R.id.x_list_right);
        this.adq = (ImageView) findViewById(R.id.x_list_tag);
        this.adu = (ViewStub) findViewById(R.id.x_list_tag_custom_lay);
    }

    public void setNum(int i) {
        this.adn.setText(String.valueOf(i));
    }

    public void setText(@Nullable CharSequence charSequence) {
        this.adh.setText(charSequence);
        this.adh.requestLayout();
    }

    public void setTextSub(@Nullable CharSequence charSequence) {
        this.adk.setText(charSequence);
    }

    public void setTagIcon(@DrawableRes int i) {
        this.adq.setImageResource(i);
    }

    public void bg(boolean z) {
        this.adq.setVisibility(z ? 0 : 8);
    }

    public void setTagOnClickListener(View.OnClickListener onClickListener) {
        this.adq.setOnClickListener(onClickListener);
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
