package com.xiaopeng.devtools.presenter.h;

import android.annotation.SuppressLint;
import android.car.hardware.CarPropertyValue;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.h;
import com.xiaopeng.lib.utils.j;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

/* compiled from: IcmUpdatePresenter.java */
/* loaded from: classes12.dex */
public class b {
    private com.xiaopeng.devtools.view.update.a zJ;
    private String zK;
    private String zL;
    private com.xiaopeng.devtools.model.a.b zM = new com.xiaopeng.devtools.model.a.b();

    public b(com.xiaopeng.devtools.view.update.a aVar) {
        this.zJ = aVar;
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        this.zM.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressLint({"MissingPermission"})
    public void onEvent(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        if (propertyId != 554702410) {
            if (propertyId == 557848139) {
                int intValue = ((Integer) carPropertyValue.getValue()).intValue();
                com.xiaopeng.lib.utils.c.i("IcmUpdatePresenter", "ID_ICM_UPDATE_RESULT value:" + intValue);
                this.zJ.dx(intValue);
                return;
            } else if (propertyId == 557848141) {
                int intValue2 = ((Integer) carPropertyValue.getValue()).intValue();
                com.xiaopeng.lib.utils.c.i("IcmUpdatePresenter", "ID_ICM_UPDATE_PROGRESS value:" + intValue2);
                this.zJ.dy(intValue2);
                return;
            } else {
                return;
            }
        }
        try {
            String str = (String) carPropertyValue.getValue();
            com.xiaopeng.lib.utils.c.i("IcmUpdatePresenter", "ID_ICM_UPDATE_REQ resStr:" + str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("status");
            String string = jSONObject.getString("message");
            String string2 = jSONObject.getString("homepath");
            String string3 = jSONObject.getString("user");
            String string4 = jSONObject.getString("passwd");
            if (i == 0) {
                k(string2, string3, string4);
            } else {
                com.xiaopeng.lib.utils.c.i("IcmUpdatePresenter", "ID_ICM_UPDATE_REQ ICM update res reject,reason:" + string);
                this.zJ.dx(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kp() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(554702410);
        arrayList.add(557848141);
        arrayList.add(557848139);
        this.zM.a(arrayList);
    }

    public boolean kq() {
        this.zK = null;
        String V = g.V(MyApplication.getContext());
        this.zL = V + "/icm/update.squashfs";
        String str = V + "/icm/update_icm_by_cdu_md5";
        if (g.aY(this.zL) && g.aY(str)) {
            this.zK = g.cs(str);
        }
        return this.zK != null;
    }

    public void kr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("md5", this.zK);
            this.zM.sendUpdateRequest(jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean k(final String str, final String str2, final String str3) {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.h.-$$Lambda$b$QshK15shaIG-rB38BI6hMwP4nlk
            @Override // java.lang.Runnable
            public final void run() {
                b.this.l(str2, str3, str);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(String str, String str2, String str3) {
        try {
            boolean a = h.a(this.zL, "172.20.1.40", 2221, str, str2, str3);
            com.xiaopeng.lib.utils.c.f("IcmUpdatePresenter", "FtpSender ret  = " + a);
            if (a) {
                this.zM.setUpdateFileTransferStatus(0);
                this.zJ.dx(2);
            } else {
                this.zM.setUpdateFileTransferStatus(1);
                this.zJ.dx(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
