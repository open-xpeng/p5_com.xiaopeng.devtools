package com.xiaopeng.devtools.view.factorytest.hardwaretest;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.HWAndSWInfoEvent;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.c;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.ToolBarActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class HWAndSWInfoActivity extends ToolBarActivity implements a {
    private TextView Hf;
    private TextView Hg;
    private TextView Hh;
    private TextView Hi;
    private TextView Hj;
    private TextView Hk;
    private ImageView Hl;
    private ImageView Hm;
    private ImageView Hn;
    private TextView Ho;
    private TextView Hp;
    private int Hq = 250;
    private int Hr = 250;
    private int Hs = 250;
    private e Ht;

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_hw_and_sw_info);
        this.Ht = new c(this);
        this.Ht.init(this);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Hi = (TextView) findViewById(R.id.tv_device_code);
        this.Hj = (TextView) findViewById(R.id.tv_device_number);
        this.Hf = (TextView) findViewById(R.id.tv_hw_info);
        this.Hg = (TextView) findViewById(R.id.tv_sw_info);
        this.Hh = (TextView) findViewById(R.id.tv_mcu_info);
        this.Hl = (ImageView) findViewById(R.id.iv_device_code);
        this.Hm = (ImageView) findViewById(R.id.iv_device_number);
        this.Ho = (TextView) findViewById(R.id.tv_vehicle_id);
        this.Hp = (TextView) findViewById(R.id.tv_car_code);
        this.Hn = (ImageView) findViewById(R.id.iv_car_code);
        this.Hk = (TextView) findViewById(R.id.tv_psu_version);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Ht.cb(this.Hs);
        this.Ht.ca(this.Hr);
        this.Ht.cc(this.Hq);
        this.Hi.setText(getString(R.string.text_device_code, new Object[]{this.Ht.fR()}));
        String iccid = r.getIccid();
        TextView textView = this.Hj;
        Object[] objArr = new Object[1];
        if (TextUtils.isEmpty(iccid)) {
            iccid = getString(R.string.get_device_number_fail);
        }
        objArr[0] = iccid;
        textView.setText(getString(R.string.text_device_number, objArr));
        mz();
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        EventBus.getDefault().register(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Ht.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HWAndSWInfoEvent hWAndSWInfoEvent) {
        switch (hWAndSWInfoEvent.getState()) {
            case 1:
                com.xiaopeng.lib.utils.d.a.b(MyApplication.qx, R.string.hw_sw_info_req_car_code_error);
                return;
            case 2:
                com.xiaopeng.lib.utils.d.a.b(MyApplication.qx, R.string.hw_sw_info_get_part_code_error);
                return;
            default:
                return;
        }
    }

    private void mz() {
        this.Hk.setText(R.string.text_loading_psu_version);
        this.Ht.fS();
        this.Hf.setText(getString(R.string.text_hw_info, new Object[]{this.Ht.fP()}));
        this.Hg.setText(getString(R.string.text_sw_info, new Object[]{this.Ht.fQ()}));
        this.Hh.setText(getString(R.string.text_mcu_info, new Object[]{r.getMcuVersion()}));
        this.Ho.setText(getString(R.string.text_vehicle_id, new Object[]{this.Ht.getVehicleId()}));
        this.Hk.setVisibility(8);
        this.Ho.setVisibility(8);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.a
    public void b(Bitmap bitmap) {
        this.Hl.setImageBitmap(bitmap);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.a
    public void c(Bitmap bitmap) {
        this.Hm.setImageBitmap(bitmap);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.a
    public void d(Bitmap bitmap) {
        this.Hn.setImageBitmap(bitmap);
    }
}
