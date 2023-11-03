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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.xiaopeng.vui.commons.b;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.g;

@Deprecated
/* loaded from: classes13.dex */
public class XTitleBar extends XLinearLayout implements View.OnClickListener, b {
    private TextView Es;
    private XImageButton ahO;
    private XImageButton ahP;
    private ViewGroup ahQ;
    private a ahR;
    private ViewGroup ahS;

    /* loaded from: classes13.dex */
    public interface a {
        void f(View view, int i);

        void rK();

        void rL();
    }

    public XTitleBar(Context context) {
        this(context, null);
    }

    public XTitleBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTitleBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XTitleBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(context).inflate(R.layout.x_titlebar, this);
        nN();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XTitleBar);
        setBackVisibility(obtainStyledAttributes.getInt(R.styleable.XTitleBar_title_back, 8));
        setCloseVisibility(obtainStyledAttributes.getInt(R.styleable.XTitleBar_title_close, 0));
        setTitle(obtainStyledAttributes.getString(R.styleable.XTitleBar_title_text));
        switch (obtainStyledAttributes.getInt(R.styleable.XTitleBar_title_action_mode, 0)) {
            case 0:
                this.ahQ.setVisibility(8);
                break;
            case 1:
                int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XTitleBar_title_action_1, -1);
                int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.XTitleBar_title_action_2, -1);
                if (resourceId != -1) {
                    setActionWithIcon(resourceId);
                }
                if (resourceId != -1 && resourceId2 != -1) {
                    C(resourceId, resourceId2);
                    break;
                }
                break;
            case 2:
                String string = obtainStyledAttributes.getString(R.styleable.XTitleBar_title_action_1);
                if (string != null) {
                    setActionWithButton(string);
                    break;
                }
                break;
            case 4:
                int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.XTitleBar_title_action_1, -1);
                if (resourceId3 != -1) {
                    setActionWithCustom(resourceId3);
                    break;
                }
                break;
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
    @Override // com.xiaopeng.xui.widget.XLinearLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        g.a(this.ahO, this.ahS);
    }

    private void g(@DrawableRes int... iArr) {
        if (iArr != null) {
            for (int i = 0; i < iArr.length; i++) {
                ImageButton imageButton = (ImageButton) LayoutInflater.from(getContext()).inflate(R.layout.x_titlebar_action_icon, this.ahQ, false);
                imageButton.setImageResource(iArr[i]);
                imageButton.setOnClickListener(this);
                imageButton.setId(i);
                this.ahQ.addView(imageButton);
            }
        }
    }

    private void a(@NonNull CharSequence... charSequenceArr) {
        for (int i = 0; i < charSequenceArr.length; i++) {
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.x_titlebar_action_ghostbtn, this.ahQ, false);
            textView.setOnClickListener(this);
            textView.setId(i);
            textView.setText(charSequenceArr[i]);
            this.ahQ.addView(textView);
        }
    }

    public void setTitle(@Nullable CharSequence charSequence) {
        this.Es.setText(charSequence);
    }

    public void setCloseVisibility(int i) {
        this.ahO.setVisibility(i);
    }

    public void setBackVisibility(int i) {
        this.ahP.setVisibility(i);
    }

    public void setActionWithIcon(@DrawableRes int i) {
        this.ahQ.removeAllViews();
        g(i);
        this.ahQ.setVisibility(0);
    }

    public void C(@DrawableRes int i, @DrawableRes int i2) {
        this.ahQ.removeAllViews();
        g(i, i2);
        this.ahQ.setVisibility(0);
    }

    public void setActionWithButton(@NonNull CharSequence charSequence) {
        this.ahQ.removeAllViews();
        a(charSequence);
        this.ahQ.setVisibility(0);
    }

    public void setActionWithCustom(@LayoutRes int i) {
        setActionWithCustom(LayoutInflater.from(getContext()).inflate(i, this.ahQ, false));
    }

    public void setActionWithCustom(View view) {
        this.ahQ.removeAllViews();
        this.ahQ.addView(view);
        this.ahQ.setVisibility(0);
    }

    public void setOnTitleBarClickListener(a aVar) {
        this.ahR = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.x_titlebar_btn_close) {
            if (this.ahR != null) {
                this.ahR.rK();
            }
        } else if (id == R.id.x_titlebar_btn_back) {
            if (this.ahR != null) {
                this.ahR.rL();
            }
        } else if (this.ahR != null) {
            this.ahR.f(view, id);
        }
    }
}
