package com.xiaopeng.lib.security.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.irdeto.securesdk.ISFException;
import com.irdeto.securesdk.SecureSDKManager;
import com.irdeto.securesdk.isf;
import com.xiaopeng.lib.http.Security;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.c.b;
import java.io.File;

/* compiled from: IrdetoSecurity.java */
/* loaded from: classes12.dex */
public class a implements com.xiaopeng.lib.security.a {
    private static SecureSDKManager Vq;
    private static boolean po;
    private static Context sContext;
    private volatile String Vr;

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    private a() {
    }

    @Override // com.xiaopeng.lib.security.a
    public Security.EncryptionType oh() {
        return Security.EncryptionType.IRDETO;
    }

    @Override // com.xiaopeng.lib.security.a
    /* renamed from: an */
    public a ak(Context context) throws Exception {
        d(context, false);
        return this;
    }

    @Override // com.xiaopeng.lib.security.a
    /* renamed from: ao */
    public a al(Context context) throws Exception {
        d(context, true);
        return this;
    }

    private void d(Context context, boolean z) {
        sContext = context;
        if (z) {
            File file = new File("/private/sec/");
            if (!file.exists()) {
                file.mkdir();
            }
            on();
        } else {
            oq();
        }
        po = false;
        try {
            Vq = new SecureSDKManager();
            Vq.isfInitialize(context, "/private/sec/");
            po = true;
            c.f("IrdetoSecurity", "irdeto sdk init success");
        } catch (ISFException e) {
            c.b("IrdetoSecurity", e);
            int errorCode = e.getErrorCode();
            if (errorCode != 10) {
                if (errorCode == 14) {
                    c.i("IrdetoSecurity", "irdeto sdk init failed : secure store error!!!!");
                    oo();
                }
            } else if (b.pl()) {
                po = true;
                c.f("IrdetoSecurity", "irdeto sdk init success for eng root");
            }
            this.Vr = "irdeto init error for " + e.getMessage() + " error code is " + e.getErrorCode();
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public void destroy() {
        if (Vq != null) {
            Vq.isfCleanup();
            Vq = null;
        }
        po = false;
    }

    private void on() {
        if (!com.xiaopeng.lib.security.b.ol().equals(op())) {
            c.i("IrdetoSecurity", "irdeto delete indiv files");
            oo();
        }
    }

    private void oo() {
        try {
            com.xiaopeng.lib.http.a.bn("/private/sec/irss0.dat");
            com.xiaopeng.lib.http.a.bn("/private/sec/irss1.dat");
            com.xiaopeng.lib.http.a.bn("/private/sec/irss2.dat");
            com.xiaopeng.lib.http.a.bn("/private/sec/si_flag.dat");
            com.xiaopeng.lib.http.a.bn("/private/sec/si_mcu.dat");
            File file = new File(sContext.getFilesDir().getPath() + "/irss0.dat");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public void ok() {
        try {
            com.xiaopeng.lib.http.a.bn("/private/sec/irss2.dat");
            com.xiaopeng.lib.http.a.bn("/private/sec/si_flag.dat");
            com.xiaopeng.lib.http.a.bn("/private/sec/si_mcu.dat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public boolean dC(String str) throws Exception {
        isf.isfProvisionResponse(str);
        return isf.isfProvisioned();
    }

    @Override // com.xiaopeng.lib.security.a
    public void oj() {
        try {
            com.xiaopeng.lib.http.a.stringToFile("/private/sec/si_flag.dat", com.xiaopeng.lib.security.b.ol());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public void aP(boolean z) {
        if (!z && new File("/private/sec/si_mcu.dat").exists()) {
            return;
        }
        try {
            com.xiaopeng.lib.http.a.stringToFile("/private/sec/si_mcu.dat", com.xiaopeng.lib.security.b.D(isf.isfGetMCUSecurityAsset()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: IrdetoSecurity.java */
    /* renamed from: com.xiaopeng.lib.security.a.a$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ com.xiaopeng.lib.http.c oq;

        @Override // java.lang.Runnable
        public void run() {
            try {
                byte[] dD = com.xiaopeng.lib.security.b.dD(com.xiaopeng.lib.http.a.readTextFile(new File("/private/sec/si_mcu.dat"), 0, null));
                if (dD == null && dD.length != 0) {
                    this.oq.j("null mcu security key");
                }
                this.oq.onSuccess(dD);
            } catch (Exception e) {
                e.printStackTrace();
                c.e("IrdetoSecurity", "null mcu security key");
                this.oq.j("null mcu security key");
            }
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public String a(String[] strArr, byte[] bArr) {
        if (!com.xiaopeng.lib.security.b.am(sContext)) {
            return com.xiaopeng.lib.http.b.a.a(sContext, strArr, bArr);
        }
        synchronized (a.class) {
            if (!po) {
                dE("build token data error, ");
                return null;
            }
            try {
                return isf.isfTokenOperate(strArr, com.xiaopeng.lib.security.b.E(bArr).getBytes());
            } catch (Exception e) {
                c.b("IrdetoSecurity", e);
                return null;
            }
        }
    }

    public String op() {
        try {
            return com.xiaopeng.lib.http.a.readTextFile(new File("/private/sec/si_flag.dat"), 1, null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public boolean oi() {
        synchronized (a.class) {
            if (!po) {
                c.f("IrdetoSecurity", "Irdeto sdk not init " + this.Vr);
                return false;
            }
            try {
                if (isf.isfProvisioned()) {
                    if (com.xiaopeng.lib.security.b.ol().equals(op())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private static void oq() {
        c.f("IrdetoSecurity", "registerISFSecureStoreReloadReceiver");
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xiaopeng.action.ISF_SECURE_STORE_RELOAD");
            sContext.registerReceiver(new BroadcastReceiver() { // from class: com.xiaopeng.lib.security.a.a.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if ("com.xiaopeng.action.ISF_SECURE_STORE_RELOAD".equals(intent.getAction())) {
                        try {
                            c.f("IrdetoSecurity", "registerISFSecureStoreReloadReceiver ACTION_BROADCAST_ISF_SECURE_STORE_RELOAD");
                            isf.isfSecureStoreReload();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: IrdetoSecurity.java */
    /* renamed from: com.xiaopeng.lib.security.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private static final class C0082a {
        private static final a Vs = new a(null);
    }

    public static a or() {
        return C0082a.Vs;
    }

    private void dE(String str) {
        c.f("IrdetoSecurity", str + this.Vr);
    }
}
