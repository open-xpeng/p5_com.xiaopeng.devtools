package com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.c;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.app.g;

/* loaded from: classes12.dex */
public class XpuTestActivity extends AsOrFacBaseActivity implements SurfaceHolder.Callback {
    private SurfaceView Jv;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.c.a Jw;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        new c(this).e(getString(R.string.title_dialog)).f(getString(R.string.xpu_warning)).a(getString(R.string.dialog_confirm), (d.a) null).b(getString(R.string.cancel), (d.a) null).show(5, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.Jw.gq();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_test_xpu);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Jv = (SurfaceView) findViewById(R.id.camera_preview);
        this.Jw = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.c.a(this.Jv.getHolder());
        this.Jv.getHolder().addCallback(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    private void cW(final int i) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.-$$Lambda$XpuTestActivity$a8vv0sSJDPBRK_8xLRjqQ9yBfLY
            @Override // java.lang.Runnable
            public final void run() {
                XpuTestActivity.this.cX(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cX(int i) {
        try {
            this.Jw.gq();
            this.Jw.cd(i);
            this.Jw.gp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        com.xiaopeng.lib.utils.c.f("XpuTestActivity", "surfaceCreated");
        int numberOfCameras = this.Jw.getNumberOfCameras();
        com.xiaopeng.lib.utils.c.f("XpuTestActivity", "camera num:" + numberOfCameras);
        if (numberOfCameras > 0) {
            cW(4);
            return;
        }
        com.xiaopeng.lib.utils.c.f("XpuTestActivity", "surfaceCreated but not found camera");
        g.show(R.string.no_camera);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        com.xiaopeng.lib.utils.c.f("XpuTestActivity", "surfaceChanged format = " + i + " , width = " + i2 + " , height = " + i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        com.xiaopeng.lib.utils.c.f("XpuTestActivity", "surfaceDestroyed");
        this.Jw.gq();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Jw.gq();
    }
}
