package com.xiaopeng.libbluetooth;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.xiaopeng.b.a;
import java.util.LinkedList;
import java.util.List;

/* compiled from: BluetoothBoxes.java */
/* loaded from: classes12.dex */
public class e {
    private static final String TAG = e.class.getSimpleName();
    private static volatile e Xj;
    private static String Xk;
    private com.xiaopeng.b.a Xl;
    private Context mContext;
    private ServiceConnection Xn = new ServiceConnection() { // from class: com.xiaopeng.libbluetooth.e.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            e.this.py();
            try {
                e.this.Xl.ef(e.Xk);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            e.this.Xl = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            e.this.Xl = a.AbstractBinderC0042a.c(iBinder);
            Log.d(e.TAG, Thread.currentThread().getName());
            Log.d(e.TAG, "onServiceConnected");
            if (e.this.Xl != null) {
                try {
                    String unused = e.Xk = e.this.Xl.ee(e.this.mContext.getPackageName());
                    Log.d("tang", "onServiceConnected_sID = " + e.Xk);
                    e.this.d(e.this.Xl);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private List<d> Xm = new LinkedList();

    private e() {
    }

    public static e pv() {
        if (Xj == null) {
            synchronized (e.class) {
                if (Xj == null) {
                    Xj = new e();
                }
            }
        }
        return Xj;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void pw() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.xiaopeng.xpbluetoothservice", "com.xiaopeng.xpbluetoothservice.XpBluetoothService"));
        this.mContext.bindServiceAsUser(intent, this.Xn, 1, UserHandle.CURRENT);
    }

    public void px() {
        for (d dVar : this.Xm) {
            b(dVar);
        }
        this.mContext.unbindService(this.Xn);
    }

    public void a(a aVar) {
        a(aVar.Xg);
    }

    public g a(c cVar) {
        return new g(this.mContext, cVar);
    }

    public f a(b bVar) {
        return new f(this.mContext, bVar);
    }

    public void a(f fVar) {
        b(fVar.Xg);
    }

    public void a(g gVar) {
        b(gVar.Xg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void py() {
        synchronized (this.Xm) {
            for (d dVar : this.Xm) {
                dVar.onDisconnected();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.xiaopeng.b.a aVar) {
        synchronized (this.Xm) {
            for (d dVar : this.Xm) {
                dVar.c(aVar);
            }
        }
    }

    private void a(d dVar) {
        if (this.Xl != null) {
            dVar.b(this.Xl);
        }
        synchronized (this.Xm) {
            this.Xm.add(dVar);
        }
    }

    private void b(d dVar) {
        dVar.pu();
        synchronized (this.Xm) {
            this.Xm.remove(dVar);
        }
    }

    public static String getID() {
        return Xk;
    }
}
