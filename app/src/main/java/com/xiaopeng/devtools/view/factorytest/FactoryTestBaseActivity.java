package com.xiaopeng.devtools.view.factorytest;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d;
import com.xiaopeng.devtools.view.AppBaseActivity;
import com.xiaopeng.lib.utils.c;

/* loaded from: classes12.dex */
public class FactoryTestBaseActivity extends AppBaseActivity implements View.OnClickListener {
    private d Ep;
    private Button FR;
    private Button FS;
    private Button FT;
    private LinearLayout FU;
    private String FV;
    private boolean FW = true;

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        this.Ep = new b();
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FR = (Button) findViewById(R.id.btn_test_succeed);
        this.FS = (Button) findViewById(R.id.btn_test_fail);
        this.FT = (Button) findViewById(R.id.btn_test_next);
        this.FU = (LinearLayout) findViewById(R.id.layout_factory_test_common);
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.FR.setOnClickListener(this);
        this.FS.setOnClickListener(this);
        this.FT.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_test_fail) {
            mw();
        } else if (id == R.id.btn_test_next) {
            mx();
        } else if (id == R.id.btn_test_succeed) {
            mv();
        }
    }

    public void mv() {
        c.f("FactoryTestBaseActivity", "onTestSucceed");
        ar(true);
    }

    public void mw() {
        c.f("FactoryTestBaseActivity", "onTestFail");
        ar(false);
    }

    public void mx() {
        c.f("FactoryTestBaseActivity", "onTestNext");
    }

    private void ar(boolean z) {
        this.Ep.d(this.FV, z);
        finish();
    }

    public String getTarget() {
        return this.FV;
    }

    public void a(String str, boolean z, String str2) {
        if (this.FW) {
            this.Ep.a(str, z, str2);
        }
    }

    public void setTarget(String str) {
        this.FV = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void my() {
        this.FW = false;
        this.FU.setVisibility(8);
    }
}
