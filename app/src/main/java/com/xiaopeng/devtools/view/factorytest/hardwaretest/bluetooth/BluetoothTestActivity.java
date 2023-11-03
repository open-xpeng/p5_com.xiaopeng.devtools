package com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.factorytest.ScanDevice;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.lib.utils.c;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes12.dex */
public class BluetoothTestActivity extends AsOrFacBaseActivity implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener, b {
    private Switch Il;
    private Button Im;
    private Button In;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a Io;
    private TextView Ip;
    private TextView Iq;
    private TextView Ir;
    private TextView Is;
    private TextView It;
    private TextView Iu;
    private TextView Iv;
    private ScanDeviceAdapter Iw;
    private ListView Ix;
    private CircularProgressView Iy;
    private View Iz;
    private com.xiaopeng.devtools.model.c.a.b.b wH;
    private Handler mHandler = new Handler();
    private boolean IB = false;
    private int IC = 0;
    private int IE = 0;
    private int IF = 0;
    private int IG = 0;

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_bluetooth_test);
        this.Il = (Switch) findViewById(R.id.switch_bluetooth_on_off);
        this.Ip = (TextView) findViewById(R.id.result_on_off_tv);
        this.Iq = (TextView) findViewById(R.id.result_addr_tv);
        this.Iu = (TextView) findViewById(R.id.bluetooth_show_addr_tv);
        this.Iv = (TextView) findViewById(R.id.bluetooth_on_off_status);
        this.Io = new com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a(this, this);
        this.wH = com.xiaopeng.devtools.model.c.a.b.b.gn();
        setTarget(getString(R.string.test_bt));
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Im = (Button) findViewById(R.id.bluetooth_addr_get_bt);
        this.In = (Button) findViewById(R.id.search_bluetooth_bt);
        this.Ir = (TextView) findViewById(R.id.result_scan_tv);
        this.Is = (TextView) findViewById(R.id.result_pair_tv);
        this.It = (TextView) findViewById(R.id.result_connect_tv);
        this.Ix = (ListView) findViewById(R.id.bluetooth_scan_lv);
        this.Iy = (CircularProgressView) findViewById(R.id.progress_view);
        this.Iz = findViewById(R.id.layout_bt_on_show);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (this.theme == 1) {
            findViewById(R.id.layout_bluetooth_pair).setVisibility(8);
            findViewById(R.id.layout_bluetooth_connect).setVisibility(8);
            findViewById(R.id.search_success_bt).setVisibility(8);
            findViewById(R.id.search_fail_bt).setVisibility(8);
        }
        this.Io.onInit();
        this.Iw = new ScanDeviceAdapter(this.wH.getItems());
        this.Ix.setAdapter((ListAdapter) this.Iw);
        this.Io.dL();
        c.f("BluetoothTestActivity", "STATUS = " + this.Io.dN());
        mD();
        BluetoothDevice connectedDevice = this.Io.getConnectedDevice();
        if (connectedDevice != null) {
            this.Iw.setAddress(connectedDevice.getAddress());
            this.Iw.setHintId(R.string.bluetooth_connected);
        }
        if (this.Io.gm()) {
            mB();
        }
        this.Iw.notifyDataSetChanged();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Ix.setOnItemClickListener(this);
        this.Im.setOnClickListener(this);
        this.In.setOnClickListener(this);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mv() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_bt));
        super.mv();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mw() {
        EventBus.getDefault().post(Integer.valueOf((int) R.id.btn_test_bt));
        super.mw();
    }

    public void onResult(View view) {
        switch (view.getId()) {
            case R.id.connect_fail_bt /* 2131296638 */:
                a(getString(R.string.bluetooth_connect_test), false, "");
                a(this.It, false);
                return;
            case R.id.connect_success_bt /* 2131296640 */:
                a(getString(R.string.bluetooth_connect_test), true, "");
                a(this.It, true);
                return;
            case R.id.pair_fail_bt /* 2131296935 */:
                a(getString(R.string.bluetooth_pair_test), false, "");
                a(this.Is, false);
                return;
            case R.id.pair_success_bt /* 2131296936 */:
                a(getString(R.string.bluetooth_pair_test), true, "");
                a(this.Is, true);
                return;
            case R.id.search_fail_bt /* 2131297060 */:
                a(getString(R.string.bluetooth_search_test), false, "");
                a(this.Ir, false);
                return;
            case R.id.search_success_bt /* 2131297065 */:
                a(getString(R.string.bluetooth_search_test), true, "");
                a(this.Ir, true);
                return;
            default:
                return;
        }
    }

    private void a(TextView textView, boolean z) {
        if (z) {
            textView.setText(R.string.test_succeed);
            textView.setTextColor(getResources().getColor(17170450));
            return;
        }
        textView.setText(R.string.test_fail);
        textView.setTextColor(getResources().getColor(17170454));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        c.f("BluetoothTestActivity", "onResume");
        super.onResume();
        mF();
        this.Il.setOnCheckedChangeListener(this);
        cU(this.Io.dN());
        if (this.theme == 1) {
            recordRepairModeAction("bt diagnosis", "triggered");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        c.f("BluetoothTestActivity", "onPause");
        super.onPause();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity
    public void mx() {
        super.mx();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        c.f("BluetoothTestActivity", "onCheckedChanged isChecked = " + z);
        if (z) {
            if (this.IB) {
                this.IE++;
                mD();
            }
            this.IB = true;
            this.Io.dL();
        } else {
            this.IB = true;
            if (this.IB) {
                this.IG++;
                mD();
            }
            this.Io.a((a.InterfaceC0067a) null);
            this.Io.dM();
            this.wH.deleteAll();
            setScanDeviceItem(this.wH.getItems());
            this.Iy.stopAnimation();
            d(this.Iy, 8);
            d(this.Iz, 8);
            d(this.Ix, 8);
        }
        this.Il.setEnabled(false);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.bluetooth_addr_get_bt) {
            mC();
        } else if (id == R.id.search_bluetooth_bt) {
            if (!this.Io.gm()) {
                gl();
            } else {
                this.Io.a((a.InterfaceC0067a) null);
            }
        }
    }

    private void gl() {
        this.wH.deleteAll();
        setScanDeviceItem(this.wH.getItems());
        this.Io.gl();
        mB();
    }

    private void mB() {
        this.Iy.setVisibility(0);
        this.Iy.startAnimation();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Io.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.b
    public void as(boolean z) {
        cU(z ? 12 : 10);
    }

    private void mC() {
        this.Iu.setVisibility(0);
        String gk = this.Io.gk();
        if (getString(R.string.status_unavailable).equals(gk) || TextUtils.isEmpty(gk)) {
            a(getString(R.string.bluetooth_addr_test), false, "");
            a(this.Iq, false);
            gk = getString(R.string.status_unavailable);
        } else {
            a(getString(R.string.bluetooth_addr_test), true, "");
            a(this.Iq, true);
        }
        this.Iu.setText(getString(R.string.bluetooth_addr, new Object[]{gk}));
    }

    private void mD() {
        this.Iv.setText(getString(R.string.onoff_success_total_times, new Object[]{Integer.valueOf(this.IC), Integer.valueOf(this.IE), Integer.valueOf(this.IF), Integer.valueOf(this.IG)}));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.b
    public void setScanDeviceItem(List<ScanDevice> list) {
        c.f("BluetoothTestActivity", "setScanDeviceItem");
        this.Iw.setScanDevices(list);
        this.Iw.notifyDataSetChanged();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.b
    public void c(String str, int i, int i2, int i3) {
        c.f("BluetoothTestActivity", "onConnectCallback, status = " + i + " preState = " + i2 + ", profileType = " + i3);
        if (i == 2) {
            if (!TextUtils.isEmpty(str)) {
                this.Iw.setAddress(str);
                this.Iw.setHintId(R.string.bluetooth_connected);
                this.Iw.notifyDataSetChanged();
            }
        } else if (i == 0) {
            this.Iw.setAddress(null);
            this.Iw.notifyDataSetChanged();
            if (!TextUtils.isEmpty(str)) {
                this.Io.bA(str);
            }
        }
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.b
    public void c(int i, String str) {
        switch (i) {
            case 10:
                this.Iw.setAddress(null);
                break;
            case 11:
                this.Iw.setAddress(str);
                this.Iw.setHintId(R.string.bluetooth_pairing);
                break;
            case 12:
                this.Iw.setAddress(str);
                this.Iw.setHintId(R.string.bluetooth_paired);
                break;
        }
        this.Iw.notifyDataSetChanged();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.b
    public void mE() {
        this.Iy.stopAnimation();
        this.Iy.setVisibility(8);
    }

    private void mF() {
        int dN = this.Io.dN();
        c.f("BluetoothTestActivity", "setSwitch bluetoothState = " + dN);
        boolean z = false;
        boolean z2 = dN == 12;
        boolean z3 = dN == 10;
        c.f("BluetoothTestActivity", "setSwitch isOn = " + z2);
        c.f("BluetoothTestActivity", "setSwitch isOff= " + z3);
        setChecked(z2);
        Switch r4 = this.Il;
        if (z2 || z3) {
            z = true;
        }
        r4.setEnabled(z);
    }

    void cU(int i) {
        switch (i) {
            case 10:
                c.f("BluetoothTestActivity", "STATE_OFF");
                if (this.IB) {
                    this.IF++;
                    mD();
                }
                setChecked(false);
                this.Il.setEnabled(true);
                this.Iu.setVisibility(4);
                return;
            case 11:
                c.f("BluetoothTestActivity", "STATE_TURNING_ON");
                this.Il.setEnabled(false);
                return;
            case 12:
                c.f("BluetoothTestActivity", "STATE_ON");
                if (this.IB) {
                    this.IC++;
                    mD();
                }
                setChecked(true);
                this.Il.setEnabled(true);
                a(getString(R.string.bluetooth_on_off_test), true, getString(R.string.onoff_success_total_times, new Object[]{Integer.valueOf(this.IC), Integer.valueOf(this.IE), Integer.valueOf(this.IF), Integer.valueOf(this.IG)}));
                a(this.Ip, true);
                d(this.Ix, 0);
                d(this.Iz, 0);
                mC();
                return;
            case 13:
                c.f("BluetoothTestActivity", "STATE_TURNING_OFF");
                this.Il.setEnabled(false);
                return;
            default:
                c.f("BluetoothTestActivity", "default");
                setChecked(false);
                this.Il.setEnabled(true);
                return;
        }
    }

    private void setChecked(boolean z) {
        this.Il.setChecked(z);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.Iy.setVisibility(8);
        this.Iy.stopAnimation();
        final String address = this.Iw.cV(i).getAddress();
        String charSequence = ((TextView) view.findViewById(R.id.scan_device_pair_tv)).getText().toString();
        c.f("BluetoothTestActivity", "hintText = " + charSequence + " address = " + address);
        if (adapterView.getId() == R.id.bluetooth_scan_lv) {
            this.Iw.setAddress(address);
            this.Iw.notifyDataSetChanged();
            if (TextUtils.isEmpty(charSequence)) {
                this.Iw.setHintId(R.string.bluetooth_pairing);
                this.Iw.notifyDataSetChanged();
                if (this.Io.gm()) {
                    this.Io.a(new a.InterfaceC0067a() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.BluetoothTestActivity.1
                        @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.b.a.InterfaceC0067a
                        public void iW() {
                            BluetoothTestActivity.this.Io.bz(address);
                        }
                    });
                } else {
                    this.Io.bz(address);
                }
            } else if (getString(R.string.bluetooth_paired).equals(charSequence)) {
                this.Iw.setHintId(R.string.bluetooth_connecting);
                this.Iw.notifyDataSetChanged();
                this.mHandler.postDelayed(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.BluetoothTestActivity.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BluetoothTestActivity.this.Io.bU(address);
                    }
                }, 500L);
            } else if (getString(R.string.bluetooth_connected).equals(charSequence) || getString(R.string.bluetooth_connecting).equals(charSequence)) {
                this.Iw.setHintId(R.string.bluetooth_disconnecting);
                this.Iw.notifyDataSetChanged();
                this.mHandler.postDelayed(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth.BluetoothTestActivity.3
                    @Override // java.lang.Runnable
                    public void run() {
                        BluetoothTestActivity.this.Io.by(BluetoothTestActivity.this.Iw.getAddress());
                    }
                }, 500L);
            } else if (getString(R.string.bluetooth_pairing).equals(charSequence)) {
                this.Io.by(address);
                this.Iw.setAddress(null);
                this.Iw.notifyDataSetChanged();
            }
        }
    }
}
