package com.xiaopeng.xui.widget.dialogview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.f;
import com.xiaopeng.xui.d.g;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XLinearLayout;
import com.xiaopeng.xui.widget.XScrollView;
import com.xiaopeng.xui.widget.dialogview.a;
import com.xiaopeng.xui.widget.dialogview.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: XDialogViewDelegateImpl.java */
/* loaded from: classes13.dex */
public class d extends c {
    private TextView aiA;
    private XButton aiB;
    private XButton aiC;
    private View aiD;
    private View aiE;
    private XDialogMessage aiF;
    private XScrollView aiG;
    private e.b aiH;
    private e.c aiI;
    private e.d aiJ;
    private e.a aiK;
    private e.a aiL;
    private boolean aiM;
    private boolean aiN;
    private com.xiaopeng.xui.widget.dialogview.a aiO;
    private a aiy;
    private ViewGroup aiz;
    private ViewGroup mContentView;
    private View.OnClickListener mOnClickListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(com.xiaopeng.xui.widget.dialogview.b bVar, @NonNull Context context, int i) {
        super(bVar, context, i);
        this.mOnClickListener = new View.OnClickListener() { // from class: com.xiaopeng.xui.widget.dialogview.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                boolean z = d.this.aiM;
                boolean z2 = d.this.aiN;
                if (id == R.id.x_dialog_button1) {
                    if (d.this.aiK == null) {
                        d.this.fh("onClick mPositiveListener is null");
                    } else {
                        d dVar = d.this;
                        dVar.fh("onClick  positiveIntercept " + z + " , mPositiveListener " + d.this.aiK);
                        d.this.aiK.onClick(d.this.aix, -1);
                        if (z) {
                            return;
                        }
                    }
                } else if (id == R.id.x_dialog_button2) {
                    if (d.this.aiL == null) {
                        d.this.fh("onClick mNegativeListener is null");
                    } else {
                        d dVar2 = d.this;
                        dVar2.fh("onClick negativeIntercept " + z2 + " , mNegativeListener " + d.this.aiL);
                        d.this.aiL.onClick(d.this.aix, -2);
                        if (z2) {
                            return;
                        }
                    }
                } else if (id == R.id.x_dialog_close) {
                    d.this.fh("onClick close");
                    if (d.this.aiH != null && d.this.aiH.onClose(d.this.aix)) {
                        d.this.fh("onClick close intercept dismiss ");
                        return;
                    }
                } else {
                    d dVar3 = d.this;
                    dVar3.fh("onClick invalid id " + id);
                }
                d.this.dismiss();
            }
        };
        init();
    }

    private void init() {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes((AttributeSet) null, R.styleable.XDialog);
        int dimension = (int) obtainStyledAttributes.getDimension(R.styleable.XDialog_dialog_max_height, 0.0f);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.XDialog_dialog_width, 0);
        int layoutDimension2 = obtainStyledAttributes.getLayoutDimension(R.styleable.XDialog_dialog_height, 0);
        obtainStyledAttributes.recycle();
        this.aiy = new a(this, this.mContext);
        this.aiy.setMaxHeight(dimension);
        this.aiy.setMinimumHeight(this.mContext.getResources().getDimensionPixelSize(R.dimen.x_dialog_min_height));
        this.aiy.setWidth(layoutDimension);
        this.aiy.setHeight(layoutDimension2);
        fh("maxHeight:" + dimension + ",w:" + layoutDimension + ", h " + layoutDimension2);
        LayoutInflater.from(this.mContext).inflate(R.layout.x_dialog, this.aiy);
        p(this.aiy);
    }

    private void p(View view) {
        this.aiz = (ViewGroup) view.findViewById(R.id.x_dialog_title_layout);
        this.aiD = view.findViewById(R.id.x_dialog_close);
        this.aiA = (TextView) view.findViewById(R.id.x_dialog_title);
        this.mContentView = (ViewGroup) view.findViewById(R.id.x_dialog_content);
        this.aiB = (XButton) view.findViewById(R.id.x_dialog_button1);
        this.aiC = (XButton) view.findViewById(R.id.x_dialog_button2);
        this.aiE = view.findViewById(R.id.x_dialog_has_buttons);
        this.aiD.setOnClickListener(this.mOnClickListener);
        this.aiB.setOnClickListener(this.mOnClickListener);
        this.aiC.setOnClickListener(this.mOnClickListener);
        this.aiy.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.xiaopeng.xui.widget.dialogview.d.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
                d.this.rR();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                if (d.this.aiO != null) {
                    d.this.aiO.stop();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss() {
        if (this.aiJ != null) {
            this.aiJ.onDismiss(this.aix);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getContext() {
        return this.mContext;
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void setTitle(@Nullable CharSequence charSequence) {
        this.aiA.setText(charSequence);
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void setTitle(@StringRes int i) {
        this.aiA.setText(i);
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void setMessage(@Nullable CharSequence charSequence) {
        rQ();
        this.aiF.setMessage(charSequence);
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void eP(@StringRes int i) {
        rQ();
        this.aiF.eP(i);
    }

    private void rQ() {
        if (this.aiF == null) {
            this.aiF = new XDialogMessage(this.mContext);
        }
        this.mContentView.removeAllViews();
        this.mContentView.addView(this.aiF);
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void h(@NonNull View view, boolean z) {
        this.mContentView.removeAllViews();
        if (z) {
            if (this.aiG == null) {
                this.aiG = (XScrollView) LayoutInflater.from(this.mContext).inflate(R.layout.x_dialog_scroll, this.mContentView, false);
            }
            this.aiG.removeAllViews();
            this.mContentView.addView(this.aiG);
            this.aiG.addView(view);
            return;
        }
        this.mContentView.addView(view);
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void bn(final boolean z) {
        this.aiD.setVisibility(z ? 0 : 4);
        if (this.aiD.getWidth() > 0) {
            bp(z);
        } else {
            this.aiD.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.xiaopeng.xui.widget.dialogview.d.3
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    d.this.aiD.getViewTreeObserver().removeOnPreDrawListener(this);
                    d.this.bp(z);
                    return true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bp(boolean z) {
        int i;
        if (!z) {
            this.aiA.setPadding(0, 0, 0, 0);
            return;
        }
        int width = this.aiD.getWidth();
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.x_dialog_close_padding);
        ViewGroup.LayoutParams layoutParams = this.aiA.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            i = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        } else {
            i = 0;
        }
        int i2 = ((dimension * 2) + width) - i;
        this.aiA.setPadding(i2, 0, i2, 0);
        fh(String.format("resetTitlePadding closeWidth: %s ,closeMargin: %s ,textMargin: %s ,padding: %s", Integer.valueOf(width), Integer.valueOf(dimension), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void r(@Nullable CharSequence charSequence) {
        this.aiB.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.aiB.setText(charSequence);
        rS();
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void c(e.a aVar) {
        this.aiK = aVar;
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void s(@Nullable CharSequence charSequence) {
        this.aiC.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        this.aiC.setText(charSequence);
        rS();
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void d(e.a aVar) {
        this.aiL = aVar;
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void bo(boolean z) {
        this.aiM = z;
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void ab(boolean z) {
        this.aiB.setEnabled(z);
        a(this.aiB, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031 A[Catch: JSONException -> 0x0038, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0038, blocks: (B:3:0x0001, B:6:0x000a, B:8:0x0012, B:16:0x0031, B:11:0x001c, B:12:0x0021, B:14:0x0029), top: B:20:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.xiaopeng.xui.widget.XButton r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            org.json.JSONObject r1 = r4.qY()     // Catch: org.json.JSONException -> L38
            r2 = 1
            if (r5 == 0) goto L1a
            if (r1 == 0) goto L2f
            java.lang.String r5 = "hasFeedback"
            boolean r5 = r1.has(r5)     // Catch: org.json.JSONException -> L38
            if (r5 == 0) goto L2f
            java.lang.String r5 = "hasFeedback"
            r1.remove(r5)     // Catch: org.json.JSONException -> L38
        L18:
            r0 = r2
            goto L2f
        L1a:
            if (r1 != 0) goto L21
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L38
            r1.<init>()     // Catch: org.json.JSONException -> L38
        L21:
            java.lang.String r5 = "hasFeedback"
            boolean r5 = r1.has(r5)     // Catch: org.json.JSONException -> L38
            if (r5 != 0) goto L2f
            java.lang.String r5 = "hasFeedback"
            r1.put(r5, r2)     // Catch: org.json.JSONException -> L38
            goto L18
        L2f:
            if (r0 == 0) goto L37
            r4.i(r1)     // Catch: org.json.JSONException -> L38
            r4.l(r4)     // Catch: org.json.JSONException -> L38
        L37:
            goto L39
        L38:
            r4 = move-exception
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.xui.widget.dialogview.d.a(com.xiaopeng.xui.widget.XButton, boolean):void");
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void eS(int i) {
        if (this.aiO != null) {
            this.aiO.stop();
        }
        if (this.aiB.getVisibility() == 0 && i > 0) {
            this.aiO = new com.xiaopeng.xui.widget.dialogview.a(new b(this.aiB, -1));
            this.aiO.F(this.aiB.getText().toString(), i);
        }
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void eT(int i) {
        if (this.aiO != null) {
            this.aiO.stop();
        }
        if (this.aiC.getVisibility() == 0 && i > 0) {
            this.aiO = new com.xiaopeng.xui.widget.dialogview.a(new b(this.aiC, -2));
            this.aiO.F(this.aiC.getText().toString(), i);
        }
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void b(e.b bVar) {
        this.aiH = bVar;
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void b(e.d dVar) {
        this.aiJ = dVar;
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public void a(ThemeViewModel.OnCallback onCallback) {
        this.aiy.a(onCallback);
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rR() {
        int[] iArr = {0, 20, 0, 0};
        g.a(this.aiB, this.aiy, iArr);
        g.a(this.aiC, this.aiy, iArr);
        int dimension = (int) this.aiD.getContext().getResources().getDimension(R.dimen.x_dialog_close_padding);
        g.a(this.aiD, this.aiy, new int[]{dimension, dimension, dimension, dimension});
    }

    @Override // com.xiaopeng.xui.widget.dialogview.c
    public ViewGroup qu() {
        return this.aiy;
    }

    private void rS() {
        if (this.aiB.getVisibility() == 0 || this.aiC.getVisibility() == 0) {
            this.aiE.setVisibility(0);
        } else {
            this.aiE.setVisibility(8);
        }
    }

    /* compiled from: XDialogViewDelegateImpl.java */
    /* loaded from: classes13.dex */
    private class b implements a.InterfaceC0089a {
        private TextView aiT;
        private int aiU;
        private String aiV;

        b(TextView textView, int i) {
            this.aiT = textView;
            this.aiU = i;
            this.aiV = d.this.getContext().getString(R.string.x_countdown_tips);
        }

        @Override // com.xiaopeng.xui.widget.dialogview.a.InterfaceC0089a
        public void h(String str, int i, int i2) {
            this.aiT.setText(String.format(this.aiV, str, Integer.valueOf(i2)));
        }

        @Override // com.xiaopeng.xui.widget.dialogview.a.InterfaceC0089a
        public void fm(String str) {
            this.aiT.setText(str);
            if (d.this.aiI == null || !d.this.aiI.c(d.this.aix, this.aiU)) {
                d.this.mOnClickListener.onClick(this.aiT);
            } else {
                d.this.fh("onCountDown intercept onClick ");
            }
        }

        @Override // com.xiaopeng.xui.widget.dialogview.a.InterfaceC0089a
        public void fn(String str) {
            this.aiT.setText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: XDialogViewDelegateImpl.java */
    /* loaded from: classes13.dex */
    public class a extends XLinearLayout {
        private int aiR;
        private int aiS;
        private int mMaxHeight;

        public a(d dVar, @NonNull Context context) {
            this(context, null);
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            setOrientation(1);
            fk(getResources().getString(R.string.vui_label_dialog));
        }

        public void setMaxHeight(int i) {
            this.mMaxHeight = i;
        }

        public void setWidth(int i) {
            this.aiR = i;
        }

        public void setHeight(int i) {
            this.aiS = i;
        }

        @Override // android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            d dVar = d.this;
            dVar.fh("hasWindowFocus " + z);
            if (this.abr != null) {
                this.abr.onWindowFocusChanged(z);
            }
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (z) {
                d dVar = d.this;
                dVar.fh("onLayout-w:" + getWidth() + ",h:" + getHeight() + ", mMaxHeight " + this.mMaxHeight);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.aiR, 1073741824);
            if (this.aiS <= 0) {
                if (this.mMaxHeight > 0) {
                    super.onMeasure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(this.mMaxHeight, Integer.MIN_VALUE));
                    return;
                } else {
                    super.onMeasure(makeMeasureSpec, i2);
                    return;
                }
            }
            super.onMeasure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(this.aiS, 1073741824));
        }

        public void a(ThemeViewModel.OnCallback onCallback) {
            if (this.abr != null && this.abr.qP() != null) {
                this.abr.qP().setCallback(onCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fh(String str) {
        f.g("xpui-XDialogView", str + "--hashcode " + this.aix.hashCode());
    }
}
