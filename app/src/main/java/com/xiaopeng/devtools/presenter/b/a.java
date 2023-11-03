package com.xiaopeng.devtools.presenter.b;

import com.xiaopeng.commonfunc.b.a.e;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: GrabLogPresenter.java */
/* loaded from: classes12.dex */
public class a implements b {
    private com.xiaopeng.commonfunc.b.a.c yn;
    private e yo;

    public a() {
        init();
    }

    public void init() {
        this.yn = new com.xiaopeng.commonfunc.b.a.c("GrabLogPresenter");
        this.yo = new e("GrabLogPresenter");
    }

    @Override // com.xiaopeng.devtools.presenter.b.b
    public void sendLogCompressRequest(String str) {
        this.yn.sendLogCompressRequest(str);
    }

    @Override // com.xiaopeng.devtools.presenter.b.b
    public boolean b(String str, boolean z, String str2) {
        com.xiaopeng.devtools.system.c.a aVar;
        if (z) {
            aVar = new com.xiaopeng.devtools.system.c.a("[fe80::22ff:fe44:6688%xpusb0]", -1, "user", "");
        } else {
            aVar = new com.xiaopeng.devtools.system.c.a("172.20.1.40", 2221, "root", "XiaoPenggz@icm2021!");
        }
        aVar.o(str, "/cache/icmlog/", str2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cmd", 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendLogCompressRequest(jSONObject.toString());
        return false;
    }

    @Override // com.xiaopeng.devtools.presenter.b.b
    public void ct(int i) {
        this.yo.sendCopyTboxLogRequest(i);
    }

    @Override // com.xiaopeng.devtools.presenter.b.b
    public void a(com.xiaopeng.commonfunc.b.a.a aVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(554702438);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(557846615);
        arrayList2.add(557846616);
        this.yn.b(arrayList, aVar);
        this.yo.b(arrayList2, aVar);
    }

    @Override // com.xiaopeng.devtools.presenter.b.b
    public void onDestroy() {
        this.yn.onDestroy();
        this.yo.onDestroy();
    }
}
