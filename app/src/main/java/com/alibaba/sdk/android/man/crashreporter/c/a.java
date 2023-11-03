package com.alibaba.sdk.android.man.crashreporter.c;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import com.alibaba.sdk.android.man.crashreporter.e.e;
import com.alibaba.sdk.android.man.crashreporter.e.g;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import com.alibaba.sdk.android.man.util.MANConfig;
import com.ut.mini.UTAnalytics;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes11.dex */
public class a implements b {

    /* renamed from: a  reason: collision with other field name */
    private ExecutorService f74a = null;

    /* renamed from: b  reason: collision with other field name */
    private AtomicBoolean f75b = new AtomicBoolean(false);
    private c b = null;

    /* renamed from: a  reason: collision with other field name */
    private c f73a = null;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.a.b f72a = null;
    private Context a = null;
    private final int B = 3600000;
    private final int C = 10;

    @Override // com.alibaba.sdk.android.man.crashreporter.c.b
    public boolean a(Context context, com.alibaba.sdk.android.man.crashreporter.a.b bVar, c cVar, c cVar2) {
        try {
            this.f74a = Executors.newCachedThreadPool();
            this.f72a = bVar;
            this.b = cVar;
            this.f73a = cVar2;
            this.a = context;
            return true;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("init sender failure!", e);
            return false;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.c.b
    public void b(Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
        if (this.a == null || this.f74a == null || this.b == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("send all err because sendPools or crashReportStorage ot context is null!");
        }
        if (!this.f75b.get() && com.alibaba.sdk.android.man.crashreporter.e.a.d(this.a)) {
            this.f74a.execute(new RunnableC0023a(3, null, this.a, map));
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.c.b
    public void a(CrashReportDataForSave crashReportDataForSave, Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map, int i) {
        if (this.a == null || this.f74a == null || this.b == null) {
            com.alibaba.sdk.android.man.crashreporter.b.a.e("send err because sendPools or crashReportStorage or context is null!");
        }
        if (!this.f75b.get() && com.alibaba.sdk.android.man.crashreporter.e.a.d(this.a)) {
            this.f74a.execute(new RunnableC0023a(i, crashReportDataForSave, this.a, map));
        }
    }

    /* renamed from: com.alibaba.sdk.android.man.crashreporter.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    final class RunnableC0023a implements Runnable {
        private final int D;
        private Context a;

        /* renamed from: a  reason: collision with other field name */
        private final CrashReportDataForSave f77a;
        Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> d;
        private final int E = 1;
        private final int F = 61006;
        private final int G = 61007;
        private final int w = 61005;
        private final String MOTU_PATH = "motu";

        public RunnableC0023a(int i, CrashReportDataForSave crashReportDataForSave, Context context, Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
            this.D = i;
            this.f77a = crashReportDataForSave;
            this.a = context;
            this.d = map;
        }

        /* renamed from: a  reason: collision with other method in class */
        public boolean m51a(CrashReportDataForSave crashReportDataForSave) {
            if (crashReportDataForSave.type.intValue() == 2 || !MotuCrashReporter.getInstance().getConfigure().enableDeduplication || crashReportDataForSave.times == null || crashReportDataForSave.triggeredTime == null || crashReportDataForSave.times.intValue() == 1 || crashReportDataForSave.times.intValue() <= 1) {
                return true;
            }
            if (System.currentTimeMillis() - crashReportDataForSave.triggeredTime.longValue() >= 3600000 || crashReportDataForSave.times.intValue() >= 10) {
                a.this.f73a.b(true);
                return true;
            }
            return false;
        }

        private void d() {
            String str;
            String str2;
            try {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("start send abort content!");
                BaseDataContent a = a.this.f73a.a();
                if (a != null && (str = a.abortFlag) != null && (str2 = this.d.get(com.alibaba.sdk.android.man.crashreporter.global.a.APP_VERSION)) != null && str2.length() != 0 && str2.equals(a.appVersion)) {
                    String str3 = "CRASH_NOT_CAUGHT";
                    if (str.contains(com.alibaba.sdk.android.man.crashreporter.b.f66e)) {
                        str3 = "CRASH_CAUGHT";
                    }
                    a("Application", 61005, com.alibaba.sdk.android.man.crashreporter.b.f69h, str2 + str, str3, null);
                    a.this.f72a.a(MotuCrashReporter.getInstance().getConfigure(), a, 2);
                }
                a.this.f72a.a(MotuCrashReporter.getInstance().getConfigure(), a, 1);
                if (a != null) {
                    a.appStatus = String.format("%s%s", com.alibaba.sdk.android.man.crashreporter.b.f71j, Long.valueOf(System.currentTimeMillis()));
                    a.this.f73a.a(a);
                }
                com.alibaba.sdk.android.man.crashreporter.b.a.e("end send abort content!");
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.a("start send abort content err", e);
            }
        }

        private String a(CrashReportDataForSave crashReportDataForSave) {
            String mo41a;
            try {
                if (crashReportDataForSave.nativeCrashPath != null && (mo41a = a.this.f72a.mo41a(crashReportDataForSave.nativeCrashPath)) != null) {
                    if (crashReportDataForSave.content != null) {
                        Map a = g.a(com.alibaba.sdk.android.man.crashreporter.e.b.decode(crashReportDataForSave.content.getBytes()));
                        if (a != null) {
                            a.put("backtrace", mo41a);
                            return com.alibaba.sdk.android.man.crashreporter.e.b.b(g.c(a));
                        }
                        return "";
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("backtrace", mo41a);
                    return com.alibaba.sdk.android.man.crashreporter.e.b.b(g.c(hashMap));
                }
                return "";
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("get native crash content failure", e);
                return "";
            }
        }

        private void a(CrashReportDataForSave crashReportDataForSave, int i) {
            int i2;
            if (crashReportDataForSave != null) {
                try {
                    if (a.this.b.a(crashReportDataForSave, i)) {
                        com.alibaba.sdk.android.man.crashreporter.b.a.e("start send crash file!");
                        int i3 = 0;
                        if (crashReportDataForSave.type.intValue() != 0) {
                            if (crashReportDataForSave.type.intValue() == 1) {
                                i3 = 61006;
                                crashReportDataForSave.content = a(crashReportDataForSave);
                            } else if (crashReportDataForSave.type.intValue() == 2) {
                                i3 = 61007;
                            }
                            i2 = i3;
                        } else {
                            i2 = 1;
                        }
                        HashMap hashMap = new HashMap();
                        String str = crashReportDataForSave.toUTCrashMsg;
                        if (str != null) {
                            hashMap.put("StackTrace=====>" + str, "--invalid--");
                        }
                        String str2 = crashReportDataForSave.content;
                        if (str2 != null) {
                            com.alibaba.sdk.android.man.crashreporter.b.a.e("content_base64 is not null");
                        }
                        if (a(crashReportDataForSave.utPage, i2, com.alibaba.sdk.android.man.crashreporter.b.f68g, str2, crashReportDataForSave.metaDataBase64, hashMap)) {
                            com.alibaba.sdk.android.man.crashreporter.b.a.e("delete crash file!");
                            e.i(crashReportDataForSave.path);
                            return;
                        }
                        com.alibaba.sdk.android.man.crashreporter.b.a.e("send file failure!");
                    }
                } catch (Exception e) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("send crashReport err.", e);
                }
            }
        }

        private boolean a(String str, int i, String str2, String str3, String str4, Map map) {
            if (map == null) {
                try {
                    map = new HashMap();
                } catch (Exception e) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("send crashReport err.", e);
                    return false;
                }
            }
            Map map2 = map;
            map2.put(MANConfig.SDK_VERSION_KEY, MANConfig.SDK_VERSION_VALUE);
            com.alibaba.sdk.android.man.crashreporter.b.a.b(com.alibaba.sdk.android.man.crashreporter.b.a.TAG, "commitEventFinally : eventId=" + i + ", arg1=" + str2 + ", arg2=" + str3 + ", arg3=" + str4 + ", " + map2.toString());
            UTAnalytics.getInstance().getTracker("aliyun_mbaas").send(new UTOriginalCustomHitBuilder(str, i, str2, str3, str4, map2).build());
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a.this.f75b.compareAndSet(false, true)) {
                    e();
                    a.this.f75b.compareAndSet(true, false);
                }
            } catch (Throwable th) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("send error.", th);
            }
        }

        public void e() {
            try {
                if (this.D == 3) {
                    String[] a = a.this.b.a(0);
                    if (a != null) {
                        for (String str : a) {
                            com.alibaba.sdk.android.man.crashreporter.b.a.b("find file and start send:", str);
                            CrashReportDataForSave b = a.this.b.b(str);
                            if (m51a(b)) {
                                a(b, 0);
                            }
                        }
                    }
                    String[] a2 = a.this.b.a(1);
                    if (a2 != null) {
                        for (String str2 : a2) {
                            com.alibaba.sdk.android.man.crashreporter.b.a.b("find file and start send:", str2);
                            com.alibaba.sdk.android.man.crashreporter.b.a.e("start send native crash here.");
                            CrashReportDataForSave b2 = a.this.b.b(str2);
                            if (b2 == null) {
                                b2 = new CrashReportDataForSave();
                                b2.type = 1;
                                b2.times = 1;
                                b2.triggeredTime = Long.valueOf(System.currentTimeMillis());
                                b2.path = null;
                                b2.nativeCrashPath = String.format("%s/%s/%s", a.this.b.i(), "motu", str2);
                            }
                            if (m51a(b2)) {
                                a(b2, 1);
                            }
                        }
                    }
                } else if ((this.D == 1 || this.D == 2) && m51a(this.f77a)) {
                    a(this.f77a, 0);
                }
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("start send crash failure", e);
            }
        }
    }
}
