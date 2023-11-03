package com.xiaopeng.datalog.a;

import android.content.Context;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounter;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounterFactory;

/* compiled from: CounterFactory.java */
/* loaded from: classes11.dex */
public class a implements ICounterFactory {
    private a() {
    }

    /* compiled from: CounterFactory.java */
    /* renamed from: com.xiaopeng.datalog.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    private static final class C0055a {
        private static final a qj = new a();
    }

    public static a eT() {
        return C0055a.qj;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounterFactory
    public ICounter createHourlyCounter(Context context, String str) {
        return new b(context, str, 3600000L);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounterFactory
    public ICounter createDailyCounter(Context context, String str) {
        return new b(context, str, 86400000L);
    }
}
