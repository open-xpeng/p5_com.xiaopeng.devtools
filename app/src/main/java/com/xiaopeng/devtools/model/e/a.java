package com.xiaopeng.devtools.model.e;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.ota.IOTAService;

/* compiled from: OtaModel.java */
/* loaded from: classes12.dex */
public class a {
    private Context mContext;
    private IOTAService tQ;
    private boolean tR;
    private ServiceConnection tS = new ServiceConnection() { // from class: com.xiaopeng.devtools.model.e.a.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            c.f("OtaModel", "onServiceDisconnected");
            a.this.tQ = null;
            a.this.tR = false;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            c.f("OtaModel", "onServiceConnected");
            a.this.tQ = IOTAService.Stub.asInterface(iBinder);
            a.this.tR = true;
        }
    };

    public a(Context context) {
        this.mContext = context;
        bindService();
    }

    private void bindService() {
        c.f("OtaModel", "bindService mHasServiceBind-->" + this.tR);
        Intent intent = new Intent();
        intent.setAction("com.xiaopeng.ota.system.OTAService");
        intent.setPackage("com.xiaopeng.ota");
        this.mContext.startServiceAsUser(intent, UserHandle.CURRENT);
        if (!this.tR) {
            this.mContext.bindServiceAsUser(intent, this.tS, 1, UserHandle.CURRENT);
        }
    }

    private void he() {
        c.f("OtaModel", "unBind mHasServiceBind-->" + this.tR);
        if (this.tR) {
            this.mContext.unbindService(this.tS);
            this.tR = false;
        }
    }

    public boolean hf() {
        return this.tR;
    }

    public void updatePsuFromUsb() {
        c.f("OtaModel", "updatePsuFromUsb");
        try {
            this.tQ.updatePsuFromUsb();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean hasPsuFileFromUsb() {
        boolean z;
        try {
            z = this.tQ.hasPsuFileFromUsb();
        } catch (RemoteException e) {
            e.printStackTrace();
            z = false;
        }
        c.f("OtaModel", "hasPsuFileFromUsb mHasPsuFile = " + z);
        return z;
    }

    public boolean hasMeterFileFromUsb() {
        boolean z;
        try {
            z = this.tQ.hasMeterFileFromUsb();
        } catch (RemoteException e) {
            e.printStackTrace();
            z = false;
        }
        c.f("OtaModel", "hasMeterFileFromUsb mHasMeterFile = " + z);
        return z;
    }

    public void updateMeterFromUsb() {
        c.f("OtaModel", "updateMeterFromUsb");
        try {
            this.tQ.updateMeterFromUsb();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean hasCduFileFromUsb() {
        boolean z;
        try {
            z = this.tQ.hasCduFileFromUsb();
        } catch (RemoteException e) {
            e.printStackTrace();
            z = false;
        }
        c.f("OtaModel", "hasCduFileFromUsb mHasCduFile = " + z);
        return z;
    }

    public void updateCduFromUsb() {
        c.f("OtaModel", "updateCduFromUsb");
        try {
            this.tQ.updateCduFromUsb();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void updateEcuComplete(String str, boolean z) {
        c.f("OtaModel", "updateEcuComplete");
        try {
            this.tQ.updateEcuComplete(str, z);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        he();
    }
}
