package com.xiaopeng.xui.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.xiaopeng.xpui.R;

/* compiled from: XLoadingDialog.java */
/* loaded from: classes13.dex */
public class e extends Dialog implements Runnable {
    private ImageButton ZX;
    private a ZY;
    private int ZZ;
    private boolean aaa;
    private Handler mHandler;
    private TextView mMessageView;

    /* compiled from: XLoadingDialog.java */
    /* loaded from: classes13.dex */
    public interface a {
        void a(e eVar);
    }

    public e(@NonNull Context context, int i) {
        super(context, i);
        this.mHandler = new Handler();
    }

    public static e a(Context context, CharSequence charSequence, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        return a(context, charSequence, z, onCancelListener, false, 0, null);
    }

    public static e a(Context context, CharSequence charSequence, boolean z, DialogInterface.OnCancelListener onCancelListener, boolean z2, int i, a aVar) {
        e eVar = new e(context, R.style.XAppTheme_XDialog_Loading);
        eVar.create();
        eVar.setMessage(charSequence);
        eVar.setCancelable(z);
        eVar.setOnCancelListener(onCancelListener);
        eVar.a(aVar);
        eVar.aZ(z2);
        eVar.dY(i);
        eVar.show();
        return eVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.x_loading_dialog, (ViewGroup) null);
        this.mMessageView = (TextView) inflate.findViewById(R.id.x_loading_dialog_text);
        this.ZX = (ImageButton) inflate.findViewById(R.id.x_loading_dialog_close);
        this.ZX.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.xui.app.e.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                e.this.log("close is click ");
                e.this.cancel();
            }
        });
        setContentView(inflate);
        super.onCreate(bundle);
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessageView.setText(charSequence);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        super.setCancelable(false);
        this.ZX.setVisibility(z ? 0 : 8);
    }

    public void a(a aVar) {
        this.ZY = aVar;
    }

    public void dY(int i) {
        this.ZZ = i;
    }

    public void aZ(boolean z) {
        this.aaa = z;
        if (!this.aaa) {
            this.mHandler.removeCallbacks(this);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        if (this.aaa) {
            this.mHandler.removeCallbacks(this);
            this.mHandler.postDelayed(this, this.ZZ);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        log("dismiss");
        this.mHandler.removeCallbacks(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.ZY != null) {
            this.ZY.a(this);
        }
        log("time out");
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        com.xiaopeng.xui.d.f.g("xpui-XLoadingDialog", str);
    }
}
