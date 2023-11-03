package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.xiaopeng.vui.commons.b;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.g;

/* loaded from: classes13.dex */
public class XTitleBarLight extends XRelativeLayout implements View.OnClickListener, b {
    private TextView Es;
    private XImageButton ahO;
    private XImageButton ahP;
    private ViewGroup ahQ;
    private ViewGroup ahS;
    private a ahT;
    private int ahU;

    /* loaded from: classes13.dex */
    public interface a {
        void rK();

        void rL();
    }

    public XTitleBarLight(Context context) {
        this(context, null);
    }

    public XTitleBarLight(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTitleBarLight(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XTitleBarLight(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(context).inflate(R.layout.x_titlebarlight, this);
        nN();
        this.ahU = (int) getResources().getDimension(R.dimen.x_title_bar_light_sparse_padding);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XTitleBarLight);
        setTitle(obtainStyledAttributes.getString(R.styleable.XTitleBarLight_title_text));
        setMainAction(obtainStyledAttributes.getInt(R.styleable.XTitleBarLight_title_main_action, 0));
        setTitleType(obtainStyledAttributes.getInt(R.styleable.XTitleBarLight_title_text_type, 0));
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XTitleBarLight_title_right_action_layout, 0);
        if (resourceId > 0) {
            setRightAction(resourceId);
        }
        obtainStyledAttributes.recycle();
    }

    private void nN() {
        this.ahO = (XImageButton) findViewById(R.id.x_titlebar_btn_close);
        this.ahP = (XImageButton) findViewById(R.id.x_titlebar_btn_back);
        this.Es = (TextView) findViewById(R.id.x_titlebar_tv_title);
        this.ahQ = (ViewGroup) findViewById(R.id.x_titlebar_right_container);
        this.ahS = (ViewGroup) findViewById(R.id.x_titlebar_btn_close_lay);
        this.ahO.setOnClickListener(this);
        this.ahP.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XRelativeLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        g.a(this.ahO, this.ahS);
        g.a(this.ahP, this.ahS);
    }

    public void setTitle(@StringRes int i) {
        this.Es.setText(i);
    }

    public void setTitle(@Nullable CharSequence charSequence) {
        this.Es.setText(charSequence);
    }

    public void setTitleType(int i) {
        if (i != 1) {
            this.Es.setPadding(0, this.Es.getPaddingTop(), this.Es.getPaddingRight(), this.Es.getPaddingBottom());
        } else {
            this.Es.setPadding(this.ahU, this.Es.getPaddingTop(), this.Es.getPaddingRight(), this.Es.getPaddingBottom());
        }
    }

    public void setMainAction(int i) {
        if (i == -1) {
            this.ahP.setVisibility(4);
            this.ahO.setVisibility(4);
        } else if (i != 1) {
            this.ahO.setVisibility(0);
            this.ahP.setVisibility(4);
        } else {
            this.ahP.setVisibility(0);
            this.ahO.setVisibility(4);
        }
    }

    public void setRightAction(@LayoutRes int i) {
        setRightAction(LayoutInflater.from(getContext()).inflate(i, this.ahQ, false));
    }

    public void setRightAction(View view) {
        this.ahQ.removeAllViews();
        this.ahQ.addView(view);
        this.ahQ.setVisibility(0);
    }

    public void setOnTitleBarClickListener(a aVar) {
        this.ahT = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.x_titlebar_btn_close) {
            if (this.ahT != null) {
                this.ahT.rK();
            }
        } else if (id == R.id.x_titlebar_btn_back && this.ahT != null) {
            this.ahT.rL();
        }
    }
}
