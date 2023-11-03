package com.xiaopeng.devtools.presenter.aftersales;

import com.xiaopeng.lib.utils.j;
import java.util.ArrayList;

/* compiled from: DidInfoPresenter.java */
/* loaded from: classes12.dex */
public class d {
    private static final String[] vv = {"CFC_CODE", "CURRENT_RUNNING_APP_ZONE", "MCU_SAVE_CONFIG", "CDU_FAULT_CODE", "CDU_CAR_FEATURE_CODE", "CDU_CAR_FEATURE_CODE_AFTERSALES", "ESK_CODE_WRITTEN_STATUS"};
    private com.xiaopeng.devtools.view.aftersales.a vu;

    public d(com.xiaopeng.devtools.view.aftersales.a aVar) {
        this.vu = aVar;
    }

    public void ig() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.d.1
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<String> arrayList = new ArrayList<>();
                for (String str : d.vv) {
                    com.xiaopeng.devtools.model.b.a.a(str, arrayList);
                }
                if (d.this.vu != null) {
                    d.this.vu.c(arrayList);
                }
            }
        });
    }
}
