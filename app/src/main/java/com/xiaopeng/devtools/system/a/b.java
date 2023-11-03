package com.xiaopeng.devtools.system.a;

import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageEventListener;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.lib.utils.c;
import java.lang.ref.WeakReference;

/* compiled from: XpStorageEventListener.java */
/* loaded from: classes12.dex */
public class b extends StorageEventListener {
    private String Ap;
    private WeakReference<Handler> sz;

    public b(Handler handler) {
        this.sz = new WeakReference<>(handler);
    }

    public String kI() {
        return this.Ap;
    }

    public void onStorageStateChanged(String str, String str2, String str3) {
        super.onStorageStateChanged(str, str2, str3);
        c.f("XpStorageEventListener", "path = " + str + ", oldState = " + str2 + ", newState = " + str3);
        if (str3.equals("mounted")) {
            if (this.sz.get() != null) {
                if ("/storage/extsd".equals(str)) {
                    Message obtainMessage = this.sz.get().obtainMessage();
                    obtainMessage.what = 3;
                    obtainMessage.sendToTarget();
                    return;
                }
                this.Ap = g.V(MyApplication.getContext());
                Message obtainMessage2 = this.sz.get().obtainMessage();
                obtainMessage2.what = 1;
                obtainMessage2.sendToTarget();
            }
        } else if ((str3.equals("unmounted") || str3.equals("ejecting")) && this.sz.get() != null && !"/storage/extsd".equals(str)) {
            this.Ap = null;
            Message obtainMessage3 = this.sz.get().obtainMessage();
            obtainMessage3.what = 2;
            obtainMessage3.sendToTarget();
        }
    }
}
