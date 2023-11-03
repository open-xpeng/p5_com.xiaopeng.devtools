package com.xiaopeng.devtools.utils;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* compiled from: IndivHelper.java */
/* loaded from: classes12.dex */
public final class j {
    private static a BQ;
    private static boolean po;
    private static boolean pq;
    private static Context sContext;
    private static com.xiaopeng.lib.security.a pm = com.xiaopeng.lib.security.c.om();
    private static com.xiaopeng.devtools.utils.a.a BR = com.xiaopeng.devtools.utils.a.b.lG();

    public static synchronized void X(Context context) {
        synchronized (j.class) {
            if (po) {
                com.xiaopeng.lib.utils.c.f("IndivHelper", "irdeto sdk has init");
                return;
            }
            sContext = context.getApplicationContext();
            try {
                es();
                pm.al(sContext);
                po = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void es() {
        if (BQ == null) {
            HandlerThread handlerThread = new HandlerThread("SecureSdkSampleHandler");
            handlerThread.start();
            BQ = new a(handlerThread.getLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void eu() {
        if (!po) {
            com.xiaopeng.lib.utils.c.f("IndivHelper", "irdeto sdk not init");
            return;
        }
        try {
            if (pm.oi()) {
                pm.aP(false);
            } else {
                ev();
                BQ.removeMessages(1);
                BQ.sendEmptyMessageDelayed(1, 60000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            pq = false;
        }
    }

    private static void ev() throws Exception {
        if (pq) {
            com.xiaopeng.lib.utils.c.i("IndivHelper", "irdeto sdk indiv is initing");
            return;
        }
        pq = true;
        IRequest a2 = BR.a(sContext, pm);
        if (a2 == null) {
            pq = false;
        } else {
            a2.execute(new Callback() { // from class: com.xiaopeng.devtools.utils.j.1
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    String b = j.BR.b(iResponse);
                    if (!TextUtils.isEmpty(b)) {
                        try {
                            if (j.pm.dC(b)) {
                                com.xiaopeng.lib.utils.c.f("IndivHelper", "个性化数据处理完成！");
                                j.ew();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    boolean unused = j.pq = false;
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    boolean unused = j.pq = false;
                    com.xiaopeng.lib.utils.c.f("IndivHelper", "Get individual info failed:" + iResponse.getException());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ew() {
        try {
            IRequest b = BR.b(sContext, pm);
            if (b == null) {
                com.xiaopeng.lib.utils.c.f("IndivHelper", "No need to post individual saving request.");
                ex();
                return;
            }
            b.execute(new Callback() { // from class: com.xiaopeng.devtools.utils.j.2
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    if (j.BR.c(iResponse)) {
                        j.BQ.post(new Runnable() { // from class: com.xiaopeng.devtools.utils.j.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    synchronized (j.class) {
                                        j.ex();
                                    }
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    com.xiaopeng.lib.utils.c.f("IndivHelper", "Failed save individual info to server:" + iResponse.getException());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ex() {
        pm.oj();
        pm.aP(true);
        ey();
    }

    private static void ey() {
        com.xiaopeng.lib.utils.c.f("IndivHelper", "notifyIndivFinished");
        BR.Y(sContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IndivHelper.java */
    /* loaded from: classes12.dex */
    public static class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.xiaopeng.lib.utils.c.f("IndivHelper", "handleMessage, event:" + message.what);
            if (message.what == 1) {
                j.eu();
            }
            super.handleMessage(message);
        }
    }

    public static String a(Context context, Map<String, String> map, long j) {
        String n = n(map);
        return com.xiaopeng.lib.utils.d.dH("xmart:appid:002" + j + n + "B638C588DCAD7C1A43E6FB").toLowerCase();
    }

    private static String n(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        Set<String> keySet = map.keySet();
        ArrayList<String> arrayList = new ArrayList();
        for (String str : keySet) {
            arrayList.add(str);
        }
        Collections.sort(arrayList);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : arrayList) {
            if (!"app_id".equals(str2) && !"timestamp".equals(str2) && !"sign".equals(str2)) {
                stringBuffer.append(str2);
                stringBuffer.append(map.get(str2));
            }
        }
        return stringBuffer.toString();
    }
}
