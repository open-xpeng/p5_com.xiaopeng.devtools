package com.xiaopeng.lib.framework.moduleinterface.datalogmodule;

import android.content.Context;

/* loaded from: classes12.dex */
public interface ICounterFactory {
    ICounter createDailyCounter(Context context, String str);

    ICounter createHourlyCounter(Context context, String str);
}