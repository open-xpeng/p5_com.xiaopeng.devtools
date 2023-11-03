package com.xiaopeng.devtools.view.factorytest.hardwaretest.gps;

import android.location.GpsSatellite;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.b;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.j;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class GPSTestActivity extends AsOrFacBaseActivity implements a {
    private TextView JE;
    private b JG;
    private TextView JH;
    private TextView JI;
    private TextView JJ;
    private TextView JK;
    private TextView JL;
    private TextView JM;
    private ListView JN;
    private Button JO;
    private Button JP;
    private Button JQ;
    private GPSSatelliteAdapter JR;
    private ViewGroup JS;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_gps_test);
        this.JG = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.a(this);
        this.JR = new GPSSatelliteAdapter();
        setTarget(getString(R.string.test_gps));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.JH = (TextView) findViewById(R.id.show_gps_on_off_tv);
        this.JE = (TextView) findViewById(R.id.show_gps_satellite_number_tv);
        this.JI = (TextView) findViewById(R.id.gps_location_info_tv);
        this.JJ = (TextView) findViewById(R.id.gps_acc_value);
        this.JK = (TextView) findViewById(R.id.gps_gyro_value);
        this.JL = (TextView) findViewById(R.id.auto_mount_onoff);
        this.JM = (TextView) findViewById(R.id.auto_mount_status);
        this.JN = (ListView) findViewById(R.id.gps_cnr_lv);
        this.JO = (Button) findViewById(R.id.btn_hot_reboot);
        this.JP = (Button) findViewById(R.id.btn_warm_reboot);
        this.JQ = (Button) findViewById(R.id.btn_cold_reboot);
        this.JS = (ViewGroup) findViewById(R.id.auto_mount_layout);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.JG.setTheme(this.theme);
        if (!a.b.getBoolean("SUPPORT_GPS_IMU_SYSST")) {
            this.JS.setVisibility(8);
        }
        this.JN.setAdapter((ListAdapter) this.JR);
        if (!this.JG.gA()) {
            this.JH.setText(getString(R.string.gps_failure));
            a(getString(R.string.gps_on_off_test), false, "未找到GPS设备");
        }
        if (this.JG.gz()) {
            this.JH.setText(getString(R.string.gps_enable));
            a(getString(R.string.gps_on_off_test), true, "");
            this.JG.a(this.JG.getLastKnownLocation());
            return;
        }
        this.JG.gw();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.JG.a(this.JG.getLastKnownLocation());
        if (this.theme == 1) {
            recordRepairModeAction("gps diagnosis", "triggered");
        }
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.JO.setOnClickListener(this);
        this.JP.setOnClickListener(this);
        this.JQ.setOnClickListener(this);
        this.JG.gB();
        this.JG.gD();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_cold_reboot) {
            this.JG.setGpsReset(2);
        } else if (id == R.id.btn_hot_reboot) {
            this.JG.setGpsReset(0);
        } else if (id == R.id.btn_warm_reboot) {
            this.JG.setGpsReset(1);
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        a(getString(R.string.gps_location_test), true, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_gps));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        a(getString(R.string.gps_location_test), false, "");
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_gps));
        super.mw();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.JG.gC();
        this.JG.gy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a
    public void iX() {
        this.JH.setText(getString(R.string.gps_enable));
        a(getString(R.string.gps_on_off_test), true, "");
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a
    public void bX(final String str) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.GPSTestActivity.1
            @Override // java.lang.Runnable
            public void run() {
                GPSTestActivity.this.JI.setText(str);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a
    public void a(final int i, final List<GpsSatellite> list) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.-$$Lambda$GPSTestActivity$MG2DuKjvO3j-o6JYxdJQO6cjXz4
            @Override // java.lang.Runnable
            public final void run() {
                GPSTestActivity.this.b(i, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(int i, List list) {
        if (this.JE != null) {
            TextView textView = this.JE;
            textView.setText(i + "");
        }
        if (this.JN != null) {
            this.JR.setGpsSatellites(list);
            this.JR.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(float[] fArr) {
        this.JJ.setText(getString(R.string.gps_acc_sensor_value_format, new Object[]{Float.valueOf(fArr[0]), Float.valueOf(fArr[1]), Float.valueOf(fArr[2])}));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a
    public void a(final float[] fArr, long j) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.-$$Lambda$GPSTestActivity$uXWW9FErZCpUTUPyId_Jd98DxBY
            @Override // java.lang.Runnable
            public final void run() {
                GPSTestActivity.this.c(fArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(float[] fArr, float f) {
        this.JK.setText(getString(R.string.gps_gyro_sensor_value_format, new Object[]{Float.valueOf(fArr[0]), Float.valueOf(fArr[1]), Float.valueOf(fArr[2]), Float.valueOf(f)}));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a
    public void a(final float[] fArr, long j, final float f) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.-$$Lambda$GPSTestActivity$fPmGy7-E_G5lSbHgSCeLC6FmeAU
            @Override // java.lang.Runnable
            public final void run() {
                GPSTestActivity.this.a(fArr, f);
            }
        });
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.a
    public void o(final int i, final int i2) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.gps.-$$Lambda$GPSTestActivity$zU8DGs4G46T0-TkYILhw1HkfQho
            @Override // java.lang.Runnable
            public final void run() {
                GPSTestActivity.this.s(i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(int i, int i2) {
        this.JL.setText(i == 1 ? R.string.gps_auto_mount_alg_running : R.string.gps_auto_mount_alg_not_running);
        switch (i2) {
            case 0:
                this.JM.setText(R.string.gps_user_angles_used);
                return;
            case 1:
                this.JM.setText(R.string.gps_roll_pitch_alignment_ongoing);
                return;
            case 2:
                this.JM.setText(R.string.gps_roll_pitch_yaw_alignment_ongoing);
                return;
            case 3:
                this.JM.setText(R.string.gps_coarse_alignment_used);
                return;
            case 4:
                this.JM.setText(R.string.gps_fine_alignment_used);
                return;
            default:
                this.JM.setText(R.string.state_unknown);
                return;
        }
    }
}
