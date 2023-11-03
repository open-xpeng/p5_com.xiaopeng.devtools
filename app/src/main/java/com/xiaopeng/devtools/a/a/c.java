package com.xiaopeng.devtools.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.support.v4.media.MediaPlayer2;
import android.text.TextUtils;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.system.service.CopyLogService;
import com.xiaopeng.devtools.utils.f;
import java.util.ArrayList;
import java.util.HashMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: XpengLog.java */
/* loaded from: classes12.dex */
public class c extends a {
    private com.xiaopeng.devtools.a.b ve;
    private HashMap<String, Integer> vh;

    public c(Context context, com.xiaopeng.devtools.a.b bVar) {
        super(context);
        this.vd = "LOG";
        this.CLASS_NAME = "XpengLog";
        this.ve = bVar;
        this.vh = new HashMap<>();
        this.vh.put("AND", 1001);
        this.vh.put("TBOX", 1003);
        this.vh.put("NAVI", Integer.valueOf((int) MediaPlayer2.MEDIAPLAYER2_STATE_ERROR));
        this.vh.put("MODEM", 1006);
        this.vh.put("ICM", 1007);
        f.k(this);
    }

    @Override // com.xiaopeng.devtools.a.a.a
    public synchronized String f(String[] strArr) {
        String str;
        str = null;
        if (a(strArr, new String[]{"1"})) {
            if (!TextUtils.isEmpty(strArr[1])) {
                String[] split = strArr[1].split("_");
                ArrayList arrayList = new ArrayList();
                for (String str2 : split) {
                    Integer num = this.vh.get(str2);
                    if (num != null) {
                        arrayList.add(num);
                    }
                }
                if (arrayList.size() > 0) {
                    com.xiaopeng.devtools.utils.b.recordRepairModeAction("copy log " + strArr[1] + " by PC command", "triggered");
                    Intent intent = new Intent(MyApplication.getContext(), CopyLogService.class);
                    intent.putExtra("storagePath", this.ve.hT());
                    intent.putExtra("copyDest", 10001);
                    intent.putExtra("copylogs", arrayList);
                    MyApplication.getContext().startServiceAsUser(intent, UserHandle.CURRENT);
                } else {
                    str = bL(strArr[0]);
                }
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
        switch (num.intValue()) {
            case 7:
                this.ve.write(bM("1"));
                return;
            case 8:
                this.ve.write(bL("1"));
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.a.a.a
    public void destroy() {
        super.destroy();
        f.l(this);
    }
}
