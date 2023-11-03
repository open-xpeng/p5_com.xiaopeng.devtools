package com.xiaopeng.devtools.presenter.oled;

import com.xiaopeng.devtools.model.d.b;
import java.util.List;

/* compiled from: OledMusicBookPresenter.java */
/* loaded from: classes12.dex */
public class b {
    private com.xiaopeng.devtools.view.oled.a zi;

    public b(com.xiaopeng.devtools.view.oled.a aVar) {
        this.zi = aVar;
    }

    public void jQ() {
        com.xiaopeng.devtools.model.d.b.hc().a(new b.a() { // from class: com.xiaopeng.devtools.presenter.oled.b.1
            @Override // com.xiaopeng.devtools.model.d.b.a
            public void r(List<CANMsg387> list) {
                if (b.this.zi != null) {
                    b.this.zi.y(list);
                }
            }
        });
    }
}
