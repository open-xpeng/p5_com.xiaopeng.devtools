package com.xiaopeng.devtools.presenter.h;

import android.annotation.SuppressLint;
import android.os.FileUtils;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: ScreenFirUpdatePresenter.java */
/* loaded from: classes12.dex */
public class d {
    private static final String zS = a.c.ec("INAP562T_REMOTE_ATTR");
    private com.xiaopeng.devtools.view.update.c zT;
    private String zU;

    public d(com.xiaopeng.devtools.view.update.c cVar) {
        this.zT = cVar;
        com.xiaopeng.lib.utils.c.f("ScreenFirUpdatePresenter", "ScreenFirUpdatePresenter construct start.");
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
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressLint({"MissingPermission"})
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                com.xiaopeng.lib.utils.c.f("ScreenFirUpdatePresenter", "copy file success.");
                ku();
                return;
            case 2:
                com.xiaopeng.lib.utils.c.i("ScreenFirUpdatePresenter", "copy file error!!!");
                return;
            default:
                return;
        }
    }

    public boolean ks() {
        String[] strArr = null;
        this.zU = null;
        String str = g.V(MyApplication.getContext()) + "/screenFir/";
        try {
            strArr = g.J(str, "XPeng_AP.bin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (strArr != null) {
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str2 = strArr[i];
                if (!str2.contains("XPeng_AP.bin")) {
                    i++;
                } else {
                    this.zU = str + str2;
                    break;
                }
            }
        }
        com.xiaopeng.lib.utils.c.f("ScreenFirUpdatePresenter", "get Screen FirmFile FromUsb mPathScreenFirFile :" + this.zU);
        return this.zU != null;
    }

    public void kt() {
        boolean aW = g.aW("/data/firmware/");
        com.xiaopeng.lib.utils.c.f("ScreenFirUpdatePresenter", "createFolerRet ret =  " + aW);
        g.deleteFile("/data/firmware/XPeng_AP.bin");
        g.A(this.zU, "/data/firmware/XPeng_AP.bin");
    }

    public boolean ku() {
        if (!g.aY("/data/firmware/XPeng_AP.bin")) {
            kt();
            return false;
        }
        FileUtils.setPermissions("/data/firmware/XPeng_AP.bin", 438, -1, -1);
        com.xiaopeng.lib.utils.c.f("ScreenFirUpdatePresenter", "XP start to upgrade screen firmware ...");
        if (kv()) {
            j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.h.d.1
                @Override // java.lang.Runnable
                public void run() {
                    g.F(d.zS, "XPeng_AP.bin ");
                    g.F(d.zS, "1 ");
                }
            });
            kw();
            return true;
        }
        return true;
    }

    public boolean kv() {
        if (!new File(zS).exists()) {
            return false;
        }
        return true;
    }

    public void kw() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.h.d.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    d.this.zT.dB(1);
                    Thread.sleep(50L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int i = 0;
                while ("1".equals(g.cs(d.zS)) && i <= 40) {
                    try {
                        Thread.sleep(1500L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    i++;
                }
                if (i > 40) {
                    d.this.zT.dB(-1);
                } else {
                    d.this.zT.dB(0);
                }
            }
        });
    }
}
