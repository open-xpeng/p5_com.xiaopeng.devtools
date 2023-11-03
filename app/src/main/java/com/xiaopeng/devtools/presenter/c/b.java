package com.xiaopeng.devtools.presenter.c;

import android.car.hardware.CarPropertyValue;
import com.google.gson.Gson;
import com.xiaopeng.devtools.bean.car.TboxApn;
import com.xiaopeng.devtools.bean.car.TboxGsmBean;
import com.xiaopeng.devtools.bean.car.TboxGsmStatus;
import com.xiaopeng.devtools.bean.car.TboxLteBean;
import com.xiaopeng.devtools.bean.car.TboxLteStatus;
import com.xiaopeng.devtools.model.a.d;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

/* compiled from: TboxNetworkPresenter.java */
/* loaded from: classes12.dex */
public class b {
    private com.xiaopeng.devtools.view.modemui.b yr;
    private TboxApn ys;
    private TboxGsmStatus yt;
    private TboxLteStatus yu;
    private d wy = d.fz();
    private Gson gson = new Gson();

    public b(com.xiaopeng.devtools.view.modemui.b bVar) {
        this.yr = bVar;
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId == 554700817) {
            c.f("TboxNetworkPresenter", "ID_TBOX_CDU_APN");
            this.ys = (TboxApn) this.gson.fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxApn.class);
            a(this.ys);
        } else if (propertyId == 554700819) {
            c.f("TboxNetworkPresenter", "ID_TBOX_CDU_MODEM_STATUS_RESP");
            try {
                String string = new JSONObject((String) carPropertyValue.getValue()).getString("Type");
                int i = 0;
                c.d("TboxNetworkPresenter", "ID_TBOX_CDU_MODEM_STATUS_RESP", string);
                if ("LTE".equals(string)) {
                    this.yu = (TboxLteStatus) this.gson.fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxLteStatus.class);
                    ch(this.yu.getTbox().toString());
                    List<TboxLteBean> neighbours = this.yu.getNeighbours();
                    ArrayList<String> arrayList = new ArrayList<>();
                    while (i < neighbours.size()) {
                        arrayList.add(i, neighbours.get(i).toString());
                        i++;
                    }
                    a(arrayList);
                } else if ("GSM".equals(string)) {
                    this.yt = (TboxGsmStatus) this.gson.fromJson((String) carPropertyValue.getValue(), (Class<Object>) TboxGsmStatus.class);
                    ch(this.yt.getTbox().toString());
                    List<TboxGsmBean> neighbours2 = this.yt.getNeighbours();
                    ArrayList<String> arrayList2 = new ArrayList<>();
                    while (i < neighbours2.size()) {
                        arrayList2.add(i, neighbours2.get(i).toString());
                        i++;
                    }
                    a(arrayList2);
                }
            } catch (Exception e) {
                c.c("TboxNetworkPresenter", "ID_TBOX_CDU_MODEM_STATUS_RESP", e);
            }
        }
    }

    public void requestTBoxModemStatus() {
        this.wy.requestTBoxModemStatus();
    }

    public void a(final TboxApn tboxApn) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.c.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.yr != null) {
                    b.this.yr.a(tboxApn);
                }
            }
        });
    }

    public void ch(final String str) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.c.b.2
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.yr != null) {
                    b.this.yr.ch(str);
                }
            }
        });
    }

    public void a(final ArrayList<String> arrayList) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.c.b.3
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.yr != null) {
                    b.this.yr.a(arrayList);
                }
            }
        });
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
