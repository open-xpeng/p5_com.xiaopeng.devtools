package com.xiaopeng.devtools.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class ActionBar extends LinearLayout implements View.OnClickListener {
    private int Cc;
    private int Cd;
    private String Ce;
    private int Cf;
    private TextView Cg;
    private ImageButton Ch;
    private ImageButton Ci;
    private View Cj;
    private int Ck;
    private TextView Cl;
    private String Cm;
    private c Cn;
    private a Co;
    private b Cp;

    /* loaded from: classes12.dex */
    public interface a {
        void onBack(View view);
    }

    /* loaded from: classes12.dex */
    public interface b {
        void onCenterClick(View view);
    }

    /* loaded from: classes12.dex */
    public interface c {
        void e(View view);
    }

    public ActionBar(Context context) {
        this(context, null);
    }

    public ActionBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    @TargetApi(11)
    public ActionBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBar);
        this.Cc = obtainStyledAttributes.getResourceId(21, -1);
        this.Cd = obtainStyledAttributes.getResourceId(28, -1);
        this.Cf = obtainStyledAttributes.getResourceId(27, -1);
        this.Ck = obtainStyledAttributes.getResourceId(3, -1);
        obtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_action_bar, (ViewGroup) null);
        this.Cg = (TextView) inflate.findViewById(R.id.text_view_right);
        this.Ci = (ImageButton) inflate.findViewById(R.id.ib_action_right);
        this.Cj = inflate.findViewById(R.id.view_line_bottom);
        this.Cl = (TextView) inflate.findViewById(R.id.text_view_center);
        this.Cg.setOnClickListener(this);
        this.Cl.setOnClickListener(this);
        this.Ci.setOnClickListener(this);
        this.Ch = (ImageButton) inflate.findViewById(R.id.ib_back);
        this.Ch.setOnClickListener(this);
        if (this.Cd != -1) {
            this.Cg.setText(this.Cd);
            this.Cg.setVisibility(0);
        }
        if (this.Cc != -1) {
            this.Ch.setBackgroundResource(this.Cc);
            this.Ch.setVisibility(0);
        }
        if (this.Cf != -1) {
            this.Ci.setBackgroundResource(this.Cf);
            this.Ci.setVisibility(0);
        }
        if (this.Ck != -1) {
            this.Cl.setText(this.Ck);
            this.Cl.setVisibility(0);
        }
        addView(inflate);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.text_view_right || id == R.id.ib_action_right) {
            if (this.Cn != null) {
                this.Cn.e(view);
            }
        } else if (id == R.id.ib_back) {
            if (this.Co != null) {
                this.Co.onBack(view);
            }
        } else if (id == R.id.text_view_center && this.Cp != null) {
            this.Cp.onCenterClick(view);
        }
    }

    public void setRightTextResId(int i) {
        this.Cd = i;
        this.Cg.setText(this.Cd);
        this.Cg.setVisibility(0);
    }

    public void setRightBtnResId(int i) {
        this.Cf = i;
        this.Ci.setBackgroundResource(this.Cf);
        this.Ci.setVisibility(0);
    }

    public void setRightText(String str) {
        this.Ce = str;
        this.Cg.setText(this.Ce);
        this.Cg.setVisibility(0);
    }

    public void setCenterText(String str) {
        this.Cm = str;
        this.Cl.setText(this.Cm);
        this.Cl.setVisibility(0);
    }

    public void setOnRightClickListener(c cVar) {
        this.Cn = cVar;
    }

    public void setRightBtnVisible(boolean z) {
        this.Ci.setVisibility(z ? 0 : 4);
    }

    public void setBottomLineVisible(boolean z) {
        this.Cj.setVisibility(z ? 0 : 4);
    }

    public void setOnBackListener(a aVar) {
        this.Co = aVar;
    }

    public void setOnCenterClickListener(b bVar) {
        this.Cp = bVar;
    }
}
