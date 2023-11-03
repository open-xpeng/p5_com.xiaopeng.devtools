package com.xiaopeng.xui.app;

import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.xiaopeng.xpui.R;

/* loaded from: classes13.dex */
public class XPanelActivity extends AppCompatActivity {
    private static final Uri aac = Settings.System.getUriFor("key_panel_car_speed");
    private a aam;
    private float aad = 0.0f;
    private final Runnable aae = new Runnable() { // from class: com.xiaopeng.xui.app.XPanelActivity.1
        @Override // java.lang.Runnable
        public void run() {
            XPanelActivity.this.qy();
        }
    };
    private final Handler aaf = new Handler();
    private boolean aag = false;
    private boolean aah = true;
    private float aai = 0.6f;
    private long aaj = 0;
    private boolean aak = false;
    private int aal = 0;
    private ContentObserver aan = new ContentObserver(this.aaf) { // from class: com.xiaopeng.xui.app.XPanelActivity.3
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            XPanelActivity.this.qz();
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        requestWindowFeature(14);
        super.onCreate(bundle);
        this.aam = new a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        int i = getResources().getConfiguration().orientation;
        int d = d(this, R.dimen.x_compat_app_panel_x);
        int d2 = d(this, R.dimen.x_compat_app_panel_y);
        int d3 = d(this, R.dimen.x_compat_app_panel_width);
        int d4 = d(this, R.dimen.x_compat_app_panel_height);
        switch (i) {
            case 1:
                qw().dZ(d).ea(d2).eb(d3).ec(d4).ed(80).apply();
                return;
            case 2:
                qw().dZ(d).ea(d2).eb(d3).ec(d4).ed(49).apply();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        bc(false);
        ba(true);
        ay(getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ba(false);
        az(getApplicationContext());
        if (this.aah) {
            qx();
        }
        this.aah = true;
    }

    @Override // android.app.Activity
    public void recreate() {
        super.recreate();
        this.aah = false;
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (shouldCloseOnTouch(this, motionEvent)) {
            qx();
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        bc(false);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
    }

    @Override // android.app.Activity
    public boolean moveTaskToBack(boolean z) {
        return super.moveTaskToBack(z);
    }

    public a qw() {
        return this.aam;
    }

    public void qx() {
        final int i = this.aal;
        Runnable runnable = new Runnable() { // from class: com.xiaopeng.xui.app.XPanelActivity.2
            @Override // java.lang.Runnable
            public void run() {
                switch (i) {
                    case 0:
                        com.xiaopeng.xui.app.a.a(XPanelActivity.this);
                        return;
                    case 1:
                        com.xiaopeng.xui.app.a.a(XPanelActivity.this, true);
                        return;
                    default:
                        return;
                }
            }
        };
        ba(false);
        this.aaf.postDelayed(runnable, this.aaj);
    }

    public void ba(boolean z) {
        if (this.aak) {
            bb(z);
        }
    }

    private void bb(boolean z) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (attributes != null) {
                attributes.alpha = z ? 1.0f : 0.0f;
                attributes.dimAmount = z ? this.aai : 0.0f;
            }
            window.setAttributes(attributes);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qy() {
        if (getSpeed() > 0.0f) {
            qx();
        }
    }

    private void bc(boolean z) {
        float speed = getSpeed();
        boolean z2 = true;
        if (z && this.aad * speed > 0.0f) {
            z2 = false;
        }
        if (speed <= 0.0f) {
            this.aaf.removeCallbacks(this.aae);
        } else if (z2) {
            this.aaf.removeCallbacks(this.aae);
            this.aaf.postDelayed(this.aae, 30000L);
        }
        this.aad = speed;
    }

    private float getSpeed() {
        try {
            return Settings.System.getFloat(getContentResolver(), "key_panel_car_speed", 0.0f);
        } catch (Exception e) {
            return 0.0f;
        }
    }

    private boolean a(Context context, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int scaledWindowTouchSlop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        View decorView = getWindow().getDecorView();
        int i = -scaledWindowTouchSlop;
        return x < i || y < i || x > decorView.getWidth() + scaledWindowTouchSlop || y > decorView.getHeight() + scaledWindowTouchSlop;
    }

    private boolean shouldCloseOnTouch(Context context, MotionEvent motionEvent) {
        return this.aag && getWindow().getDecorView() != null && ((motionEvent.getAction() == 0 && a(context, motionEvent)) || motionEvent.getAction() == 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qz() {
        bc(true);
    }

    private void ay(Context context) {
        try {
            context.getContentResolver().registerContentObserver(aac, true, this.aan);
        } catch (Exception e) {
        }
    }

    private void az(Context context) {
        try {
            context.getContentResolver().unregisterContentObserver(this.aan);
        } catch (Exception e) {
        }
    }

    /* loaded from: classes13.dex */
    public static final class a {
        private int flags;
        private int gravity;
        private int height;
        private final Activity mActivity;
        private int systemUiVisibility;
        private int width;
        private int x;
        private int y;

        public a(@NonNull Activity activity) {
            this.mActivity = activity;
            init();
        }

        private void init() {
            WindowManager.LayoutParams attributes;
            if (this.mActivity != null && this.mActivity.getWindow() != null && (attributes = this.mActivity.getWindow().getAttributes()) != null) {
                this.x = attributes.x;
                this.y = attributes.y;
                this.flags = attributes.flags;
                this.width = attributes.width;
                this.height = attributes.height;
                this.gravity = attributes.gravity;
                this.systemUiVisibility = attributes.systemUiVisibility;
            }
        }

        public void apply() {
            WindowManager.LayoutParams attributes;
            if (this.mActivity != null && this.mActivity.getWindow() != null && (attributes = this.mActivity.getWindow().getAttributes()) != null) {
                attributes.x = this.x;
                attributes.y = this.y;
                attributes.flags |= this.flags;
                attributes.width = this.width;
                attributes.height = this.height;
                attributes.gravity = this.gravity;
                attributes.systemUiVisibility |= this.systemUiVisibility;
                this.mActivity.getWindow().setAttributes(attributes);
            }
        }

        public a dZ(int i) {
            this.x = i;
            return this;
        }

        public a ea(int i) {
            this.y = i;
            return this;
        }

        public a eb(int i) {
            this.width = i;
            return this;
        }

        public a ec(int i) {
            this.height = i;
            return this;
        }

        public a ed(int i) {
            this.gravity = i;
            return this;
        }
    }

    public static int d(Context context, @DimenRes int i) {
        return (int) ((context.getResources().getDimension(i) * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
