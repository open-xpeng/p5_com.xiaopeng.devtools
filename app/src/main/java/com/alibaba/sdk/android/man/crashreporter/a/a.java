package com.alibaba.sdk.android.man.crashreporter.a;

import android.content.Context;
import android.os.Looper;
import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.c;
import com.alibaba.sdk.android.man.crashreporter.e.e;
import com.alibaba.sdk.android.man.crashreporter.e.i;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class a implements b {
    private static int k = 0;
    private static int l = 0;
    private static int m = 10;
    private static int n = 10;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.a.a.b f53a = null;
    private Context a = null;

    /* renamed from: m  reason: collision with other field name */
    private String f56m = null;

    /* renamed from: a  reason: collision with other field name */
    private Map<String, String> f55a = null;

    /* renamed from: n  reason: collision with other field name */
    private String f57n = null;
    private String o = null;
    private String p = "";
    private c environment = null;

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.d.c f54a = null;
    private com.alibaba.sdk.android.man.crashreporter.d.c b = null;

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    public boolean a(Context context, ReporterConfigure reporterConfigure, c cVar, com.alibaba.sdk.android.man.crashreporter.d.c cVar2, com.alibaba.sdk.android.man.crashreporter.d.c cVar3) {
        try {
            if (context != null) {
                if (this.f53a == null) {
                    this.f53a = new com.alibaba.sdk.android.man.crashreporter.a.a.a();
                }
                this.a = context;
                this.f53a.a(reporterConfigure, this.a, cVar3, this);
                this.environment = cVar;
                this.f54a = cVar3;
                this.b = cVar2;
                return true;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.h("init builder failure!");
            return false;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("init builder err!", e);
            return false;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    public synchronized Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> a(int i, int i2, int i3, int i4) {
        try {
            ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
            k = i;
            l = i2;
            m = i3;
            n = i4;
            a(configure, k, l, m, n);
            if (this.f53a == null) {
                this.f53a = new com.alibaba.sdk.android.man.crashreporter.a.a.a();
            }
            if (this.f53a == null || this.environment == null || this.f54a == null) {
                return null;
            }
            Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> mo43b = this.f53a.mo43b();
            a((com.alibaba.sdk.android.man.crashreporter.a.c.a.a) null);
            mo43b.put(com.alibaba.sdk.android.man.crashreporter.global.a.IMSI, com.alibaba.sdk.android.man.crashreporter.e.a.f(this.a));
            mo43b.put(com.alibaba.sdk.android.man.crashreporter.global.a.IMEI, com.alibaba.sdk.android.man.crashreporter.e.a.e(this.a));
            mo43b.put(com.alibaba.sdk.android.man.crashreporter.global.a.APP_KEY, this.environment.appKey);
            mo43b.put(com.alibaba.sdk.android.man.crashreporter.global.a.APP_VERSION, this.environment.appVersion);
            mo43b.put(com.alibaba.sdk.android.man.crashreporter.global.a.CHANNEL, this.environment.channel);
            mo43b.put(com.alibaba.sdk.android.man.crashreporter.global.a.USER_NICK, this.environment.userNick);
            mo43b.put(com.alibaba.sdk.android.man.crashreporter.global.a.IS_BACKGROUD, this.f53a.b());
            return mo43b;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("get internal Data failed", e);
            return null;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    /* renamed from: a  reason: collision with other method in class */
    public Map<String, String> mo42a() {
        if (this.f53a != null) {
            return this.f53a.a();
        }
        return null;
    }

    public synchronized boolean a(ReporterConfigure reporterConfigure) {
        return false;
    }

    private void a(ReporterConfigure reporterConfigure, int i, int i2, int i3, int i4) {
        if (reporterConfigure != null) {
            reporterConfigure.enableMaxThreadNumber = i;
            reporterConfigure.enableMaxThreadStackTraceNumber = i2;
            reporterConfigure.enableSysLogcatMaxCount = i3;
            reporterConfigure.enableSysLogcatLinkMaxCount = i4;
        }
    }

    private String a(ReporterConfigure reporterConfigure, com.alibaba.sdk.android.man.crashreporter.a.b.a aVar, com.alibaba.sdk.android.man.crashreporter.a.b.b bVar) {
        if (reporterConfigure == null || !reporterConfigure.enableDumpAllThread) {
            return "";
        }
        return bVar.a(aVar.c());
    }

    private String a(com.alibaba.sdk.android.man.crashreporter.a.b.a aVar, com.alibaba.sdk.android.man.crashreporter.a.b.b bVar) {
        return bVar.a(com.alibaba.sdk.android.man.crashreporter.a.b.a.d());
    }

    /* renamed from: a  reason: collision with other method in class */
    private boolean m39a() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            return true;
        }
        return false;
    }

    private void a(com.alibaba.sdk.android.man.crashreporter.a.c.a.a aVar) {
        try {
            if (this.environment == null) {
                return;
            }
            if (this.environment.userNick == null || this.environment.userNick.length() <= 0) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("user nick is null or length <= 0!");
                this.environment.userNick = this.f54a.h();
            }
            if (this.environment.appKey == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("use taobao default appKey,because your appKey is null!");
                this.environment.appKey = this.environment.k;
            }
            if (this.environment.appVersion == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("use taobao app base or default Version,because your appVersion is null!");
                String d = com.alibaba.sdk.android.man.crashreporter.a.d.a.d(this.a);
                if (d != null) {
                    this.environment.appVersion = d;
                } else {
                    this.environment.appVersion = this.environment.l;
                }
            }
            if (aVar != null) {
                aVar.c.put("sdkname", "MOTU");
                aVar.c.put("sdkVersion", "2.0.0");
                aVar.c.put("platform", "ANDROID");
                aVar.c.put("launchedTime", Long.valueOf(this.environment.startupTime));
                aVar.c.put("channel", this.environment.channel);
                aVar.c.put("user", this.environment.userNick);
                aVar.c.put("appKey", this.environment.appKey);
                aVar.c.put("appVersion", this.environment.appVersion);
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("set base info failure", e);
        }
    }

    private void a(CrashReportDataForSave crashReportDataForSave) {
        try {
            String i = this.b.i();
            String a = this.b.a(crashReportDataForSave.triggeredTime.longValue());
            crashReportDataForSave.path = String.format("%s/%s%s", i, a, this.b.j());
            crashReportDataForSave.fileName = a;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("set crash report file path err", e);
        }
    }

    private String a(com.alibaba.sdk.android.man.crashreporter.a.c.a.a aVar, ReporterConfigure reporterConfigure) {
        try {
            byte[] a = new com.alibaba.sdk.android.man.crashreporter.a.c.b().a(aVar, this.a, a(reporterConfigure.enableMaxThreadNumber, reporterConfigure.enableMaxThreadStackTraceNumber, reporterConfigure.enableSysLogcatMaxCount, reporterConfigure.enableSysLogcatLinkMaxCount));
            if (a == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("reporter build failure!");
            }
            return com.alibaba.sdk.android.man.crashreporter.e.b.b(a);
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("reporter build err!", e);
            return null;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    /* renamed from: a  reason: collision with other method in class */
    public String mo41a(String str) {
        if (str != null) {
            String str2 = null;
            try {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    str2 = new String(bArr, "GB2312");
                    e.i(str);
                }
                if (str2 != null) {
                    return str2;
                }
                return "";
            } catch (FileNotFoundException e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("native file not found err!", e);
                return "";
            } catch (Exception e2) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("get native stack trace err!", e2);
                return "";
            }
        }
        return "";
    }

    private String a() {
        return null;
    }

    private String a(ReporterConfigure reporterConfigure, CrashReportDataForSave crashReportDataForSave) {
        int i;
        String str;
        BaseDataContent a;
        try {
            HashMap hashMap = new HashMap();
            com.alibaba.sdk.android.man.crashreporter.a.d.a.a(hashMap, this.a);
            if (this.f53a != null) {
                String b = this.f53a.b();
                if (b == null) {
                    b = "no status info";
                }
                hashMap.put("appStatus", b);
            }
            if (reporterConfigure.enableDeduplication && (a = this.f54a.a()) != null) {
                i = a.times.intValue();
            } else {
                i = 1;
            }
            crashReportDataForSave.times = Integer.valueOf(i);
            if (crashReportDataForSave.times != null) {
                if (crashReportDataForSave.times.intValue() > 1) {
                    hashMap.put("ts", String.format("%s", Integer.valueOf(crashReportDataForSave.times.intValue() - 1)));
                } else {
                    hashMap.put("ts", "1");
                }
            }
            String b2 = com.alibaba.sdk.android.man.crashreporter.a.c.a.b(hashMap);
            if (b2 != null) {
                str = com.alibaba.sdk.android.man.crashreporter.e.b.b(b2.getBytes());
            } else {
                str = null;
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getMetaData err!", e);
        }
        if (str == null) {
            return null;
        }
        return str;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    public void a(Map map, String str, String str2, String str3) {
        this.f55a = map;
        this.f57n = str;
        this.o = str2;
        this.p = str3;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    public CrashReportDataForSave a(String str, String str2, String str3, Map map) {
        try {
            ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
            k = 0;
            l = 0;
            m = 400;
            n = 200;
            a(configure, k, l, m, n);
            com.alibaba.sdk.android.man.crashreporter.a.b.a a = com.alibaba.sdk.android.man.crashreporter.a.b.a.a("", false);
            com.alibaba.sdk.android.man.crashreporter.a.b.b bVar = new com.alibaba.sdk.android.man.crashreporter.a.b.b();
            String a2 = a(configure, a, bVar);
            String a3 = a(a, bVar);
            com.alibaba.sdk.android.man.crashreporter.a.c.a.a aVar = new com.alibaba.sdk.android.man.crashreporter.a.c.a.a();
            CrashReportDataForSave crashReportDataForSave = new CrashReportDataForSave();
            crashReportDataForSave.triggeredTime = Long.valueOf(System.currentTimeMillis());
            crashReportDataForSave.toUTCrashMsg = str3;
            crashReportDataForSave.hashCode = String.format("%s", Integer.valueOf(i.a(str2)));
            crashReportDataForSave.type = 0;
            a(crashReportDataForSave);
            a(aVar);
            CrashReportDataForSave a4 = a(crashReportDataForSave.type.intValue(), configure, crashReportDataForSave.hashCode, crashReportDataForSave.path, this.environment.userNick);
            if (a4 != null) {
                return a4;
            }
            aVar.c.put("triggeredTime", crashReportDataForSave.triggeredTime);
            aVar.c.put("exception", str);
            aVar.c.put("backtrace", str2);
            aVar.c.put("threads", a2);
            aVar.c.put("currentThread", a3);
            if (!m39a()) {
                aVar.c.put("isMainThread", false);
            } else {
                aVar.c.put("isMainThread", true);
            }
            aVar.c.put("type", "ANDROID");
            aVar.c.put("extData", com.alibaba.sdk.android.man.crashreporter.a.c.a.b(map));
            crashReportDataForSave.content = a(aVar, configure);
            crashReportDataForSave.metaDataBase64 = a(configure, crashReportDataForSave);
            crashReportDataForSave.utPage = a();
            com.alibaba.sdk.android.man.crashreporter.b.a.e("build java crash data end!");
            this.b.b(crashReportDataForSave);
            return crashReportDataForSave;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("buildJavaCrashReport err!", e);
            return null;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    /* renamed from: a  reason: collision with other method in class */
    public CrashReportDataForSave mo40a() {
        return b(null, null, null, null);
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    public CrashReportDataForSave b(String str, String str2, String str3, Map map) {
        String str4;
        if (str == null) {
            try {
                str4 = this.o;
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("buildNativeCrashReport err!", e);
                return null;
            }
        } else {
            str4 = str;
        }
        String str5 = str3 == null ? this.p : str3;
        String str6 = str2 == null ? this.f57n : str2;
        Map<String, String> map2 = map == null ? this.f55a : map;
        ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
        k = 15;
        l = 15;
        m = 100;
        n = 50;
        a(configure, k, l, m, n);
        String a = a(configure, com.alibaba.sdk.android.man.crashreporter.a.b.a.a("", false), new com.alibaba.sdk.android.man.crashreporter.a.b.b());
        com.alibaba.sdk.android.man.crashreporter.a.c.a.a aVar = new com.alibaba.sdk.android.man.crashreporter.a.c.a.a();
        CrashReportDataForSave crashReportDataForSave = new CrashReportDataForSave();
        crashReportDataForSave.triggeredTime = Long.valueOf(System.currentTimeMillis());
        if (str5.length() <= 0) {
            str5 = a;
        }
        crashReportDataForSave.hashCode = String.format("%s", Integer.valueOf(i.a(str5)));
        crashReportDataForSave.nativeCrashPath = str4;
        crashReportDataForSave.type = 1;
        String i = this.b.i();
        String trim = str4.trim();
        String substring = trim.substring(trim.lastIndexOf("/") + 1);
        crashReportDataForSave.path = String.format("%s/%s", i, substring);
        crashReportDataForSave.fileName = substring;
        a(aVar);
        CrashReportDataForSave a2 = a(crashReportDataForSave.type.intValue(), configure, crashReportDataForSave.hashCode, crashReportDataForSave.path, this.environment.userNick);
        if (a2 != null) {
            return a2;
        }
        aVar.c.put("triggeredTime", crashReportDataForSave.triggeredTime);
        aVar.c.put("exception", str6);
        aVar.c.put("threads", a);
        aVar.c.put("currentThread", str5);
        if (m39a()) {
            aVar.c.put("isMainThread", true);
        } else {
            aVar.c.put("isMainThread", false);
        }
        aVar.c.put("type", "ANDROID_NATIVE");
        aVar.c.put("extData", com.alibaba.sdk.android.man.crashreporter.a.c.a.b(map2));
        crashReportDataForSave.content = a(aVar, configure);
        crashReportDataForSave.metaDataBase64 = a(configure, crashReportDataForSave);
        crashReportDataForSave.utPage = a();
        com.alibaba.sdk.android.man.crashreporter.b.a.e("build native crash data end!");
        this.b.b(crashReportDataForSave);
        return crashReportDataForSave;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    public CrashReportDataForSave a(String str) {
        com.alibaba.sdk.android.man.crashreporter.a.b.a a;
        try {
            ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
            k = 30;
            l = 5;
            m = 60;
            n = 20;
            a(configure, k, l, m, n);
            if (str != null) {
                a = com.alibaba.sdk.android.man.crashreporter.a.b.a.a(str, false);
            } else {
                a = com.alibaba.sdk.android.man.crashreporter.a.b.a.a();
            }
            com.alibaba.sdk.android.man.crashreporter.a.b.b bVar = new com.alibaba.sdk.android.man.crashreporter.a.b.b();
            String aVar = a.toString();
            String a2 = bVar.a(a.c());
            String a3 = bVar.a(com.alibaba.sdk.android.man.crashreporter.a.b.a.e());
            com.alibaba.sdk.android.man.crashreporter.a.c.a.a aVar2 = new com.alibaba.sdk.android.man.crashreporter.a.c.a.a();
            CrashReportDataForSave crashReportDataForSave = new CrashReportDataForSave();
            crashReportDataForSave.triggeredTime = Long.valueOf(System.currentTimeMillis());
            crashReportDataForSave.type = 2;
            a(crashReportDataForSave);
            a(aVar2);
            aVar2.c.put("triggeredTime", crashReportDataForSave.triggeredTime);
            aVar2.c.put("exception", aVar);
            aVar2.c.put("threads", a2);
            aVar2.c.put("backtrace", a3);
            aVar2.c.put("isMainThread", true);
            aVar2.c.put("type", "ANDROID_ANR");
            crashReportDataForSave.content = a(aVar2, configure);
            crashReportDataForSave.metaDataBase64 = a(configure, crashReportDataForSave);
            crashReportDataForSave.utPage = a();
            com.alibaba.sdk.android.man.crashreporter.b.a.e("build stuck data end!");
            this.b.b(crashReportDataForSave);
            return crashReportDataForSave;
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("buildStuckReport err!", e);
            return null;
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.b
    public void a(ReporterConfigure reporterConfigure, BaseDataContent baseDataContent, int i) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (baseDataContent != null && reporterConfigure.enableAbortCount) {
                if (i == 0) {
                    String str = baseDataContent.abortFlag;
                    if (str != null && !str.contains(com.alibaba.sdk.android.man.crashreporter.b.f66e)) {
                        baseDataContent.abortFlag = String.format("%s%s%s", str, com.alibaba.sdk.android.man.crashreporter.b.f66e, Long.valueOf(currentTimeMillis));
                    } else if (str == null) {
                        baseDataContent.abortFlag = String.format("%s%s", com.alibaba.sdk.android.man.crashreporter.b.f66e, Long.valueOf(currentTimeMillis));
                    }
                } else if (i == 1) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("abort content APPLICATION_STARTED");
                    if (baseDataContent.abortFlag != null && baseDataContent.abortFlag.contains(com.alibaba.sdk.android.man.crashreporter.b.f67f)) {
                        return;
                    }
                    baseDataContent.appVersion = this.environment.appVersion;
                    baseDataContent.abortFlag = String.format("%s%s", com.alibaba.sdk.android.man.crashreporter.b.f67f, Long.valueOf(currentTimeMillis));
                } else if (i == 2) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.e("remove abort content flag ");
                    if (baseDataContent.abortFlag == null) {
                        return;
                    }
                    baseDataContent.abortFlag = null;
                }
            } else if (reporterConfigure.enableAbortCount && i == 1) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("abort content APPLICATION_STARTED");
                BaseDataContent baseDataContent2 = new BaseDataContent();
                baseDataContent2.appVersion = this.environment.appVersion;
                baseDataContent2.abortFlag = String.format("%s%s", com.alibaba.sdk.android.man.crashreporter.b.f67f, Long.valueOf(currentTimeMillis));
            }
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("build abort flag failure!", e);
        }
    }

    private CrashReportDataForSave a(int i, ReporterConfigure reporterConfigure, String str, String str2, String str3) {
        if (i == 2) {
            return null;
        }
        BaseDataContent a = this.f54a.a();
        if (a != null) {
            try {
                a(reporterConfigure, a, 0);
                a.userNick = str3;
                a.appVersion = this.environment.appVersion;
                if (!reporterConfigure.enableDeduplication) {
                    a.path = null;
                    a.times = 0;
                    a.hashCode = null;
                    this.f54a.a(a);
                    return null;
                }
                String str4 = a.hashCode;
                String str5 = a.path;
                Integer num = a.times;
                if (str4 != null && num.intValue() != 0 && str.equals(str4)) {
                    if (num.intValue() != 1) {
                        if (num.intValue() >= 2) {
                            a.hashCode = str4;
                            Integer valueOf = Integer.valueOf(num.intValue() + 1);
                            a.times = valueOf;
                            a.path = str5;
                            this.f54a.a(a);
                            CrashReportDataForSave a2 = this.b.a(str5, valueOf.intValue());
                            if (a2 == null) {
                                this.f54a.b(true);
                                return null;
                            }
                            return a2;
                        }
                    } else {
                        a.hashCode = str4;
                        a.times = Integer.valueOf(num.intValue() + 1);
                        a.path = str2;
                        this.f54a.a(a);
                    }
                } else {
                    a.hashCode = str;
                    a.times = 1;
                    a.path = str2;
                    this.f54a.a(a);
                }
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("parse base data file error.", e);
            }
        } else {
            BaseDataContent baseDataContent = new BaseDataContent();
            baseDataContent.abortFlag = String.format("%s%s", com.alibaba.sdk.android.man.crashreporter.b.f66e, Long.valueOf(System.currentTimeMillis()));
            baseDataContent.path = str2;
            baseDataContent.times = 1;
            baseDataContent.appVersion = this.environment.appVersion;
            baseDataContent.userNick = str3;
            baseDataContent.hashCode = str;
            this.f54a.a(baseDataContent);
        }
        return null;
    }
}
