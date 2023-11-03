package com.xiaopeng.commonfunc.b.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.commonfunc.utils.d;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.security.xmartv1.RandomKeySecurity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: IndivModel.java */
/* loaded from: classes11.dex */
public class a {
    private static final String nX = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("INDIV_TEST_ENCRYPTION");
    private static final String nY = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("INDIV_TEST_DECRYPTION");
    private Context mContext;
    private final Handler mHandler = new Handler(j.dR(1)) { // from class: com.xiaopeng.commonfunc.b.d.a.1
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                c.g("IndivModel", "handleMessage TIME_OUT");
                if (a.this.nZ != null) {
                    a.this.nZ.onReceiveResult(6, 2);
                }
            }
        }
    };
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.commonfunc.b.d.a.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.xiaopeng.action.SECURE_STORE_RELOAD".equals(intent.getAction())) {
                c.g("IndivModel", "onReceive ACTION_BROADCAST_SECURE_STORE_RELOAD");
                if (a.this.mHandler.hasMessages(1)) {
                    a.this.mHandler.removeMessages(1);
                    if (a.this.nZ != null) {
                        a.this.nZ.onReceiveResult(6, 1);
                    }
                }
                com.xiaopeng.commonfunc.utils.j.X(a.this.mContext);
            }
        }
    };
    private com.xiaopeng.commonfunc.a.c nZ;

    public a(Context context, com.xiaopeng.commonfunc.a.c cVar) {
        this.nZ = cVar;
        this.mContext = context;
    }

    public void init() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xiaopeng.action.SECURE_STORE_RELOAD");
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
    }

    public void dO() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public boolean dP() {
        return com.xiaopeng.commonfunc.utils.j.et();
    }

    public void dQ() {
        com.xiaopeng.commonfunc.utils.j.dQ();
    }

    public void dR() {
        this.mHandler.sendEmptyMessageDelayed(1, 30000L);
        com.xiaopeng.commonfunc.utils.j.dR();
    }

    public void dS() {
        j.d(new Runnable() { // from class: com.xiaopeng.commonfunc.b.d.-$$Lambda$a$mum8_8PvPjjqMBECKnjhYRuxmwQ
            @Override // java.lang.Runnable
            public final void run() {
                a.this.dV();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dV() {
        boolean z = dT() && dU();
        if (this.nZ != null) {
            this.nZ.onReceiveResult(0, z ? 1 : 2);
        }
    }

    private boolean dT() {
        C0053a c0053a = new C0053a();
        c0053a.ob = "工厂检查indiv加解密状态";
        c0053a.oc = new ArrayList<>();
        for (int i = 0; i < com.xiaopeng.lib.security.xmartv1.a.VS; i++) {
            c0053a.oc.add(RandomKeySecurity.oI().t("工厂检查indiv加解密状态", i));
        }
        String json = new Gson().toJson(c0053a);
        c.g("IndivModel", "Encrypt check string:" + json);
        try {
            if (d.a(((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(nX, json).build().execute()).getCode() != 200) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean dU() {
        C0053a c0053a;
        HashMap hashMap = new HashMap(1);
        hashMap.put("rawData", "工厂检查indiv加解密状态");
        try {
            com.xiaopeng.lib.http.a.a a = d.a(((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(nY, new Gson().toJson(hashMap)).build().execute());
            if (a.getCode() != 200) {
                return false;
            }
            String data = a.getData();
            if (!TextUtils.isEmpty(data) && (c0053a = (C0053a) new Gson().fromJson(data, (Class<Object>) C0053a.class)) != null && c0053a.oc.size() == com.xiaopeng.lib.security.xmartv1.a.VS) {
                for (int i = 0; i < c0053a.oc.size(); i++) {
                    if (!"工厂检查indiv加解密状态".equals(RandomKeySecurity.oI().decode(c0053a.oc.get(i)))) {
                        c.g("IndivModel", "Decryption check failed with key: #" + i);
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IndivModel.java */
    /* renamed from: com.xiaopeng.commonfunc.b.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0053a {
        public String ob;
        public ArrayList<String> oc;

        private C0053a() {
        }
    }
}
