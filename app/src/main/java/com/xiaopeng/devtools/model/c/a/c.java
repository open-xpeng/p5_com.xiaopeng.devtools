package com.xiaopeng.devtools.model.c.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.bean.event.HWAndSWInfoEvent;
import com.xiaopeng.devtools.utils.n;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/* compiled from: HWAndSWInfoModel.java */
/* loaded from: classes12.dex */
public class c implements d {
    private static final String sr = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("CAR_CODE");
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e ss;
    private com.xiaopeng.devtools.model.e.a st;
    private final Object mLock = new Object();
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.model.c.a.c.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Bitmap bitmap = (Bitmap) message.obj;
            switch (message.what) {
                case 1:
                    if (bitmap != null) {
                        c.this.ss.b(bitmap);
                        return;
                    }
                    return;
                case 2:
                    if (bitmap != null) {
                        c.this.ss.c(bitmap);
                        return;
                    }
                    return;
                case 3:
                    if (bitmap != null) {
                        c.this.ss.d(bitmap);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public c(com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e eVar) {
        this.ss = eVar;
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public void init(Context context) {
        this.st = new com.xiaopeng.devtools.model.e.a(context);
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public String fP() {
        return r.hJ();
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public String fQ() {
        return r.lq();
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public String fR() {
        String hardwareId = r.getHardwareId();
        return TextUtils.isEmpty(hardwareId) ? "Unknown" : hardwareId;
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public void ca(int i) {
        new Thread(new a(1, i, this.mHandler)).start();
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public void cb(int i) {
        new Thread(new a(2, i, this.mHandler)).start();
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public String getVehicleId() {
        String valueOf = String.valueOf(r.lp());
        return TextUtils.isEmpty(valueOf) ? "Unknown" : valueOf;
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public void cc(final int i) {
        b(new com.xiaopeng.lib.http.c<String, String>() { // from class: com.xiaopeng.devtools.model.c.a.c.2
            @Override // com.xiaopeng.lib.http.c
            public void onSuccess(String str) {
                new Thread(new a(3, i, str, c.this.mHandler)).start();
            }

            @Override // com.xiaopeng.lib.http.c
            /* renamed from: onError */
            public void j(String str) {
                EventBus.getDefault().post(new HWAndSWInfoEvent(1));
            }
        });
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public void fS() {
    }

    private void b(final com.xiaopeng.lib.http.c<String, String> cVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("iccid", r.getIccid());
        hashMap.put("cduId", r.getHardwareId());
        hashMap.put("partsNo", r.getPartNumber());
        hashMap.put("hVer", r.hJ());
        hashMap.put("sVer", com.xiaopeng.lib.utils.c.b.getSystemVersion());
        com.xiaopeng.lib.utils.c.f("HWAndSWInfoModel", "requestCarCode param = " + hashMap.toString());
        IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
        iHttp.cancelTag(sr);
        iHttp.bizHelper().post(sr, new Gson().toJson(hashMap)).build().tag(sr).execute(new Callback() { // from class: com.xiaopeng.devtools.model.c.a.c.3
            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onSuccess(IResponse iResponse) {
                com.xiaopeng.lib.http.a.a a2 = c.this.a(iResponse);
                if (a2.getCode() == 200) {
                    String data = a2.getData();
                    com.xiaopeng.lib.utils.c.f("HWAndSWInfoModel", "" + data);
                    if (TextUtils.isEmpty(data)) {
                        if (cVar != null) {
                            cVar.j(null);
                            return;
                        }
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(data);
                        if (cVar != null) {
                            cVar.onSuccess(jSONObject.getString("qrCode"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (cVar != null) {
                            cVar.j(null);
                        }
                    }
                }
            }

            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onFailure(IResponse iResponse) {
                if (cVar != null) {
                    cVar.j(null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public com.xiaopeng.lib.http.a.a a(IResponse iResponse) {
        try {
            JSONObject jSONObject = new JSONObject(iResponse.body());
            com.xiaopeng.lib.http.a.a aVar = new com.xiaopeng.lib.http.a.a();
            aVar.setCode(jSONObject.getInt("code"));
            aVar.setData(jSONObject.getString("data"));
            try {
                aVar.dB(jSONObject.getString("msg"));
                return aVar;
            } catch (Exception e) {
                return aVar;
            }
        } catch (Exception e2) {
            com.xiaopeng.lib.utils.c.a("HWAndSWInfoModel", "Failed to parser the response data. response:" + iResponse.body(), e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: HWAndSWInfoModel.java */
    /* loaded from: classes12.dex */
    public class a implements Runnable {
        private String mData;
        private int mWhat;
        private int mWidth;
        private WeakReference<Handler> sv;

        public a(int i, int i2, Handler handler) {
            this.mWhat = i;
            this.mWidth = i2;
            this.sv = new WeakReference<>(handler);
        }

        public a(int i, int i2, String str, Handler handler) {
            this.mWhat = i;
            this.mWidth = i2;
            this.mData = str;
            this.sv = new WeakReference<>(handler);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mWhat == 1) {
                a(n.a(c.this.fR(), this.mWidth, this.mWidth, null));
            } else if (this.mWhat == 2) {
                a(n.a(r.getIccid(), this.mWidth, this.mWidth, null));
            } else if (this.mWhat == 3) {
                a(n.a(this.mData, this.mWidth, this.mWidth, null));
            }
        }

        private void a(Bitmap bitmap) {
            if (this.sv.get() != null && bitmap != null) {
                Message obtainMessage = this.sv.get().obtainMessage();
                obtainMessage.what = this.mWhat;
                obtainMessage.obj = bitmap;
                this.sv.get().sendMessage(obtainMessage);
            }
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.d
    public void onDestroy() {
        this.st.onDestroy();
    }
}
