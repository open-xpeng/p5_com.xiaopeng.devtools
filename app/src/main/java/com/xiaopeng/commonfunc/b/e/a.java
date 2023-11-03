package com.xiaopeng.commonfunc.b.e;

import android.content.Context;
import com.xiaopeng.lib.utils.c;
import java.security.KeyStore;

/* compiled from: KeyStoreModel.java */
/* loaded from: classes11.dex */
public class a {
    private Context mContext;
    private KeyStore od;

    public a(Context context) {
        this.mContext = context;
        try {
            this.od = KeyStore.getInstance("XpengAndroidKeyStore");
            this.od.load(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean dW() {
        c.g("KeyStoreModel", "CreateCaCert");
        try {
            KeyStore keyStore = KeyStore.getInstance("BKS");
            keyStore.load(this.mContext.getResources().getAssets().open("index_kstp.html"), "chengzi".toCharArray());
            this.od.setCertificateEntry("ca", keyStore.getCertificate("server"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dX() {
        c.g("KeyStoreModel", "CreateV18CaCert");
        try {
            KeyStore keyStore = KeyStore.getInstance("BKS");
            keyStore.load(this.mContext.getResources().getAssets().open("index_kstp_eu.html"), "chengzi".toCharArray());
            this.od.setCertificateEntry("ca", keyStore.getCertificate("server"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
