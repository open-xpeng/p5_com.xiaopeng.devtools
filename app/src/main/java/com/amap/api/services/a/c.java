package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.a.bv;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.b;
import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;

/* compiled from: GeocodeSearchCore.java */
/* loaded from: classes11.dex */
public final class c implements com.amap.api.services.b.a {
    private Context a;
    private b.a ej;
    private Handler ek = bv.bD();

    public c(Context context) {
        this.a = context.getApplicationContext();
    }

    public RegeocodeAddress a(com.amap.api.services.geocoder.c cVar) throws AMapException {
        try {
            bt.R(this.a);
            if (cVar == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new b(this.a, cVar).a();
        } catch (AMapException e) {
            br.a(e, "GeocodeSearch", "getFromLocationAsyn");
            throw e;
        }
    }

    @Override // com.amap.api.services.b.a
    public void a(b.a aVar) {
        this.ej = aVar;
    }

    @Override // com.amap.api.services.b.a
    public void b(final com.amap.api.services.geocoder.c cVar) {
        new Thread(new Runnable() { // from class: com.amap.api.services.a.c.1
            @Override // java.lang.Runnable
            public void run() {
                Message obtainMessage = bv.bD().obtainMessage();
                try {
                    try {
                        obtainMessage.arg1 = 2;
                        obtainMessage.what = TestResultItem.INDEX_BURNTEST_4G;
                        bv.i iVar = new bv.i();
                        iVar.ej = c.this.ej;
                        obtainMessage.obj = iVar;
                        iVar.go = new com.amap.api.services.geocoder.d(cVar, c.this.a(cVar));
                        obtainMessage.arg2 = 1000;
                    } catch (AMapException e) {
                        obtainMessage.arg2 = e.getErrorCode();
                    }
                } finally {
                    c.this.ek.sendMessage(obtainMessage);
                }
            }
        }).start();
    }
}
