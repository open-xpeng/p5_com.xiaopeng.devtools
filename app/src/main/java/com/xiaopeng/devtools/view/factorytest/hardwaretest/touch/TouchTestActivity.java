package com.xiaopeng.devtools.view.factorytest.hardwaretest.touch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.xiaopeng.devtools.view.AppBaseActivity;
import com.xiaopeng.lib.utils.c;
import java.lang.reflect.Array;

/* loaded from: classes12.dex */
public class TouchTestActivity extends AppBaseActivity {
    private int Mn;
    private int Mo;
    private boolean[][] Mp;
    private boolean[][] Mq;
    private boolean[][] Mr;
    private int Ms;
    private int Mt;
    private int Mu;
    private int Mv;
    private int Mw;
    private int Mx;
    private BroadcastReceiver My = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.touch.TouchTestActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.xiaopeng.action.TOUCH_TEST_END")) {
                TouchTestActivity.this.finish();
            }
        }
    };

    private void init() {
        if (getResources().getConfiguration().orientation == 1) {
            this.Mn = 51;
            this.Mo = 25;
        } else {
            this.Mn = 27;
            this.Mo = 49;
        }
        this.Mp = (boolean[][]) Array.newInstance(boolean.class, this.Mn, this.Mo);
        this.Mq = (boolean[][]) Array.newInstance(boolean.class, this.Mn, this.Mo);
        this.Mr = (boolean[][]) Array.newInstance(boolean.class, this.Mn, this.Mo);
        this.Ms = 0;
        this.Mt = this.Mn - 1;
        this.Mu = this.Mn / 2;
        this.Mv = 0;
        this.Mw = this.Mo - 1;
        this.Mx = this.Mo / 2;
    }

    private void mR() {
        this.Mp = (boolean[][]) Array.newInstance(boolean.class, this.Mn, this.Mo);
        this.Mq = (boolean[][]) Array.newInstance(boolean.class, this.Mn, this.Mo);
        this.Mr = (boolean[][]) Array.newInstance(boolean.class, this.Mn, this.Mo);
        this.Ms = 0;
        this.Mt = this.Mn - 1;
        this.Mu = this.Mn / 2;
        this.Mv = 0;
        this.Mw = this.Mo - 1;
        this.Mx = this.Mo / 2;
    }

    private void mS() {
        for (int i = 0; i < this.Mn; i++) {
            for (int i2 = 0; i2 < this.Mo; i2++) {
                if (!u(i, i2)) {
                    this.Mr[i][i2] = false;
                } else {
                    this.Mr[i][i2] = true;
                }
            }
        }
    }

    private boolean u(int i, int i2) {
        return i == this.Ms || i == this.Mt || i == this.Mu || i2 == this.Mv || i2 == this.Mw || i2 == this.Mx;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean mT() {
        int i = 0;
        boolean z = true;
        while (i < this.Mn) {
            boolean z2 = z;
            for (int i2 = 0; i2 < this.Mo; i2++) {
                if (this.Mr[i][i2]) {
                    z2 = z2 && this.Mq[i][i2];
                }
            }
            i++;
            z = z2;
        }
        return z;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.My != null) {
            unregisterReceiver(this.My);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        c.i("TouchTestActivity", "onKeyUp [%d]", Integer.valueOf(i));
        if (i == 4) {
            finish();
        }
        return super.onKeyUp(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
        mR();
        registerReceiver(this.My, new IntentFilter("com.xiaopeng.action.TOUCH_TEST_END"));
        setContentView(new TouchView(this));
        mS();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.theme == 1) {
            recordRepairModeAction("touch panel diagnosis", "triggered");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    /* loaded from: classes12.dex */
    public class TouchView extends View {
        private int MA;
        private int MB;
        private float MC;
        private float MD;
        private float ME;
        private float MF;
        private Bitmap MG;
        private Bitmap MH;
        private Canvas MI;
        private Canvas MJ;
        private Paint MK;
        private Paint ML;
        private Paint MM;
        private boolean MN;

        public TouchView(Context context) {
            super(context);
            this.MC = 0.0f;
            this.MD = 0.0f;
            this.ME = 0.0f;
            this.MF = 0.0f;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            this.MA = displayMetrics.widthPixels;
            this.MB = displayMetrics.heightPixels;
            c.f("TouchTestActivity", "Screen size: " + this.MA + " x " + this.MB);
            this.MH = Bitmap.createBitmap(this.MA, this.MB, Bitmap.Config.ARGB_8888);
            this.MJ = new Canvas(this.MH);
            this.MJ.drawColor(-1);
            this.MG = Bitmap.createBitmap(this.MA, this.MB, Bitmap.Config.ARGB_8888);
            this.MI = new Canvas(this.MG);
            mU();
            mV();
            this.MN = false;
        }

        private void mU() {
            this.MK = new Paint();
            this.MK.setAntiAlias(true);
            this.MK.setDither(true);
            this.MK.setColor(-16777216);
            this.MK.setStyle(Paint.Style.STROKE);
            this.MK.setStrokeJoin(Paint.Join.ROUND);
            this.MK.setStrokeCap(Paint.Cap.SQUARE);
            this.MK.setStrokeWidth(5.0f);
            this.ML = new Paint();
            this.ML.setAntiAlias(false);
            this.ML.setColor(-16711936);
            this.MM = new Paint();
            this.MM.setAntiAlias(false);
            this.MM.setColor(-1);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(this.MH, 0.0f, 0.0f, (Paint) null);
            canvas.drawBitmap(this.MG, 0.0f, 0.0f, (Paint) null);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    a(motionEvent);
                    return true;
                case 1:
                    c(motionEvent);
                    return true;
                case 2:
                    b(motionEvent);
                    return true;
                default:
                    return true;
            }
        }

        private void a(MotionEvent motionEvent) {
            this.ME = motionEvent.getX();
            this.MF = motionEvent.getY();
            a(this.ME, this.MF, this.ML);
            this.MN = true;
        }

        private void b(MotionEvent motionEvent) {
            if (this.MN) {
                for (int i = 0; i < motionEvent.getHistorySize(); i++) {
                    this.MC = this.ME;
                    this.MD = this.MF;
                    this.ME = motionEvent.getHistoricalX(i);
                    this.MF = motionEvent.getHistoricalY(i);
                    a(this.ME, this.MF, this.ML);
                    b(this.MC, this.MD, this.ME, this.MF);
                }
                this.MC = this.ME;
                this.MD = this.MF;
                this.ME = motionEvent.getX();
                this.MF = motionEvent.getY();
                a(this.ME, this.MF, this.ML);
                b(this.MC, this.MD, this.ME, this.MF);
                this.MN = true;
            }
        }

        private void c(MotionEvent motionEvent) {
            if (this.MN) {
                this.MC = this.ME;
                this.MD = this.MF;
                this.ME = motionEvent.getX();
                this.MF = motionEvent.getY();
                if (this.MC == this.ME && this.MD == this.MF) {
                    c(this.ME, this.MF);
                }
                this.MN = false;
            }
        }

        private void b(float f, float f2, float f3, float f4) {
            int i;
            int i2;
            int i3;
            int i4;
            this.MI.drawLine(f, f2, f3, f4, this.MK);
            if (f >= f3) {
                int i5 = (int) f3;
                i = (int) f;
                i2 = i5;
            } else {
                i = (int) f3;
                i2 = (int) f;
            }
            if (f2 >= f4) {
                int i6 = (int) f4;
                i3 = (int) f2;
                i4 = i6;
            } else {
                i3 = (int) f4;
                i4 = (int) f2;
            }
            invalidate(new Rect(i2 - 6, i4 - 6, i + 6, i3 + 6));
        }

        private void c(float f, float f2) {
            this.MI.drawPoint(f, f2, this.MK);
            int i = (int) f;
            int i2 = (int) f2;
            invalidate(new Rect(i - 6, i2 - 6, i + 6, i2 + 6));
        }

        private void mV() {
            float f = this.MB / TouchTestActivity.this.Mn;
            float f2 = this.MA / TouchTestActivity.this.Mo;
            Paint paint = new Paint();
            paint.setColor(-16777216);
            for (int i = 0; i < TouchTestActivity.this.Mn; i++) {
                int i2 = (int) (i * f);
                for (int i3 = 0; i3 < TouchTestActivity.this.Mo; i3++) {
                    float f3 = (int) (i3 * f2);
                    float f4 = i2;
                    this.MJ.drawLine(f3, f4, this.MA, f4, paint);
                    this.MJ.drawLine(f3, f4, f3, this.MB, paint);
                    TouchTestActivity.this.Mq[i][i3] = false;
                    TouchTestActivity.this.Mp[i][i3] = false;
                }
            }
            float f5 = f2 + 1.0f;
            float f6 = f + 1.0f;
            this.MJ.drawRect(f5, f6, ((TouchTestActivity.this.Mo / 2) * f2) - 1.0f, ((TouchTestActivity.this.Mn / 2) * f) - 1.0f, this.MM);
            this.MJ.drawRect((((TouchTestActivity.this.Mo / 2) + 1) * f2) + 1.0f, f6, ((TouchTestActivity.this.Mo - 1) * f2) - 1.0f, ((TouchTestActivity.this.Mn / 2) * f) - 1.0f, this.MM);
            this.MJ.drawRect(f5, (((TouchTestActivity.this.Mn / 2) + 1) * f) + 1.0f, ((TouchTestActivity.this.Mo / 2) * f2) - 1.0f, ((TouchTestActivity.this.Mn - 1) * f) - 1.0f, this.MM);
            this.MJ.drawRect((((TouchTestActivity.this.Mo / 2) + 1) * f2) + 1.0f, (((TouchTestActivity.this.Mn / 2) + 1) * f) + 1.0f, (f2 * (TouchTestActivity.this.Mo - 1)) - 1.0f, (f * (TouchTestActivity.this.Mn - 1)) - 1.0f, this.MM);
        }

        private void a(float f, float f2, Paint paint) {
            float f3 = this.MB / TouchTestActivity.this.Mn;
            float f4 = this.MA / TouchTestActivity.this.Mo;
            int i = (int) (f / f4);
            int i2 = (int) (f2 / f3);
            float f5 = i * f4;
            float f6 = i2 * f3;
            if (i2 <= TouchTestActivity.this.Mn - 1 && i <= TouchTestActivity.this.Mo - 1) {
                if (!TouchTestActivity.this.Mq[i2][i]) {
                    TouchTestActivity.this.Mq[i2][i] = true;
                    if (TouchTestActivity.this.Mq[i2][i] && TouchTestActivity.this.Mr[i2][i]) {
                        this.MJ.drawRect(((int) f5) + 1, ((int) f6) + 1, (int) (f5 + f4), (int) (f6 + f3), paint);
                    } else {
                        this.MJ.drawRect(((int) f5) + 1, ((int) f6) + 1, (int) (f5 + f4), (int) (f6 + f3), this.MM);
                    }
                    invalidate(new Rect((int) (f5 - 1.0f), (int) (f6 - 1.0f), (int) (f5 + f4 + 1.0f), (int) (f6 + f3 + 1.0f)));
                }
                if (TouchTestActivity.this.mT()) {
                    TouchTestActivity.this.sendBroadcast(new Intent("com.xiaopeng.action.TOUCH_TEST_END"));
                    return;
                }
                return;
            }
            c.d("TouchTestActivity", "drawRect", "Out of bounds");
        }
    }
}
