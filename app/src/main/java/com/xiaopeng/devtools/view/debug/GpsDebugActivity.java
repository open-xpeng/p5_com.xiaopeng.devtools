package com.xiaopeng.devtools.view.debug;

import android.os.Bundle;
import android.os.SystemProperties;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.b.a;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.b;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class GpsDebugActivity extends ActionBarActivity {
    private int CQ = 0;
    private int CS = 0;
    private CheckBox FH;
    private TextView FI;
    private TextView FJ;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                this.CS++;
                if (this.CS == this.CQ) {
                    this.FJ.setText(R.string.copyed_gps_nmea);
                    this.FH.setClickable(true);
                    j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.debug.GpsDebugActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            b.bn("/data/gps/");
                        }
                    });
                    return;
                }
                return;
            case 2:
                this.FJ.setText(R.string.fail_copy_gps_nmea);
                this.FH.setClickable(true);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_gps_debug);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FH = (CheckBox) findViewById(R.id.gps_nmea_data);
        this.FI = (TextView) findViewById(R.id.tv_gps_version);
        this.FJ = (TextView) findViewById(R.id.tv_gps_action_status);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.FH.setChecked("true".equals(SystemProperties.get("sys.gps.dump", "false")));
        ms();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.FH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.view.debug.-$$Lambda$GpsDebugActivity$gZdObJuzHAGAjhnsVQ8Hj6S0GZ4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GpsDebugActivity.this.g(compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(CompoundButton compoundButton, boolean z) {
        if (z) {
            SystemProperties.set("sys.gps.dump", "true");
            this.FJ.setText(R.string.catching_gps_nmea);
            return;
        }
        SystemProperties.set("sys.gps.dump", "false");
        this.FJ.setText(R.string.copying_gps_nmea);
        this.FH.setClickable(false);
        U("/data/gps/", "/data/Log/log0/gps/");
    }

    private void ms() {
        String str = "";
        for (int i = 1; i <= 5; i++) {
            str = (str + SystemProperties.get("sys.gps.version" + i)) + "\n";
        }
        this.FI.setText(str);
    }

    private void U(String str, String str2) {
        File file = new File(str);
        if (!g.aW(str2)) {
            c.f("GpsDebugActivity", "mkDir fail ");
        }
        c.f("GpsDebugActivity", "srcPath = " + str + " destPath = " + str2);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                c.f("GpsDebugActivity", "filesList[i].getName() =  " + listFiles[i].getName());
                if (listFiles[i].isFile()) {
                    this.CQ++;
                    j.execute(new a(listFiles[i].getPath(), str2 + listFiles[i].getName(), 0L, listFiles[i].length()));
                } else {
                    U(listFiles[i].getPath(), str2 + listFiles[i].getName() + "/");
                }
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
