package com.xiaopeng.lib.framework.moduleinterface.syncmodule;

import android.support.annotation.NonNull;
import java.util.List;

/* loaded from: classes12.dex */
public final class SyncRestoreEvent {
    public List<SyncData> list;
    public long uid;

    public SyncRestoreEvent(long j, @NonNull List<SyncData> list) {
        this.uid = j;
        this.list = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SyncRestoreEvent { uid:");
        sb.append(this.uid);
        sb.append("; list size:");
        sb.append(this.list == null ? 0 : this.list.size());
        sb.append("; }");
        return sb.toString();
    }
}
