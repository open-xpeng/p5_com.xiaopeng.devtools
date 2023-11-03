package com.xiaopeng.system.delegate.module;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.systemdelegate.ISystemDelegate;
import com.xiaopeng.lib.utils.c;

/* compiled from: SystemDelegateService.java */
/* loaded from: classes13.dex */
public class b implements ISystemDelegate {
    private Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context) {
        this.mContext = context;
        Module.register(a.class, new a(context));
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.systemdelegate.ISystemDelegate
    @Nullable
    public String getCertificate() throws RemoteException {
        c.f("SystemDelegateService", "start getCertificate!");
        Cursor query = this.mContext.getContentResolver().query(Uri.parse("content://com.xiaopeng.system.delegate/cert/ssl"), null, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    c.h("SystemDelegateService", "query result success");
                    return string;
                }
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        c.h("SystemDelegateService", "cursor is empty!");
        if (query != null) {
            query.close();
        }
        return null;
    }
}
