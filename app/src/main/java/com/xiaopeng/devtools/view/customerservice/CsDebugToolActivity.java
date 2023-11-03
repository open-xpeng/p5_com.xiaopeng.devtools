package com.xiaopeng.devtools.view.customerservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.BoardCastEvent;
import com.xiaopeng.devtools.model.e.a;
import com.xiaopeng.devtools.system.a.b;
import com.xiaopeng.devtools.system.service.CopyFileService;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.a;
import com.xiaopeng.devtools.view.clean.ClearDataActivity;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class CsDebugToolActivity extends ActionBarActivity implements View.OnClickListener {
    private String AH;
    private b CL;
    private Button FC;
    private Button FD;
    private Button FE;
    private TextView Fx;
    private StorageManager mStorageManager;
    private a st;
    Double Fv = Double.valueOf(0.0d);
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.customerservice.CsDebugToolActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    CsDebugToolActivity.this.ap(true);
                    return;
                case 2:
                    CsDebugToolActivity.this.ap(false);
                    return;
                default:
                    return;
            }
        }
    };
    private Thread Fz = new Thread(new Runnable() { // from class: com.xiaopeng.devtools.view.customerservice.CsDebugToolActivity.2
        @Override // java.lang.Runnable
        public void run() {
            CsDebugToolActivity.this.Fv = Double.valueOf(0.0d);
            for (int i = 0; i < com.xiaopeng.devtools.a.nx.length; i++) {
                CsDebugToolActivity.this.Fv = Double.valueOf(CsDebugToolActivity.this.Fv.doubleValue() + com.xiaopeng.lib.utils.b.u(com.xiaopeng.devtools.a.nx[i], 3));
                c.f("CsDebugToolActivity", "mGetFileSizeThread " + com.xiaopeng.devtools.a.nx[i] + " " + CsDebugToolActivity.this.Fv);
            }
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.view.customerservice.CsDebugToolActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    CsDebugToolActivity.this.Fx.setText(CsDebugToolActivity.this.getString(R.string.log_copying, new Object[]{Long.valueOf((long) Math.floor(CsDebugToolActivity.this.Fv.doubleValue()))}));
                }
            });
        }
    });

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                ap(true);
                this.FD.setTextColor(getResources().getColor(17170443));
                this.FD.setEnabled(true);
                this.Fx.setText(R.string.finish_grab_log);
                return;
            case 2:
                ap(true);
                this.FD.setTextColor(getResources().getColor(17170443));
                this.FD.setEnabled(true);
                this.Fx.setText(R.string.fail_grab_log);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        c.f("CsDebugToolActivity", "initBeforeView");
        setContentView(R.layout.activity_cs_debug);
        this.st = new a(this);
        this.AH = g.V(this);
        this.mStorageManager = StorageManager.from(MyApplication.getContext());
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.FC = (Button) findViewById(R.id.btn_state_usb);
        this.FD = (Button) findViewById(R.id.btn_reset_system);
        this.FE = (Button) findViewById(R.id.btn_grab_log);
        this.Fx = (TextView) findViewById(R.id.tv_copy_result);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        if (!TextUtils.isEmpty(this.AH)) {
            ap(true);
        } else {
            ap(false);
        }
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.CL = new b(this.mHandler);
        this.mStorageManager.registerListener(this.CL);
        this.FD.setOnClickListener(this);
        this.FC.setOnClickListener(this);
        this.FE.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ap(boolean z) {
        if (z) {
            this.FC.setTextColor(getResources().getColor(17170443));
            this.FC.setEnabled(true);
            this.FE.setTextColor(getResources().getColor(17170443));
            this.FE.setEnabled(true);
            return;
        }
        this.FC.setTextColor(getResources().getColor(R.color.color_6ce5f2));
        this.FC.setEnabled(false);
        this.FC.setText(R.string.search_usb);
        this.FE.setTextColor(getResources().getColor(R.color.color_6ce5f2));
        this.FE.setEnabled(false);
        this.Fx.setText("");
    }

    private void aq(final boolean z) {
        com.xiaopeng.devtools.view.a aVar = new com.xiaopeng.devtools.view.a(this);
        aVar.show();
        aVar.cH(558);
        aVar.setCanceledOnTouchOutside(true);
        aVar.cQ(getString(R.string.dialog_title_upgrade_warn));
        aVar.cR(getString(R.string.upgrade_warn_tip));
        aVar.cO(getString(R.string.common_cancel));
        aVar.cP(getString(R.string.common_continue));
        aVar.b(new a.InterfaceC0071a() { // from class: com.xiaopeng.devtools.view.customerservice.-$$Lambda$CsDebugToolActivity$EMpS7WIIyu4zT2u8qJlpUwl8AkE
            @Override // com.xiaopeng.devtools.view.a.InterfaceC0071a
            public final void onClick(com.xiaopeng.devtools.view.a aVar2) {
                CsDebugToolActivity.this.a(z, aVar2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, com.xiaopeng.devtools.view.a aVar) {
        aVar.dismiss();
        if (z) {
            this.st.updateCduFromUsb();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_grab_log) {
            if (id == R.id.btn_reset_system) {
                Intent intent = new Intent(this, ClearDataActivity.class);
                intent.addFlags(335544320);
                startActivity(intent);
                return;
            } else if (id == R.id.btn_state_usb) {
                if (!this.st.hf()) {
                    c.f("CsDebugToolActivity", "btn_state_usb service not ready.");
                    return;
                } else if (this.st.hasCduFileFromUsb()) {
                    this.FC.setText(R.string.search_usb);
                    aq(true);
                    return;
                } else {
                    this.FC.setText(R.string.has_no_update_file_in_usb);
                    return;
                }
            } else {
                return;
            }
        }
        this.Fx.setText(R.string.grabing_log);
        if (!this.Fz.isAlive()) {
            j.execute(this.Fz);
        }
        Intent intent2 = new Intent(this, CopyFileService.class);
        if (TextUtils.isEmpty(this.AH)) {
            this.AH = this.CL.kI();
        }
        c.f("CsDebugToolActivity", "mStoragePath = " + this.AH);
        intent2.putExtra("storagePath", this.AH);
        intent2.putExtra("sourcePath", com.xiaopeng.devtools.a.nx);
        startServiceAsUser(intent2, UserHandle.CURRENT);
        ap(false);
        this.FD.setTextColor(getResources().getColor(R.color.color_6ce5f2));
        this.FD.setEnabled(false);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.st.onDestroy();
        if (this.mStorageManager != null && this.CL != null) {
            this.mStorageManager.unregisterListener(this.CL);
        }
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onReceiveData(IIpcService.IpcMessageEvent ipcMessageEvent) {
        c.e("CsDebugToolActivity", "onReceiveData event=" + ipcMessageEvent);
        if (ipcMessageEvent == null) {
            return;
        }
        String senderPackageName = ipcMessageEvent.getSenderPackageName();
        char c = 65535;
        if (senderPackageName.hashCode() == -773268792 && senderPackageName.equals("com.xiaopeng.ota")) {
            c = 0;
        }
        if (c == 0) {
            a(ipcMessageEvent.getPayloadData(), ipcMessageEvent.getMsgID());
        }
    }

    private void a(Bundle bundle, int i) {
        if (i != 1302 || bundle == null) {
            return;
        }
        BoardCastEvent boardCastEvent = (BoardCastEvent) new Gson().fromJson(bundle.getString("string_msg"), new TypeToken<BoardCastEvent>() { // from class: com.xiaopeng.devtools.view.customerservice.CsDebugToolActivity.3
        }.getType());
        if (boardCastEvent == null) {
            return;
        }
        c.f("CsDebugToolActivity", "handleOTAIpcMsg " + boardCastEvent.toString());
        if (boardCastEvent.getType() == 0) {
            a(boardCastEvent.getData(), this.FC, R.string.success_update_cdu, R.string.fail_update_cdu);
        }
    }

    private void a(String str, Button button, int i, int i2) {
        if ("0".equals(str)) {
            button.setText(i);
        } else if ("1".equals(str)) {
            button.setText(i2);
        }
    }
}
