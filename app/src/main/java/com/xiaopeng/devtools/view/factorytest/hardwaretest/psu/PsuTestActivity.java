package com.xiaopeng.devtools.view.factorytest.hardwaretest.psu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.g.a;
import com.xiaopeng.devtools.view.ToolBarActivity;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class PsuTestActivity extends ToolBarActivity {
    private TextView KN;
    private TextView KO;
    private a KP;
    private boolean xa = false;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.psu.PsuTestActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    PsuTestActivity.this.a(PsuTestActivity.this.KN, message.arg1);
                    return;
                case 2:
                    PsuTestActivity.this.a(PsuTestActivity.this.KO, message.arg1);
                    return;
                case 3:
                    PsuTestActivity.this.KP.jj();
                    PsuTestActivity.this.xa = true;
                    return;
                default:
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TextView textView, int i) {
        switch (i) {
            case 1:
                textView.setText(R.string.test_fail);
                textView.setTextColor(getResources().getColor(17170454));
                return;
            case 2:
                textView.setText(R.string.test_succeed);
                textView.setTextColor(getResources().getColor(17170450));
                return;
            default:
                textView.setText(R.string.test_fail);
                textView.setTextColor(getResources().getColor(17170454));
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_psu_test);
        setTarget(getString(R.string.test_psu));
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.KN = (TextView) findViewById(R.id.result_psu_can);
        this.KO = (TextView) findViewById(R.id.result_psu_usb);
        this.KP = new a(this.mHandler);
        this.KP.init();
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.KP.jk()) {
            this.KO.setText(R.string.test_succeed);
            this.KO.setTextColor(getResources().getColor(17170450));
            return;
        }
        this.KO.setText(R.string.test_fail);
        this.KO.setTextColor(getResources().getColor(17170454));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessageDelayed(3, 500L);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mHandler.hasMessages(3)) {
            this.mHandler.removeMessages(3);
        }
        if (this.mHandler.hasMessages(1)) {
            this.mHandler.removeMessages(1);
        }
        this.KP.destroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        super.mv();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_psu));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        super.mw();
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_psu));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }
}
