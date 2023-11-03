package com.xiaopeng.devtools.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.TextUtils;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.model.can.CanCollectModel;
import com.xiaopeng.devtools.system.service.CanDataCollectService;
import com.xiaopeng.devtools.utils.f;
import java.util.HashMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: XpengCan.java */
/* loaded from: classes12.dex */
public class b extends a {
    private int mStep;
    private com.xiaopeng.devtools.a.b ve;
    private HashMap<String, Integer> vf;
    private CanCollectModel vg;

    public b(Context context, com.xiaopeng.devtools.a.b bVar) {
        super(context);
        this.mStep = 0;
        this.vd = "CAN";
        this.CLASS_NAME = "XpengCan";
        this.ve = bVar;
        this.vg = CanCollectModel.fo();
        this.vf = new HashMap<>();
        this.vf.put("ICAN", Integer.valueOf(CanCollectModel.CanData.ICAN.ordinal()));
        this.vf.put("DCAN", Integer.valueOf(CanCollectModel.CanData.DCAN.ordinal()));
        this.vf.put("BCAN", Integer.valueOf(CanCollectModel.CanData.BCAN.ordinal()));
        this.vf.put("CCAN", Integer.valueOf(CanCollectModel.CanData.CCAN.ordinal()));
        this.vf.put("ECAN", Integer.valueOf(CanCollectModel.CanData.ECAN.ordinal()));
        this.vf.put("ADCAN", Integer.valueOf(CanCollectModel.CanData.ADCAN.ordinal()));
        f.k(this);
    }

    @Override // com.xiaopeng.devtools.a.a.a
    public synchronized String f(String[] strArr) {
        String str;
        str = null;
        if (a(strArr, new String[]{"1"})) {
            if (!this.vg.fq() && !TextUtils.isEmpty(strArr[1])) {
                this.vg.B(false);
                boolean z = false;
                for (String str2 : strArr[1].split("_")) {
                    Integer num = this.vf.get(str2);
                    if (num != null) {
                        this.vg.c(num.intValue(), true);
                        z = true;
                    }
                }
                if (z) {
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("collect candata " + strArr[1] + " by PC command", "triggered");
                    this.ve.I(true);
                    this.mStep = 1;
                    Intent intent = new Intent(MyApplication.getContext(), CanDataCollectService.class);
                    this.vg.ft();
                    this.vg.bT(10001);
                    intent.putExtra("storagePath", this.ve.hT());
                    MyApplication.getContext().startServiceAsUser(intent, UserHandle.CURRENT);
                } else {
                    str = bL(strArr[0]);
                }
            } else {
                str = bL(strArr[0]);
            }
        } else if (a(strArr, new String[]{"0"})) {
            this.ve.I(false);
            if (this.vg.fq() && this.vg.fp() == 10001) {
                this.mStep = 2;
                MyApplication.getContext().stopService(new Intent(MyApplication.getContext(), CanDataCollectService.class));
            } else {
                str = bL(strArr[0]);
            }
        } else {
            str = bN(strArr[0]);
        }
        return str;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    @SuppressLint({"MissingPermission"})
    public void onEvent(Integer num) {
        if (num.intValue() == 4 && CanCollectModel.fo().fp() == 10001) {
            if (this.mStep == 1 && CanCollectModel.fo().fq()) {
                this.ve.write(bM("1"));
                this.mStep = 0;
            } else if (this.mStep == 2 && !CanCollectModel.fo().fq()) {
                this.ve.write(bM("0"));
                this.mStep = 0;
            }
        }
    }

    @Override // com.xiaopeng.devtools.a.a.a
    public void destroy() {
        super.destroy();
        f.l(this);
        if (this.vg.fq() && this.vg.fp() == 10001) {
            this.vg.D(true);
            MyApplication.getContext().stopService(new Intent(MyApplication.getContext(), CanDataCollectService.class));
        }
    }
}
