package com.xiaopeng.xui.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import com.xiaopeng.libtheme.ThemeViewModel;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.widget.dialogview.e;

/* compiled from: XDialog.java */
/* loaded from: classes13.dex */
public class c {
    private static int ZM;
    private com.xiaopeng.xui.widget.dialogview.b ZN;
    private int ZO;
    private e.b ZP;
    private e.a ZQ;
    private e.a ZR;
    private d.b ZS;
    private d.a ZT;
    private d.a ZU;
    private int ZV;
    private Context mContext;
    private Dialog mDialog;
    private DialogInterface.OnKeyListener mOnKeyListener;

    public c(@NonNull Context context) {
        this(context, 0);
    }

    public c(@NonNull Context context, @StyleRes int i) {
        this(context, i, null);
    }

    public c(@NonNull Context context, @StyleRes int i, @Nullable a aVar) {
        this.ZN = new com.xiaopeng.xui.widget.dialogview.b(context, i);
        this.mContext = context;
        aVar = aVar == null ? a.qv() : aVar;
        if (aVar.mTheme != 0) {
            this.mDialog = new Dialog(context, aVar.mTheme);
        } else {
            this.mDialog = new Dialog(context, R.style.XAppTheme_XDialog);
        }
        if (aVar.ZW) {
            com.xiaopeng.xui.d.d.a(this.mDialog);
        }
        this.mDialog.setContentView(this.ZN.qu());
        init();
        ZM++;
    }

    private void init() {
        this.ZO = (int) this.mContext.getResources().getDimension(R.dimen.x_dialog_system_offset_y);
        this.ZN.a(new e.d() { // from class: com.xiaopeng.xui.app.-$$Lambda$c$evIU78BAU2Dyr-Qc3LzMKTGWG_k
            @Override // com.xiaopeng.xui.widget.dialogview.e.d
            public final void onDismiss(com.xiaopeng.xui.widget.dialogview.b bVar) {
                c.this.b(bVar);
            }
        });
        this.ZN.a(new ThemeViewModel.OnCallback() { // from class: com.xiaopeng.xui.app.-$$Lambda$c$RKvrKk09Pzaxnl6oeq3oU8LCxEU
            @Override // com.xiaopeng.libtheme.ThemeViewModel.OnCallback
            public final void onThemeChanged() {
                c.this.er();
            }
        });
        this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.xiaopeng.xui.app.-$$Lambda$c$d205BG8HD_Zke7Rhnyum3xL-aiI
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                boolean a2;
                a2 = c.this.a(dialogInterface, i, keyEvent);
                return a2;
            }
        });
        TypedArray obtainStyledAttributes = this.mDialog.getContext().obtainStyledAttributes(new int[]{16842836});
        this.ZV = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(com.xiaopeng.xui.widget.dialogview.b bVar) {
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void er() {
        fh("onThemeChanged, mWindowBackgroundId " + this.ZV);
        if (this.ZV == 0) {
            this.ZV = R.drawable.x_bg_dialog;
        }
        if (this.mDialog.getWindow() != null && this.ZV > 0) {
            this.mDialog.getWindow().setBackgroundDrawableResource(this.ZV);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean a(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (this.mOnKeyListener != null && this.mOnKeyListener.onKey(dialogInterface, i, keyEvent)) {
            fh("custom key listener return true  keyCode : " + i + ", event " + keyEvent.getAction());
            return true;
        }
        return this.ZN.a(i, keyEvent);
    }

    private void qr() {
        if (this.ZP == null) {
            this.ZP = new e.b() { // from class: com.xiaopeng.xui.app.-$$Lambda$c$bwWaMW1cFRr1s2WlV6B6XG7b3S0
                @Override // com.xiaopeng.xui.widget.dialogview.e.b
                public final boolean onClose(com.xiaopeng.xui.widget.dialogview.b bVar) {
                    boolean a2;
                    a2 = c.this.a(bVar);
                    return a2;
                }
            };
            this.ZN.a(this.ZP);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean a(com.xiaopeng.xui.widget.dialogview.b bVar) {
        if (this.ZS != null) {
            return this.ZS.onClose(this);
        }
        return false;
    }

    private void qs() {
        if (this.ZQ == null) {
            this.ZQ = new e.a() { // from class: com.xiaopeng.xui.app.-$$Lambda$c$TRfYm1Zjbv1ejnxCtvBt02JS4rk
                @Override // com.xiaopeng.xui.widget.dialogview.e.a
                public final void onClick(com.xiaopeng.xui.widget.dialogview.b bVar, int i) {
                    c.this.b(bVar, i);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(com.xiaopeng.xui.widget.dialogview.b bVar, int i) {
        if (this.ZT != null) {
            this.ZT.onClick(this, i);
        }
    }

    private void qt() {
        if (this.ZR == null) {
            this.ZR = new e.a() { // from class: com.xiaopeng.xui.app.-$$Lambda$c$Zu3-j__3zCUByzpjzaAEM9vkZWw
                @Override // com.xiaopeng.xui.widget.dialogview.e.a
                public final void onClick(com.xiaopeng.xui.widget.dialogview.b bVar, int i) {
                    c.this.a(bVar, i);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(com.xiaopeng.xui.widget.dialogview.b bVar, int i) {
        if (this.ZU != null) {
            this.ZU.onClick(this, i);
        }
    }

    public ViewGroup qu() {
        return this.ZN.qu();
    }

    public c aV(boolean z) {
        this.ZN.bl(z);
        return this;
    }

    public c e(@Nullable CharSequence charSequence) {
        this.ZN.n(charSequence);
        return this;
    }

    public c dV(@StringRes int i) {
        this.ZN.eQ(i);
        return this;
    }

    public c f(@Nullable CharSequence charSequence) {
        this.ZN.o(charSequence);
        return this;
    }

    public c dW(@StringRes int i) {
        this.ZN.eR(i);
        return this;
    }

    public c d(@NonNull View view, boolean z) {
        this.ZN.g(view, z);
        return this;
    }

    public c aW(boolean z) {
        this.ZN.bk(z);
        return this;
    }

    public c g(@Nullable CharSequence charSequence) {
        this.ZN.p(charSequence);
        return this;
    }

    public c a(d.a aVar) {
        this.ZT = aVar;
        if (aVar != null) {
            qs();
        }
        this.ZN.a(this.ZQ);
        return this;
    }

    public c a(@Nullable CharSequence charSequence, d.a aVar) {
        g(charSequence);
        a(aVar);
        return this;
    }

    public c h(@Nullable CharSequence charSequence) {
        this.ZN.q(charSequence);
        return this;
    }

    public c b(d.a aVar) {
        this.ZU = aVar;
        if (aVar != null) {
            qt();
        }
        this.ZN.b(this.ZR);
        return this;
    }

    public c b(@Nullable CharSequence charSequence, d.a aVar) {
        h(charSequence);
        b(aVar);
        return this;
    }

    public c aX(boolean z) {
        this.ZN.bm(z);
        return this;
    }

    public void show() {
        show(0, 0);
    }

    public void show(int i, int i2) {
        fh("show");
        if (i > 0 && i2 == 0) {
            this.ZN.eS(i);
        }
        if (i2 > 0 && i == 0) {
            this.ZN.eT(i2);
        }
        if (this.mDialog.getWindow() != null) {
            WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
            attributes.gravity = 17;
            attributes.y = attributes.type != 9 ? this.ZO : 0;
            this.mDialog.getWindow().setAttributes(attributes);
        }
        this.mDialog.show();
    }

    public void dismiss() {
        fh("dismiss");
        this.mDialog.dismiss();
    }

    public boolean isShowing() {
        return this.mDialog.isShowing();
    }

    public c a(d.b bVar) {
        this.ZS = bVar;
        if (bVar != null) {
            qr();
        }
        return this;
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public c dX(int i) {
        if (this.mDialog.getWindow() != null) {
            this.mDialog.getWindow().setType(i);
        }
        return this;
    }

    public c aY(boolean z) {
        this.mDialog.setCanceledOnTouchOutside(z);
        return this;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        ZM--;
        fh(" finalize object size " + ZM);
    }

    /* compiled from: XDialog.java */
    /* loaded from: classes13.dex */
    public static class a {
        private boolean ZW = com.xiaopeng.xui.a.qp();
        private int mTheme;

        public static a qv() {
            return new a();
        }

        private a() {
        }
    }

    private void fh(String str) {
        com.xiaopeng.xui.d.f.g("XDialog", str + "--hashcode " + hashCode());
    }
}
