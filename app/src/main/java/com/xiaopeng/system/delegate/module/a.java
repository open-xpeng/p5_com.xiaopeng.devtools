package com.xiaopeng.system.delegate.module;

import android.content.Context;
import com.xiaopeng.lib.framework.module.IModuleEntry;
import com.xiaopeng.lib.framework.moduleinterface.systemdelegate.ISystemDelegate;

/* compiled from: SystemDelegateModuleEntry.java */
/* loaded from: classes13.dex */
public class a implements IModuleEntry {
    private volatile ISystemDelegate YE;
    private Context mContext;

    public a(Context context) {
        this.mContext = context;
    }

    @Override // com.xiaopeng.lib.framework.module.IModuleEntry
    public Object get(Class cls) {
        if (cls == ISystemDelegate.class) {
            if (this.YE == null) {
                synchronized (this) {
                    if (this.YE == null) {
                        this.YE = new b(this.mContext);
                    }
                }
            }
            return this.YE;
        }
        return null;
    }
}
