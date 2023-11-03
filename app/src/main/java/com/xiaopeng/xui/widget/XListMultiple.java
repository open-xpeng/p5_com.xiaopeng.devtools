package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;

/* loaded from: classes13.dex */
public class XListMultiple extends XRelativeLayout {
    private TextView adh;
    private TextView adk;
    private ViewStub adl;
    private ViewStub adm;

    public XListMultiple(Context context) {
        this(context, null);
    }

    public XListMultiple(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XListMultiple(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XListMultiple(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XListMultiple);
        switch (obtainStyledAttributes.getInt(R.styleable.XListMultiple_list_multiple_type, 0)) {
            case 1:
                i3 = R.layout.x_list_multiple_two;
                break;
            case 2:
                i3 = R.layout.x_list_multiple_wrap;
                break;
            default:
                i3 = R.layout.x_list_multiple;
                break;
        }
        LayoutInflater.from(context).inflate(i3, this);
        nN();
        this.adk.setMaxLines(obtainStyledAttributes.getInt(R.styleable.XListMultiple_list_text_sub_lines, 1));
        setText(obtainStyledAttributes.getString(R.styleable.XListMultiple_list_multiple_text));
        setTextSub(obtainStyledAttributes.getString(R.styleable.XListMultiple_list_multiple_text_sub));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XListMultiple_list_multiple_bottom, -1);
        if (resourceId != -1) {
            this.adl.setLayoutResource(resourceId);
            this.adl.inflate();
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.XListMultiple_list_multiple_right, -1);
        if (resourceId2 != -1) {
            this.adm.setLayoutResource(resourceId2);
            this.adm.inflate();
        }
        obtainStyledAttributes.recycle();
    }

    private void nN() {
        this.adh = (TextView) findViewById(R.id.x_list_tv);
        this.adk = (TextView) findViewById(R.id.x_list_tv_sub);
        this.adl = (ViewStub) findViewById(R.id.x_list_bottom);
        this.adm = (ViewStub) findViewById(R.id.x_list_right);
    }

    public void setText(@Nullable CharSequence charSequence) {
        this.adh.setText(charSequence);
    }

    public void setTextSub(@Nullable CharSequence charSequence) {
        this.adk.setText(charSequence);
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
