package com.xiaopeng.devtools.view.usersettings;

import android.util.SparseArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;

/* loaded from: classes12.dex */
public class CfcActivity extends ActionBarActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private SparseArray<RadioButton> Hd;
    private RadioButton Um;
    private RadioButton Un;
    private RadioButton Uo;
    private RadioButton Up;
    private RadioButton Uq;
    private RadioButton Ur;
    private RadioButton Us;
    private RadioButton Ut;
    private RadioButton Uu;
    private RadioButton Uv;
    private int index = 0;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_cfc_set);
        this.Hd = new SparseArray<>();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Um = (RadioButton) findViewById(R.id.config_0);
        this.Um.setTag(0);
        this.Hd.put(0, this.Um);
        this.Un = (RadioButton) findViewById(R.id.config_1);
        this.Un.setTag(1);
        this.Hd.put(1, this.Un);
        this.Uo = (RadioButton) findViewById(R.id.config_2);
        this.Uo.setTag(2);
        this.Hd.put(2, this.Uo);
        this.Up = (RadioButton) findViewById(R.id.config_3);
        this.Up.setTag(3);
        this.Hd.put(3, this.Up);
        this.Uq = (RadioButton) findViewById(R.id.config_4);
        this.Uq.setTag(4);
        this.Hd.put(4, this.Uq);
        this.Ur = (RadioButton) findViewById(R.id.config_5);
        this.Ur.setTag(5);
        this.Hd.put(5, this.Ur);
        this.Us = (RadioButton) findViewById(R.id.config_6);
        this.Us.setTag(6);
        this.Hd.put(6, this.Us);
        this.Ut = (RadioButton) findViewById(R.id.config_7);
        this.Ut.setTag(7);
        this.Hd.put(7, this.Ut);
        this.Uu = (RadioButton) findViewById(R.id.config_8);
        this.Uu.setTag(8);
        this.Hd.put(8, this.Uu);
        this.Uv = (RadioButton) findViewById(R.id.config_9);
        this.Uv.setTag(9);
        this.Hd.put(9, this.Uv);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.index = r.lx();
        c.f("CfcActivity", "setView index:" + this.index);
        this.Hd.get(this.index).setChecked(true);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        findViewById(R.id.bt_set_cfc).setOnClickListener(this);
        this.Um.setOnCheckedChangeListener(this);
        this.Un.setOnCheckedChangeListener(this);
        this.Uo.setOnCheckedChangeListener(this);
        this.Up.setOnCheckedChangeListener(this);
        this.Uq.setOnCheckedChangeListener(this);
        this.Ur.setOnCheckedChangeListener(this);
        this.Us.setOnCheckedChangeListener(this);
        this.Ut.setOnCheckedChangeListener(this);
        this.Uu.setOnCheckedChangeListener(this);
        this.Uv.setOnCheckedChangeListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        c.e("CfcActivity", "onClick v=" + ((Object) view.getIterableTextForAccessibility()));
        if (view.getId() == R.id.bt_set_cfc) {
            j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.usersettings.-$$Lambda$CfcActivity$zvJjHs5jVyHksCdW1ly7-HKtFRg
                @Override // java.lang.Runnable
                public final void run() {
                    CfcActivity.this.nZ();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void nZ() {
        c.f("CfcActivity", "onClick index:" + this.index);
        r.cF(this.index);
        m.f(MyApplication.getContext(), "CfcActivity");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        c.e("CfcActivity", "onCheckedChanged buttonView=" + ((Object) compoundButton.getText()) + ";isChecked=" + z);
        if (z) {
            this.index = compoundButton.getTag() != null ? ((Integer) compoundButton.getTag()).intValue() : 0;
        }
    }
}
