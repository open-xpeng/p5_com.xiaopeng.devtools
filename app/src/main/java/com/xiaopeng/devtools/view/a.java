package com.xiaopeng.devtools.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.p;

/* compiled from: NewConfirmDialog.java */
/* loaded from: classes12.dex */
public class a extends Dialog implements View.OnClickListener {
    private ImageButton Db;
    private TextView Dc;
    private TextView Dd;
    private Button De;
    private Button Df;
    private LinearLayout Dg;
    private View Dh;
    private View Di;
    private View Dj;
    private AnimationSet Dk;
    private AnimationSet Dl;
    private Animation Dm;
    private boolean Dn;
    private InterfaceC0071a Do;
    private InterfaceC0071a Dp;
    private String Dq;
    private String Dr;
    private String Ds;
    private String Dt;
    private boolean Du;
    private float mDownX;
    private float mDownY;

    /* compiled from: NewConfirmDialog.java */
    /* renamed from: com.xiaopeng.devtools.view.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public interface InterfaceC0071a {
        void onClick(a aVar);
    }

    public a(Activity activity) {
        super(activity, R.style.new_confirm_dialog);
        this.mDownX = 0.0f;
        this.mDownY = 0.0f;
        this.Du = false;
        setOwnerActivity(activity);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        this.Dk = (AnimationSet) b.loadAnimation(getContext(), R.anim.modal_in);
        this.Dl = (AnimationSet) b.loadAnimation(getContext(), R.anim.modal_out);
        this.Dl.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaopeng.devtools.view.a.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                a.this.Dj.setVisibility(8);
                a.this.Dj.post(new Runnable() { // from class: com.xiaopeng.devtools.view.a.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (a.this.Dn) {
                            a.super.cancel();
                        } else {
                            a.super.dismiss();
                        }
                    }
                });
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.Dm = new Animation() { // from class: com.xiaopeng.devtools.view.a.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                WindowManager.LayoutParams attributes = a.this.getWindow().getAttributes();
                attributes.alpha = 1.0f - f;
                a.this.getWindow().setAttributes(attributes);
            }
        };
        this.Dm.setDuration(120L);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = (int) (p.ae(activity) * 1.0f);
        window.setAttributes(attributes);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.confirm_dialog);
        ff();
        lL();
        lU();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_confirm) {
            confirm();
        } else if (id == R.id.btn_cancel) {
            lT();
        } else if (id == R.id.ib_close) {
            lT();
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        if (this.Du) {
            this.Dj.startAnimation(this.Dk);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        Z(true);
    }

    public void lS() {
        Z(false);
    }

    private void Z(boolean z) {
        this.Dn = z;
        this.Df.startAnimation(this.Dm);
        this.Dj.startAnimation(this.Dl);
    }

    private void ff() {
        this.Dg = (LinearLayout) findViewById(R.id.view_container_content);
        this.Db = (ImageButton) findViewById(R.id.ib_close);
        this.Dc = (TextView) findViewById(R.id.text_view_title);
        this.Dd = (TextView) findViewById(R.id.text_view_content);
        this.De = (Button) findViewById(R.id.btn_cancel);
        this.Df = (Button) findViewById(R.id.btn_confirm);
        this.Dj = getWindow().getDecorView().findViewById(16908290);
        this.Dh = findViewById(R.id.view_dialog_root);
        this.Di = findViewById(R.id.view_dialog_content);
    }

    private void lL() {
        this.Dc.setText(this.Ds);
        this.Dd.setText(this.Dt);
        this.De.setText(this.Dq);
        this.Df.setText(this.Dr);
    }

    private void confirm() {
        if (this.Dp != null) {
            this.Dp.onClick(this);
        } else {
            lS();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lT() {
        if (this.Do != null) {
            this.Do.onClick(this);
        } else {
            lS();
        }
    }

    private void lU() {
        if (this.De != null) {
            this.De.setOnClickListener(this);
        }
        if (this.Df != null) {
            this.Df.setOnClickListener(this);
        }
        if (this.Db != null) {
            this.Db.setOnClickListener(this);
        }
        if (this.Di != null) {
            this.Di.setOnClickListener(this);
        }
        if (this.Dh != null) {
            this.Dh.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaopeng.devtools.view.a.3
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            a.this.mDownX = motionEvent.getX();
                            a.this.mDownY = motionEvent.getY();
                            if (a.this.Db != null) {
                                a.this.Db.requestFocus();
                                a.this.Db.setSelected(true);
                                break;
                            }
                            break;
                        case 1:
                            if (Math.abs(motionEvent.getX() - a.this.mDownX) >= 20.0f || Math.abs(motionEvent.getY() - a.this.mDownY) >= 20.0f) {
                                a.this.aa(false);
                                break;
                            } else {
                                a.this.lT();
                                break;
                            }
                            break;
                        case 2:
                            a.this.aa(true);
                            break;
                    }
                    return true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa(boolean z) {
        if (this.Db == null) {
            return;
        }
        if (z) {
            this.Db.requestFocus();
            this.Db.setSelected(true);
            return;
        }
        this.Db.clearFocus();
        this.Db.setSelected(false);
    }

    public void cO(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.Dq = str;
            if (this.De != null) {
                this.De.setText(str);
                this.De.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.Dr)) {
                this.Df.setVisibility(0);
                return;
            }
            return;
        }
        this.De.setVisibility(8);
    }

    public void cP(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.Dr = str;
            if (this.Df != null) {
                this.Df.setText(str);
                this.Df.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.Dq)) {
                this.De.setVisibility(0);
                return;
            }
            return;
        }
        this.Df.setVisibility(8);
    }

    public void cQ(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.Ds = str;
            if (this.Dc != null) {
                this.Dc.setText(str);
            }
        }
    }

    public void cR(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.Dt = str;
            if (this.Dd != null) {
                this.Dd.setText(str);
            }
        }
    }

    public void cH(int i) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.Di.getLayoutParams();
        layoutParams.height = i;
        layoutParams.topMargin = (getOwnerActivity().getWindow().getDecorView().getMeasuredHeight() - i) / 2;
        this.Di.setLayoutParams(layoutParams);
    }

    public void a(InterfaceC0071a interfaceC0071a) {
        this.Do = interfaceC0071a;
    }

    public void b(InterfaceC0071a interfaceC0071a) {
        this.Dp = interfaceC0071a;
    }
}
