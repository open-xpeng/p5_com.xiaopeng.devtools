package com.xiaopeng.devtools.presenter.oled;

import android.support.v4.util.ArrayMap;
import com.xiaopeng.devtools.bean.oled.CustomOledMusicBook;
import com.xiaopeng.devtools.bean.oled.OledData;
import com.xiaopeng.devtools.model.d.a;
import java.util.List;

/* compiled from: OledPresenter.java */
/* loaded from: classes12.dex */
public class c {
    private com.xiaopeng.devtools.view.oled.b zk;

    public c(com.xiaopeng.devtools.view.oled.b bVar) {
        this.zk = bVar;
    }

    public void onCreate() {
        com.xiaopeng.devtools.model.d.a.gZ().onCreate();
    }

    public void jR() {
        com.xiaopeng.devtools.model.d.a.gZ().a(new a.c() { // from class: com.xiaopeng.devtools.presenter.oled.c.1
            @Override // com.xiaopeng.devtools.model.d.a.c
            public void q(List<OledData> list) {
                if (c.this.zk != null) {
                    c.this.zk.z(list);
                }
            }
        });
    }

    public void jS() {
        com.xiaopeng.devtools.model.d.a.gZ().a(new a.b<List<CustomOledMusicBook>>() { // from class: com.xiaopeng.devtools.presenter.oled.c.2
            @Override // com.xiaopeng.devtools.model.d.a.b
            /* renamed from: t */
            public void m(List<CustomOledMusicBook> list) {
                if (c.this.zk != null) {
                    c.this.zk.A(list);
                }
            }
        });
    }

    public void jT() {
        com.xiaopeng.devtools.model.d.a.gZ().b(new a.b<List<CANMsg387>>() { // from class: com.xiaopeng.devtools.presenter.oled.c.3
            @Override // com.xiaopeng.devtools.model.d.a.b
            /* renamed from: t */
            public void m(List<CANMsg387> list) {
                ArrayMap<String, List<CANMsg387>> arrayMap = new ArrayMap<>();
                arrayMap.put("oled_default", list);
                if (c.this.zk != null) {
                    c.this.zk.a(arrayMap);
                }
            }
        });
    }

    public void a(OledData oledData) {
        com.xiaopeng.devtools.model.d.a.gZ().a(oledData);
    }

    public void a(CANMsg387 cANMsg387) {
        com.xiaopeng.devtools.model.d.a.gZ().a(cANMsg387);
    }

    public void bC(String str) {
        com.xiaopeng.devtools.model.d.a.gZ().bC(str);
    }

    public void ha() {
        com.xiaopeng.devtools.model.d.a.gZ().ha();
    }

    public void onDestroy() {
        com.xiaopeng.devtools.model.d.a.gZ().onDestroy();
    }
}
