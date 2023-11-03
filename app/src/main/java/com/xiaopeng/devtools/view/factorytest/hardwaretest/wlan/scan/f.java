package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;

/* compiled from: XpWifiDialog.java */
/* loaded from: classes12.dex */
public class f extends Dialog implements View.OnClickListener, e {
    private static final String TAG = f.class.getSimpleName();
    private ImageButton Db;
    private TextView Dc;
    private LinearLayout Dg;
    private View Dh;
    private View Di;
    private View Dj;
    private boolean Dn;
    private String Ds;
    private final AccessPoint Ny;
    private final boolean Nz;
    private a OA;
    private WifiConfigController OB;
    private Button Ou;
    private Button Ov;
    private Button Ow;
    private View Ox;
    private a Oy;
    private a Oz;
    private Context context;
    private float mDownX;
    private float mDownY;

    /* compiled from: XpWifiDialog.java */
    /* loaded from: classes12.dex */
    public interface a {
        void a(f fVar);
    }

    public f(Context context, AccessPoint accessPoint, boolean z) {
        super(context, R.style.wifi_alert_dialog);
        this.mDownX = 0.0f;
        this.mDownY = 0.0f;
        this.context = context;
        this.Nz = z;
        this.Ny = accessPoint;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = getLayoutInflater().inflate(R.layout.xp_wifi_dialog, (ViewGroup) null);
        setContentView(inflate);
        ff();
        lU();
        this.OB = new WifiConfigController(this, inflate, this.Ny, this.Nz);
        this.OB.ne();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ib_connect) {
            confirm();
        } else if (id == R.id.ib_forget) {
            ns();
        } else if (id == R.id.ib_close) {
            lT();
        } else if (id == R.id.ib_con_cancel && (view instanceof Button)) {
            if (((Button) view).getText().toString().equals(this.context.getString(R.string.wifi_connect))) {
                confirm();
            } else {
                ns();
            }
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
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
    }

    private void ff() {
        this.Dg = (LinearLayout) findViewById(R.id.info);
        this.Di = findViewById(R.id.view_dialog_content);
        this.Db = (ImageButton) findViewById(R.id.ib_close);
        this.Dc = (TextView) findViewById(R.id.wifi_title);
        this.Dj = getWindow().getDecorView().findViewById(16908290);
        this.Ow = (Button) findViewById(R.id.ib_con_cancel);
        this.Ov = (Button) findViewById(R.id.ib_forget);
        this.Ou = (Button) findViewById(R.id.ib_connect);
        this.Dh = findViewById(R.id.view_dialog_root);
        this.Ox = findViewById(R.id.connect_forget_layout);
    }

    private void confirm() {
        if (this.Oz != null) {
            this.Oz.a(this);
        } else {
            lS();
        }
    }

    private void ns() {
        if (this.OA != null) {
            this.OA.a(this);
        } else {
            lS();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lT() {
        if (this.Oy != null) {
            this.Oy.a(this);
        } else {
            lS();
        }
    }

    private void lU() {
        if (this.Ou != null) {
            this.Ou.setOnClickListener(this);
        }
        if (this.Ow != null) {
            this.Ow.setOnClickListener(this);
        }
        if (this.Ov != null) {
            this.Ov.setOnClickListener(this);
        }
        if (this.Db != null) {
            this.Db.setOnClickListener(this);
        }
        if (this.Di != null) {
            this.Di.setOnClickListener(this);
        }
        if (this.Dh != null) {
            this.Dh.setOnTouchListener(new View.OnTouchListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.f.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            f.this.mDownX = motionEvent.getX();
                            f.this.mDownY = motionEvent.getY();
                            if (f.this.Db != null) {
                                f.this.Db.requestFocus();
                                f.this.Db.setSelected(true);
                                break;
                            }
                            break;
                        case 1:
                            float abs = Math.abs(motionEvent.getX());
                            float abs2 = Math.abs(motionEvent.getY());
                            float x = f.this.Di.getX();
                            float y = f.this.Di.getY();
                            if (abs < x || abs > x + f.this.Di.getMeasuredWidth() || abs2 > f.this.Di.getMeasuredHeight() + y || abs2 < y) {
                                f.this.lT();
                                break;
                            }
                            break;
                        case 2:
                            f.this.aa(true);
                            break;
                    }
                    return true;
                }
            });
        }
    }

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

    public void a(a aVar) {
        this.Oz = aVar;
    }

    public void b(a aVar) {
        this.Oy = aVar;
    }

    public void c(a aVar) {
        this.OA = aVar;
    }

    public WifiConfigController nw() {
        return this.OB;
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.e
    public void dq(int i) {
        String string = this.context.getString(i);
        if (!TextUtils.isEmpty(string)) {
            this.Ds = string;
            if (this.Dc != null) {
                this.Dc.setText(string);
            }
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.e
    public void cQ(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.Ds = str;
            if (this.Dc != null) {
                this.Dc.setText(str);
            }
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.e
    public void dn(String str) {
        this.Ox.setVisibility(8);
        this.Ow.setVisibility(0);
        this.Ow.setText(str);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.e
    public void nt() {
        this.Ow.setVisibility(8);
        this.Ox.setVisibility(0);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.e
    public boolean nu() {
        if (this.Ow.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.e
    public Button nv() {
        return this.Ow;
    }
}
