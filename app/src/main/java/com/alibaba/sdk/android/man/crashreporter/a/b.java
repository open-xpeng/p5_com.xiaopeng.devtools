package com.alibaba.sdk.android.man.crashreporter.a;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.c;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;
import java.util.Map;

/* loaded from: classes11.dex */
public interface b {
    /* renamed from: a */
    CrashReportDataForSave mo40a();

    CrashReportDataForSave a(String str);

    CrashReportDataForSave a(String str, String str2, String str3, Map map);

    /* renamed from: a */
    String mo41a(String str);

    /* renamed from: a */
    Map<String, String> mo42a();

    Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> a(int i, int i2, int i3, int i4);

    void a(ReporterConfigure reporterConfigure, BaseDataContent baseDataContent, int i);

    void a(Map map, String str, String str2, String str3);

    boolean a(Context context, ReporterConfigure reporterConfigure, c cVar, com.alibaba.sdk.android.man.crashreporter.d.c cVar2, com.alibaba.sdk.android.man.crashreporter.d.c cVar3);

    CrashReportDataForSave b(String str, String str2, String str3, Map map);
}
