package com.alibaba.sdk.android.man.crashreporter.d;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.global.BaseDataContent;
import com.alibaba.sdk.android.man.crashreporter.global.CrashReportDataForSave;

/* loaded from: classes11.dex */
public interface c {
    BaseDataContent a();

    CrashReportDataForSave a(String str, int i);

    String a(long j);

    void a(BaseDataContent baseDataContent);

    boolean a(CrashReportDataForSave crashReportDataForSave, int i);

    String[] a(int i);

    CrashReportDataForSave b(String str);

    void b(CrashReportDataForSave crashReportDataForSave);

    void b(boolean z);

    boolean c(Context context);

    String h();

    String i();

    String j();
}
