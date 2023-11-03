package com.xiaopeng.libbluetooth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/* loaded from: classes12.dex */
public class DemoActivity extends Activity {
    private static final String TAG = DemoActivity.class.getSimpleName();
    private f Xp;
    private g Xq;
    private e Xr;
    String[] Xs = new String[16];
    int[] Xt = new int[16];
    int[] Xu = new int[1];
    String[] Xv = new String[16];
    private b Xw = new b() { // from class: com.xiaopeng.libbluetooth.DemoActivity.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.libbluetooth.b
        public void a(String str, String str2, int i, int i2, boolean z) {
            String str3 = DemoActivity.TAG;
            Log.d(str3, "address = " + str + ",deviceName = " + str2 + ",cod = " + i + ",rssi = " + i2 + ",complete = " + z);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.libbluetooth.b
        public void onBindSuccess() {
            if (DemoActivity.this.Xp != null) {
                DemoActivity.this.Xp.pA();
                DemoActivity.this.Xp.a(DemoActivity.this.Xu, DemoActivity.this.Xs, DemoActivity.this.Xv, DemoActivity.this.Xt);
                String str = DemoActivity.TAG;
                Log.d(str, "count = " + DemoActivity.this.Xu[0]);
                for (int i = 0; i < DemoActivity.this.Xu[0]; i++) {
                    String str2 = DemoActivity.TAG;
                    Log.d(str2, "address = " + DemoActivity.this.Xv[i] + ",name = " + DemoActivity.this.Xs[i]);
                }
            }
        }
    };
    private c Xx = new c() { // from class: com.xiaopeng.libbluetooth.DemoActivity.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.libbluetooth.c
        public void a(int i, int i2, int i3, int i4, int i5, String str, String str2, String str3) {
            String str4 = DemoActivity.TAG;
            Log.d(str4, "status = " + i + ",dataType = " + i2 + ",cur = " + i3 + ",total = " + i4 + ",error = " + i5 + ",name = " + str + ",number=" + str2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.xiaopeng.libbluetooth.c
        public void onBindSuccess() {
            g unused = DemoActivity.this.Xq;
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_demo_layout);
        Log.d(TAG, "onCreate_before");
        this.Xr = e.pv();
        this.Xp = this.Xr.a(this.Xw);
        this.Xr.a((a) this.Xp);
        this.Xq = this.Xr.a(this.Xx);
        this.Xr.a((a) this.Xq);
        Log.d(TAG, "onCreate_after");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        e.pv().a(this.Xp);
        e.pv().a(this.Xq);
    }
}
