package com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd;

import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class ScreenSelftestActivity extends AsOrFacBaseActivity implements CompoundButton.OnCheckedChangeListener, a {
    private Switch Kq;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e.a Kr;
    private ScreenSelfAdapter Ks;
    private TextView Kt;
    private TextView Ku;
    private ListView mListView;

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_screen_selftest);
        setTarget(getString(R.string.test_screen_selftest));
        this.Kr = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e.a(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Kq = (Switch) findViewById(R.id.switch_dismode_on_off);
        this.Kt = (TextView) findViewById(R.id.tv_count_total);
        this.mListView = (ListView) findViewById(R.id.listview);
        this.Ku = (TextView) findViewById(R.id.tv_current_result);
        this.Ks = new ScreenSelfAdapter(null);
        this.mListView.setAdapter((ListAdapter) this.Ks);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        c.g("ScreenSelftestActivity", "setView");
        this.Kr.iY();
        this.Kq.setChecked(this.Kr.jb());
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Kq.setOnCheckedChangeListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.Kr.ja();
        if (this.theme == 1) {
            recordRepairModeAction("screen diagnosis", "triggered");
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        if (compoundButton.getId() == R.id.switch_dismode_on_off) {
            j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.-$$Lambda$ScreenSelftestActivity$qO6n-bXTzdM9g34kE_BYSXDMcJg
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenSelftestActivity.this.av(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void av(boolean z) {
        this.Kr.K(z);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Kr.iZ();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_screen_selftest));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_screen_selftest));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.a
    public void a(boolean z, String str) {
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.a
    public void d(ArrayList<ArrayList<String>> arrayList) {
        this.Ks.e(arrayList);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.a
    public void cY(int i) {
        TextView textView = this.Kt;
        textView.setText(i + "");
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.a
    public void dh(String str) {
        this.Ku.setText(str);
    }
}
