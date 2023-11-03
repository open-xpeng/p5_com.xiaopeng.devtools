package com.xiaopeng.devtools.view.factorytest.hardwaretest.camera;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.c.a;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.g;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class CameraTestActivity extends AsOrFacBaseActivity implements SurfaceHolder.Callback {
    private Button Jo;
    private Button Jp;
    private Button Jq;
    private Button Jr;
    private Button Js;
    private Button Jt;
    private TextView Ju;
    private SurfaceView Jv;
    private a Jw;
    private int Jx = 2;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.theme == 1) {
            recordRepairModeAction("camera diagnosis", "triggered");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.Jw.gu();
        this.Jw.gq();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_test_camera_2);
        setTarget(getString(R.string.test_camera));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Jo = (Button) findViewById(R.id.btn_cam0);
        this.Jp = (Button) findViewById(R.id.btn_cam1);
        this.Jq = (Button) findViewById(R.id.btn_cam2);
        this.Jr = (Button) findViewById(R.id.btn_cam3);
        this.Js = (Button) findViewById(R.id.btn_camera_record);
        this.Jt = (Button) findViewById(R.id.btn_take_picture);
        this.Ju = (TextView) findViewById(R.id.tv_cur_camera);
        this.Jv = (SurfaceView) findViewById(R.id.camera_preview);
        this.Jw = new a(this.Jv.getHolder());
        this.Jv.getHolder().addCallback(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.theme == 1) {
            this.Js.setVisibility(8);
            this.Jt.setVisibility(8);
        }
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Jo.setOnClickListener(this);
        this.Jp.setOnClickListener(this);
        this.Jq.setOnClickListener(this);
        this.Jr.setOnClickListener(this);
        this.Js.setOnClickListener(this);
        this.Jt.setOnClickListener(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id != R.id.btn_take_picture) {
            switch (id) {
                case R.id.btn_cam0 /* 2131296457 */:
                    cW(4);
                    return;
                case R.id.btn_cam1 /* 2131296458 */:
                    cW(1);
                    return;
                case R.id.btn_cam2 /* 2131296459 */:
                    cW(2);
                    return;
                case R.id.btn_cam3 /* 2131296460 */:
                    cW(3);
                    return;
                case R.id.btn_camera_record /* 2131296461 */:
                    if (this.Jw.gs()) {
                        c.f("CameraTestActivity", "stop Record");
                        this.Jw.stopRecording();
                        this.Js.setText(R.string.camera_record_start);
                        return;
                    } else if (this.Jw.gr()) {
                        c.f("CameraTestActivity", "start record");
                        this.Js.setText(R.string.camera_record_stop);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
        this.Jw.gv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_camera));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_camera));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    private void cW(final int i) {
        this.Js.setEnabled(false);
        this.Jt.setEnabled(false);
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.-$$Lambda$CameraTestActivity$dsHBULMNFFknUW2BPuQWbtsfBH4
            @Override // java.lang.Runnable
            public final void run() {
                CameraTestActivity.this.cX(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void cX(int i) {
        if (this.Jw.gs()) {
            this.Jw.stopRecording();
        }
        boolean z = true;
        try {
            this.Jw.gq();
            this.Jw.cd(i);
            this.Jw.gp();
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        f(i, z);
    }

    private void f(final int i, final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.-$$Lambda$CameraTestActivity$j9ZVdqlHgAV_X_lnsfToYXiSsBs
            @Override // java.lang.Runnable
            public final void run() {
                CameraTestActivity.this.d(z, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(boolean z, int i) {
        this.Js.setText(R.string.camera_record_start);
        if (z) {
            this.Jt.setEnabled(true);
            this.Js.setEnabled(true);
        } else {
            g.show(R.string.camera_preview_fail);
        }
        switch (i) {
            case 0:
                this.Ju.setText(getString(R.string.cur_camera_is, new Object[]{getString(R.string.test_car_camera_0)}));
                return;
            case 1:
                this.Ju.setText(getString(R.string.cur_camera_is, new Object[]{getString(R.string.test_car_camera_1)}));
                return;
            case 2:
                this.Ju.setText(getString(R.string.cur_camera_is, new Object[]{getString(R.string.test_car_camera_2)}));
                return;
            case 3:
                this.Ju.setText(getString(R.string.cur_camera_is, new Object[]{getString(R.string.test_car_camera_3)}));
                return;
            default:
                return;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        c.f("CameraTestActivity", "surfaceCreated");
        int numberOfCameras = this.Jw.getNumberOfCameras();
        c.f("CameraTestActivity", "camera num:" + numberOfCameras);
        if (numberOfCameras > 0) {
            cW(this.Jx);
            return;
        }
        c.f("CameraTestActivity", "surfaceCreated but not found camera");
        g.show(R.string.no_camera);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        c.f("CameraTestActivity", "surfaceChanged format = " + i + " , width = " + i2 + " , height = " + i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        c.f("CameraTestActivity", "surfaceDestroyed");
        this.Jw.gq();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Jw.gq();
    }
}
