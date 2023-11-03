package com.xiaopeng.devtools.view.smartdrive.bcan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ActionBarActivity;

/* loaded from: classes12.dex */
public class BCanCollectActivity extends ActionBarActivity implements View.OnClickListener {
    private Button Tk;
    private Button Tl;
    private Button Tm;
    private Button Tn;
    private View To;
    private TimeCountView Tp;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_bcan_collect);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Tk = (Button) findViewById(R.id.btn_start_collect);
        this.Tl = (Button) findViewById(R.id.btn_continue_collect);
        this.Tm = (Button) findViewById(R.id.btn_upload_log);
        this.Tn = (Button) findViewById(R.id.btn_cancel_continue);
        this.To = findViewById(R.id.layout_bcan_option);
        this.Tp = (TimeCountView) findViewById(R.id.tv_show_time);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        d(this.To, 8);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Tk.setOnClickListener(this);
        this.Tl.setOnClickListener(this);
        this.Tm.setOnClickListener(this);
        this.Tn.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        dw(2);
        this.Tp.nR();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel_continue) {
            dw(3);
            this.Tk.setEnabled(true);
            this.Tk.setText(R.string.start_collect_adas);
            d(this.To, 8);
            this.Tp.nR();
        } else if (id == R.id.btn_continue_collect) {
            dw(0);
            this.Tk.setEnabled(true);
            this.Tk.setText(R.string.stop_collect);
            d(this.To, 8);
            this.Tp.nQ();
        } else if (id != R.id.btn_start_collect) {
            if (id == R.id.btn_upload_log) {
                dw(2);
                this.Tk.setEnabled(true);
                this.Tk.setText(R.string.start_collect_adas);
                d(this.To, 8);
                this.Tp.nR();
            }
        } else if (view instanceof Button) {
            if (getString(R.string.start_collect_adas).equals((String) ((Button) view).getText())) {
                this.Tk.setText(R.string.stop_collect);
                dw(0);
                d(this.To, 8);
                this.Tp.nQ();
                return;
            }
            this.Tk.setEnabled(false);
            d(this.To, 0);
            dw(1);
            this.Tp.nS();
        }
    }

    private void dw(int i) {
        Bundle bundle = new Bundle();
        MyApplication myApplication = MyApplication.qx;
        MyApplication.fd().sendData(i, bundle, "com.xiaopeng.data.collector");
    }
}
