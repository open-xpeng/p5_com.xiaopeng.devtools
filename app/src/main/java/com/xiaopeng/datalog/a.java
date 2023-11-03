package com.xiaopeng.datalog;

import android.content.Context;
import com.xiaopeng.lib.framework.module.IModuleEntry;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog;

/* compiled from: DataLogModuleEntry.java */
/* loaded from: classes11.dex */
public class a implements IModuleEntry {
    private Context mContext;
    private volatile IDataLog mDataLog;

    public a(Context context) {
        this.mContext = context;
    }

    @Override // com.xiaopeng.lib.framework.module.IModuleEntry
    public Object get(Class cls) {
        if (cls == IDataLog.class) {
            if (this.mDataLog == null) {
                synchronized (this) {
                    if (this.mDataLog == null) {
                        this.mDataLog = new b(this.mContext);
                    }
                }
            }
            return this.mDataLog;
        }
        return null;
    }
}
