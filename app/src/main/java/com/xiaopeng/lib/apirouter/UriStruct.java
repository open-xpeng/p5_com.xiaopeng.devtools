package com.xiaopeng.lib.apirouter;

import android.support.annotation.NonNull;

/* loaded from: classes12.dex */
public class UriStruct {
    public String applicationId;
    public String processTag;
    public String serviceName;

    @NonNull
    public String toString() {
        return this.applicationId + "." + this.serviceName;
    }
}
