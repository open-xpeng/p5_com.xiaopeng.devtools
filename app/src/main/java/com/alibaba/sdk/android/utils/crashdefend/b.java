package com.alibaba.sdk.android.utils.crashdefend;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: CrashDefendManager.java */
/* loaded from: classes11.dex */
public class b {
    private static b b = null;
    private com.alibaba.sdk.android.utils.c a;

    /* renamed from: a  reason: collision with other field name */
    private c f108a;
    private ExecutorService c;
    private Context context;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.utils.crashdefend.a f107a = new com.alibaba.sdk.android.utils.crashdefend.a();

    /* renamed from: a  reason: collision with other field name */
    private final List<c> f109a = new ArrayList();
    private Map<String, String> h = new HashMap();

    /* renamed from: a  reason: collision with other field name */
    private final int[] f110a = new int[5];

    public static synchronized b a(Context context, com.alibaba.sdk.android.utils.c cVar) {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b(context, cVar);
            }
            bVar = b;
        }
        return bVar;
    }

    private b(Context context, com.alibaba.sdk.android.utils.c cVar) {
        this.c = null;
        this.context = context;
        this.a = cVar;
        this.c = new f().a();
        for (int i = 0; i < 5; i++) {
            this.f110a[i] = (5 * i) + 5;
        }
        this.h.put("sdkId", "utils");
        this.h.put("sdkVersion", "2.0.0");
        try {
            a();
            b();
        } catch (Exception e) {
            Log.d("UtilsSDK", e.getMessage(), e);
        }
    }

    private void a() {
        if (!e.m67a(this.context, this.f107a, this.f109a)) {
            this.f107a.a = 1L;
            return;
        }
        this.f107a.a++;
    }

    private void b() {
        this.f108a = null;
        ArrayList arrayList = new ArrayList();
        synchronized (this.f109a) {
            for (c cVar : this.f109a) {
                if (cVar.crashCount >= cVar.b) {
                    arrayList.add(cVar);
                }
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                c cVar2 = (c) it.next();
                if (cVar2.d >= 5) {
                    Log.i("UtilsSDK", "SDK " + cVar2.f113c + " has been closed");
                } else {
                    long j = this.f107a.a - this.f110a[cVar2.d];
                    g.a("UtilsSDK", "after restart " + ((cVar2.a - j) + 1) + " times, sdk will be restore");
                    if (cVar2.a < j) {
                        this.f108a = cVar2;
                        break;
                    }
                }
            }
            if (this.f108a == null) {
                Log.i("UtilsSDK", "NO SDK restore");
            } else {
                this.f108a.d++;
                Log.i("UtilsSDK", this.f108a.f113c + " will restore --- startSerialNumber:" + this.f108a.a + "   crashCount:" + this.f108a.crashCount);
            }
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m66a(c cVar, SDKMessageCallback sDKMessageCallback) {
        c a2;
        if (cVar == null || sDKMessageCallback == null) {
            return false;
        }
        try {
            if (TextUtils.isEmpty(cVar.f114d) || TextUtils.isEmpty(cVar.f113c) || (a2 = a(cVar, sDKMessageCallback)) == null) {
                return false;
            }
            boolean m65a = m65a(a2);
            if (a2.crashCount == a2.b) {
                a(a2.f113c, a2.f114d, a2.crashCount, a2.b);
            }
            a2.crashCount++;
            e.a(this.context, this.f107a, this.f109a);
            if (m65a) {
                a(a2);
                Log.i("UtilsSDK", "START:" + a2.f113c + " --- limit:" + a2.b + "  count:" + (a2.crashCount - 1) + "  restore:" + a2.d + "  startSerialNumber:" + a2.a + "  registerSerialNumber:" + a2.f112b);
            } else {
                sDKMessageCallback.crashDefendMessage(a2.b, a2.crashCount - 1);
                Log.i("UtilsSDK", "STOP:" + a2.f113c + " --- limit:" + a2.b + "  count:" + (a2.crashCount - 1) + "  restore:" + a2.d + "  startSerialNumber:" + a2.a + "  registerSerialNumber:" + a2.f112b);
            }
            return true;
        } catch (Exception e) {
            Log.d("UtilsSDK", e.getMessage(), e);
            return false;
        }
    }

    public void d(String str, String str2) {
    }

    private c a(c cVar, SDKMessageCallback sDKMessageCallback) {
        synchronized (this.f109a) {
            c cVar2 = null;
            if (this.f109a != null && this.f109a.size() > 0) {
                Iterator<c> it = this.f109a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    c next = it.next();
                    if (next != null && next.f113c.equals(cVar.f113c)) {
                        if (!next.f114d.equals(cVar.f114d)) {
                            next.f114d = cVar.f114d;
                            next.b = cVar.b;
                            next.c = cVar.c;
                            next.crashCount = 0;
                            next.d = 0;
                        }
                        if (next.f115d) {
                            Log.i("UtilsSDK", "SDK " + cVar.f113c + " has been registered");
                            return null;
                        }
                        next.f115d = true;
                        next.f111a = sDKMessageCallback;
                        next.f112b = this.f107a.a;
                        cVar2 = next;
                    }
                }
            }
            if (cVar2 == null) {
                cVar2 = (c) cVar.clone();
                cVar2.f115d = true;
                cVar2.f111a = sDKMessageCallback;
                cVar2.crashCount = 0;
                cVar2.f112b = this.f107a.a;
                this.f109a.add(cVar2);
            }
            return cVar2;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    private boolean m65a(c cVar) {
        if (cVar.crashCount < cVar.b) {
            cVar.a = cVar.f112b;
            return true;
        } else if (this.f108a != null && this.f108a.f113c.equals(cVar.f113c)) {
            cVar.crashCount = cVar.b - 1;
            cVar.a = cVar.f112b;
            return true;
        } else {
            return false;
        }
    }

    private void a(c cVar) {
        if (cVar == null) {
            return;
        }
        d dVar = new d();
        dVar.b = cVar;
        dVar.e = cVar.c;
        a(dVar);
        if (cVar.f111a != null) {
            cVar.f111a.crashDefendMessage(cVar.b, cVar.crashCount - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(c cVar) {
        if (cVar == null) {
            return;
        }
        if (cVar.d > 0) {
            b(cVar.f113c, cVar.f114d, cVar.d, 5);
        }
        cVar.crashCount = 0;
        cVar.d = 0;
    }

    private void a(d dVar) {
        if (dVar == null || dVar.b == null) {
            return;
        }
        this.c.execute(new a(dVar));
    }

    private void a(String str, String str2, int i, int i2) {
        if (this.a == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.h);
        hashMap.put("crashSdkId", str);
        hashMap.put("crashSdkVer", str2);
        hashMap.put("curCrashCount", String.valueOf(i));
        hashMap.put("crashThreshold", String.valueOf(i2));
        this.a.sendCustomHit("utils_biz_crash", 0L, hashMap);
    }

    private void b(String str, String str2, int i, int i2) {
        if (this.a == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.h);
        hashMap.put("crashSdkId", str);
        hashMap.put("crashSdkVer", str2);
        hashMap.put("recoverCount", String.valueOf(i));
        hashMap.put("recoverThreshold", String.valueOf(i2));
        this.a.sendCustomHit("utils_biz_recover", 0L, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CrashDefendManager.java */
    /* loaded from: classes11.dex */
    public class a implements Runnable {
        private d a;

        a(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            do {
                try {
                    Thread.sleep(1000L);
                    d dVar = this.a;
                    dVar.e--;
                } catch (InterruptedException e) {
                    return;
                } catch (Exception e2) {
                    Log.d("UtilsSDK", e2.getMessage(), e2);
                }
            } while (this.a.e > 0);
            if (this.a.e <= 0) {
                b.this.b(this.a.b);
                e.a(b.this.context, b.this.f107a, (List<c>) b.this.f109a);
            }
        }
    }
}
